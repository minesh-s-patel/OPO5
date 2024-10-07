package opo.beans;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  ContactBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the ContactBean.
 *  Database access for the Contact is all done in this class
 */

public class ContactBeanMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public ContactBeanMethods(connection connect) {
		conPool = connect;	
	}

	/**
	*  adds a new contact into the data base for
	*  the given user. 
	*  @param userName
	*  @param nickName
	*  @param firstName
	*  @param surname
	*  @param groupName
	*  @param homeEmailAddress
	*  @param workEmailAddress
	*  @param homePostalAddress
	*  @param workPostalAddress
	*  @param homeNumber
	*  @param mobileNumber
	*  @param workNumber
	*  @param faxNumber
	*  @param birthday
	*/
  public void addContact(String userName,
						 String nickName,
						 String firstName,
						 String surname,
						 String groupName,
						 String homeEmailAddress,
						 String workEmailAddress,
						 String homePostalAddress,
						 String workPostalAddress,
						 String homeNumber,
						 String mobileNumber,
						 String workNumber,
						 String faxNumber,
						 String birthday) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;


	try {
	  stmt = con.createStatement();
		String sInsert = "INSERT INTO " + userName + "_contacts VALUES ('" 
												 + nickName + "', '"
												 + firstName + "', '"
												 + surname + "', '"
												 + groupName + "', '"
												 + homeEmailAddress + "', '"
												 + workEmailAddress + "', '"
												 + homePostalAddress + "', '"
												 + workPostalAddress + "', '"
												 + homeNumber + "', '"
												 + mobileNumber + "', '"
												 + workNumber + "', '"
												 + faxNumber + "', '"
												 + birthday + "');";

	  stmt.executeQuery(sInsert);
    }
	catch (Exception e) {
	  System.out.println("Error adding contact " + nickName);
	}
	conPool.closeCon(con);
  }

	/**
	*  updates a contact into the data base for
	*  the given user. 
	*  @param userName
	*  @param nickName
	*  @param firstName
	*  @param surname
	*  @param groupName
	*  @param homeEmailAddress
	*  @param workEmailAddress
	*  @param homePostalAddress
	*  @param workPostalAddress
	*  @param homeNumber
	*  @param mobileNumber
	*  @param workNumber
	*  @param faxNumber
	*  @param birthday
	*/
	public void UpdateContact(String userName,
						 String nickName,
						 String oldNickname,
						 String firstName,
						 String surname,
						 String groupName,
						 String homeEmailAddress,
						 String workEmailAddress,
						 String homePostalAddress,
						 String workPostalAddress,
						 String homeNumber,
						 String mobileNumber,
						 String workNumber,
						 String faxNumber,
						 String birthday) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;


	try {
	  stmt = con.createStatement();
		
		DeleteContact(userName, oldNickname);

		String sInsert = "INSERT INTO " + userName + "_contacts VALUES ('" 
												 + nickName + "', '"
												 + firstName + "', '"
												 + surname + "', '"
												 + groupName + "', '"
												 + homeEmailAddress + "', '"
												 + workEmailAddress + "', '"
												 + homePostalAddress + "', '"
												 + workPostalAddress + "', '"
												 + homeNumber + "', '"
												 + mobileNumber + "', '"
												 + workNumber + "', '"
												 + faxNumber + "', '"
												 + birthday + "');";

	  stmt.executeQuery(sInsert);
    }
	catch (Exception e) {
	  System.out.println("Error adding contact " + nickName);
	}
	conPool.closeCon(con);
  }

