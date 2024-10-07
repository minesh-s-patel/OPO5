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
<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
}
</script>
</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">

  <table border="1" width="600" height="500" valign=top>

      <td height="100%"  valign=top>
	  <p><font face='Courier New' size='5'><b><center>Options!</center><b></font></p>

	
	<p><a href="EditUserInfo.jsp">Change Personal Infomation</a>
	<br>
	Options to change your personal infomation.
	</p>
	<p><a href="changPassword.jsp">Change Password</a>
	<br>
	Options to change your Password, this should be done periodicly for security reasons.
	</p>
	<p><a href="Options?par=getTimeBean">Change Diary view</a><br>
	Options to change the display for the diary.
	</p>
	<p><a href="Options?par=changereminder">Change Reminder Options</a><br>
	Choose if you want to recieve reminders on your default email account.
	</p>
	<p><a href="SecondUserOp.jsp">Change Second User Infomation</a>
	<br>
	Options to change Second User infomation.


	  </td>

  </table>


            

</body>
</html>
