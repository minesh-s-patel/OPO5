package opo.util;

import java.util.*;
import opo.connections.*;
import opo.beans.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  ScheduleBean Methods<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class contains methods to
 *  create html calender and associated mothods. 
 */

public class CalendarMethods
{	
	private connection conPool = null;
	/**
	*  Constructor that inialises class.
	*  @param connect The opo.connections.connection class
	*/
	public CalendarMethods(connection connect) {
		conPool = connect;	
	}

	/**
	*  calculated the days in the month
	*  @param cal Calendar set to date needed.
	*  @return Days In Month
	*/
	public int getDaysInMonth(Calendar cal) {
	  int year = cal.get(cal.YEAR);
	  int month = cal.get(cal.MONTH);
	  int days = 0;
	  if (month==0 || month==2 || month==4 || month==6 || month==7 || month==9 || month==11)
		  days=31;
	  else
		if (month==3 || month==5 || month==8 || month==10)
		  days=30;
		else
		  if (month==1) {
			if (isLeapYear(year))
			  days=29;
			else
			  days=28;
		}
	  return days;
	}

	/**
	*  sees if the year is a leap year
	*  @param Year with year YYYY.
	*  @return true if leap year
	*/
	public boolean isLeapYear(int Year) {
      if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0))
        return true;
      else 
		return false;
    }

	/**
	*  converts month java fields in java.util.Calendar to strings
	*  @param cal Calendar set to date needed.
	*  @return string month
	*/
	public String getMonth(Calendar cal) {
		int month = cal.get(cal.MONTH);
		String mon = "";
		if (month == 0) mon = "January";
		if (month == 1) mon = "February";
		if (month == 2) mon = "March";
		if (month == 3) mon = "April";
		if (month == 4) mon = "May";
		if (month == 5) mon = "June";
		if (month == 6) mon = "July";
		if (month == 7) mon = "August";
		if (month == 8) mon = "September";
		if (month == 9) mon = "October";
		if (month == 10) mon = "November";
		if (month == 11) mon = "December";
		return mon;
	}

	/**
	*  converts month from int to string
	*  @param month Calendar set to date needed.
	*  @return string month
	*/
	public String getMonth(int month) {
		String mon = "";
		if (month == 0) mon = "January";
		if (month == 1) mon = "February";
		if (month == 2) mon = "March";
		if (month == 3) mon = "April";
		if (month == 4) mon = "May";
		if (month == 5) mon = "June";
		if (month == 6) mon = "July";
		if (month == 7) mon = "August";
		if (month == 8) mon = "September";
		if (month == 9) mon = "October";
		if (month == 10) mon = "November";
		if (month == 11) mon = "December";
		return mon;
	}

	/**
	*  Draws Calendar for the small calendar
	*  @param cal Calendar set to date needed.
	*  @return HTML string to genarate the calendar for the month
	*/
	public String drawCalendar(Calendar cal) {
		Calendar today = Calendar.getInstance();
		String ret = "";
        int startPos = cal.get(cal.DAY_OF_WEEK);
		int DaysInMonth = getDaysInMonth(cal);

        int number = (startPos*(-1))+2;

		
		ret = ret + "<table width='21%' border='0' height='148' cellspacing='2' bgcolor='#3366FE'>";
		ret = ret + "<tr>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>S</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>M</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>T</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>W</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>T</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>F</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF'><b><center>S</center></b></td>";
		ret = ret + "</tr>";

		for (int p = 0; p < 6; p++) {
			ret = ret + "<tr>";
			for(int i = 0; i < 7; i++) {
			  if (number > 0 && number < DaysInMonth+1) {
					int n1 = number++;
					if (CheckToday(today, cal, n1)) {
						ret = ret + "<td bgcolor='#FFFFFF'>";
						ret = ret + "<button bgcolor='#FFFFFF' type='submit' value=" + n1 + " name='" + n1 + "'";
						ret = ret + " onClick='document.calButtons.display_date.value = name'>";
						ret = ret + "<img src='/OPO5/Organiser_main/Organiser_files/cal_img/"+n1+".gif' width='15' height='15'></button>";
						ret = ret + "</td>";

					}
					else {
						ret = ret + "<td bgcolor='#3366FF'>";
						ret = ret + "<button type='submit' value=" + n1 + " name='" + n1 + "'";
						ret = ret + " onClick='document.calButtons.display_date.value = name'>";
						ret = ret + "<img src='/OPO5/Organiser_main/Organiser_files/cal_img/"+n1+".gif' width='15' height='15'></button>";
						ret = ret + "</td>";
				}
			  }
			  else { 
				number++;
				ret = ret + "<td bgcolor='#3366FF'>&nbsp;</td>";
			  }
			}
			ret = ret + "</tr>";
		}
		ret = ret + "</table>";

		return ret;
	}

	/**
	*  Draws Calendar in calendar section
	*  @param cal Calendar set to date needed
	*  @param username of main user
	*  @param h hieght of cell
	*  @param w width of cell
	*  @return HTML string to genarate the calendar for the month
	*    with dates initailised with schedule info for the user
	*/
	public String drawCal(Calendar cal, String username, int h, int w) {
		Calendar today = Calendar.getInstance();
		String ret = "";
        int startPos = cal.get(cal.DAY_OF_WEEK);
		int DaysInMonth = getDaysInMonth(cal);
		ScheduleBeanMethods b = new ScheduleBeanMethods(conPool);

        int number = (startPos*(-1))+2;
		ret = ret + "<table border='0' cellspacing='3'>";
		ret = ret + "<tr height="+(h-30)+">";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>S</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>M</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>T</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>W</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>T</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>F</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>S</center></b></td>";
		ret = ret + "</tr>";
		

		for (int p = 0; p < 6; p++) {
			ret = ret + "<tr height="+h+">";
			for(int i = 0; i < 7; i++) {
				String bgcolor = "#3366FF";
				String fontcolor = "black";
				if ((number > 0) && (number < DaysInMonth+1)) {
					int year = (cal.get(cal.YEAR));
					int month = (cal.get(cal.MONTH)+1);
					int day = number++;
					if (CheckWeekend(cal, day)) {
						bgcolor = "#FFFF99";
					}
					if (CheckToday(today, cal, day)) {
						bgcolor = "#EEEEEE";
						fontcolor = "black";
					}
				ret = ret + "<td bgcolor="+bgcolor+" valign=top>";
				ret = ret + "<a href='/OPO5/Organiser_main/Diary/Diary?";
				ret = ret + "display_year="+year+"&display_month="+month+"&display_date="+day+"'>";
				ret = ret + "<b>";
				ret = ret + "<font color=black>";
				ret = ret + day;
				ret = ret + "</b></font></a>";

				ret = ret + "</td>";
			  }
			  else { 
				number++;
					ret = ret + "<td bgcolor='#3366FF'>&nbsp;</td>";
			  }
			}
			ret = ret + "</tr>";
		}


		ret = ret + "</table>";

		return ret;
	}

	/**
	*  Draws Calendar in calendar section
	*  @param Calendar set to date needed
	*  @param username
	*  @param h -  hieght of cell
	*  @param w - width of cell
	*  @return HTML string to genarate the calendar for the month
	*    with dates initailised with schedule info for the user
	*/
	public String drawCalendarMonthly(Calendar cal, String username, int h, int w) {
		Calendar today = Calendar.getInstance();
		String ret = "";
        int startPos = cal.get(cal.DAY_OF_WEEK);
		int DaysInMonth = getDaysInMonth(cal);
		ScheduleBeanMethods b = new ScheduleBeanMethods(conPool);

        int number = (startPos*(-1))+2;
		ret = ret + "<table border='0' cellspacing='3'>";
		ret = ret + "<tr height="+(h-30)+">";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Sunday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Monday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Tuesday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Wednesday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Thursday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Friday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width="+w+"><b><center>Satarday</center></b></td>";
		ret = ret + "</tr>";
		

		for (int p = 0; p < 6; p++) {
			ret = ret + "<tr height="+h+">";
			for(int i = 0; i < 7; i++) {
				String bgcolor = "#3366FF";
				String fontcolor = "black";
				if ((number > 0) && (number < DaysInMonth+1)) {
					int year = (cal.get(cal.YEAR));
					int month = (cal.get(cal.MONTH)+1);
					int day = number++;
					if (CheckWeekend(cal, day)) {
						bgcolor = "#FFFF99";
					}
					if (CheckToday(today, cal, day)) {
						bgcolor = "#EEEEEE";
						fontcolor = "black";
					}
				ret = ret + "<td bgcolor="+bgcolor+" valign=top>";
				ret = ret + "<a href='/OPO5/Organiser_main/Diary/Diary?";
				ret = ret + "display_year="+year+"&display_month="+month+"&display_date="+day+"'>";
				ret = ret + "<b>";
				ret = ret + "<font color=black>";
				ret = ret + day;
				ret = ret + "</b></font></a>";
				String date = year + "-" + month + "-" + day;
				Object[] v = b.getCalEvents(username, date);
				for (int q = 0; q < v.length; q++)  {
					String timeAndDes = v[q].toString();
					String startTime = timeAndDes.substring(0,8);
					ret = ret + "<br>";
					ret = ret + "<a href='/OPO5/Organiser_main/Diary/ViewScheduleEvent.jsp?";
					ret = ret + "startTime="+startTime+"&date="+date+"'>";
					ret = ret + "<font size=-2 color="+fontcolor+">";
					ret = ret + v[q];
					ret = ret + "</font>";
					ret = ret + "</a>";
				}
				ret = ret + "</td>";
			  }
			  else { 
				number++;
					ret = ret + "<td bgcolor='#3366FF'>&nbsp;</td>";
			  }
			}
			ret = ret + "</tr>";
		}


		ret = ret + "</table>";

		return ret;
	}


	/**
	*  Draws Calendar in weekly view in calendar section
	*  @param cal Calendar set to date needed
	*  @param username for main user
	*  @return HTML string to genarate the calendar for the week
	*    with dates initailised with schedule info for the user
	*/
	public String drawCalendarWeekly(Calendar cal, String username) {
		Calendar today = Calendar.getInstance();
		String ret = "";
        int startPos = cal.get(cal.DAY_OF_WEEK);
		int DaysInMonth = getDaysInMonth(cal);
		ScheduleBeanMethods b = new ScheduleBeanMethods(conPool);

        int number = (startPos*(-1))+2;
		ret = ret + "<table border='0' cellspacing='3'>";
		ret = ret + "<tr height=50>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Sunday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Monday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Tuesday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Wednesday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Thursday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Friday</center></b></td>";
		ret = ret + "<td bgcolor='#6699FF' width=100><b><center>Satarday</center></b></td>";
		ret = ret + "</tr>";
		

		for (int p = 0; p < 6; p++) {
			if (today.get(today.WEEK_OF_MONTH) == p+1) {

			ret = ret + "<tr height=80>";
			for(int i = 0; i < 7; i++) {
				String bgcolor = "#3366FF";
				String fontcolor = "black";
				if ((number > 0) && (number < DaysInMonth+1)) {
					int year = (cal.get(cal.YEAR));
					int month = (cal.get(cal.MONTH)+1);
					int day = number++;
					if (CheckWeekend(cal, day)) {
						bgcolor = "#FFFF99";
					}
					if (CheckToday(today, cal, day)) {
						bgcolor = "#EEEEEE";
						fontcolor = "black";
					}
				ret = ret + "<td bgcolor="+bgcolor+" valign=top>";
				ret = ret + "<a href='/OPO5/Organiser_main/Diary/Diary?";
				ret = ret + "display_year="+year+"&display_month="+month+"&display_date="+day+"'>";
				ret = ret + "<b>";
				ret = ret + "<font color=black>";
				ret = ret + day;
				ret = ret + "</b></font></a>";
				String date = year + "-" + month + "-" + day;
				Object[] v = b.getCalEventsW(username, date);
				for (int q = 0; q < v.length; q++)  {
					String timeAndDes = v[q].toString();
					String startTime = timeAndDes.substring(0,8);
					ret = ret + "<br>";
					ret = ret + "<a href='/OPO5/Organiser_main/Diary/ViewScheduleEvent.jsp?";
					ret = ret + "startTime="+startTime+"&date="+date+"'>";
					ret = ret + "<font size=-1 color="+fontcolor+">";
					ret = ret + v[q];
					ret = ret + "<br>";
					ret = ret + "</font>";
					ret = ret + "</a>";
				}
				ret = ret + "</td>";
			  }
			  else { 
				number++;
					ret = ret + "<td bgcolor='#3366FF'>&nbsp;</td>";
			  }
			}
			
			}else number = number + 7;
			ret = ret + "</tr>";
			
		}


		ret = ret + "</table>";

		return ret;
	}

	/**
	*  Draws Calendar in yearly view in calendar section
	*  @param cal Calendar set to date needed
	*  @param username for main user
	*  @return HTML string to genarate the calendar for the week
	*    with dates initailised with schedule info for the user
	*/
	public String drawCalendarYearly(Calendar cal7, String username) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.MONTH, cal.JANUARY);
		cal.set(cal.DAY_OF_MONTH, 1);
		String ret = "";

		ret = ret + "<table>";

		ret = ret + "<tr>";
		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "</tr>";
		ret = ret + "<tr>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "</tr>";
		ret = ret + "<tr>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "<td>";
		ret = ret + getMonth(cal.get(cal.MONTH));
		ret = ret + drawCal(cal, username, 5, 5);
		cal.roll(cal.MONTH, 1);
		ret = ret + "</td>";

		ret = ret + "</tr>";

		ret = ret + "</table>";

		return ret;
	}



	/**
	*  checks to see if it a date is today..
	*  @param today Calendar set to todays date.
	*  @param cal Calendar set to date needed.
	*  @param date Date
	*  @return true if it today
	*/
		public boolean CheckToday(Calendar today, Calendar cal, int date) { 
			if ( (today.get(today.DAY_OF_MONTH)==date) && (today.get(today.MONTH)==cal.get(cal.MONTH)) && (today.get(today.YEAR)==cal.get(cal.YEAR)) )
				return true;
			else
				return false;
		}

	/**
	*  checks to see if it a weekend.
	*  @param cal Calendar set to todays date.
	*  @param date date needed.
	*  @return true if it is a weekend
	*/
		public boolean CheckWeekend(Calendar cal, int date) {
			cal.set(cal.DAY_OF_MONTH, date);
			int sat = cal.SATURDAY;
			int sun = cal.SUNDAY;
			int day_of_week = cal.get(cal.DAY_OF_WEEK);
			if ( (day_of_week == sat) || (day_of_week == sun) )
				return true;
			else
				return false;
		}

}