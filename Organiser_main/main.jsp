<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />

</head>

<body bgcolor="#BFC8DF" text="#000000">

  <table border="1" width="600" height="500">

      <td height="100%" background="Organiser_files\mainBG.gif">
	  <p><font size="+2">Session is logged in!</font></p>
            <p>Your First name is: 
              <jsp:getProperty name="userInfo" property="firstName" />
            </p>
            <p>Your Last name is: 
              <jsp:getProperty name="userInfo" property="surname" />
            </p>
            <p>Your username is: 
              <jsp:getProperty name="userInfo" property="userName" />
            </p>
            <p>Your Email Address is: 
              <jsp:getProperty name="userInfo" property="emailAddress" />
            </p>
	  
	  </td>

  </table>
         

</body>
</html>
