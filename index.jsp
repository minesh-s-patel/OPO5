<%@ page language="java" contentType="text/html" %>
<html>
<head>
<title>Online Personal Organiser</title>
</head>

<body bgcolor="BFC8DF">
<p align="center"><font size="6"><strong>Online Personal Organiser</strong></font></p>
<table width="75%" border="0">
  <tr>
    <td><img src="login_images/organiser_image.gif" width="417" height="287"></td>
    <td>
	<form name="login" method="post" action="/OPO5/LoginServlet">
  <div align="right"></div>
  <div align="left">
          <table border="0" align="center">
            <tr> 
              <td height="29" width="83">User Name:</td>
              <td height="29" width="149"> 
                <input type="text" name="userName" action="">
        </td>
      </tr>
      <tr> 
              <td width="83"> Password:</td>
              <td width="149">
                <input type="password" name="password" action="">
              </td>
      </tr>
    </table>
  </div>
        <p>
          <center><input type="submit" value="Enter" name="submit"></center>
        </p>
</form>
      <b><p align="center">
        <% 
				Object message = request.getAttribute("invalidUserName");
				if (message != null)
				out.println(message);
				else
				out.println("&nbsp;");
		%>
      </p></b>
      <p align="center"><a href="/OPO5/New_user/CreateUser.jsp"><b><font face="tahoma,sans-serif" color="#0000CC" size="2">Create 
        New User</font></b></a></p>
      <p align="center"><a href="/OPO5/ForgotPass.jsp"><b><font face="tahoma,sans-serif" color="#0000CC" size="2">Forgotten 
        Password? </font></b></a> </p>
	
	</td>
  </tr>
</table>


</body>
</html>
