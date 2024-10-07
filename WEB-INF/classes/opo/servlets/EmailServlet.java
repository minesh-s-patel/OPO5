package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import opo.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Sends password to user<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the Email section.
 *  The infomation for the Email are prepered and a mail
 *  is sent to the user with thier password.
 */

public class EmailServlet extends HttpServlet {
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
	//Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");



	String Username = request.getParameter("userName");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);
	boolean validuser = false;

	try {
		validuser = UserReg.isUsernameValid(Username);
	}
	catch (SQLException e) {
		System.out.println("error!!!!!!!!!!!!!!!!!");
	}
	if (validuser) {

	// set up mailing props
      Properties props = new Properties();
      props.put("mail.smtp.host", "mail.dcs.kcl.ac.uk");
      Session s = Session.getInstance(props,null);

      MimeMessage message = new MimeMessage(s);

      InternetAddress from = new InternetAddress("patelmi@dcs.kcl.ac.uk");
      message.setFrom(from);

	  String EmailAddress = UserReg.getEmailAddress(Username);

      message.addRecipients(Message.RecipientType.TO, EmailAddress);

      String subject = "Online Personal Organiser - Forgotten Password";
      message.setSubject(subject);

	  String password = UserReg.getPassword(Username);

	  String text = "The password for username "+Username+" is "+password+".";
      message.setText(text);

      Transport.send(message);
		// send mail
	  response.sendRedirect("PassSent.html");
	}

	response.sendRedirect("index.jsp");
    }
    catch (MessagingException m) {
	System.out.println("mail error");
	System.out.println(m);
		  return;
	}
  }

}
