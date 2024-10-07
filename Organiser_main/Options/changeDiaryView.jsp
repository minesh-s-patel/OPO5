<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Options");
%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />
<jsp:useBean id="OptionBean" scope="request" class="opo.beans.OptionBean" />

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
}
</script>

</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3" valign=top>

      <td height="100%" valign=top>
		<form name="times" method="post" action="Options?par=changetimes">
        <p><font face='Courier New' size='3'><b><center>Edit your Diary View</center><b></font></p>

          <table width="77%" border="0" cellspacing="5" valign=top>
		  Username:	<jsp:getProperty name="userInfo" property="userName" />
			<br>
			Enter the start and end time you wish for your diary to display.
		  <tr>
              <td  bgcolor='#6699FF' width="200">Start time</td>
              <td width="200">
			  
			  <% int ScheduleStart = OptionBean.getScheduleStart();
				 int ScheduleEnd = OptionBean.getScheduleEnd();
			  %>
			<select name=start>
		<%  for (int i = 0; i < 24; i++) {
			  if (i == ScheduleStart) {%>
				<option selected><%= i %></option>
			<%} else {%>
				<option><%= i %></option>
			<%}
			}%>
			  </select>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">End Time</td>
              <td width="200">
			  <select name=end>
		<%  for (int i = 0; i < 24; i++) {
			  if (i == ScheduleEnd) {%>
				<option selected><%= i %></option>
			<%} else {%>
				<option><%= i %></option>
			<%}
			}%>
			  </select>
			  </td>
          </tr>
          </table>


        <p align="left"> 
          <input type="submit" value="Update">
        </p>

      </form>
	  
	  </td>

  </table>

</body>
</html>
