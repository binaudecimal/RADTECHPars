
package com.radtech;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.service.ServiceRegistry;


public class UserAction extends ActionSupport implements ModelDriven<User>, SessionAware{
    private User user = new User();
    private SessionMap sessionmap;
    

    public void setUser(User u){
        user = u;
    }
    
    
    @Override
    public User getModel() {
        return user;
    }
    
    public void setSession(Map m){
        sessionmap = (SessionMap)m;
        if(sessionmap.get("factory")==null){
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Information.class);
            configuration.addAnnotatedClass(Diagnosis.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            sessionmap.put("factory", sessionFactory);
        }
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public void validate(){
        if(!(sessionmap.get("currentuser") instanceof User)){}
        if(sessionmap == null){}
    }
    
    public String login(){
        //Insert login logic
        Session session =null;
        user.setPassword("" + user.getPassword().hashCode());
        try {
            session= ((SessionFactory)sessionmap.get("factory")).openSession();
            User db = (User)session.get(User.class, user.getUsername());
            if(db == null){
                addFieldError("password", "Username/Password doesn't match");
                return INPUT;
            }
            else {
                if(db.getUsername().equalsIgnoreCase(user.getUsername())){
                    if(db.getPassword().equals(user.getPassword())){
                        sessionmap.put("currentuser", user);
                        System.out.println(sessionmap.get("currentuser") == null);
                        sessionmap.put("view", (List)session.createQuery("from Information").list());
                        sessionmap.put("archive", (List)session.createQuery("from Archive").list());
                        sessionmap.put("appointments", (List)session.createQuery("from Appointment where adate is null order by date").list());
                        
                        return SUCCESS;
                    }
                    else{
                        addFieldError("password", "Username/Password doesn't match");
                       return INPUT;
                    }
                }
                else{
                    addFieldError("username", "Username/Password doesn't match");
                    return INPUT;
                }
            }
            
        }catch (HibernateException e){
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
        }
        return SUCCESS;
    }
    
    public String signup(){
        //Insert register logic
        Session session =null;
        Transaction tx = null;
        user.setPassword("" + user.getPassword().hashCode());
        if(user!=null){
            try {
                session= ((SessionFactory)sessionmap.get("factory")).openSession();
                User db = (User)session.get(User.class, user.getUsername());
                if(db ==null){
                    tx.begin();
                    if(user.getPassword().equals(user.getPassword2())){
                        session.save(user);
                        user=null;
                    }
                    else{
                        addFieldError("password2", "Password does not match");
                        return INPUT;
                    }
                    tx.commit();
                }
                else {
                    addFieldError("username", "Username already Used");
                    return INPUT;
                }

            }catch (HibernateException e){
                tx.rollback();
                e.printStackTrace();
            }
            finally{
                if(session!=null)session.close();
            }
        }
        return SUCCESS;
    }
    
    
    public String changePassword(){
        Session session = null;
        Transaction tx = null;
        try{
            session = ((SessionFactory)sessionmap.get("factory")).openSession();
            tx = session.getTransaction();
            tx.begin();
            if(user.getPassword() != null & user.getPassword2() != null & user.getPassword3() != null){
                user = (User)sessionmap.get("currentuser");
                User current = (User)session.load(User.class, user.getUsername());
                if(user==null | current==null){
                    addFieldError("password3", "Internal error. Field not found");
                    return INPUT;
                }
                else if(user.getPassword().equals(current.getPassword())){
                    if(ServletActionContext.getRequest().getParameter("password2")
                        .equals(ServletActionContext.getRequest().getParameter("password3"))){
                        current.setPassword(ServletActionContext.getRequest().getParameter("password2").hashCode() + "");
                        session.saveOrUpdate(current);
                        current=null;
                    }
                    else{
                        addFieldError("password3", "Password does not match");
                        return INPUT;
                    }
                }
                else{
                    addFieldError("password", "Incorrect password");
                    return INPUT;
                }
            }
            else{
                addFieldError("password3", "A field is left blank");
            }
            tx.commit();
        }
        catch(HibernateException e){
            tx.rollback();
        }
        finally{
            if(session!=null)session.close();
        }
        
        return SUCCESS;
    }
    public String logout(){
        sessionmap.invalidate();

        //renew servlet session
        sessionmap.put("renewServletSession", null);
        sessionmap.remove("renewServletSession");

        //populate the struts session
        sessionmap.entrySet();
        return SUCCESS;
    }
}
