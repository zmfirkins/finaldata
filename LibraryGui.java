import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryGui extends JFrame {
    private JTextField bookIdField, titleField, authorField, isbnField;
    private JTextField searchBookIdField, removeBookIdField, userIdField;
    private Library library;

    public LibraryGui(Library library) {
        this.library = library;
        setTitle("Zoie's Library System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdField = new JTextField(10);
        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(30);
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(30);
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField(15);

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField(10);

        inputPanel.add(bookIdLabel);
        inputPanel.add(bookIdField);
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);
        inputPanel.add(userIdLabel);
        inputPanel.add(userIdField);

        JButton addButton = new JButton("Add Book");
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton searchButton = new JButton("Search");
        JButton removeButton = new JButton("Remove Book");
        JButton checkOutButton = new JButton("Check Out");
        JButton returnButton = new JButton("Return");

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search Book ID:");
        searchBookIdField = new JTextField(10);
        searchPanel.add(searchLabel);
        searchPanel.add(searchBookIdField);
        searchPanel.add(searchButton);

        JPanel removePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel removeLabel = new JLabel("Remove Book ID:");
        removeBookIdField = new JTextField(10);
        removePanel.add(removeLabel);
        removePanel.add(removeBookIdField);
        removePanel.add(removeButton);

        buttonPanel.add(searchPanel);
        buttonPanel.add(removePanel);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(returnButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(bookIdField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();
                Book book = new Book(bookId, title, author, isbn);
                library.addBook(book);

                bookIdField.setText("");
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
            }
        });
        //search for book
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int searchBookId = Integer.parseInt(searchBookIdField.getText());
                Book foundBook = library.searchBook(searchBookId);
                if (foundBook != null) {
                    displayBookInformation(foundBook);
                } else {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book not found.");
                }
                searchBookIdField.setText("");
            }

            private void displayBookInformation(Book book) {
                String bookInfo = "Book ID: " + book.getBookId() + "\n" +
                        "Title: " + book.getTitle() + "\n" +
                        "Author: " + book.getAuthor() + "\n" +
                        "ISBN: " + book.getIsbn();

                JOptionPane.showMessageDialog(LibraryGui.this, bookInfo, "Book Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //remove book

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int removeBookId = Integer.parseInt(removeBookIdField.getText());

                library.removeBook(removeBookId);

                if (library.isBookRemoved()) {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book removed successfully.", "Book Removal", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book not found or removal failed.", "Book Removal Error", JOptionPane.ERROR_MESSAGE);
                }
                removeBookIdField.setText("");
            }
        });
        //checkout book
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(bookIdField.getText());
                int userId = Integer.parseInt(userIdField.getText());
                User user = getUserById(userId);

                if (user != null && library.checkOutBook(bookId, user)) {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book checked out successfully.", "Book Checkout", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book not found or user not found.", "Book Checkout Error", JOptionPane.ERROR_MESSAGE);
                }

                bookIdField.setText("");
                userIdField.setText("");
            }
        });
        	//return book
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(bookIdField.getText());
                int userId = Integer.parseInt(userIdField.getText());
                User user = getUserById(userId);

                if (user != null && library.returnBook(bookId, user)) {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book returned successfully.", "Book Return", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LibraryGui.this, "Book not found or user not found.", "Book Return Error", JOptionPane.ERROR_MESSAGE);
                }

                bookIdField.setText("");
                userIdField.setText("");
            }
        });
    }

    private User getUserById(int userId) {
        return library.getUserById(userId);
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addUser(new User(1, "John Doe"));
        library.addUser(new User(2, "Jane Doe"));

        SwingUtilities.invokeLater(() -> {
            LibraryGui gui = new LibraryGui(library);
            gui.setVisible(true);
        });
    }
}