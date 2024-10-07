package opo.beans;

import java.io.*;
import java.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  UserBean<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This Bean holds the information for a user
 */

public class UserBean implements Serializable {
    // Properties
    private String firstName;
    private String surname;
    private String password;
    private String userName;
	private String emailAddress;
	private String session;


    /**
     * Returns the firstName property value.
	 * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName property value.
	 * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the lastName property value.
     * @return surname
	 */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the lastName property value.
	 * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * Returns the password property value.
	 * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password property value.
	 * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the userName property value.
	 * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName property value.
	 * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

	/**
    * Returns the emailAddress property value.
	* @return emailAddress
    */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the emailAddress property value.
	 * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets the session property value.
	 * @param session
     */
    public void setSession(String session) {
        this.session = session;
    }
}
