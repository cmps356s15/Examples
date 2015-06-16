package qu.ls.Controller;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qu.ls.entity.Book;
import qu.ls.entity.Borrower;
import qu.ls.entity.Loan;
import qu.ls.repository.LoanRepository;

/**
 *
 * @author shiny_000
 */
@WebServlet("/addLoan")
public class AddLoanController extends HttpServlet {

    @Inject
    LoanRepository loanRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = loanRepository.getBooks();
        List<Borrower> borrowers = loanRepository.getBorrowers();
        request.setAttribute("books", books);
        request.setAttribute("borrowers", borrowers);
        request.getRequestDispatcher("addLoan.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String borrower = request.getParameter("borrower");
        int borrowerId = Integer.parseInt(borrower);
        String bookISBN = request.getParameter("book");
        loanRepository.addLoan(bookISBN, borrowerId);
        
        response.sendRedirect("loans");
    }
}
