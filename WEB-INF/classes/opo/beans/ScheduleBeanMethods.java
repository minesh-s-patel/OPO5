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
 *  Description:  ScheduleBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the ScheduleBean.
 *  Database access for the Schedule section is all done in this class
 */

public class ScheduleBeanMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public ScheduleBeanMethods(connection connect) {
		conPool = connect;	
	}


	/**
	*  adds a scheadule event for given user, with infomation provided.
	*  @param userName
	*  @param date
	*  @param start Time
	*  @param end Time
	*  @param event Description
	*  @param location
	*  @param notes
	*  @param reminder
	*/
  public void addScheduleEvent(String userName,
							   String date,
							   String startTime,
							   String endTime,
							   String eventDescription,
  							   String location,
							   String notes,
							   boolean reminder) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;

	int rem = 0;
	if (reminder == true)
	  rem = 1;

	  stmt = con.createStatement();
	  String sInsert = "INSERT INTO " + userName + "_diary VALUES ('" 
													 + date + "', '"
													 + startTime + "', '" 
													 + endTime + "', '"
													 + eventDescription + "', '"
													 + location + "', '" 
													 + notes + "', '"
													 + rem + "');"; // rem is 0 if there is no reminder, 1 if there is

	  rs = stmt.executeQuery(sInsert);

	conPool.closeCon(con);
  }

	/**
	*  gets the scheadule event for given user.
	*  @param userName
	*  @param date
	*  @param start Time
	*  @return ScheduleBean with initialised fields
	*/
  public ScheduleBean getScheduleEvent(String username, String date, String startTime) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	ScheduleBean SchedEvent = null;

	String endTime = "";
	String eventDescription = "";
	String location = "";
	String notes = "";
	boolean reminder = false;
	int rem = 0;
	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM " + username + "_diary WHERE date = '" + date + 
													"' AND startTime = '" + startTime + "';";

	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
	    endTime = rs.getString("endtime");
	    eventDescription = rs.getString("eventdescription");
	    location = rs.getString("location");
	    notes = rs.getString("notes");
		rem = rs.getInt("reminder");


	    SchedEvent = new ScheduleBean();
        SchedEvent.setUserName(username);
		SchedEvent.setDate(date);
		SchedEvent.setStartTime(startTime);
		SchedEvent.setEndTime(endTime);
		SchedEvent.setEventDescription(eventDescription) ;
		SchedEvent.setLocation(location);
		SchedEvent.setNotes(notes);

		if (rem == 1)
		  reminder = true;
		SchedEvent.setReminder(reminder);
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin Schedule for " + username + " at " + startTime);
	  }  
	    conPool.closeCon(con);
        return SchedEvent;
    }

	/**
	*  Deletes the scheadule event for given user.
	*  @param userName
	*  @param date
	*  @param start Time
	*/
  public void DeleteScheduleEvent(String userName, String date, String startTime) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();
		
		String sDelete = "DELETE FROM " + userName + "_diary WHERE date = '" + date + 
													"' AND startTime = '" + startTime + "';";

	  stmt.executeQuery(sDelete);
    }
	catch (Exception e) {
	  System.out.println("Error deleting Schedule for " + userName + " at " + startTime);
	}
	conPool.closeCon(con);
  }

	/**
	*  Updates the scheadule event for given user with new information.
	*  @param userName
	*  @param date
	*  @param old date
	*  @param start Time
	*  @param old start Time
	*  @param endTime
	*  @param eventDescription
	*  @param location
	*  @param notes
	*  @param reminder 
	*/
  public void UpdateScheduleEvent(String userName,
								  String date,
								  String olddate,
								  String startTime,
								  String oldstartTime,
								  String endTime,
								  String eventDescription,
  							      String location,
								  String notes,
								  boolean reminder) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;

	int rem = 0;
	if (reminder == true)
	  rem = 1;

	  stmt = con.createStatement();
		
		DeleteScheduleEvent(userName, olddate, oldstartTime);

		String sInsert = "INSERT INTO " + userName + "_diary VALUES ('" 
													 + date + "', '"
													 + startTime + "', '" 
													 + endTime + "', '"
													 + eventDescription + "', '"
													 + location + "', '" 
													 + notes + "', '"
													 + rem + "');";

	  stmt.executeQuery(sInsert);
	conPool.closeCon(con);
  }

	/**
	*  returns a list of all schedule events for a given date for a user.
	*  @param userName
	*  @param date
	*  @return Vector of ScheduleBean.
	*/
  public Vector getSchedule(String username, String date) throws SQLException {
	Vector Schedule = new Vector();


	for (int i = 0; i < 24; i++) {
		String time = i + ":00:00";
		ScheduleBean sBean = getScheduleEvent(username, date, time);

		if (sBean != null)
			Schedule.add(sBean);
		else
			Schedule.add(null);
	}
    return Schedule;
  }
	/**
	*  returns a list of booked schedule events for a given date for a user.
	*  @param userName
	*  @param date
	*  @return Vector of ScheduleBean.
	*/
   public Vector getCalSchedule(String username, String date) {
	Vector Schedule = new Vector();
	try {
	for (int i = 0; i < 24; i++) {
		String time = i + ":00:00";
		ScheduleBean sBean = getScheduleEvent(username, date, time);

		if (sBean != null)
			Schedule.add(sBean);
	}
   }
	catch (Exception e) {
	  System.out.println("Error updating Schedule Eve......");
	}

    return Schedule;
  }
	/**
	*  returns a list of calendar Events with 
	*  descriptions for a given day for a user.
	*  @param userName
	*  @param date
	*  @return array of time and Desc strings.
	*/
  public Object[] getCalEventsW(String username, String date) {
	Vector Schedule = new Vector();
	Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;


	try {
		stmt = con.createStatement();

		String sQuery = "SELECT eventdescription, starttime, location FROM " + username + "_diary WHERE date = '" + date + "';";

		rs = stmt.executeQuery(sQuery);
		int counter = 0;

		while (rs.next()) {

			
			String startTime = rs.getString("starttime");
			String time = counter + ":00:00";
			
			String location = rs.getString("location");
			String EDescription = rs.getString("eventdescription");
			String timeAndDesc = startTime + "<br>&nbsp;&nbsp;" + EDescription + "<br>&nbsp;&nbsp;" + location;
			
			Schedule.add(timeAndDesc);
		}

    }
	catch (Exception e) {
	  System.out.println("Error updating Schedule Eve......");
	}
	conPool.closeCon(con);
    return Schedule.toArray();
  }

	/**
	*  returns a list of calendar Events with 
	*  descriptions for a given day for a user.
	*  @param userName
	*  @param date
	*  @return array of time and Desc strings.
	*/
    public Object[] getCalEvents(String username, String date) {
	Vector Schedule = new Vector();
	Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;

	try {
		stmt = con.createStatement();

		String sQuery = "SELECT eventdescription, starttime FROM " + username + "_diary WHERE date = '" + date + "';";

		rs = stmt.executeQuery(sQuery);

		while (rs.next()) {	
			String startTime = rs.getString("starttime");
			String EDescription = rs.getString("eventdescription");

			String timeAndDesc = startTime + "-" + EDescription;
			int len = timeAndDesc.length();

			if (len > 20)
				timeAndDesc = timeAndDesc.substring(0,18) + "...";
			
			Schedule.add(timeAndDesc);
		}

    }
	catch (Exception e) {
	  System.out.println("Error updating Schedule Eve......");
	}
	conPool.closeCon(con);
    return Schedule.toArray();
  }
}