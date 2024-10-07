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
 *  Description:  Options<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the options the user can change.
 *  the user is sent to different pages according to thier request.
 */

public class OptionsServlet extends HttpServlet {
  /**
   *  Method that handles get requests to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
	   doPost(request, response);
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

		OptionMethods ob = new OptionMethods(pooledCon);

		String par = request.getParameter("par");

		if (par == null)
			par = "";
		// Forward page with inisialised info, to specific pages according to the users request.
		if (par.equals("changetimes")) {
		String ScheduleStart = request.getParameter("start");
		String ScheduleEnd = request.getParameter("end");
		int SS = Integer.parseInt(ScheduleStart);
		int SE = Integer.parseInt(ScheduleEnd);

		ob.setScheduleTimes(Username, SS, SE);
		Send.forward(request, response, "/Organiser_main/Options/Options.jsp");
		}
		else if (par.equals("changerem")) {
		int ReminderTime = -1;
		String reminder = request.getParameter("Reminder");
		if (!reminder.equals("No Reminder"))
			ReminderTime = Integer.parseInt(reminder);

		ob.setReminderTime(Username, ReminderTime);
		Send.forward(request, response, "/Organiser_main/Options/Options.jsp");
		}		  // handle errors
		else if (par.equals("getTimeBean"))
		{	OptionBean b = ob.getOptionBean(Username);
			request.setAttribute("OptionBean", b);
			Send.forward(request, response, "/Organiser_main/Options/changeDiaryView.jsp");
		}
		else if (par.equals("changereminder"))
		{	OptionBean b = ob.getOptionBean(Username);
			request.setAttribute("OptionBean", b);
			Send.forward(request, response, "/Organiser_main/Options/reminderOptions.jsp");
		}
		else		// Forward page
			Send.forward(request, response, "/Organiser_main/Options/Options.jsp");

		

  }
}