<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Calendar");
%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />
<jsp:useBean id="schedule" scope="request" class="opo.beans.ScheduleViewBean" />
<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
	parent.leftFrame.location.href="/OPO5/Organiser_main/Calendar/cal_frame.jsp"

}
</script>
</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">

  <table border="1">

      <td height="100%">

	  

<%	
   	ServletContext context = getServletContext();
	opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");

	java.util.Calendar cal = java.util.Calendar.getInstance();
	opo.util.CalendarMethods CM = new opo.util.CalendarMethods(pooledCon);

	String view = (String)request.getAttribute("view");

	String month = request.getParameter("mon");
	String year = request.getParameter("year");
	if (month != null) {
		int m = Integer.parseInt(month);
		cal.set(cal.MONTH, m);
		int y = Integer.parseInt(year);
		cal.set(cal.YEAR, y);
	}
	cal.set(cal.DAY_OF_MONTH, 1);
%>	
	<form name=nav method=post action="Calendar.jsp">
		<input type=hidden name=mon value='<%=request.getParameter("mon")%>' >
		<input type=hidden name=year value='<%=request.getParameter("year")%>' >
		<font face='Courier New' size=4><br>
		<p><center>
		<input type=submit value="<<"
			Onclick="document.nav.mon.value='<%= cal.get(cal.MONTH) - 1 %>'
			document.nav.year.value='<%= cal.get(cal.YEAR)%>';">
		<b><%= CM.getMonth(cal) %>&nbsp;&nbsp;<%= cal.get(cal.YEAR)%></b>
		<input type=submit value=">>"
			Onclick="document.nav.mon.value='<%= cal.get(cal.MONTH) + 1 %>'
			document.nav.year.value='<%= cal.get(cal.YEAR)%>';">
		</center></p>
		</font>

<%
	if (view != null) {
		if (view.equals("weekly"))
			out.println(CM.drawCalendarWeekly(cal, userInfo.getUserName()));
		else if (view.equals("monthly"))
			out.println(CM.drawCalendarMonthly(cal, userInfo.getUserName(), 80, 100));
		else if (view.equals("yearly"))
			out.println(CM.drawCalendarYearly(cal, userInfo.getUserName()));
	}
	else
		out.println(CM.drawCalendarMonthly(cal, userInfo.getUserName(), 80, 100));	
%>

	</form>
	  
	  </td>

  </table>

</body>
</html>
