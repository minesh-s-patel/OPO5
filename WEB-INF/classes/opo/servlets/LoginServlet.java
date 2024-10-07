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
 *  Description:  Logs the user into the system<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class authenicates the user and allows him/her
 *  to proceed or generates a messege if user was not authenicated.
 */

public class LoginServlet extends HttpServlet {
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

	boolean authenticate = false;
	boolean isPrimaryUser1 = false;

	String Username = request.getParameter("userName");
    String Password = request.getParameter("password");

	UserBeanMethods UserReg = new UserBeanMethods(pooledCon);

    try {
	// authentcate user.
	  authenticate = UserReg.authenticate(Username, Password);

	  isPrimaryUser1 = UserReg.isPrimaryUser(Username);

      if (authenticate == true) {

		  UserBean userInfo;
		// check to see if primary user or not
		  if (!isPrimaryUser1) {
			userInfo = UserReg.getUser(UserReg.getPrimaryUser(Username));
		  }
		  else
			userInfo = UserReg.getUser(Username);

		  
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		// Forward page
		Send.forward(request, response, "/Organiser_main/Organiser.jsp");
      }
	  else {
		  // handle error
		request.setAttribute("invalidUserName", "Invalid Username or Password");
		Send.forward(request, response, "index.jsp");
	  }
	 }
	 catch (SQLException e) {
	   System.out.println("error gettin user info");
	 }
  }
}