<%-- 
	Document   : diagnoHistory
	Created on : Apr 18, 2017, 8:02:21 PM
	Author     : Carl
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<style>
			body { 
				background: url(loginbg.jpg) no-repeat center center fixed; 
				-webkit-background-size: cover;
				-moz-background-size: cover;
				-o-background-size: cover;
				background-size: cover;
				height:100%;
				width:100%;
			}
		</style>
		<title>Diagnosis History</title>
	</head>
	<body>
		<s:include value="home.jsp"/>
		<div class="container-fluid">
			<h1>Diagnosis History of <s:property value="%{#session.currentCustomer.name}"/></h1>
			<div class="table table-responsive">
			<table class="table table-bordered table-striped ">
				<thead>
					<th width="20%">Date</th>
					<th width="40%">Diagnosis</th>
					<th width="40%">Prescription</th>
					
				</thead>
				<tbody>
				<s:iterator value="%{#session.currentSomething.}">	
				<tr>
					<td><s:property value="%{#session.currentSomething.date}"/></td>
					<td><s:property value="%{#session.currentSomething.diagnosis}" /></td>
					<td><s:property  value="%{#session.currentSomething.prescription}" /></td>
				</tr>
				</s:iterator>
				<tr>
					<td>03/28/1997</td>
					<td>some diagnosis here</td>
					<td>drug1, drug2, drug3, drugs...</td>
				</tr>
				</tbody>
			</table>
			</div>
		</div>
	</body>
</html>
