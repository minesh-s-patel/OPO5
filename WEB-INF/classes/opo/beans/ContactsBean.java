package opo.beans;

import java.io.*;
import java.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  ContactsBean<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This Bean holds the information for a Contacts of a user
 */

public class ContactsBean implements Serializable {
    
	// Properties
    private String userName;
	private String groupName;
	private String firstName;
	private String surname;
	private String nickName;
	private String homeEmailAddress;
	private String workEmailAddress;
	private String homePostalAddress;
	private String workPostalAddress;
	private String homeNumber;
	private String mobileNumber;
	private String workNumber;
	private String faxNumber;
	private String birthday;

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
     * Returns the group name property value.
	 * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }

	/**
     * Sets the userName property value.
	 * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

	/**
     * Returns the first name property value.
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
     * Returns the surname property value.
	 * @return surname
     */
    public String getSurname() {
        return surname;
    }

	/**
     * Sets the surname property value.
	 * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

	/**
     * Returns the nick name property value.
	 * @return nickName
     */
    public String getNickName() {
        return nickName;
    }

	/**
     * Sets the nickName property value.
	 * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

	/**
     * Returns the home Email Address property value.
	 * @return homeEmailAddress
     */
    public String getHomeEmailAddress() {
        return homeEmailAddress;
    }

	/**
     * Sets the homeEmailAddress property value.
	 * @param homeEmailAddress
     */
    public void setHomeEmailAddress(String homeEmailAddress) {
        this.homeEmailAddress = homeEmailAddress;
    }

	/**
     * Returns the work Email Address property value.
	 * @return workEmailAddress
     */
    public String getWorkEmailAddress() {
        return workEmailAddress;
    }

	/**
     * Sets the workEmailAddress property value.
	 * @param workEmailAddress
     */
    public void setWorkEmailAddress(String workEmailAddress) {
        this.workEmailAddress = workEmailAddress;
    }

	/**
     * Returns the home Postal Address property value.
	 * @return homePostalAddress
     */
    public String getHomePostalAddress() {
        return homePostalAddress;
    }

	/**
     * Sets the homePostalAddress property value.
	 * @param homePostalAddress
     */
    public void setHomePostalAddress(String homePostalAddress) {
        this.homePostalAddress = homePostalAddress;
    }


	/**
     * Returns the work Postal Address property value.
	 * @return workPostalAddress
     */
    public String getWorkPostalAddress() {
        return workPostalAddress;
    }

	/**
     * Sets the workPostalAddress property value.
	 * @param workPostalAddress
     */
    public void setWorkPostalAddress(String workPostalAddress) {
        this.workPostalAddress = workPostalAddress;
    }

	/**
     * Returns the Home Number property value.
	 * @return homeNumber
     */
    public String getHomeNumber() {
        return homeNumber;
    }

	/**
     * Sets the homeNumber property value.
	 * @param homeNumber
     */
    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

	/**
     * Returns the Mobile Number property value.
	 * @return mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

	/**
     * Sets the mobileNumber property value.
	 * @param mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

	/**
     * Returns the work number property value.
	 * @return workNumber
     */
    public String getWorkNumber() {
        return workNumber;
    }

	/**
     * Sets the workNumber property value.
	 * @param workNumber
     */
    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

	/**
     * Returns the fax Number property value.
	 * @return faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

	/**
     * Sets the faxNumber property value.
	 * @param faxNumber
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

	/**
     * Returns the birthday property value.
	 * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

	/**
     * Sets the birthday property value.
	 * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
