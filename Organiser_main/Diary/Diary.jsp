<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Diary");
   ServletContext context = getServletContext();
   opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");

%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />
<jsp:useBean id="schedule" scope="request" class="opo.beans.ScheduleViewBean" />
<% opo.beans.OptionMethods ob = new opo.beans.OptionMethods(pooledCon); 
String username = userInfo.getUserName();
%>
<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
	parent.leftFrame.location.href="/OPO5/Organiser_main/Diary/cal_frame.jsp"
}

function checkAll() {
			for (var i=0; i < document.Diary.elements.length; i++)
			{
			var e = Diary.elements[i];
			if ((e.type=='checkbox') && (e.name != 'CA')) {
				e.checked = Diary.CA.checked;
			}
			}
		}

</script>
</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">

  <table border="1" width="750" height="500">
	<tr>

      <td width="600" height="100%" valign=top>

	<center>
		<%
		String date = (String)request.getAttribute("date");
		request.setAttribute("date", date);
		String dateString = opo.util.StringFormat.toDateString(date, "yyyy-MM-dd", "dd/MM/yyyy");
		%>
		<p><font face='Courier New' size='4'><b>Schedule for <%= dateString %><b></font></p>
		
		<form name='Diary' method='post' action='DeleteSchedule'>
		<% out.println("<input type='hidden' name='Deldate' value='" + date + "'>"); %>
		<table border="0">
			<tr>
			<td  bgcolor='#6699EE' width='15'>
			<input type='checkbox' name='CA' value='checkAll' onClick="checkAll()">&nbsp;
			</td>
			<td  bgcolor='#6699EE' width='80'>Time&nbsp;</td>
			<td  bgcolor='#6699EE' width='150'>Description</td>
			<td  bgcolor='#6699EE' width='100'>Location</td>
			<td  bgcolor='#6699EE' width='170'>Notes</td>
			<td  bgcolor='#6699EE' width='10'>Rem</td>
			</tr>

	  		<%
				java.util.Vector ScheduleVec = (java.util.Vector)schedule.getScheduleBeans();

				int show_from = ob.getScheduleStart(username);
				int show_until = ob.getScheduleEnd(username);

				for (int i = show_from; i < show_until; i++) {
				  opo.beans.ScheduleBean s = (opo.beans.ScheduleBean)ScheduleVec.get(i);

				  String startTime = i + ":00:00";
				  String endTime = null;
				  String description = "";
				  String location = "";
				  String notes = "";
				  String rem = "";
				  boolean stat = false;
					if (s != null)
					{	startTime = s.getStartTime();
						endTime = s.getEndTime();
						description = s.getEventDescription();
						location = s.getLocation();
						notes = s.getNotes();
						boolean reminder = s.getReminder();
						if (reminder == true)
							rem = "Y";
						else rem = "N";
						int stringLength = notes.length();
						if (stringLength > 25) {
							notes = notes.substring(0, 24) + ".......";
						}
						stat = true;
					}

				  out.println("<tr>");

				  out.println("<td  bgcolor='#6699EE'>");		
				  out.println("<input type='checkbox' name='times' value='" + startTime + "'>");		  
				  out.println("&nbsp;</td>");

				  out.println("<td  bgcolor='#3366FE'>");

				  if (stat) 
				    out.println("<a href='/OPO5/Organiser_main/Diary/EditSchedule.jsp?startTime=" + startTime + "&date=" + date +"&endTime=" + endTime + "'>");
				  else
					out.println("<a href='/OPO5/Organiser_main/Diary/AddSchedule.jsp?startTime=" + startTime + "&date=" + date + "'>");

				  out.println("<font color='FFFF99'>");
				  out.println("<b>" + startTime + "</b>");
				  out.println("</font>");
				  out.println("</a>");
				  out.println("</td>");

				  out.println("<td bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(description + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");

				  out.println("<td bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(location + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");
			  
				  out.println("<td  bgcolor='#3366FE'>");
				  if (stat)
					out.println("<a href='/OPO5/Organiser_main/Diary/ViewScheduleEvent.jsp?startTime=" + startTime + "&date=" + date + "'>");
				  out.println("<font color='FFFF99'>");
				  out.println(notes + "&nbsp;");
				  out.println("</font>");
				  if (stat)
					out.println("</a>");
				  out.println("</td>");

				  out.println("<td bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(rem + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");
				  
				  out.println("</tr>");
				}
			%>
			<tr bgcolor=#6699EE>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			</tr>
		</table>
		</center>
		<div align="left"><input type="submit" value="Delete" onClick='document.Diary.add.value=value'></div>
		</form>

		<td width="150" height="100%" valign=top>&nbsp;
		</td>

		</tr>
	  </td>

  </table>


            

</body>
</html>
