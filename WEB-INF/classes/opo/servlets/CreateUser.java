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
 *  Description:  Create User<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the diary section,
 *  it gets the infonation for the diary for a given date
 *  and then sends the user to a page where that can be displayed.
 */

public class CreateUser extends HttpServlet {
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

    String FirstName = request.getParameter("firstName");
	String LastName = request.getParameter("lastName");
	String Username = request.getParameter("userName");
    String Password = request.getParameter("password");
	String PasswordCheck = request.getParameter("password_check");
	String EmailAdd = request.getParameter("emailAddress");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);
	boolean isUsernameValid;
	boolean areFieldsFull = false;
	int isPasswordValid = UserReg.isPasswordValid(Password, PasswordCheck);

	if (!FirstName.equals("") && !LastName.equals("") && !Username.equals("") && 
		!Password.equals("") && !PasswordCheck.equals("") && !EmailAdd.equals("") ) 
	  areFieldsFull = true;


		

    try {
	  isUsernameValid = UserReg.isUsernameValid(Username);

	  if ((isUsernameValid == false) && (isPasswordValid == 1) && (areFieldsFull == true)) {

		UserReg.addUser(FirstName, LastName, Username, Password, EmailAdd);

		UserBean userInfo = UserReg.getUser(Username);

		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		// Forward page
	    Send.forward(request, response, "/New_user/NewUserCreated.jsp");
      }	
	  // mange errors
	  else {
		UserBean userInfo = new UserBean();
		userInfo.setFirstName(FirstName);
		userInfo.setSurname(LastName);
		userInfo.setUserName(Username);
		userInfo.setEmailAddress(EmailAdd);
		request.setAttribute("userInfo", userInfo);

		if (isUsernameValid == true)
		request.setAttribute("UseNameTaken", "The Username you have selected is already in use. Please select a different username");
		if (isPasswordValid == 2 || isPasswordValid == 0)
		request.setAttribute("PasswordError", "Your passwords do not match, Please try again");
		if (isPasswordValid == 3)
		request.setAttribute("PasswordError", "Your password must be between 5 and 16 charectors in length, Please try again");
		if (areFieldsFull == false)
		request.setAttribute("FieldsUnfilled", "You must fill in all of the fields marked *, Please try again");
		// Forward page
	    Send.forward(request, response, "/New_user/CreateUser.jsp");
	  }
	}
	catch (SQLException e) { }
  }
}
