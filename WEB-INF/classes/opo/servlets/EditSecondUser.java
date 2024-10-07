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
 *  Description:  Edit second user<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if second user infomation entered in 
 *  previous page is valid, if it is,
 *  the user infomation is added or updates in the database.
 */

public class EditSecondUser extends HttpServlet {
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
	 
    String seconduser = request.getParameter("seconduser");
	String password = request.getParameter("password");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);
	boolean areFieldsFull = false;

	if (!seconduser.equals("") && !password.equals("") ) 
	  areFieldsFull = true;

    try {
	  if (areFieldsFull == true) {

		if (UserReg.isSecondUser(Username))
		  	UserReg.EditSecondUser(Username, seconduser, password);
		else
			UserReg.addSecondUser(Username, seconduser, password);
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Options/Options");
      }
	  else {
		if (areFieldsFull == false)
		request.setAttribute("unfilled", "You must fill in all of the fields, Please try again");
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Options/SecondUserOP.jsp");
	  }
	}
	catch (SQLException e) { System.out.println(e);}
  }
}
