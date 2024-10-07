package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import java.util.*;
import opo.connections.*;
import opo.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Viewing a Contact<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles contact data posted to it, 
 *	retieves the contact information and sends the user to a
 *  page to display information.
 */

public class ViewContact extends HttpServlet {
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  { //Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();
	String Nickname = request.getParameter("name");

	ContactBeanMethods ConMeth = new ContactBeanMethods(pooledCon);

	try {
		ContactsBean contacts = ConMeth.getContact(Username, Nickname);
		request.setAttribute("contactBean", contacts);
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Contacts/ViewContact.jsp");
		
	}
	catch (SQLException e) {
		System.out.println("error");
	}
      
  }
}