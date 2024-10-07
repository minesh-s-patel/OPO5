<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Options");
   ServletContext context = getServletContext();
   opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");
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
<%
opo.beans.UserBeanMethods userReg = new opo.beans.UserBeanMethods(pooledCon); 
String username = userInfo.getUserName();
String seconduser = userReg.getSecondUser(username);
String password = userReg.getSecondPassword(username);
%>


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3" valign=top>

      <td height="100%" valign=top>
		<form name="EditUser" method="get" action="EditSecondUser">
        <p><font face='Courier New' size='3'><b><center>Add/Edit your Second user</center><b></font></p>

          <table width="77%" border="0" cellspacing="5" valign=top>
		  Username:	<jsp:getProperty name="userInfo" property="userName" />

		  <tr>
              <td  bgcolor='#6699FF' width="200">Second username</td>
              <td width="200">
			    <input type="text" name="seconduser" size=25
				value='<%= seconduser %>'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Password</td>
              <td width="200">
			    <input type="text" name="password" size=25
				value='<%= password %>'>
			  </td>
          </tr>

          </table>


        <p align="left"> 
          <input type="submit" value="Update">
        </p>

      </form>
		<% 
			Object message = request.getAttribute("unfilled");
			if (message != null)
			out.println(message);
			else
			out.println("&nbsp;");
		%>
	  
	  </td>

  </table>


            

</body>
</html>
