package controller;

import java.io.*;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/**
 * Servlet that uses session-tracking to keep per-client access counts. Also shows other info about the session. <p> From <a href="http://courses.coreservlets.com/Course-Materials/">the
 * coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, Spring, Hibernate, JPA, and Java</a>.
 */
@WebServlet("/show-session")
public class ShowSession extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String heading;
        Integer accessCount = (Integer) session.getAttribute("accessCount");

        if (accessCount == null) {
            accessCount = new Integer(0);
            heading = "Welcome, Newcomer";
        } else {
            heading = "Welcome Back";
            accessCount = new Integer(accessCount.intValue() + 1);
        }

        // Integer is an immutable data structure. So, you
        // cannot modify the old one in-place. Instead, you
        // have to allocate a new one and redo setAttribute.
        session.setAttribute("accessCount", accessCount);

        //create SimpleDateFormat object with desired date format
        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        request.setAttribute("heading", heading);

        Date sessionCreationDate = new Date(session.getCreationTime());
        String sessionCreationDateStr = sdfDestination.format(sessionCreationDate);
        request.setAttribute("sessionCreationDate", sessionCreationDateStr);

        Date sessionLastAccessedTime = new Date(session.getLastAccessedTime());
        String sessionLastAccessedTimeStr = sdfDestination.format(sessionLastAccessedTime);
        request.setAttribute("sessionLastAccessedTime", sessionLastAccessedTimeStr);

        request.getRequestDispatcher("sessioninfo.jsp").forward(request, response);
    }
}
