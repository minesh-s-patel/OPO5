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
 *  Description:  UserBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class has all methods that can be
 *  carried out on the UserBean.
 *  Database access for the user is all done in this class
 */

public class UserBeanMethods implements Serializable {

	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public UserBeanMethods(connection connect) {
		conPool = connect;	
	}


	/**
	*  authenticates user password with what is stored
	*  the database.
	*  @param username
	*  @param password
	*  @return boolean that is true if authenticated
	*/
  public boolean authenticate(String username, String password) throws SQLException {
	boolean passed = false;
	UserBean userInfo = getUser(username);
	if ((userInfo != null) && (getPassword(username)).equals(password))
	  passed = true;
	else {
		 String username1 = getPrimaryUser(username);
		 UserBean userInfo1 = getUser(username1);
		 	if ((userInfo1 != null) && (getSecondPassword(username1)).equals(password))
				passed = true;
	}
	
	return passed;
  }

	/**
	*  checked to see if the user is a promary user
	*  or a second user.
	*  @param username
	*  @return true if primary user
	*/
  public boolean isPrimaryUser(String username) throws SQLException {
	boolean passed = false;
	UserBean userInfo = getUser(username);
	if ((userInfo != null))
	  passed = true;
	else {
		 String username1 = getPrimaryUser(username);
		 UserBean userInfo1 = getUser(username1);
		 	if ((userInfo1 != null))
				passed = false;
	}
	return passed;
  }

