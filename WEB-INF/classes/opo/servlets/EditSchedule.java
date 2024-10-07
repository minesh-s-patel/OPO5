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
 *  Description:  Edit schedule<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if schedule infomation entered in 
 *  previous page is valid, if it is,
 *  the schedule is updates in the database.
 */

public class EditSchedule extends HttpServlet {
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

	String date = request.getParameter("date");
	String StartTime = request.getParameter("startTime");
	String EndTime = request.getParameter("endTime");
	String EventDescription = request.getParameter("eventDescription");
	String Location = request.getParameter("location");
	String Notes = request.getParameter("notes");
	String reminder = request.getParameter("reminder");
	String valid_Date = "";

	String old_date = request.getParameter("old_date");
	String old_StartTime = request.getParameter("old_StartTime");

	boolean rem = false;



	if (reminder.equals("true"))
		rem = true;

	ScheduleBeanMethods SchedMeth = new ScheduleBeanMethods(pooledCon);

	boolean filled = false;
	boolean isDateValid = StringFormat.isValidDate(date, "dd/MM/yyyy");
	boolean areTimesValid = false;

	try {
		if (isDateValid == true) {
			valid_Date = StringFormat.toDateString(date, "dd/MM/yyyy", "yyyy-MM-dd");
		}
		areTimesValid = StringFormat.compareTimes(StartTime, EndTime);

	}
	catch (ParseException e) { System.out.println("date error"); }

	if ( !(date.equals("")) && !(StartTime.equals("")) && !(EventDescription.equals("")) ) {
	  filled = true;
	}

	ScheduleBean b_old = SchedMeth.getScheduleEvent(Username, old_date, old_StartTime);
	ScheduleBean b = SchedMeth.getScheduleEvent(Username, valid_Date, StartTime);
	try {
		

		if (filled == true && areTimesValid == true &&(isDateValid == true || date.equals(""))) {
		SchedMeth.UpdateScheduleEvent(Username,
								   valid_Date,
								   old_date,
								   StartTime,
								   old_StartTime,
								   EndTime,
								   EventDescription,
								   Location,
								   Notes,
								   rem);
		String display_date = date.substring(0,2);
		String display_month = date.substring(3,5);
		String display_year = date.substring(6,10);

		// Forward page
	    Send.forward(request, response,
			"/Organiser_main/Diary/Diary?display_date="+display_date+"&display_month="+display_month+"&display_year="+display_year);
		}
		// handle errors
		else {
			request.setAttribute("b", b);
			if (filled == false) {
				request.setAttribute("unfilled", "The start time, date and Description must be filled in.");
				Send.forward(request, response, "/Organiser_main/Diary/EditSchedule.jsp?date=" + date);
			}
			if (isDateValid == false) {
				request.setAttribute("unfilled", "The date you hav entered is in an invalid format.");
				Send.forward(request, response, "/Organiser_main/Diary/EditSchedule.jsp?date=" + date);
			}
			if (areTimesValid == false) {
				request.setAttribute("unfilled", "The start time must be before the end time.");
				Send.forward(request, response, "/Organiser_main/Diary/EditSchedule.jsp?date=" + date);
			}

		}

	}
	catch (SQLException e) {
		if (e.getErrorCode() == 1062) {
			request.setAttribute("b", b);
			request.setAttribute("b_old", b_old);
		// Forward page
			Send.forward(request, response, "/Organiser_main/Diary/confirmMove.jsp");

		}
		else
			System.out.println("error");
	}
      
  }
}