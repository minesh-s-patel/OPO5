package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import opo.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Sends password to user<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if user infomation entered in 
 *  previous page is valid, if it is,
 *  the user infomation is updates in the database.
 */

public class EditUser extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();
	 
    String FirstName = request.getParameter("firstName");
	String LastName = request.getParameter("surname");
	String EmailAdd = request.getParameter("emailAddress");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);
	boolean areFieldsFull = false;

	if (!FirstName.equals("") && !LastName.equals("") && !EmailAdd.equals("") ) 
	  areFieldsFull = true;

    try {
	  if (areFieldsFull == true) {

		UserReg.EditUser(Username, FirstName, LastName, EmailAdd);

		UserBean newuserInfo = UserReg.getUser(Username);

		session.removeAttribute("userInfo");
		session.setAttribute("userInfo", newuserInfo);

	    Send.forward(request, response, "/Organiser_main/Options/Options");
      }
	  else {
		if (areFieldsFull == false)
		request.setAttribute("unfilled", "You must fill in all of the fields, Please try again");

	    Send.forward(request, response, "/Organiser_main/Options/EditUserInfo.jsp");
	  }
	}
	catch (SQLException e) { }
  }
}
