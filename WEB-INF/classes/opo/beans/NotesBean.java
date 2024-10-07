package opo.beans;

import java.io.*;
import java.util.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  NotesBean<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This Bean holds the information for a Notes
 */

public class NotesBean implements Serializable {
    
	// Properties
    private String FileName;
	private String Notes;


    /**
     * Returns the FileName property value.
	 * @return FileName
     */
    public String getFileName() {
        return FileName;
    }

	/**
     * Sets the FileName property value.
	 * @param FileName
     */
    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

	 /**
     * Returns the Notes property value.
	 * @return Notes
     */
    public String getNotes() {
        return Notes;
    }

	/**
     * Sets the Notes property value.
	 * @param Notes
     */
    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

	
}
