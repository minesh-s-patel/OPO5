package opo.beans;

import java.io.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  NotesBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the NotesBean.
 *  Database access for the Notes section is all done in this class
 */

public class NotesBeanMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public NotesBeanMethods(connection connect) {
		conPool = connect;	
	}


	/**
	*  adds new Notes into the data base for
	*  the given user. 
	*  @param userName
	*  @param filename
	*  @param notes
	*/
  public void addNotes(String username, String filename, String notes) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;


	try {
	  stmt = con.createStatement();
	  String sInsert = "INSERT INTO " + username + "_notes VALUES ('" 
													 + filename + "', '"
													 + notes + "');"; 

	  rs = stmt.executeQuery(sInsert);
    }
	catch (Exception e) {
	  System.out.println("Error adding notes for " + username + "file = " + filename);
	}
	conPool.closeCon(con);
  }
 

	/**
	*  retrieves Notes from the data base for
	*  the given user. 
	*  @param userName
	*  @param filename
	*  @return NotesBean with initailised info for the filename
	*/
  public NotesBean getNotesBean(String username, String filename) throws SQLException {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	NotesBean nBean = null;

	String notes = "";

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM " + username + "_notes WHERE filename = '" + filename + "';";

	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
	    notes = rs.getString("notes");

	    nBean = new NotesBean();

        nBean.setFileName(filename);

		nBean.setNotes(notes);

		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin notes for " + username + " file =  " + filename);
	  }  
	    conPool.closeCon(con);
        return nBean;
    }

	/**
	*  retrieves Notes from the data base for
	*  the given user. 
	*  @param userName
	*  @return Vector of Notes Names
	*/
  public Vector getNotesNames(String username) throws SQLException {

	Vector filenames = new Vector();
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	NotesBean nBean = null;

	String name = "";

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT filename FROM " + username + "_notes;";

	  rs = stmt.executeQuery(sQuery);

	  while (rs.next()) {
  	    name = rs.getString("filename");

	    filenames.add(name);

		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin file names for " + username);
	  } 
	  conPool.closeCon(con);
        return filenames;
    }

	/**
	*  Deletes Notes from the data base for
	*  the given user. 
	*  @param userName
	*  @param filename
	*/
	public void DeleteNotes(String username, String filename) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();

	  String sDelete = "DELETE FROM " + username + "_notes WHERE filename = '" + filename + "';";

	  stmt.executeQuery(sDelete);
    }
	catch (Exception e) {
	  System.out.println("Error deleting notes for " + username + " filename = " + filename);
	}
	conPool.closeCon(con);
  }
	/**
	*  Saves Notes from the data base for
	*  the given user. 
	*  @param userName
	*  @param filename
	*  @param notes
	*/
	public void SaveNotes(String username, String filename, String notes) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();

	  String sUpdate = "UPDATE " + username + "_notes SET notes ='" + notes + 
						"' WHERE filename = '" + filename + "';";

	  stmt.executeQuery(sUpdate);
    }
	catch (Exception e) {
	  System.out.println("Error updating notes for " + username + " filename = " + filename);
	}
	conPool.closeCon(con);
  }

}