/**********************************************************************************************/
	/**
	*  deletes a contact from the database for
	*  the given user. 
	*  @param userName
	*  @param nickName to be deleted
	*/
  public void DeleteContact(String userName, String nickName) throws SQLException {

    Connection con = conPool.getCon();
	Statement stmt = null;

	try {
	  stmt = con.createStatement();
		
		String sDelete = "DELETE FROM " + userName + "_contacts WHERE nickName='"+nickName+"';";

	  stmt.executeQuery(sDelete);
    }
	catch (Exception e) {
	  System.out.println("Error Deleting contact " + nickName);
	}
	conPool.closeCon(con);
  }

	/**
	*  gets contact info from the database for
	*  the given user. 
	*  @param userName
	*  @param nickName
	*  @return ContactsBean initialised with the contact with nickname
	*/
  public ContactsBean getContact(String userName, String nickName) throws SQLException {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	ContactsBean Contact = null;

	String firstName = "";
	String surname = "";
	String groupName = "";
	String homeEmailAddress = "";
	String workEmailAddress = "";
	String homePostalAddress = "";
	String workPostalAddress = "";
	String homeNumber = "";
	String mobileNumber = "";
	String workNumber = "";
	String faxNumber = "";
	String birthday = "";

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM " + userName + "_contacts WHERE nickName = '" + nickName + "';";
	  
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {

		firstName = rs.getString("firstname");
		surname = rs.getString("surname");
		groupName = rs.getString("groupname");
		homeEmailAddress = rs.getString("homeemailaddress");
		workEmailAddress = rs.getString("workemailaddress");
		homePostalAddress = rs.getString("homepostaladdress");
		workPostalAddress = rs.getString("workpostaladdress");
		homeNumber = rs.getString("homenumber");
		mobileNumber = rs.getString("mobilenumber");
		workNumber = rs.getString("worknumber");
		faxNumber = rs.getString("faxnumber");
		birthday = rs.getString("birthday");

	    Contact = new ContactsBean();

		Contact.setUserName(userName);
		Contact.setNickName(nickName);
		Contact.setFirstName(firstName);
		Contact.setSurname(surname);
		Contact.setGroupName(groupName);
		Contact.setHomeEmailAddress(homeEmailAddress);
		Contact.setWorkEmailAddress(workEmailAddress);
		Contact.setHomePostalAddress(homePostalAddress);
		Contact.setWorkPostalAddress(workPostalAddress);
		Contact.setHomeNumber(homeNumber);
		Contact.setMobileNumber(mobileNumber);
		Contact.setWorkNumber(workNumber);
		Contact.setFaxNumber(faxNumber);
		Contact.setBirthday(birthday);

		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin contact detail for " + userName);
	  } 
	    conPool.closeCon(con);
        return Contact;
    }

	/**
	*  Gets contact list from the database for
	*  the given user.
	*  @param userName
	*  @return Vector of All Contacts
	*/
  public Vector getAllContacts(String userName) throws SQLException {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	ContactsBean Contact = null;
	Vector allNames = new Vector();

	String nickName = "";
	String firstName = "";
	String surname = "";
	String groupName = "";
	String homeEmailAddress = "";
	String workEmailAddress = "";
	String homePostalAddress = "";
	String workPostalAddress = "";
	String homeNumber = "";
	String mobileNumber = "";
	String workNumber = "";
	String faxNumber = "";
	String birthday = "";

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM " + userName + "_contacts ORDER BY groupname;";
	  
	  rs = stmt.executeQuery(sQuery);

	  while (rs.next()) {
		nickName = rs.getString("nickname");
		firstName = rs.getString("firstname");
		surname = rs.getString("surname");
		groupName = rs.getString("groupname");
		homeEmailAddress = rs.getString("homeemailaddress");
		workEmailAddress = rs.getString("workemailaddress");
		homePostalAddress = rs.getString("homepostaladdress");
		workPostalAddress = rs.getString("workpostaladdress");
		homeNumber = rs.getString("homenumber");
		mobileNumber = rs.getString("mobilenumber");
		workNumber = rs.getString("worknumber");
		faxNumber = rs.getString("faxnumber");
		birthday = rs.getString("birthday");

	    Contact = new ContactsBean();

		Contact.setUserName(userName);
		Contact.setNickName(nickName);
		Contact.setFirstName(firstName);
		Contact.setSurname(surname);
		Contact.setGroupName(groupName);
		Contact.setHomeEmailAddress(homeEmailAddress);
		Contact.setWorkEmailAddress(workEmailAddress);
		Contact.setHomePostalAddress(homePostalAddress);
		Contact.setWorkPostalAddress(workPostalAddress);
		Contact.setHomeNumber(homeNumber);
		Contact.setMobileNumber(mobileNumber);
		Contact.setWorkNumber(workNumber);
		Contact.setFaxNumber(faxNumber);
		Contact.setBirthday(birthday);

		allNames.add(Contact);
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin contact detail for " + userName);
	  }  
	    conPool.closeCon(con);
        return allNames;
    }

	/**
	*  Gets contact list from the database for
	*  the given user.
	*  @param userName
	*  @return Vector of All Group Names
	*/
  public Vector getGroupName(String userName) throws SQLException {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	Vector gNames = new Vector();

	String groupName = "";

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT groupname FROM " + userName + "_contacts GROUP BY groupname;";
	  
	  rs = stmt.executeQuery(sQuery);

	  while (rs.next()) {
		gNames.add(rs.getString("groupname"));
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin groupnames detail for " + userName);
	  }  
	  conPool.closeCon(con);
        return gNames;
    }
}