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
<jsp:useBean id="SchedEvent" scope="request" class="opo.beans.ScheduleBean" />

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
}
</script>

</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3">

      <td height="100%">
		<form name="AddSchedule" method="post" action="AddSchedule">
        <p><font face='Courier New' size='3'><b><center>Please enter the Schedule information</center><b></font></p>
		<% 
			Object message = request.getAttribute("unfilled");
			if (message != null)
			out.println(message);
			else
			out.println("&nbsp;");
		%>
          <table width="77%" border="0" cellspacing="5">
		  <%
				String timeMessage = "enter time here";
				String startTime = (String)request.getParameter("startTime");
				if (startTime == null)
					startTime = timeMessage;
					String endTime = startTime;
				if (endTime == null)
					endTime = timeMessage;

					String date = (String)request.getParameter("date");
					String dateString = date;

					if (date == null) {
					java.util.Calendar today = java.util.Calendar.getInstance();
					String year = today.get(today.YEAR) + "";
					String month = (today.get(today.MONTH)+1) + "";
					String day = today.get(today.DAY_OF_MONTH) + "";

					date = year + "-" + month + "-" + day;
					}

				if ( opo.util.StringFormat.isValidDate(date, "yyyy-MM-dd") ) 
					dateString = opo.util.StringFormat.toDateString(date, "yyyy-MM-dd", "dd/MM/yyyy");
				
		  %>

		  <tr> 
              <td  bgcolor='#6699FF' width="200">Date</td>
              <td width="200">
			  <%	
					out.println("<input type='text' name='date' size=25 value=" + dateString + ">");
			  %><font color="#FF0000">*</font>
			  </td>
          </tr>
		  <tr>
              <td  bgcolor='#6699FF' width="200">Start Time</td>
              <td width="200">
			    
				<select name=startTime>
				<%
				String disStartTime = "";
				for (int i = 0; i < 24; i ++) {
					disStartTime = i + ":00:00";
					if (startTime.equals(disStartTime))
						out.println("<option value=" + disStartTime + " selected>" + disStartTime + "</option>");
					else
						out.println("<option value=" + disStartTime + ">" + disStartTime + "</option>");
				}
				%>
				</select>
				<font color="#FF0000">*</font>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">End Time</td>
              <td width="200">
			   <select name=endTime>
				<%
				String disEndTime = "";
				String disEndTime1 = "";
				for (int i = 0; i < 24; i ++) {
					disEndTime = i + ":00:00";
					disEndTime1 = (i + 1) + ":00:00";
					if (endTime.equals(disEndTime))
						out.println("<option name=endTime value=" + disEndTime1 + " selected>" + disEndTime1 + "</option selected>");
					else
						out.println("<option name=endTime value=" + disEndTime + ">" + disEndTime + "</option>");
				}
				%>
				</select>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Event Description</td>
              <td width="200">
			    <input type='text' name='eventDescription' size=25
				value='<jsp:getProperty name="SchedEvent" property="eventDescription" />'>
				<font color="#FF0000">*</font>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Location</td>
              <td width="200">
			    <input type='text' name='location' size=25
				value='<jsp:getProperty name="SchedEvent" property="location" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200" valign=top>Notes</td>
              <td width="200">
				<textarea name='notes' rows='4' cols=21><jsp:getProperty name="SchedEvent" property="notes" /></textarea>
			  </td>
          </tr>

		  <tr> 
              <td  bgcolor='#6699FF' width="200">Reminder set</td>
              <td width="200">
				<select name=reminder>
				<option value=true>true</option>
				<option value=false>false</option>
				</select>
			  </td>
          </tr>

          </table>

        <p align="left"> 
          <input type="submit" value="Add Schedule">
        </p>

      </form>


	  <p align="left">All fields marked <font color="#FF0000">*</font> must be filled in</P>
	  
	  </td>

  </table>

</body>
</html>
