package qu.ls.entity;

import java.util.Calendar;
import java.util.Date;

public class Loan {
    //This should be auto-assigned by the Database
    int id;
    private Borrower borrower;
    private Book book;
    
    private Date dueDate;

    public Loan() {
    }

    public Loan(Borrower borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
        setDefaultDueDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    private void setDefaultDueDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        c.add(Calendar.DATE, 30);
        dueDate = c.getTime();
    }
}
