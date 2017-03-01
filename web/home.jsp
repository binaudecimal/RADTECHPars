<%-- 
    Document   : home
    Created on : 01 31, 17, 9:03:37 PM
    Author     : Aspire
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <title>RADTECH PARS</title>
    </head>
    
    
    <body>
        
        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    

        <!-- Logo -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavBar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">RADTECH PARS <s:iterator value="view">
                        <s:property/> Appendix
                </s:iterator></a>
        </div>

        <!-- Menu Items -->
        <div class="collapse navbar-collapse" id="mainNavBar">
            
            <ul class="nav navbar-nav">
                <li><a href="home.jsp">Home</a></li>
                <li><a href="add.jsp">Add Record</a></li>
                <li><a href="search.jsp">Search</a></li>
                <li><a href="userProfile.jsp">Schedule</a></li>
                <li><a href="#">Archives</a></li>
                <li><a href="#">Statistics</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="index.jsp"><span class="glyphicon-log-in"></span>Logout</a></li>
            </ul>        
               
        </div>
    </nav> 
        <div class="container-fluid">
            <br>
            <br>
            <h1>some text <s:property value="#session.login"/></h1>
            
            



            

        </div>   
    </body>
</html>
