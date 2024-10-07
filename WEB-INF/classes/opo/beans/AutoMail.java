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

public class AutoMail implements Serializable {
    // Properties
    private Object[] mailbeans;
    private Object[] schedulebeans;


    /**
     * Returns the mailbeans property value.
	 * @return mail beans
     */
    public Object[] getMailbeans() {
        return mailbeans;
    }

    /**
     * Sets the mailbeans property value.
	 * @param mail beans
     */
    public void setMailbeans(Object[] mailbeans) {
        this.mailbeans = mailbeans;
    }

    /**
     * Returns the schedulebeans property value.
	 * @return schedule beans
     */
    public Object[] getSchedulebeans() {
        return schedulebeans;
    }

    /**
     * Sets the schedulebeans property value.
	 * @param schedule beans
     */
    public void setSchedulebeans(Object[] schedulebeans) {
        this.schedulebeans = schedulebeans;
    }
    
}
