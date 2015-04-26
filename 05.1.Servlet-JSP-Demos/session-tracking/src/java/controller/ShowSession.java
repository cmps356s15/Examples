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

/*
package qu.ls.controller;

import java.io.*;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import qu.ls.entity.Book;
import qu.ls.entity.Borrower;
import qu.ls.entity.Loan;
import qu.ls.repository.LoanRepository;

@WebServlet("/loan")
public class LoanController extends HttpServlet {

    @Inject
    public LoanRepository loanRepository;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = loanRepository.getBooks();
        List<Borrower> borrowers = loanRepository.getBorrowers();
        List<Loan> loans = loanRepository.getLoans();

        PrintWriter pr = response.getWriter();

        for (Book book : books) {
            pr.println(book.getIsbn());
        }

        for (Borrower book : borrowers) {
            pr.println(book.getFirstName());
        }
        for (Loan book : loans) {
            pr.println(book.getBook().getIsbn());
            pr.println(book.getBorrower().getLastName());
            pr.println(book.getDueDate());
        }
        
        pr.println(loanRepository.getBook("123").getTitle());
        pr.println(loanRepository.getBorrower(1).getFirstName());
    }
}

*/