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
 *  Description:  Edit contact<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if contact infomation entered in 
 *  previous page is valid, if it is,
 *  the contact is updates in the database.
 */

public class EditContact extends HttpServlet {
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {	//Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();
	String[] checked = request.getParameterValues("names");
	String Nickname = "";

	ContactBeanMethods ConMeth = new ContactBeanMethods(pooledCon);

	if (checked != null) {
	  for (int i = 0; i < checked.length; i++) {
		  Nickname = checked[i];
	  }
	}

	try { // Send user to requested pae with initialised information
		if(request.getParameter("action").equals("Edit")) {

			if (Nickname.equals(""))
				Send.forward(request, response, "Contacts");
			else {
				ContactsBean contacts = ConMeth.getContact(Username, Nickname);
				request.setAttribute("contactBean", contacts);
				// Forward page
				Send.forward(request, response, "/Organiser_main/Contacts/EditContact.jsp");
			}
		}

		if(request.getParameter("action").equals("Add")) {

			Send.forward(request, response, "/Organiser_main/Contacts/AddContact.jsp");
		}

		if(request.getParameter("action").equals("Delete")) {
			if (checked != null) {
				for (int i = 0; i < checked.length; i++) {
				ConMeth.DeleteContact(Username, checked[i]);
				}
			}
	    Send.forward(request, response, "/Organiser_main/Contacts/Contacts");
		}
	}
	catch (SQLException e) {
		System.out.println("error!!!!!!!!!!!!!!!!!");
	}
      
  }
}