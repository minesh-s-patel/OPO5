<%@ page language="java" contentType="text/html" %>
<html>
<head>
<title>Online Personal Organiser</title>
</head>

<body bgcolor="BFC8DF">
<p align="center"><font size="6"><strong>Forgotten Password</strong></font></p>
<table width="75%" border="0">
  <tr>
    <td><img src="login_images/organiser_image.gif" width="417" height="287"></td>
    <td>
	<form name="forgot" method="post" action="/OPO5/EmailServlet">
  <div align="right"></div>
  <div align="left">
          <table border="0" align="center">
            <tr> 
              <td height="29" width="83">User Name:</td>
              <td height="29" width="149"> 
                <input type="text" name="userName" action="">
        </td>
      </tr>
    </table>
  </div>
        <p>
          <center><input type="submit" value="Send Password" name="submit"></center>
        </p>
</form>
	
	</td>
  </tr>
</table>


</body>
</html>
