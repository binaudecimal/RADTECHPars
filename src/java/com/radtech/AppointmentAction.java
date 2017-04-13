package com.radtech;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class AppointmentAction extends GenericAction{

    Appointment app = new Appointment();
    int[] scores = new int[12];

    @Override
    public Appointment getModel() {
        return app;
    }

    public String addAppointment() {
        session = getSession();
        tx = session.getTransaction();
        try{
            //get pet
            //get customer from pet
            //make the appointment
            tx.begin();
            //get the pet being done
            Pet currentPet = (Pet)session.load(Pet.class, getCurrentPet());
            //load owner from pet instance
            Customer currentCustomer = currentPet.getOwner();
            //set appointment values and associations
            app.setPet(currentPet);
            app.setCustomer(currentCustomer);
            app.setAppointmentDate(toDate(app.getDateInput()));
            //save appointment, pet, and customer objects on db
            session.save(app);
            session.merge(currentCustomer);
            session.merge(currentPet);
            session.flush();
            tx.commit();
            //reload session values for display
            refresh();
            return SUCCESS;
        }
        catch(HibernateException e){
            e.printStackTrace();
            tx.rollback();
            return INPUT;
        }
        finally{
            if(session!= null) session.close();
        }
    }

//    public String accomplishAppointment() {
//        if(checkUser()){
//            addFieldError("username", "Session timeout");
//            return "error";
//        }
//        Session session = null;
//        Transaction tx = null;
//        if (app != null) {
//            try {
//                session = ((SessionFactory) sessionmap.get("factory")).openSession();
//                tx = session.getTransaction();
//                tx.begin();
//                app = (Appointment) session.load(Appointment.class, Long.parseLong(app.getAppinput()));
//                if (app != null) {
//                    app.setAdate(new java.util.Date());
//                    session.merge(app);
//                    tx.commit();
//                    app = null;
//                    sessionmap.put("appointments", (List) session.createQuery("from Appointment where adate is null order by date").list());
//                }
//                else{
//                    tx.rollback();
//                }
//                
//            } catch (HibernateException e) {
//                e.printStackTrace();
//                tx.rollback();
//            } finally {
//                if (session != null) {
//                    session.close();
//                }
//            }
//        }
//        return SUCCESS;
//    }

//    public String statistics() {
//        tallyMonths();
//
//        return SUCCESS;
//    }

//    public void tallyMonths() {
//        //where MONTH(so.date) = MONTH(:date);
//        System.out.println("Inside tally month");
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        Session session = null;
//        try {
//            session = ((SessionFactory) sessionmap.get("factory")).openSession();
//            java.time.LocalDateTime now = java.time.LocalDateTime.now();
//            int year = now.getYear();
//            for (int i = 0; i < 12; i++) {
//                java.util.Calendar cal = java.util.Calendar.getInstance();
//                cal.setTime(sdf.parse(i + 1 + "/01/" + year));
//                Date inTime = cal.getTime();
//                System.out.println(inTime);
//                cal.add(Calendar.MONTH, 1);
//                cal.add(Calendar.DATE, -1);
//                Date outTime = cal.getTime();
//                System.out.println(outTime);
//                Criteria crit = session.createCriteria(Appointment.class);
//                crit.setProjection(Projections.property("date"));
//                crit.add(Restrictions.between("date", inTime, outTime));
//                List list = crit.list();
//                if (list == null) {
//                    scores[i] = 0;
//                } else {
//                    scores[i] = list.size();
//                    System.out.println("Size of " + (i+1) + " is " + list.size());
//                }
//            }
//            sessionmap.put("scores", scores);
//        } catch (HibernateException | ParseException e) {
//            System.out.println("Something happened midway...");
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    public String cancelAppointment(){
        try{
            session = getSession();
            tx = session.getTransaction();
            tx.begin();
            Appointment appointment = (Appointment)session.load(Appointment.class, Long.parseLong(app.getNumberInput()));
            if(appointment!=null){
                Customer currentCustomer = appointment.getCustomer();
                currentCustomer.getAppointments().remove(appointment);
                session.merge(currentCustomer);
                session.delete(appointment);
                session.flush();
                tx.commit();
                refreshAppointments();
            }
            else{
                tx.rollback();
                return INPUT;
            }

        }
        catch(HibernateException e){
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            if(session!=null)session.close();
        }

        return INPUT;
    }

}

//appointment
//	add appointment check

//	delete appointment