<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Diary");
%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
}
</script>

</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3">

      <td height="100%" valign=top>

	    <%
		opo.beans.ScheduleBean b = (opo.beans.ScheduleBean)request.getAttribute("b");
		opo.beans.ScheduleBean b_old = (opo.beans.ScheduleBean)request.getAttribute("b_old");

		String oldDate = opo.util.StringFormat.toDateString(b_old.getDate(), "yyyy-MM-dd", "dd/MM/yyyy");
		String newDate = opo.util.StringFormat.toDateString(b.getDate(), "yyyy-MM-dd", "dd/MM/yyyy");
		%>

		<p><font face='Courier New' size='3'><b><center>
		You have chosen to move the Schedule event <%= b_old.getEventDescription() %> from <%= oldDate %> at 
		<%= b_old.getStartTime() %> to
		<%= newDate %> at <%= b.getStartTime() %>. The Schedule event <%= b.getEventDescription() %> on 
		<%= newDate %> at <%= b.getStartTime() %> will be overwritten, are you sure you wish to continue?
		</center><b></font></p>

		<form name="EditSchedule" method="post" action="\OPO5\Organiser_main\Diary\EditSchedule">
		<input type="hidden" name="date" value="<%= newDate %>">
		<input type="hidden" name="startTime" value="<%= b.getStartTime() %>">
		<input type="hidden" name="endTime" value="<%= b_old.getEndTime() %>">
		<input type="hidden" name="eventDescription" value="<%= b_old.getEventDescription() %>">
		<input type="hidden" name="location" value="<%= b_old.getLocation() %>">
		<input type="hidden" name="notes" value="<%= b_old.getNotes() %>">
		<input type="hidden" name="reminder" value="<%= b_old.getReminder() %>">
		<input type="hidden" name="old_date" value="<%= b.getDate() %>">
		<input type="hidden" name="old_StartTime" value="<%= b.getStartTime() %>">

		<input type="submit" value="Continue">
		</form>

		<form name="AddSchedule" method="post" action="\OPO5\Organiser_main\Diary\AddSchedule">
		<input type="hidden" name="date" value="<%= oldDate %>">
		<input type="hidden" name="startTime" value="<%= b_old.getStartTime() %>">
		<input type="hidden" name="endTime" value="<%= b_old.getEndTime() %>">
		<input type="hidden" name="eventDescription" value="<%= b_old.getEventDescription() %>">
		<input type="hidden" name="location" value="<%= b_old.getLocation() %>">
		<input type="hidden" name="notes" value="<%= b_old.getNotes() %>">
		<input type="hidden" name="reminder" value="<%= b_old.getReminder() %>">
		<input type="hidden" name="old_date" value="<%= oldDate %>">
		<input type="hidden" name="old_StartTime" value="<%= b_old.getStartTime() %>">
		<input type="submit" value="Cancel">
		</form>


	  </td>

  </table>


            

</body>
</html>
