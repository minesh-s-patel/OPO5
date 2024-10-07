<%@ page language="java" contentType="text/html" %>
<% response.setDateHeader("Expires", 0);
   response.setHeader("Pragma","no-cache");
   response.setHeader("Cache-Control","no-store");
   if (request.getProtocol().equals("HTTP/1.1")) {
   response.setHeader("Cache-Control","no-cache");
   }
   session.setAttribute("state", "Notes");
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
	  <%
		java.util.Vector filenames = (java.util.Vector)request.getAttribute("filenames");
		int size = 0;
		if (filenames.size() != 0)
			size = filenames.size();

		opo.beans.NotesBean nb = (opo.beans.NotesBean)request.getAttribute("note");
		String notes = "";
		String open = "";
		String file = "&nbsp;";
		if (nb != null) {
			notes = nb.getNotes();
			open = "File currently open is ";
			file = nb.getFileName();
		}
	  %>

<body bgcolor="#BFC8DF" text="#000000" Onload="change()">

  <table border="1" width="600" height="500">

      <td height="100%">
      <center>


      <p><font face='Courier New' size='4'><b>Notes<b></font></p>
	<form name='notes' method='post' action='ProcessNotes'>
	<input type="hidden" name=action>
	<table border="1" cellpadding=2>
	<td width=400 valign=top>

      <textarea name='notes_text' rows='20' cols=50><%= notes %></textarea>
		
		<font face='Courier New'>
		<b>
		<%= open %><%= file%>
		<b>
		</font>
      <p>
	  <input type="submit" value="New">
	  <br>
	  <input type="submit" value="Save" Onclick="document.notes.action.value=value">
	  <input type="text" name=filename>
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;
	  <input type="submit" value="Open" Onclick="document.notes.action.value=value">
	  <input type="submit" value="Delete" Onclick="document.notes.action.value=value">
	  </p>

      
	  </td>
	  <td width=200 valign=top>

	  <select name="filename_list" multiple size='<%= size %>' >
	<% for (int i = 0; i < size; i++) { 
		String n = filenames.get(i).toString();
		if (n.equals(file)) {%>
	  <option selected><%= n %>
	<% } else {%>
	  <option><%= n %>
	<%  } 
	   } %>

	  </select>
	  
	  </td>

	</table>

	</center>
      </td>
  </form>
  </table>


            

</body>
</html>
