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

  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3" valign=top>

      <td height="100%" valign=top>

      <form name="changePass" method="get" action="EditUserPassword">
        <p><font face='Courier New' size='3'><b><center>Change Password</center><b></font></p>

          <table width="77%" border="0" cellspacing="5" valign=top>
            <tr> 
				<td  bgcolor='#6699FF' width="200">Please your current password:</td>
				<td height="29" width="200"> 
				<input type="password" name="old_password">
				</td>
            </tr>
            <tr> 
				<td bgcolor='#6699FF' width="200"> 
				Please enter a password: (password must be 5 to 16 charecters long) 
				</td>
				<td height="29" width="200"> 
				<input type="password" name="password" action="">
				</td>
            </tr>
            <tr> 
				<td  bgcolor='#6699FF' width="200">Please re-type your password:</td>
				<td height="29" width="200"> 
				<input type="password" name="password_check">
				</td>
            </tr>
          </table>

        <p align="left"> 
          <input type="submit" value="Update">
        </p>
        <b>
		<p>
		<%
				Object PasswordMessage = request.getAttribute("PasswordError");
				if (PasswordMessage != null)
				out.println(PasswordMessage);
				else
				out.println("&nbsp;");
		%>
		</p>
		</b>
      </form>

	  </td>

  </table>


            

</body>
</html>
