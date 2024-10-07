package opo.beans;

import java.io.*;
import java.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  AutoMail<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This Bean holds information on reminders 
 *  that need to be sent to the users via email
 */

public class AutoEmailBean implements Serializable {
    // Properties
    private String userName;
    private String emailAddress;
    private String startTime;
    private String endTime;
	private String sendTime;
	private String date;
	private String eventDescription;
	private String location;
	private String notes;
	private boolean reminder;
    
    /**
     * Returns the password property value.
	 * @return sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * Sets the password property value.
	 * @param sendTime
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

	/**
     * Returns the emailAddress property value.
     * @return email Address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the emailAddress property value.
     * @param email Address
     */
	public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the userName property value.
     * @return user Name
     */
	public String getUserName() {
        return userName;
    }

	 /**
     * Sets the userName property value.
	 * @param user Name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

	 /**
     * Returns the date property value.
	 * @return date
     */
    public String getDate() {
        return date;
    }

	/**
     * Sets the date property value.
	 * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

	 /**
     * Returns the startTime property value.
	 * @return start Time
     */
    public String getStartTime() {
        return startTime;
    }

	/**
     * Sets the startTime property value.
	 * @param start Time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
	 /**
     * Returns the endTime property value.
	 * @return end Time
     */
	public String getEndTime() {
        return endTime;
    }

	/**
     * Sets the endTime property value.
	 * @param end Time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

     /**
     * Returns the eventDescription property value.
	 * @return event Description
     */
	public String getEventDescription() {
        return eventDescription;
    }

	/**
     * Sets the firstName property value.
	 * @param event Description
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

	/**
     * Returns the location property value.
	 * @return location
     */
	public String getLocation() {
        return location;
    }

	/**
     * Sets the location property value.
	 * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

	/**
     * Returns the notes property value.
	 * @return notes
     */
	public String getNotes() {
        return notes;
    }

	/**
     * Sets the firstName property value.
	 * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

	/**
     * Returns the reminder property value.
	 * @return reminder
     */
	public boolean getReminder() {
        return reminder;
    }

	/**
     * Sets the firstName property value.
	 * @param reminder
     */
    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

}