	/**
	*  checked to see if the user is a Second User
	*  or a primary User.
	*  @param username
	*  @return true if Second user
	*/
  public boolean isSecondUser(String username) throws SQLException {
	boolean passed = false;
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM seconduser WHERE username = '"+username+"';";

	  rs = stmt.executeQuery(sQuery);

	  if (rs.next())
	  {	passed = true;
	  }
	  
    }
	catch (Exception e) {
	  System.out.println("Error adding second user");
	}
	conPool.closeCon(con);
	return passed;
  }

	/**
	*  checked to see if the username is a valid.
	*  if it is not already used.
	*  @param username
	*  @return true if usename valid
	*/
  public boolean isUsernameValid(String username) throws SQLException {
    boolean passed = false;
	UserBean userInfo= getUser(username);
	if (userInfo != null)
	  passed = true;
	return passed;
  }

	/**
	*  checked to see if Password isValid
	*  genarates appropriate error state.
	*  @param username
	*  @return state of validation
	*  return 1 if password is correct size and passwords match
	*  return 2 if password is correct size and passwords dont match
	*  return 3 if password is not correct size and passwords match
	*/
  public int isPasswordValid(String password, String passwordCheck) {
    int passed = 0;
	int passLength = password.length();
	if ((password.equals(passwordCheck)) && ((passLength >=5) && (passLength <=16)))
	passed = 1;	//return 1 if password is correct size and passwords match
	if ((!password.equals(passwordCheck)) && ((passLength >=5) && (passLength <=16)))
	passed = 2; //return 2 if password is correct size and passwords dont match
	if ((password.equals(passwordCheck)) && !((passLength >=5) && (passLength <=16)))
	passed = 3; //return 3 if password is not correct size and passwords match

	return passed;
  }

	/**
	*  adds a new user to the database with infomation provided.
	*  @param firstname
	*  @param surname
	*  @param username
	*  @param password
	*  @param emailaddress
	*/
  public void addUser(String firstname, String surname, String username, String password, String emailaddress) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sInsert = "INSERT INTO users VALUES ('" + firstname + "', '" 
													 +  surname + "', '"
													 + username + "', '" 
													 +  password + "', '" 
													 + emailaddress + "');";

	  rs = stmt.executeQuery(sInsert);

	  CreateOptions(username);
	  con.createStatement().executeQuery(CreateContactTable(username));
	  con.createStatement().executeQuery(CreateDiaryTable(username));
	  con.createStatement().executeQuery(CreateNotesTable(username));
	  
    }
	catch (Exception e) {
	  System.out.println("Error adding user");
	}
	conPool.closeCon(con);
  }

	/**
	*  adds a second user to the main users account.
	*  @param username
	*  @param seconduser
	*  @param password
	*/
  public void addSecondUser(String username, String seconduser, String password) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sInsert = "INSERT INTO seconduser VALUES ('" + username + "', '" 
													 +  seconduser + "', '"
													 + password + "');";

	  rs = stmt.executeQuery(sInsert);
	  
    }
	catch (Exception e) {
	  System.out.println("Error adding second user");
	}
	conPool.closeCon(con);
  }

	/**
	*  updates a users infomation to the database with infomation provided.
	*  @param firstname
	*  @param surname
	*  @param username
	*  @param password
	*  @param emailaddress
	*/
  public void EditUser(String username, String firstname, String surname, String emailaddress) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sUpdate1 = "UPDATE users SET firstname = '" + firstname + "' WHERE username = '" + username + "'";
	  String sUpdate2 = "UPDATE users SET surname = '" + surname + "' WHERE username = '" + username + "'";
	  String sUpdate3 = "UPDATE users SET emailaddress = '" + emailaddress + "' WHERE username = '" + username + "'";

	  stmt.executeQuery(sUpdate1);
	  stmt.executeQuery(sUpdate2);
	  stmt.executeQuery(sUpdate3);
	  
    }
	catch (Exception e) {
	  System.out.println("Error Editing user info");
	}
	conPool.closeCon(con);
  }

	/**
	*  updates a second users info to the main users account.
	*  @param username
	*  @param seconduser
	*  @param password
	*/
  public void EditSecondUser(String username, String seconduser, String password) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sUpdate1 = "UPDATE seconduser SET seconduser = '" + seconduser + "' WHERE username = '" + username + "'";
	  String sUpdate2 = "UPDATE seconduser SET password = '" + password + "' WHERE username = '" + username + "'";

	  stmt.executeQuery(sUpdate1);
	  stmt.executeQuery(sUpdate2);
	  
    }
	catch (Exception e) {
	  System.out.println("Error Editing user second user info");
	}
	conPool.closeCon(con);
  }

	/**
	*  changes password in the database for a user.
	*  @param username
	*  @param password
	*/
  public void changePassword(String username, String password) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	try {
	  stmt = con.createStatement();
	  String sUpdate1 = "UPDATE users SET password = '" + password + "' WHERE username = '" + username + "'";

	  stmt.executeQuery(sUpdate1);

    }
	catch (Exception e) {
	  System.out.println("Error Editing user password");
	}
	conPool.closeCon(con);
  }

	/**
	*  retrieves the users infomation from the
	*  database and initialses bean with info.
	*  @param username
	*  @return UserBean
	*/
  public UserBean getUser(String username) throws SQLException {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	UserBean UserInfo = null;

	String First_name = "";
	String Surname = "";
	String Email_address = "";
	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT * FROM users WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		First_name = rs.getString("firstname");
		Surname = rs.getString("surname");
		Email_address = rs.getString("emailaddress");

	    UserInfo = new UserBean();
        UserInfo.setFirstName(First_name);
        UserInfo.setSurname(Surname);
	    UserInfo.setUserName(username);
        UserInfo.setEmailAddress(Email_address);
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin user info");
	  }  
	    conPool.closeCon(con);
        return UserInfo;
    }

	/**
	*  retrieves the users password from the
	*  database
	*  @param username
	*  @return password for user
	*/
  public String getPassword(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	String Password = null;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT password FROM users WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		Password = rs.getString("password");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin password");
	  }  
		conPool.closeCon(con);
        return Password;
    }

	/**
	*  retrieves the Second users password from the
	*  database
	*  @param username
	*  @return password for Second user
	*/
  public String getSecondPassword(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	String Password = null;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT password FROM seconduser WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		Password = rs.getString("password");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin password for second user");
	  }  
		conPool.closeCon(con);
        return Password;
    }

	/**
	*  retrieves the Second users username from the
	*  database.
	*  @param username for primary user
	*  @return username for Second user
	*/
  public String getSecondUser(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	String user = null;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT seconduser FROM seconduser WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		user = rs.getString("seconduser");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin username for second user");
	  }  
		conPool.closeCon(con);
        return user;
    }

	/**
	*  retrieves the primary users username from the
	*  database from the second users username.
	*  @param username for Second user
	*  @return username for primary user
	*/
  public String getPrimaryUser(String seconduser) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	String user = null;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT username FROM seconduser WHERE seconduser = '" + seconduser + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		user = rs.getString("username");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin username for second user");
	  }  
		conPool.closeCon(con);
        return user;
    }

	/**
	*  retrieves the primary users Email Address from the
	*  database.
	*  @param username for primary user
	*  @return Email Address for primary user
	*/
  public String getEmailAddress(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	ResultSet rs = null;
	String mail = null;

	try {
	  stmt = con.createStatement();
	  String sQuery = "SELECT emailaddress FROM users WHERE username = '" + username + "';";
	  rs = stmt.executeQuery(sQuery);

	  if (rs.next() == true) {
		mail = rs.getString("emailaddress");
		}
	  }
	  catch (Exception e) {
	    System.out.println("Error gettin emailaddress");
	  }  
		conPool.closeCon(con);
        return mail;
    }

	/**
	*  Create Options for the primary user.
	*  done when user is craeted. initialises with
	*  default info.
	*  @param username for primary user
	*/
  public void CreateOptions(String username) {
    Connection con = conPool.getCon();
	Statement stmt = null;
	//default values
	int scheduleStart = 8;
	int scheduleEnd = 18;
	int sendreminder = -1; //OFF!

	try {
	  stmt = con.createStatement();
	  String sInsert = "INSERT INTO useroptions VALUES ('" + username + "', '" 
														 + scheduleStart + "', '" 
														 +  scheduleEnd + "', '"
														 + sendreminder + "');";
	  stmt.executeQuery(sInsert);

	  }
	  catch (Exception e) {
	    System.out.println("Error gettin scheduleend");
	  }  
		conPool.closeCon(con);
    }

