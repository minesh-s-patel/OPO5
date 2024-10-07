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
 *  Description:  Notes<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class handles the Notes section.
 *  The infomation for the notes are prepered and the user
 *  sent to the next page.
 */

public class NotesServlet extends HttpServlet {
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

	NotesBeanMethods NotesMeth = new NotesBeanMethods(pooledCon);

	  try {
		Vector filenames = NotesMeth.getNotesNames(Username);
		request.setAttribute("filenames", filenames);

		// Forward page
		Send.forward(request, response, "/Organiser_main/Notes/Notes.jsp");

	  }
	  catch (SQLException e) {
	    System.out.println("error gettin contacts info");
	  }
  }
}