package opo.beans;

import java.io.*;
import java.util.*;

public class ContactViewBean implements Serializable {
    
	// Properties
    private Vector ContactBeans;

    /**
     * Returns the ContactBeans property value.
	 * @return Contact Beans
     */
    public Vector getContactBeans() {
        return ContactBeans;
    }

	/**
     * Sets the ContactBeans property value.
	 * @param Contact Beans
     */
	public void addContactBean(ContactsBean contactBean) {
        ContactBeans.add(contactBean);
    }

	public void setContactBean(Vector ContactVec) {
        ContactBeans = ContactVec;
    }

}