/**********************************************************************************************/

	/**
	*  Create Diary Table for the primary user.
	*  done when user is craeted. initialises with
	*  default info.
	*  @param username for primary user
	*  @return sql string to create table for username
	*/
	public String CreateDiaryTable(String userName) {
	  String ret = "CREATE TABLE " + userName + "_diary (date DATE NOT NULL, starttime TIME NOT NULL, endtime TIME, eventdescription CHAR(255), location CHAR(255), notes CHAR(255), reminder TINYINT(1), PRIMARY KEY(date, starttime));";
	  return ret;
	}

	/**
	*  Create Contact Table for the primary user.
	*  done when user is craeted. initialises with
	*  default info.
	*  @param username for primary user
	*  @return sql string to create table for username
	*/
	public String CreateContactTable(String userName) {
	  String ret = "CREATE TABLE " + userName + "_contacts (nickname CHAR(20) NOT NULL, firstname CHAR(20), surname CHAR(20), groupname CHAR(50) NOT NULL, homeemailaddress CHAR(50), workemailaddress CHAR(50), homepostaladdress CHAR(50), workpostaladdress CHAR(50), homenumber CHAR(20), mobilenumber CHAR(20), worknumber CHAR(20), faxnumber CHAR(20), birthday DATE, PRIMARY KEY(nickname, groupname));";
	  return ret;
	}

	/**
	*  Create Notes Table for the primary user.
	*  done when user is craeted. initialises with
	*  default info.
	*  @param username for primary user
	*  @return sql string to create table for username
	*/
	public String CreateNotesTable(String userName) {
	  String ret = "CREATE TABLE " + userName + "_notes (filename CHAR(50) NOT NULL, notes LONGTEXT, PRIMARY KEY(filename));";
	  return ret;
	}
}

