<html>

<body bgcolor="#BFC8EE" text="#000000">
<%		ServletContext context = getServletContext();
		opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");

		opo.util.CalendarMethods CM = new opo.util.CalendarMethods(pooledCon);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		int m = -1; 
		int y = 0;

		String mm = request.getParameter("display_month1");
		String yyyy = request.getParameter("display_year1");

		if (mm != null)
			m = Integer.parseInt(mm);
		if (yyyy != null)
			y = Integer.parseInt(yyyy);

		if ( y > 0 && m > -1)
		cal.set(y, m, 1);
		else;
		cal.set(cal.DAY_OF_MONTH, 1);
%>

		<font face='Courier New'>
		<b><center><%= CM.getMonth(cal) %>&nbsp;&nbsp;<%= cal.get(cal.YEAR)%></b>		
		</font>
		
		<script>
		function update() {
			for (i=0; i < document.UpdateCal.month.length; i++) {
				if (document.UpdateCal.month.options[i].selected) {
					document.UpdateCal.display_month1.value = document.UpdateCal.month.options[i].value
					var m = document.UpdateCal.month.options[i].value
					var y = UpdateCal.display_year1.value
					var p = "cal_frame.jsp?display_month1=" + m + "&display_year1=" + y
				}
			}
			location.href = p
		}
		</script>

		<form name='UpdateCal' method='post' action='cal_frame.jsp'>

		<input type='hidden' name='display_month1' value ='<%= cal.get(cal.MONTH) %>'>
		<input type='hidden' name='display_year1' value ='<%= cal.get(cal.YEAR) %>'>

		<input type="submit" value=" << " onClick='document.UpdateCal.display_year1.value--'>
		<select name=month onChange="update()">
		<%
		int calmonth = cal.get(cal.MONTH);
		for (int i = 0; i < 12; i++) {
			if (i == calmonth)
			  out.println("<option value=" + i +" selected>" + CM.getMonth(i) + "</option>");
			else
			  out.println("<option value=" + i +">" + CM.getMonth(i) + "</option>");
		}
		%>
		</select>
		
		<input type="submit" value=" >> " onClick='document.UpdateCal.display_year1.value++'>
		</form>

		<form name='calButtons' method='post' target='mainFrame' action='/OPO5/Organiser_main/Diary/Diary' onSubmit='parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"'>
		
		<table border=4><tr><td>

		<input type='hidden' name='display_date'>
		<input type='hidden' name='display_month' value ='<%= (cal.get(cal.MONTH)+1)%>'>
		<input type='hidden' name='display_year' value ='<%= cal.get(cal.YEAR)%>'>
		
		<%= CM.drawCalendar(cal) %>

		</td></tr></table>
		</form></center>
</body>
</html>
