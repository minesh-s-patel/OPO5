package opo.connections;

import java.io.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

 /**
 *  Title:        Online Personal Organiser<p>
 *  Description:  conection pooling implementation<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  A class for connectin to the JDBC.
 *  uses connection pooling class
 */

public class connection 
{
  private ConnectionPool connectionPool;
  private boolean connected = false;

  /**
   *  Method that gets a connection.
   *  @return Connection object
   */
  public Connection getCon() {
    Connection con = null;

	if (connected == false)
	{	startPool();
	}
	try
	{
		con = connectionPool.getConnection();
	}
	  catch (Exception e) {
	    System.out.println("error making pool");
	  }  
	
	return con;
  }

/**********************************************************************************************/
  /**
   *  Method that frees a connection.
   *  @param con Connection object
   */
  public void closeCon(Connection con) {
	connectionPool.free(con);
  }

/**********************************************************************************************/
  /**
   *  Method that closes all open connections.
   */
  public void closeAllCons() {
	connectionPool.closeAllConnections();
  }

/**********************************************************************************************/

  /**
   *  Method that starts Pool
   *  creates 50 Connections in pool.
   */
  public void startPool() {
    String driver = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://localhost/OPO";
    String username = "";
    String password = "";
    try {
      connectionPool = new ConnectionPool(driver, url,
                        username, password,
                        10,
                        50,
                        true);
	  connected = true;
    } catch(SQLException sqle) {
      System.err.println("Error making pool: " + sqle);
      connectionPool = null;
    }
  }

/**********************************************************************************************/
}