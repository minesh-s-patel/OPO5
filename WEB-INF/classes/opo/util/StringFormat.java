package opo.util;

import java.text.*;
import java.util.*;
import opo.beans.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Convets strings<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class is used to convert strings in useful ways
 */

public class StringFormat {
    // Static format objects
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();
    private static DecimalFormat numberFormat = new DecimalFormat();

    /**
     * Returns true if the specified date string represents a valid
     * date in the specified format.
     *
     * @param dateString a String representing a date/time.
     * @param dateFormatPattern a String specifying the format to be used
     *   when parsing the dateString. The pattern is expressed with the
     *   pattern letters defined for the java.text.SimpleDateFormat class.
     * @return true if valid, false otherwise
     */
    public static boolean isValidDate(String dateString, String dateFormatPattern) {
        Date validDate = null;
        synchronized (dateFormat) { 
            try {
                dateFormat.applyPattern(dateFormatPattern);
                dateFormat.setLenient(false);
                validDate = dateFormat.parse(dateString);
            }
            catch (ParseException e) {
                // Ignore and return null
            }
        }
        return validDate != null;
    }
    
    /**
     * Returns true if the specified number string represents a valid
     * integer in the specified range.
     *
     * @param numberString a String representing an integer
     * @param min the minimal value in the valid range
     * @param min the maximal value in the valid range
     * @return true if valid, false otherwise
     */
    public static boolean isValidInteger(String numberString, int min, int max) {
        Integer validInteger = null;
        try {
            Number aNumber = numberFormat.parse(numberString);
            int anInt = aNumber.intValue();
            if (anInt >= min && anInt <= max) {
                validInteger = new Integer(anInt);
            }
        }
        catch (ParseException e) {
            // Ignore and return null
        }
        return validInteger != null;
    }
    
    /**
     * Returns true if the email string contains an at sign ("@") and
     * at least one dot ("."), i.e. "hans@gefionsoftware.com" is accepted
     * but "hans@gefionsoftware" is not. Note! This rule is not always
     * correct (e.g. on an intranet it may be okay with just a name) and
     * does not gurantee a valid Internet email address but it takes
     * care of the most obvious Internet mail address format errors.
     *
     * @param emailAddrString a String representing an email address
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmailAddr(String emailAddrString) {
        boolean isValid = false;
        if (emailAddrString != null && 
            emailAddrString.indexOf("@") != -1 &&
            emailAddrString.indexOf(".") != -1) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Returns true if the specified string matches a string in the set
     * of provided valid strings, ignoring case if specified.
     *
     * @param value the String validate
     * @param validStrings an array of valid Strings
     * @param ignoreCase if true, case is ignored when comparing the value
     *  to the set of validStrings
     * @return true if valid, false otherwise
     */
    public static boolean isValidString(String value, String[] validStrings, 
            boolean ignoreCase) {
        boolean isValid = false;
        for (int i = 0; validStrings != null && i < validStrings.length; i++) {
            if (ignoreCase) {
                if (validStrings[i].equalsIgnoreCase(value)) {
                    isValid = true;
                    break;
                }
            }
            else {
                if (validStrings[i].equals(value)) {
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }

    /**
     * Returns the specified string converted to a format suitable for
     * HTML. All signle-quote, double-quote, greater-than, less-than and
     * ampersand characters are replaces with their corresponding HTML
     * Character Entity code.
     *
     * @param in the String to convert
     * @return the converted String
     */
    public static String toHTMLString(String in) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; in != null && i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '\'') {
                out.append("&#39;");
            }
            else if (c == '\"') {
                out.append("&#34;");
            }
            else if (c == '<') {
                out.append("&lt;");
            }
            else if (c == '>') {
                out.append("&gt;");
            }
            else if (c == '&') {
                out.append("&amp;");
            }
            else {
                out.append(c);
            }
        }
        return out.toString();
    }

