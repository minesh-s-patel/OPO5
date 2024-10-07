package opo.beans;

import java.io.*;
import java.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  OptionBean<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This Bean holds the information for a Options the use can choose
 */

public class OptionBean implements Serializable {
    // Properties
    private int scheduleStart;
    private int scheduleEnd;
    private int sendreminder;



    /**
     * Returns the scheduleStart property value.
	 * @return scheduleStart
     */
    public int getScheduleStart() {
        return scheduleStart;
    }

    /**
     * Sets the scheduleStart property value.
	 * @param scheduleStart
     */
    public void setScheduleStart(int scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    /**
     * Returns the scheduleEnd property value.
	 * @return scheduleEnd
     */
    public int getScheduleEnd() {
        return scheduleEnd;
    }

    /**
     * Sets the scheduleEnd property value.
	 * @param scheduleEnd
     */
    public void setScheduleEnd(int scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }
    
    /**
     * Returns the sendreminder property value.
	 * @return sendreminder
     */
    public int getSendreminder() {
        return sendreminder;
    }

    /**
     * Sets the sendreminder property value.
	 * @param sendreminder
     */
    public void setSendreminder(int sendreminder) {
        this.sendreminder = sendreminder;
    }

}
