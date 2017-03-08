<%-- 
	Document   : schedule
	Created on : Mar 5, 2017, 3:43:34 PM
	Author     : Carl
--%>
<%--<%@taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">		
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<title>Doctor's Schedule</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1>Current Schedule</h1>

			<center>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newAppointment">Add Appointment</button>
			</center>
			<div class="modal fade" id="newAppointment">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h3 align="center" class="modal-title">New Appointment</h3>
						</div>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped table-inverse" align="center">
					<thead>
						<th width="15%">Completion</th>
						<th width="20%">Appointment Number</th>
						<th width="15%">Date</th>
						<th width="50%">Comments</th>
					</thead>
					<tbody>
						<tr>
							<td>
								<!--<input type="hidden" name="controlnum" value="${item.control_number}" >-->         
								<button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#confirmDelete">Completed</button>
							</td>
							<td>
								201
							</td>
							<td>
								03/7/2017
							</td>
							<td>
								big black doge
							</td>
						</tr>	

						

						<s:iterator value="#session.appointments" var="record">	
                                                    <s:url value="accomplishAppointment" var="adate">
                                                        <s:param name="appointmentNumber" value="#record.appointmentNumber"/>
                                                    </s:url>
						<tr>
                                                    
                                                        <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDelete" >Delete</button></td>
                                                        <div class="modal fade" id="confirmDelete">
                                                            <div class="modal-dialog modal-sm">
                                                                    <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                <h3 align="center" class="modal-title">Appointment Complete?</h3>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                    <p align="center">This appointment will now be removed</p>
                                                                            </div>
                                                                            <div class="modal-footer form-group" >
                                                                                    <div class="row">
                                                                                            <div class="col-md-6 col-sm-6">
                                                                                                <s:a href="%{adate}"><center><s:submit type="button" cssClass="btn btn-primary btn-block" value="Continue" /></center></s:a>
                                                                                            </div>
                                                                                            <div class="col-md-6 col-sm-6">
                                                                                                    <center><button type="button" class="btn btn-secondary btn-block" data-dismiss="modal">Cancel</button></center>
                                                                                            </div>

                                                                                    </div>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                    </div>
							<td><s:property value="#record.controlNumber" /></td>
							<td><s:property value="#record.date" /></td>
							<td><s:property value="#record.comment" /></td>
							<td></td>
						</tr>
                                                </s:iterator>

					</tbody>
				</table>
			</div>
		</div>        
	</body>
</html>
