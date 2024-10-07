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
 *  Description:  Logs user out<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class invalidates the session and
 *  sends the user to the login page.
 */

public class LogoutServlet extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
	HttpSession session = request.getSession();
	session.invalidate();
	// Redirect page
	response.sendRedirect("logout.html");



  }
}