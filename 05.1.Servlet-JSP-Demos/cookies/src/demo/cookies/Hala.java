package demo.cookies;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet that says "Welcome aboard" to first-time visitors and "Welcome back" to repeat visitors. Also see RepeatVisitor2 for variation that uses cookie utilities from later in this chapter. <p>
 * From <a href="http://courses.coreservlets.com/Course-Materials/">the coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */
@WebServlet("/hala")
public class Hala extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //Get the number of previous visits from the cookie
        int count = 1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("accessCount")) {
                    count = Integer.parseInt(c.getValue());
                    break;
                }
            }
        }
        
        //Set the number of previous visits
        Cookie accessCountCookie = new Cookie("accessCount", String.valueOf(count + 1));
        accessCountCookie.setMaxAge(60 * 60 * 24 * 365); // 1 year
        response.addCookie(accessCountCookie);


        String title;
        if (count == 1) {
            title = "Welcome Aboard";
        } else {
            title = "Welcome Back";
        }

        request.setAttribute("heading", title);
        request.getRequestDispatcher("hala.jsp").forward(request, response);
    }
}
