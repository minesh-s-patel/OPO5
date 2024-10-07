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

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
	parent.leftFrame.location.href="/OPO5/Organiser_main/Contacts/cal_frame.jsp"
}

function checkAll() {
			for (var i=0; i < document.EditContact.elements.length; i++)
			{
			var e = EditContact.elements[i];
			if ((e.type=='checkbox') && (e.name != 'CA')) {
				e.checked = EditContact.CA.checked;
			}
			}
		}
</script>

</head>
<body bgcolor="#BFC8DF" text="#000000" Onload="change()">

  <table border="2" width="600" height="500" cellpadding=3>
      <td height="100%" valign=top>
		<center>
		<p><font face='Courier New' size='4'><b>Contacts<b></font></p>
		
		<form name='EditContact' method='post' action='EditContact'>
		<input type="hidden" name="action">
		<table border="0">
			<tr>
			<td  bgcolor='#6699EE' width='15'>
			<input type='checkbox' name='CA' value='checkAll' onClick="checkAll()">&nbsp;
			</td>
			<td  bgcolor='#6699EE' width='100'>Name&nbsp;</td>
			<td  bgcolor='#6699EE' width='100'>Nickname</td>
			<td  bgcolor='#6699EE' width='150'>Telephone</td>
			<td  bgcolor='#6699EE' width='150'>E-mail</td>
			</tr>

	  		<%
				opo.beans.ContactViewBean contacts = (opo.beans.ContactViewBean)request.getAttribute("ContactList");

				java.util.Vector list = contacts.getContactBeans();
				
				for (int i = 0; i < list.size(); i++) {
				  opo.beans.ContactsBean c = (opo.beans.ContactsBean)list.get(i);
				  out.println("<tr>");

				  out.println("<td  bgcolor='#6699EE'>");		
				  
				  out.println("<input type='checkbox' name='names' value='" + c.getNickName() + "'>");		  
				  out.println("&nbsp;</td>");
				  out.println("<td  bgcolor='#3366FE'>");
				  
				  out.println("<a href='/OPO5/Organiser_main/Contacts/ViewContact?name=" + c.getNickName() + "'>");
				  out.println("<font color='FFFF99'>");
				  out.println("<b>" + c.getFirstName() + "&nbsp;" + c.getSurname() + "</b>");
				  out.println("</font>");
				  out.println("</a>");
				  
				  out.println("</td>");

				  out.println("<td bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(c.getNickName() + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");

				  out.println("<td bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(c.getHomeNumber() + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");
			  
				  out.println("<td  bgcolor='#3366FE'>");
				  out.println("<font color='FFFF99'>");
				  out.println(c.getHomeEmailAddress() + "&nbsp;");
				  out.println("</font>");
				  out.println("</td>");
				  
				  out.println("</tr>");
				}
			%>
			<tr bgcolor=#6699EE>
			<td  bgcolor='#6699EE' width='15'>
			&nbsp;
			</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			<td  bgcolor='#6699EE'>&nbsp;</td>
			</tr>
		</table>
		</center>
		<div align="center">
		<input type="submit" value="Edit" onClick='document.EditContact.action.value=value'>
		<input type="submit" value="Delete" onClick='document.EditContact.action.value=value'>
		<input type="submit" value="Add"   onClick='document.EditContact.action.value=value'>
		</div>
		</form>

	  </td>
  </table>

</body>
</html>
