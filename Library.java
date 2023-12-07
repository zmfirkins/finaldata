import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private boolean bookRemoved;

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookId) {
        bookRemoved = false;

        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                bookRemoved = true;
                break;
            }
        }
    }

    public Book searchBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public boolean checkOutBook(int bookId, User user) {
        Book book = searchBook(bookId);
        if (book != null) {
            user.checkOutBook(book);
            removeBook(bookId);
            return true;
        }
        return false;
    }

    public boolean returnBook(int bookId, User user) {
        Book book = user.returnBook(bookId);
        if (book != null) {
            addBook(book);
            return true;
        }
        return false;
    }
}
