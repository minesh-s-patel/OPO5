package opo.beans;

import java.io.*;
import java.util.*;

public class ScheduleViewBean implements Serializable {
    
	// Properties
    private Vector ScheduleBeans;

    /**
     * Returns the ContactBeans property value.
	 * @return ScheduleBeans
     */
    public Vector getScheduleBeans() {
        return ScheduleBeans;
    }

	/**
     * Sets the ContactBeans property value.
	 * @param ScheduleBeans
     */
	public void setScheduleBean(Vector ScheduleVec) {
        ScheduleBeans = ScheduleVec;
    }
}
