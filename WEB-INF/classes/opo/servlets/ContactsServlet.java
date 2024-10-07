package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import java.util.*;
import opo.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Contact Servlet<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the Contact section,
 *  it gets the infonation for the Contacts
 *  and then sends the user to a page where that can be displayed.
 */

public class ContactsServlet extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
   doPost(request, response);
  }
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
	//Get Pool from Servlet Context
   	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();

	ContactBeanMethods ConMeth = new ContactBeanMethods(pooledCon);

	  try {
        Vector contacts = ConMeth.getAllContacts(Username);
		ContactViewBean cvb = new ContactViewBean();
		cvb.setContactBean(contacts);
		request.setAttribute("ContactList", cvb);
	// Forward page
			Send.forward(request, response, "/Organiser_main/Contacts/Contacts.jsp");

	  }
	  catch (SQLException e) {
	    System.out.println("error gettin contacts info");
	  }
  }
}