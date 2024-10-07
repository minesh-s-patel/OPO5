<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Contacts");
%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />
<jsp:useBean id="contactBean" scope="request" class="opo.beans.ContactsBean" />



</head>

<body bgcolor="#BFC8DF" text="#000000">

<table border="1" width="49%">
  <tr>
    <td width="37%">Group Name:</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="groupName" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Name:</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="firstName" />&nbsp;
	<jsp:getProperty name="contactBean" property="surname" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Nickname</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="nickName" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Home Email Address</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="homeEmailAddress" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Work Email Address</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="workEmailAddress" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Home Postal Address</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="homePostalAddress" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Work Postal Address</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="workPostalAddress" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Home Number</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="homeNumber" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Work Number</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="workNumber" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Mobile Number</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="mobileNumber" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Fax Number</td>
    <td width="63%"><jsp:getProperty name="contactBean" property="faxNumber" />&nbsp;</td>
  </tr>
  <tr>
    <td width="37%">Birthday</td>
    <td width="63%">
	<%
				String bday = contactBean.getBirthday();
				if (bday == null) bday = "";
				if (bday.equals("0000-00-00"))
					out.println("&nbsp;");
				else {
					bday = opo.util.StringFormat.toDateString(bday, "yyyy-MM-dd", "dd/MM/yyyy");
					out.println(bday);
				}
	%>
&nbsp;</td>
  </tr>
</table>



</body>
</html>
