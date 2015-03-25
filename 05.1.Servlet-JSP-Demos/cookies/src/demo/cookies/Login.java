package demo.cookies;

import java.io.IOException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AuthenticationService;
import model.User;

@WebServlet("/login")
public class Login extends HttpServlet {

    @EJB
    AuthenticationService authenticationService;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User currentUser = null;
//        //1. Check if the user has a valid session.
//        User currentUser = (User) request.getSession().getAttribute("currentUser");
//
//        if (currentUser != null) {
//            request.getRequestDispatcher("welcome.jsp").forward(request, response);
//            return;
//        }

        //2. If the user do not have a session then Get the authenticationToken from the cookie
        String authenticationToken = null;

        Cookie tokenCookie = getCookie(request.getCookies(), "authenticationToken");;
        if (tokenCookie != null) {
            authenticationToken = tokenCookie.getValue();
        }

        System.out.println("Authentication token from the cookie: " + authenticationToken);

        if (authenticationToken != null) {
            currentUser = authenticationService.getUser(authenticationToken);
            if (currentUser != null) {
                System.out.println("User with authentication token " + authenticationToken
                        + " is " + currentUser.getFullName());
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
        } else {
            //If authentication fails then clear the session and remove the cookie
            //Clear the session
            request.getSession().invalidate();

            //remove the cookie
            if (tokenCookie != null) {
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            }

            //3. If the user has no session and no authenticationToken then request them to login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //Get the username and the password entered by the user
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        User currentUser = authenticationService.getUser(userName, password);

        //If the login failed then return the user to the login page
        if (currentUser == null) {

            //When autentication fails then remove the authentication token cookie
            //Clear the session
            request.getSession().invalidate();

            //ad remove the cookie
            Cookie tokenCookie = getCookie(request.getCookies(), "authenticationToken");
            if (tokenCookie != null) {
                //If authentication fails then remove the cookie
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            }

            request.setAttribute("errorMessage", "Login failed. Please check your username and password and try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        //If the login is successful
        if (currentUser != null) {
            //Store the user object in the session
            request.getSession().setAttribute("currentUser", currentUser);

            if (rememberMe != null) {
                //Set authenticationCookie
                String authenticationToken = UUID.randomUUID().toString();
                //Store the authenticationToken sent as a cookie 
                authenticationService.setAuthenticationToken(userName, authenticationToken);

                Cookie authenticationCookie = new Cookie("authenticationToken", authenticationToken);
                authenticationCookie.setMaxAge(60 * 60 * 24 * 365); // 1 year
                response.addCookie(authenticationCookie);
            }

            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
    }

    private Cookie getCookie(Cookie[] cookies, String cookieName) {
        Cookie aCookie = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    aCookie = c;
                    break;
                }
            }
        }
        return aCookie;
    }
}
