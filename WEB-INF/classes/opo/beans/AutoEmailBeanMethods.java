package opo.beans;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.mail.*;
import javax.mail.internet.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  AutoEmailBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the EmailBean.
 *  Database access for the email is all done in this class
 */

public class AutoEmailBeanMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public AutoEmailBeanMethods(connection connect) {
		conPool = connect;	
	}

	/**
	*  Automail method accesses database and retrives
	*  info on reminders that need to be sent.
	*  @param Todays Date
	*  @return AutoMail bean
	*/
  public AutoMail getAutoMail(String todaysDate) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	AutoMail bean = new AutoMail();
	Vector b = new Vector();


	try {
	  stmt = con.createStatement();
	  String sQuery1 = "";

	  sQuery1 = sQuery1 + "SELECT users.username, users.emailaddress, useroptions.sendreminder ";
	  sQuery1 = sQuery1 + "FROM users, useroptions ";
	  sQuery1 = sQuery1 + "WHERE users.username = useroptions.username ";
	  sQuery1 = sQuery1 + "AND useroptions.sendreminder > 0;";
	  
	  rs = stmt.executeQuery(sQuery1);

	  while (rs.next()) {
		String username = rs.getString("username");
		String emailaddress = rs.getString("emailaddress");
		int rem = rs.getInt("sendreminder");

		for (int i = 0; i < 24; i++) {
			String time = i + ":00:00";
			
			AutoEmailBean EB = getMailEvents(username, todaysDate, time, rem);

			if (EB != null)
				b.add(EB);
		}
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error in automail somewhere");
	  }
		Object[] ba = b.toArray();

		bean.setMailbeans(ba);
		conPool.closeCon(con);
        return bean;
    }

	/**
	*  sendMail method sends all mails to
	*  users in that nees reminders sent.
	*  @param AutoEmailBean
	*/
  public void sendMail(AutoEmailBean AEB) {

    try {

	String username = AEB.getUserName();
	String emailaddress = AEB.getEmailAddress();
	String senttime = AEB.getSendTime();

	String startTime = AEB.getStartTime();
	String endTime = AEB.getEndTime();
	String eventDescription = AEB.getEventDescription();
	String location = AEB.getLocation();
	String notes = AEB.getNotes();

      Properties props = new Properties();
      props.put("mail.smtp.host", "mail.dcs.kcl.ac.uk");
      Session s = Session.getInstance(props,null);

      MimeMessage message = new MimeMessage(s);

      InternetAddress from = new InternetAddress("patelmi@dcs.kcl.ac.uk");
      message.setFrom(from);


      message.addRecipients(Message.RecipientType.TO, emailaddress);

      String subject = "Reminder";
      message.setSubject(subject);

	  String text = "";
		  text = text + "\n Username:                     " + username;
		  text = text + "\n Email Address:                " + emailaddress;
		  text = text + "\n Time Reminder sent:           " + senttime;
		  text = text + "\n Start time of appointment:    " + startTime;
		  text = text + "\n End time of appointment:      " + endTime;
		  text = text + "\n Description of event:         " + eventDescription;
		  text = text + "\n Location of event:            " + location;
		  text = text + "\n notes:                        " + notes;

      message.setText(text);
	  System.out.println("mail sent for " + username);
      Transport.send(message);

    }
    catch (MessagingException m) {
	System.out.println("mail error");
	System.out.println(m);
		  return;
	}
  }
	/**
	*  getMailEvents method accesses database and retrives
	*  info on reminders that need to be sent.
	*  @param username
	*  @param date
	*  @param startTime
	*  @param sendTime
	*  @return AutoEmailBean
	*/
  public AutoEmailBean getMailEvents(String username, String date, String startTime, int sendTime) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	AutoEmailBean eb = null;

    String emailAddress = "";
	String endTime = "";
	String eventDescription = "";
	String location = "";
	String notes = "";
	boolean reminder = false;
	int rem = 0;

	String sendTime1 = (getHour(startTime) - sendTime) + ":00:00";
	
	try {
	  stmt = con.createStatement();

	  String sQuery = "";
	  sQuery = sQuery + "SELECT * FROM " + username + "_diary, users ";
	  sQuery = sQuery + "WHERE date = '" + date + "' ";
	  sQuery = sQuery + "AND startTime = '" + startTime + "' ";
	  sQuery = sQuery + "AND users.username = '" + username + "';";


	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {

		emailAddress = rs.getString("emailaddress");
		
	    endTime = rs.getString("endtime");
	    eventDescription = rs.getString("eventdescription");
	    location = rs.getString("location");
	    notes = rs.getString("notes");
		rem = rs.getInt("reminder");


	    eb = new AutoEmailBean();
		eb.setEmailAddress(emailAddress);
        eb.setUserName(username);
		eb.setDate(date);
		eb.setStartTime(startTime);
		eb.setSendTime(sendTime1);
		eb.setEndTime(endTime);
		eb.setEventDescription(eventDescription) ;
		eb.setLocation(location);
		eb.setNotes(notes);

		if (rem == 1)
		  reminder = true;
		eb.setReminder(reminder);
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin mailevents for " + username + " at " + startTime);
	  }
	    conPool.closeCon(con);
        return eb;
    }
	/**
	*  Returns hour of string time.
	*  @param time
	*  @return hour
	*/
	public int getHour(String time) {
	String ret = "";

	for (int i = 0; i < time.length(); i++)
	{	
		String t = time.substring(i,i+1);
		if (!(t.equals(":")))
		{	ret = ret + t;
		}
		else break;
	}
	return Integer.parseInt(ret);
	}
}