package opo.servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import opo.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Processes Notes<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class processes notes. according to the users request,
 *  notes are either added, deleted or updated in the database. The
 *  user is sent to the notes page.
 */

public class ProcessNotes extends HttpServlet {
  /**
   *  Method that handles data posted to this servlet.
   *  @param request The request header
   *  @param response The response header
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
//Get Pool from Servlet Context
	ServletContext context = getServletContext();
	connection pooledCon = (connection)context.getAttribute("pooledCon");


	//Get user bean from session and extract username
	HttpSession session = request.getSession();
	UserBean userInfo = (UserBean)session.getAttribute("userInfo");

	String UserName = userInfo.getUserName();

    String newFilename = request.getParameter("filename");
	String action = request.getParameter("action");
	String notes = request.getParameter("notes_text");
	notes = StringFormat.toHTMLString(notes);

	String[] file_names = request.getParameterValues("filename_list");
	String filename = "";

	if (file_names != null) {
		for (int i = 0; i < file_names.length; i++) {
		  filename = file_names[i];
		}
	}

	NotesBeanMethods NotesMeth = new NotesBeanMethods(pooledCon);
		// Forward page to location according to use action
    try {
	 
	  if (action.equals("Save")) {	    
		  if (newFilename.equals("")) {
			NotesMeth.SaveNotes(UserName, filename, notes);
			NotesBean nb = NotesMeth.getNotesBean(UserName, filename);
			request.setAttribute("note", nb);
		  }
		  else {
			NotesMeth.addNotes(UserName, newFilename, notes);
			NotesBean nb = NotesMeth.getNotesBean(UserName, newFilename);
			request.setAttribute("note", nb);
		  }
	  }
	  else if (action.equals("Open")) {
		NotesBean nb = NotesMeth.getNotesBean(UserName, filename);
		request.setAttribute("note", nb);
	  }
	  else if (action.equals("Delete")) {
		NotesMeth.DeleteNotes(UserName, filename);
	  }
	  // setup vectors
	  Vector filenames = NotesMeth.getNotesNames(UserName);
	  request.setAttribute("filenames", filenames);
 	  Send.forward(request, response, "/Organiser_main/Notes/Notes.jsp");
	}
	catch (SQLException e) { }
  }
}
