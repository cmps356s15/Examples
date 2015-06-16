package qu.ls.repository;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import qu.ls.entity.Book;
import qu.ls.entity.Borrower;
import qu.ls.entity.Loan;

@Singleton
public class LoanRepository {

    private List<Book> books;
    private List<Borrower> borrowers;
    private List<Loan> loans;

    public LoanRepository() {
    }

    public void addLoan(String bookIsbn, int borrowerId) {
        Loan loan = new Loan(getBorrower(borrowerId), getBook(bookIsbn));
        loan.setId(loans.size() + 1);
        loans.add(loan);
    }

    public List<Loan> getLoans() {
        insertTestData();
        return loans;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public Book getBook(String isbn) {
        return books.stream().filter(c -> c.getIsbn().equals(isbn)).findFirst().get();
    }

    public Borrower getBorrower(int id) {
        return borrowers.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    private void insertTestData() {
        if (loans != null && loans.size() > 0) {
            return;
        }
        
        loans = new ArrayList<>();

        //Insert 3 books
        books = new ArrayList<>();
        books.add(new Book("123", "Java EE is cool!"));
        books.add(new Book("234", "JavaScript is powerful!"));
        books.add(new Book("345", "Web services are fashionable!"));

        //Insert 3 borrowers
        borrowers = new ArrayList<>();
        borrowers.add(new Borrower(1, "Mariam", "Saleh", "33445566"));
        borrowers.add(new Borrower(2, "Ali", "Dahak", "55667788"));
        borrowers.add(new Borrower(3, "Abbas", "Ibn Fernas", "11337755"));

        //Insert 2 loans for the 3rd Borrower borrowing the first 2 books
        loans = new ArrayList<>();
        addLoan("123", 1);
        addLoan("234", 2);
    }
}