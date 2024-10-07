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
 *  Description:  Contact Servlet<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the Calendar section,
 *  it gets the infonation for the schedules on all days on the month,
 *  and then sends the user to a page where that can be displayed.
 *  The user can also choose the view of the calendar.
 */

public class CalendarServlet extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
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

	String view = request.getParameter("view");
	if (view == null)
		view = "monthly";
	
	request.setAttribute("view", view);
	// Forward page
	    RequestDispatcher rd = request.getRequestDispatcher("/Organiser_main/Calendar/Calendar.jsp");
		rd.forward(request, response);

  }
}