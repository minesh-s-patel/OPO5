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
<jsp:useBean id="OptionBean" scope="request" class="opo.beans.OptionBean" />

<script>
function change() {
	parent.navbar.location.href="/OPO5/Organiser_main/Organiser_files/navbar.jsp"
}
</script>

</head>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">


  <table border="3" width="600" height="500" cellpadding="6" cellspacing="3" valign=top>

      <td height="100%" valign=top>
		<form name="times" method="post" action="Options?par=changerem">
        <p><font face='Courier New' size='3'><b><center>Edit your Diary View</center><b></font></p>

          <table width="77%" border="0" cellspacing="5" valign=top>
			<br>
			Choose how many hours in advance you want to recieve the reminder sent to your default email account.

		  <tr>
              <td  bgcolor='#6699FF' width="200">Hours in advane the reminder is sent</td>
              <td width="200">
			  
			  <%  int rem = OptionBean.getSendreminder();
			  %>
			<select name=Reminder>
		<%  for (int i = -1; i < 24; i++) {%>
			<%  if (i == rem && rem == -1) {%>
				<option selected>No Reminder</option>
			<%} else if (i == -1) {%>
				<option selected>No Reminder</option>
			<%} else if (i == rem) {%>
				<option selected><%= i %></option>
			<%} else {%>
				<option><%= i %></option>
			<%}
			}%>
			Hours in anvance.

			  </td>
          </tr>
          </table>


        <p align="left"> 
          <input type="submit" value="Update">
        </p>

      </form>
	  
	  </td>

  </table>

</body>
</html>
