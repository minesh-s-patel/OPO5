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
 *  Description:  Option Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the OptionsBean.
 *  Database access for the Options section is all done in this class
 */

public class OptionMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public OptionMethods(connection connect) {
		conPool = connect;	
	}


	/**
	*  gets the start time for the scheadule of given user 
	*  @param userName
	*  @return hour start
	*/
	public int getScheduleStart(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	int scheduleStart = 0;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT schedulestart FROM useroptions WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		scheduleStart = rs.getInt("schedulestart");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin schedulestart");
	  }  
		conPool.closeCon(con);
        return scheduleStart;
    }

	/**
	*  gets the end time for the scheadule of given user 
	*  @param userName
	*  @return hour end
	*/
	public int getScheduleEnd(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	int scheduleEnd = 0;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT scheduleend FROM useroptions WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		scheduleEnd = rs.getInt("scheduleend");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin scheduleend");
	  }  
		conPool.closeCon(con);
        return scheduleEnd;
    }

	/**
	*  sets the Schedule start and end Times for the scheadule of given user.
	*  @param userName
	*  @param scheduleStart
	*  @param scheduleEnd
	*/
  public void setScheduleTimes(String username, int scheduleStart, int scheduleEnd) {
    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();
	  String sUpdate1 = "UPDATE useroptions SET schedulestart = '" + scheduleStart + "' WHERE username = '" + username + "'";
	  String sUpdate2 = "UPDATE useroptions SET scheduleend = '" + scheduleEnd + "' WHERE username = '" + username + "'";

	  stmt.executeQuery(sUpdate1);
	  stmt.executeQuery(sUpdate2);

	  }
	  catch (Exception e) {
	    System.out.println("Error updating schedule times");
	  }  
		conPool.closeCon(con);
    }

	/**
	*  sets the Reminder Time for the scheadule of given user.
	*  @param userName
	*  @param ReminderTime
	*/
	public void setReminderTime(String username, int ReminderTime) {
    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();
	  String sUpdate = "UPDATE useroptions SET sendreminder = '" + ReminderTime + "' WHERE username = '" + username + "'";

	  stmt.executeQuery(sUpdate);

	  }
	  catch (Exception e) {
	    System.out.println("Error updating schedule times");
	  }  
		conPool.closeCon(con);
   }

	/**
	*  gets the info for the options of user.
	*  @param userName
	*  @param OptionBean with info reqired
	*/
  public OptionBean getOptionBean(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	OptionBean ob = null;

    int scheduleStart = 0;
    int scheduleEnd = 0;
    int sendreminder = 0;
	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM useroptions WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		scheduleStart = rs.getInt("schedulestart");
		scheduleEnd = rs.getInt("scheduleend");
		sendreminder = rs.getInt("sendreminder");

	    ob = new OptionBean();
        ob.setScheduleStart(scheduleStart);
        ob.setScheduleEnd(scheduleEnd);
        ob.setSendreminder(sendreminder);
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error in getOptionBean");
	  }  
	    conPool.closeCon(con);
        return ob;
    }
}

