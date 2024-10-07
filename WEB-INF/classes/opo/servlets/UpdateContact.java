package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import java.text.*;
import java.util.*;
import opo.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Updates a Contact<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles contact data posted to it,
 *	it retieves the contact information from previous page,
 *  and updates the database. If there is an error, a message
 *  is genarated and user is sent back to previous page.
 */

public class UpdateContact extends HttpServlet {
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {//Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");


	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();

	String GroupName = request.getParameter("group");
	String FirstName = request.getParameter("firstName");
	String Surname = request.getParameter("surname");
	String Nickname = request.getParameter("nickname");
	String HomeEmailAddress = request.getParameter("homeEmailAddress");
	String WorkEmailAddress = request.getParameter("workEmailAddress");
	String HomePostalAddress = request.getParameter("homePostalAddress");
	String WorkPostalAddress = request.getParameter("workPostalAddress");
	String HomeTelephone = request.getParameter("homeTelephone");
	String WorkTelephone = request.getParameter("workTelephone");
	String MobileTelephone = request.getParameter("mobileTelephone");
	String FaxNumber = request.getParameter("faxNumber");
	String Birthday = request.getParameter("birthday");

	String oldNickName = request.getParameter("oldNickname");

	String Bdate = "";
	String Birthday_Date = "";

	ContactBeanMethods ConMeth = new ContactBeanMethods(pooledCon);

	boolean filled = false;
	boolean isDateValid = StringFormat.isValidDate(Birthday, "dd/MM/yyyy");

	try {
		if (isDateValid == true) {
			Birthday_Date = StringFormat.toDateString(Birthday, "dd/MM/yyyy", "yyyy-MM-dd");
		}
	}
	catch (ParseException e) { System.out.println("date error"); }

	if (!GroupName.equals("") && !Nickname.equals("")) {
	  filled = true;
	}

	try {
		if (filled == true && (isDateValid == true || Birthday.equals(""))) {
			// update contacts
			ConMeth.UpdateContact(Username,
							   Nickname,
							   oldNickName,
							   FirstName,
							   Surname,
							   GroupName,
							   HomeEmailAddress,
							   WorkEmailAddress,
							   HomePostalAddress,
							   WorkPostalAddress,
							   HomeTelephone,
							   MobileTelephone,
							   WorkTelephone,
							   FaxNumber,
							   Birthday_Date);
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Contacts/Contacts");
		}
		else {
			ContactsBean contacts = ConMeth.getContact(Username, Nickname);
			request.setAttribute("contactBean", contacts);

			if (filled == false) {
				request.setAttribute("unfilled", "The group name and nickname of the user must be filled in.");
				Send.forward(request, response, "/Organiser_main/Contacts/EditContact.jsp");
			}
			else
			if (isDateValid == false) {
				request.setAttribute("unfilled", "The Birthdate you hav entered is in an invalid format.");
				Send.forward(request, response, "/Organiser_main/Contacts/EditContact.jsp");
			}
		}

	}
	catch (SQLException e) {
		System.out.println("error!!!!!!!!!!!!!!!!!");
	}
      
  }
}