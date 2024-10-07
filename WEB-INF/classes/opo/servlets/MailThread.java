package opo.servlets;

import java.util.*;
import opo.beans.*;
import opo.util.*;
import opo.connections.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Mailing Thread<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This class describes a thread that runs once every minute,
 *  checking if there are any reminders needing to be sent out
 *  to the users of the system. If a reminder needs to be sent,
 *  an email is sent to the user.
 */

public class MailThread extends Thread {

	private connection pooledCon = null;
	/**
	*  Constructor that inialises thread.
	*  @param connect The opo.connections.connection class
	*/
	public MailThread(connection connect) {
		pooledCon = connect;	
	}
  /**
   *  thread method that will run once every 60
   *  seconds.
   *  
   */
	public void run() {
	
		while (true) {
			// get date time for "now"
			Calendar now = Calendar.getInstance();
			int t = now.get(Calendar.HOUR_OF_DAY);
			// setup dates and times needed for comparisns
			int t2 = now.get(Calendar.MINUTE);
			String tt2 = t2 + "";
			if (t2 == 0)
			{	tt2 = "00";
			}
			String t3 = "00";

			String timenow = t + ":" + tt2 + ":" + t3;

			String time = t + ":00:00";

			String year = now.get(now.YEAR) + "";
			String month = (now.get(now.MONTH)+1) + "";
			String day = now.get(now.DAY_OF_MONTH) + "";

			String date = year + "-" + month + "-" + day;

			AutoEmailBeanMethods m = new AutoEmailBeanMethods(pooledCon);
			AutoMail AM = m.getAutoMail(date);

			Object[] mail = AM.getMailbeans();

			for (int i = 0; i < mail.length; i++)
			{	
				AutoEmailBean AEB = (AutoEmailBean)mail[i];

				String username = AEB.getUserName();
				String startTime = AEB.getStartTime();

				String sendTime = AEB.getSendTime();

				boolean reminder = AEB.getReminder();

				if (reminder == true && sendTime.equals(timenow))
					m.sendMail(AEB);
			}

			try {
				// run thread once evry 60 seconds
				Thread.currentThread().sleep(60000);
			}
			catch (InterruptedException e) {
				System.err.println("interupted while sleeping");
				System.exit(1);
			}
		}
	}
}
