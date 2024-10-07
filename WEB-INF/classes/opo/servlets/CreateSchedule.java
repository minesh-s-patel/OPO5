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
 *  Description:  Create User<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class checks to see if user infomation entered in 
 *  previous page is valid, if it is,
 *  the user infomation is creates an entry in the database.
 */

public class CreateSchedule extends HttpServlet {
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
	catch (ParseException e) { System.out.println(e); }

	if ( !(date.equals("")) && !(StartTime.equals("")) && !(EventDescription.equals("")) ) {
	  filled = true;
	}
	ScheduleBean b_old = SchedMeth.getScheduleEvent(Username, valid_Date, StartTime);
	ScheduleBean b = new ScheduleBean();
		b.setUserName(Username);
		b.setDate(valid_Date);
		b.setStartTime(StartTime);
		b.setEndTime(EndTime);
		b.setEventDescription(EventDescription);
		b.setLocation(Location);
		b.setNotes(Notes);
		b.setReminder(rem);

	try {
		if (filled == true && areTimesValid == true &&(isDateValid == true || date.equals(""))) {
		// add schedule
		SchedMeth.addScheduleEvent(Username,
								   valid_Date,
								   StartTime,
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
		else {

			ScheduleBean SchedEvent = new ScheduleBean();
			SchedEvent.setUserName(Username);
			SchedEvent.setEventDescription(EventDescription) ;
			SchedEvent.setLocation(Location);
			SchedEvent.setNotes(Notes);

			request.setAttribute("SchedEvent", SchedEvent);

			if (filled == false) {
				request.setAttribute("unfilled", "The start time, date and Description must be filled in.");
				Send.forward(request, response, "/Organiser_main/Diary/AddSchedule.jsp?date=" + date);
			}
			if (isDateValid == false) {
				request.setAttribute("unfilled", "The date you have entered is in an invalid format.");
				Send.forward(request, response, "/Organiser_main/Diary/AddSchedule.jsp?date=" + date);
			}
			if (areTimesValid == false) {
				request.setAttribute("unfilled", "The start time must be before the end time.");
				Send.forward(request, response, "/Organiser_main/Diary/AddSchedule.jsp?date=" + date);
			}
		}

	}
	catch (SQLException e) {
		if (e.getErrorCode() == 1062) {
			request.setAttribute("b", b);
			request.setAttribute("b_old", b_old);

			Send.forward(request, response, "/Organiser_main/Diary/confirmAdd.jsp");

		}
		else
			System.out.println("error");
	}
      
  }
}