<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Contacts");
   ServletContext context = getServletContext();
   opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");

%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />

<%
String userName = userInfo.getUserName();
String startTime = request.getParameter("startTime");
String date = request.getParameter("date");

String dateString = opo.util.StringFormat.toDateString(date, "yyyy-MM-dd", "dd/MM/yyyy");

opo.beans.ScheduleBean event = (new opo.beans.ScheduleBeanMethods(pooledCon)).getScheduleEvent(userName, date, startTime);
%>
</head>



<body bgcolor="#BFC8DF" text="#000000">

<table border="1" width="49%">
  <tr>
    <td width="37%">Date:</td>
    <td width="63%">
	<%= dateString %>
	&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Start Time:</td>
    <td width="63%">
	<%= startTime %>
	&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">End Time</td>
    <td width="63%">
	<%= event.getEndTime() %>
	&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Event Description</td>
    <td width="63%">
	<%= event.getEventDescription() %>
	&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Notes</td>
    <td width="63%">
	<%= event.getNotes() %>
	&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Reminder set?</td>
    <td width="63%">
	<%= event.getReminder() %>
	&nbsp;</td>
  </tr>
  

</table>



</body>
</html>
