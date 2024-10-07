<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
%>
<jsp:useBean id="userInfo" scope="request" class="opo.beans.UserBean" />
<html>
<head>
<title>Create User</title>
</head>
<body bgcolor="BFC8DF">
<p align="center"><b><font size="+3">Create New User:</font></b></p>
<table width="100%" border="0">
  <tr> 
    <td width="30%" height="225">
      <p><img src="/OPO5/New_user/newUser_images/organiser_image.gif"></p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
      <p>&nbsp;</p>
    </td>
    <td width="70%"> 
      <form name="CreateUser" method="post" action="/OPO5/CreateUserServlet">
        <p align="left">Please enter your personal information</p>
        <div align="left"> 
          <table width="77%" border="0">
            <tr> 
              <td width="38%">Please enter your First Name: </td>
              <td width="62%"> 
                <input type="text" name="firstName" 
				value='<jsp:getProperty name="userInfo" property="firstName" />'>
                <font color="#FF0000">*</font> </td>
            </tr>
            <tr> 
              <td width="200">Please enter your Surname:</td>
              <td width="200"> 
                <input type="text" name="lastName" 
				value='<jsp:getProperty name="userInfo" property="surname" />'>
                <font color="#FF0000">*</font> </td>
            </tr>
            <tr> 
              <td height="31" width="200"> 
                <p>Please enter a username:</p>
              </td>
              <td height="31" width="200"> 
                <p> 
                  <input type="text" name="userName" 
				  value='<jsp:getProperty name="userInfo" property="userName" />'>
                  <font color="#FF0000"> *</font></p>
              </td>
            </tr>
            <tr> 
              <td height="29" width="200"> 
                <p>Please enter a password: (password must be 5 to 16 charecters 
                  long) 
              </td>
              <td height="29" width="200"> 
                <p> 
                  <input type="password" name="password" action="">
                  <font color="#FF0000"> *</font></p>
                <p>&nbsp; </p>
              </td>
            </tr>
            <tr> 
              <td height="29" width="200">Please re-type your password:</td>
              <td height="29" width="200"> 
                <input type="password" name="password_check">
                <font color="#FF0000"> *</font></td>
            </tr>
            <tr> 
              <td height="29" width="200">Primary Email Address</td>
              <td height="29" width="200"> 
                <input type="text" name="emailAddress" 
				value='<jsp:getProperty name="userInfo" property="emailAddress" />'>
                <font color="#FF0000"> *</font></td>
            </tr>
          </table>
        </div>
        <p align="left"> 
          <input type="submit" value="Create New User">
        </p>
        <b><p>
		<% 
				Object UsernameMessage = request.getAttribute("UseNameTaken");
				if (UsernameMessage != null)
				out.println(UsernameMessage);
				else
				out.println("&nbsp;");
		%>
		</p>
		<p>
		<%
				Object PasswordMessage = request.getAttribute("PasswordError");
				if (PasswordMessage != null)
				out.println(PasswordMessage);
				else
				out.println("&nbsp;");
		%>
		</p>
		<p>
		<%
				Object FieldMessage = request.getAttribute("FieldsUnfilled");
				if (FieldMessage != null)
				out.println(FieldMessage);
				else
				out.println("&nbsp;");
		%>
		</p></b>
      </form>
      <p align="left"> <font color="#FF0000"> * </font>These fields must be filled in.</p>
    </td>
  </tr>
</table>


</body>
</html>