    /**
     * Converts a String to a Date, using the specified pattern.
     * (see java.text.SimpleDateFormat for pattern description)
     *
     * @param dateString the String to convert
     * @param dateFormatPattern the pattern
     * @return the corresponding Date
     * @exception ParseException, if the String doesn't match the pattern
     */
    public static Date toDate(String dateString, String dateFormatPattern) 
        throws ParseException {
        Date date = null;
        if (dateFormatPattern == null) {
            dateFormatPattern = "yyyy-MM-dd";
        }
        synchronized (dateFormat) { 
            dateFormat.applyPattern(dateFormatPattern);
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateString);
        }
        return date;
    }
    /**
     * Converts a String to a Date, using the specified pattern.
     * (see java.text.SimpleDateFormat for pattern description)
     *
     * @param dateString the String to convert
     * @param dateFormatPattern the pattern
     * @return the corresponding Date
     * @exception ParseException, if the String doesn't match the pattern
     */
	 public static Date toDateTime(String dateString, String dateFormatPattern) 
        throws ParseException {
        Date date = null;
        if (dateFormatPattern == null) {
            dateFormatPattern = "yyyy-MM-dd";
        }
        synchronized (dateFormat) { 
            dateFormat.applyPattern(dateFormatPattern);
            date = dateFormat.parse(dateString);
        }
        return date;
    }
    /**
     * Converts a String to a Date, using the specified pattern.
     * (see java.text.SimpleDateFormat for pattern description)
     *
     * @param time1 first
     * @param time2 second
     * @return true if time1 before time2
     * @exception ParseException, if the String doesn't match the pattern
     */
	public static boolean compareTimes(String time1, String time2) throws ParseException {
		Date t1 = toDateTime(time1,	"HH:mm:ss");
		Date t2 = toDateTime(time2, "HH:mm:ss");
		if (t2.after(t1))
			return true;
		else
			return false;
    }

    /**
     * Converts a dateString in one String Format to another, 
	 *  using the specified pattern.
     * (see java.text.SimpleDateFormat for pattern description)
     *
     * @param dateString the String to convert
     * @param from_dateFormatPattern the pattern
	 * @param to_dateFormatPattern) the pattern
     * @return the corresponding dateString
     * @exception ParseException, if the String doesn't match the pattern
     */
	public static String toDateString(String dateString, String from_dateFormatPattern, String to_dateFormatPattern)
        throws ParseException {
        Date date = null;
		String formattedDate = "";
        if (from_dateFormatPattern == null) {
            from_dateFormatPattern = "yyyy-MM-dd";
        }
		if (to_dateFormatPattern == null) {
            to_dateFormatPattern = "yyyy-MM-dd";
        }
        synchronized (dateFormat) { 
            dateFormat.applyPattern(from_dateFormatPattern);
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateString);	
        }
		dateFormat.applyPattern(to_dateFormatPattern);
		formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    /**
     * Converts a String to a Number, using the specified pattern.
     * (see java.text.NumberFormat for pattern description)
     *
     * @param numString the String to convert
     * @param numFormatPattern the pattern
     * @return the corresponding Number
     * @exception ParseException, if the String doesn't match the pattern
     */
    public static Number toNumber(String numString, String numFormatPattern) 
        throws ParseException {
        Number number = null;
        if (numFormatPattern == null) {
            numFormatPattern = "######.##";
        }
        synchronized (numberFormat) { 
            numberFormat.applyPattern(numFormatPattern);
            number = numberFormat.parse(numString);
        }
        return number;
    }

    /**
     * Replaces one string with another throughout a source string.
     *
     * @param in the source String
     * @param from the sub String to replace
     * @param to the sub String to replace with
     * @return a new String with all occurences of from replaced by to
     */
    public static String replaceInString(String in, String from, String to) {
        if (in == null || from == null || to == null) {
            return in;
        }

        StringBuffer newValue = new StringBuffer();
        char[] inChars = in.toCharArray();
        int inLen = inChars.length;
        char[] fromChars = from.toCharArray();
        int fromLen = fromChars.length;

        for (int i = 0; i < inLen; i++) {
            if (inChars[i] == fromChars[0] && (i + fromLen) <= inLen) {
                boolean isEqual = true;
                for (int j = 1; j < fromLen; j++) {
                    if (inChars[i + j] != fromChars[j]) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    newValue.append(to);
                    i += fromLen - 1;
                }
                else {
                    newValue.append(inChars[i]);
                }
            }
            else {
                newValue.append(inChars[i]);
            }
        }
        return newValue.toString();
    }
}
