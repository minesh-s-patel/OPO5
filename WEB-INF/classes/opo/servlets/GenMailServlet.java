package opo.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import opo.connections.connection;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Initialises system<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  This servlet class creates the connection pool
 *  starts the mail thread.
 */

public class GenMailServlet extends HttpServlet {
  /**
   *  Init method that starts when the servlet is started.
   *  creates the connection pool and
   *  starts the mail thread
   */
  public void init() throws ServletException {
	connection pooledCons = new connection();
	getServletContext().setAttribute("pooledCon", pooledCons);
	MailThread m = new MailThread(pooledCons);
	m.start();
  }
  /**
   *  destroy method that ends connection pool when the servlet is destroyed.
   */
  public void destroy() {
	connection pooledcons = (connection)getServletContext().getAttribute("pooledCon");
    pooledcons.closeAllCons();
  }
}