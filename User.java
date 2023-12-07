import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class User {
    private int userId;
    private String name;
    private Queue<Book> checkedOutBooks;
    private Map<Integer, Book> checkedOutBooksMap = new HashMap<>();

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.checkedOutBooks = new LinkedList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public Map<Integer, Book> getCheckedOutBooksMap() {
        return checkedOutBooksMap;
    }

    public void checkOutBook(Book book) {
        checkedOutBooks.add(book);
        checkedOutBooksMap.put(book.getBookId(), book);
    }

    public Book returnBook(int bookId) {
        Book returnedBook = checkedOutBooksMap.remove(bookId);
        if (returnedBook != null) {
            checkedOutBooks.remove(returnedBook);
        }
        return returnedBook;
    }

    public boolean hasCheckedOutBooks() {
        return !checkedOutBooks.isEmpty();
    }
}