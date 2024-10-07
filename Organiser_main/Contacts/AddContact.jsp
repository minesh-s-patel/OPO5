<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Contacts");
   ServletContext context = getServletContext();
   opo.connections.connection pooledCon = (opo.connections.connection)context.getAttribute("pooledCon");

%>

<html>
<head>
<title>Organiser-main</title>
<jsp:useBean id="userInfo" scope="session" class="opo.beans.UserBean" />
<jsp:useBean id="contactBean" scope="request" class="opo.beans.ContactsBean" />

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
	parent.leftFrame.location.href="/OPO5/Organiser_main/Contacts/cal_frame.jsp"
}
</script>

</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3">

      <td height="100%">
		<form name="AddContact" method="post" action="AddContact">
        <p><font face='Courier New' size='3'><b><center>Please enter the Contact's information</center><b></font></p>
		<% 
			Object message = request.getAttribute("unfilled");
			if (message != null)
			out.println(message);
			else
			out.println("&nbsp;");
		%>

          <table width="77%" border="0" cellspacing="5">
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Group Name</td>
              <td>
				<select name=month onChange="document.AddContact.group.value=this.value">
				<%
				String Username = userInfo.getUserName();
				opo.beans.ContactBeanMethods cont = new opo.beans.ContactBeanMethods(pooledCon);
				java.util.Vector groupnames = cont.getGroupName(Username);
				groupnames.add(0,"new");

				for (int i = 0; i < groupnames.size(); i++) {
					  out.println("<option value=" + groupnames.get(i) + ">" + groupnames.get(i) + "</option>");
				}
				%>
				</select>
				<input type="text" name="group" size=10>
				<font color="#FF0000"> *</font>
			  </td>
          </tr>

		  <tr> 
              <td  bgcolor='#6699FF' width="200">First Name</td>
              <td width="200">
			    <input type="text" name="firstName" size=25
				value='<jsp:getProperty name="contactBean" property="firstName" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Surname</td>
              <td width="200">
			    <input type="text" name="surname" size=25
				value='<jsp:getProperty name="contactBean" property="surname" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Nickname</td>
              <td width="200">
			    <input type="text" name="nickname" size=25
				value='<jsp:getProperty name="contactBean" property="nickName" />'>
				<font color="#FF0000"> *</font>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Home Email Address</td>
              <td width="200">
			    <input type="text" name="homeEmailAddress" size=25
				value='<jsp:getProperty name="contactBean" property="homeEmailAddress" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Work Email Address</td>
              <td width="200">
			    <input type="text" name="workEmailAddress" size=25
				value='<jsp:getProperty name="contactBean" property="workEmailAddress" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200" valign=top>Home Postal Address</td>
              <td width="200">
			    <textarea name="homePostalAddress" rows="4" cols=21><jsp:getProperty name="contactBean" property="homePostalAddress" /></textarea>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200" valign=top>Work Postal Address</td>
              <td width="200">
			    <textarea name="workPostalAddress" rows="4" cols=21><jsp:getProperty name="contactBean" property="workPostalAddress" /></textarea>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Home Telephone Number</td>
              <td width="200">
			    <input type="text" name="homeTelephone" size=25
				value='<jsp:getProperty name="contactBean" property="homeNumber" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Work Telephone Number</td>
              <td width="200">
			    <input type="text" name="workTelephone" size=25
				value='<jsp:getProperty name="contactBean" property="workNumber" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Mobile Telephone Number</td>
              <td width="200">
			    <input type="text" name="mobileTelephone" size=25
				value='<jsp:getProperty name="contactBean" property="mobileNumber" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Facsimile Number</td>
              <td width="200">
			    <input type="text" name="faxNumber" size=25
				value='<jsp:getProperty name="contactBean" property="faxNumber" />'>
			  </td>
          </tr>
		  <tr> 
              <td  bgcolor='#6699FF' width="200">Birthday (dd/mm/yyyy)</td>
              <td width="200">
			    <input type="text" name="birthday" size=25>
			  </td>
          </tr>
          </table>

        <p align="left"> 
          <input type="submit" value="Add Contact">
        </p>

      </form>



	  <p align="left">All fields marked <font color="#FF0000">*</font> must be filled in</P>
	  
	  </td>

  </table>


            

</body>
</html>
