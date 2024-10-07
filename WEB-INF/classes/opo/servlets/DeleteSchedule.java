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
 *  Description:  Delete schedule<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class deletes selected schedules.
 */

public class DeleteSchedule extends HttpServlet {
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
	String[] checked = request.getParameterValues("times");	

	String date = request.getParameter("Deldate");

	ScheduleBeanMethods SchedMeth = new ScheduleBeanMethods(pooledCon);

	try {
			if (checked != null) {
				for (int i = 0; i < checked.length; i++) {
					SchedMeth.DeleteScheduleEvent(Username, date, checked[i]);
				}
			}
		// Forward page
	    Send.forward(request, response, "/Organiser_main/Diary/Diary");
		}
	catch (SQLException e) {
		System.out.println("error!!!!!!!!!!!!!!!!!");
	}
      
  }
}