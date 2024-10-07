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
 *  Description:  Edit User Password<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if passwords entered in 
 *  previous page are valid, if they are,
 *  the passwords are updates in the database.
 */

public class EditUserPassword extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {	doPost(request, response);
  }
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

	String oldPassword = request.getParameter("old_password");
    String Password = request.getParameter("password");
	String PasswordCheck = request.getParameter("password_check");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);

	String oldPasswordInDB = UserReg.getPassword(Username);
	boolean areFieldsFull = false;
	boolean passOK = false;

	int isPasswordValid = UserReg.isPasswordValid(Password, PasswordCheck);

	if (!Password.equals("") && !PasswordCheck.equals("") && !oldPassword.equals("")) 
	  areFieldsFull = true;

	if (oldPassword.equals(oldPasswordInDB))
		passOK = true;

    try {
	  if ((areFieldsFull == true) && (isPasswordValid == 1) && (passOK == true)) {

		UserReg.changePassword(Username, Password);

		UserBean newuserInfo = UserReg.getUser(Username);

		session.removeAttribute("userInfo");
		session.setAttribute("userInfo", newuserInfo);
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Options/Options");
      }
	  else {
		// handle errors
		if (areFieldsFull == false)
		request.setAttribute("PasswordError", "You must fill in all of the fields, Please try again");
		if (isPasswordValid == 2 || isPasswordValid == 0)
		request.setAttribute("PasswordError", "Your passwords do not match, Please try again");
		if (isPasswordValid == 3)
		request.setAttribute("PasswordError", "Your password must be between 5 and 16 charectors in length, Please try again");
		if (passOK == false)
		request.setAttribute("PasswordError", "Your old password was not valid, Please try again");

	    Send.forward(request, response, "/Organiser_main/Options/changPassword.jsp");
	  }
	}
	catch (SQLException e) { System.out.println(e);}
  }
}
