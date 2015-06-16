package qu.ls.Controller;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qu.ls.entity.Loan;
import qu.ls.repository.LoanRepository;

@WebServlet("/loans")
public class LoanController extends HttpServlet {

    @Inject
    LoanRepository loanRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Loan> loans = loanRepository.getLoans();
        request.setAttribute("loans", loans);
        request.getRequestDispatcher("bookLoans.jsp").forward(request, response);
    }
}
