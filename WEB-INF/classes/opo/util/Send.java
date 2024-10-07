package opo.util;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import opo.beans.*;
import java.util.*;
import opo.connections.*;

/**
 *  Title:        Online Personal Organiser<p>
 *  Description:  Convets strings<p>
 *
 *  @version 1.0 April 2002<p>
 *  @author Minesh Patel<p>
 *
 *  redirect specified pages
 */

public class Send
{    /**
     * static method that forward page to given url.
	 * @param request header
     * @param response header
	 * @param Url to redirect to
     */
  public static void forward(HttpServletRequest request, HttpServletResponse response, String Url)
	  throws IOException, ServletException {
    RequestDispatcher rd = request.getRequestDispatcher(Url);
    rd.forward(request, response);
  }
}