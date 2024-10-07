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
 *  Description:  Create Contact<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if Contact infomation entered in 
 *  previous page is valid, if it is,
 *  the contact is creates an entry in the database.
 */

public class CreateContact extends HttpServlet {
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
			//add contact
			ConMeth.addContact(Username,
							   Nickname,
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
			ContactsBean Contact = new ContactsBean();
			Contact.setUserName(Username);
			Contact.setNickName(Nickname);
			Contact.setFirstName(FirstName);
			Contact.setSurname(Surname);
			Contact.setGroupName(GroupName);
			Contact.setHomeEmailAddress(HomeEmailAddress);
			Contact.setWorkEmailAddress(WorkEmailAddress);
			Contact.setHomePostalAddress(HomePostalAddress);
			Contact.setWorkPostalAddress(WorkPostalAddress);
			Contact.setHomeNumber(HomeTelephone);
			Contact.setMobileNumber(MobileTelephone);
			Contact.setWorkNumber(WorkTelephone);
			Contact.setFaxNumber(FaxNumber);
			Contact.setBirthday(Birthday);

			request.setAttribute("contactBean", Contact);

			if (filled == false) {
				request.setAttribute("unfilled", "The group name and nickname of the user must be filled in.");
				// Forward page
				Send.forward(request, response, "/Organiser_main/Contacts/AddContact.jsp");
			}
			if (isDateValid == false) {
				request.setAttribute("unfilled", "The Birthdate you hav entered is in an invalid format.");
				// Forward page
				Send.forward(request, response, "/Organiser_main/Contacts/AddContact.jsp");
			}
		}

	}
	catch (SQLException e) {
		System.out.println("error!!!!!!!!!!!!!!!!!");
	}
      
  }
}