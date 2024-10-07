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
 *  Description:  Diary Servlet<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the diary section,
 *  it gets the infonation for the diary for a given date
 *  and then sends the user to a page where that can be displayed.
 */

public class DiaryServlet extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
	    doPost(request, response);
  }
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException {
	//Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");

	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String Username = userInfo.getUserName();

	String day = "";
	String month = "";
	String year = "";

	ScheduleBeanMethods sched= new ScheduleBeanMethods(pooledCon);

	year = request.getParameter("display_year");
	month = request.getParameter("display_month");
	day = request.getParameter("display_date");
	// check dates
	if (year == null || month == null || day == null) {
		Calendar today = Calendar.getInstance();
		year = today.get(today.YEAR) + "";
		month = (today.get(today.MONTH)+1) + "";
		day = today.get(today.DAY_OF_MONTH) + "";
	}
		
	String date = year + "-" + month + "-" + day;

	request.setAttribute("date", date);
	  try {
			Vector ScheduleVec = sched.getSchedule(Username, date);

			ScheduleViewBean svb = new ScheduleViewBean();
			svb.setScheduleBean(ScheduleVec);

			request.setAttribute("schedule", svb);
		// Forward page
			Send.forward(request, response, "/Organiser_main/Diary/Diary.jsp");

		  }
	  catch (SQLException e) {
	    System.out.println("error gettin contacts info");
	  }
 
  }
}