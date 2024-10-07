<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Calculator");
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

  <table border="1" width="600" height="500">

      <td height="100%">

	  <p><center><font face='Courier New' size='4'><b>Calculator<b></font></center></p>

      <%@ include file="calculator.html" %>
	  
	  </td>

  </table>

</body>
</html>
