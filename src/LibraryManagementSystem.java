import java.awt.*;
import java.sql.*; 
import javax.swing.*;       

public class LibraryManagementSystem extends JFrame {
    private JTextField titleField, authorField, yearField, idField;
    private JTextArea displayArea;
 
    Connection con;              
    PreparedStatement pst;                        
    ResultSet rs; 
 
    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Book ID:"); 
        idField = new JTextField(10);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);

        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(20);

        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField(5);

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");

        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(idLabel); add(idField);
        add(titleLabel); add(titleField);
        add(authorLabel); add(authorField);
        add(yearLabel); add(yearField);

        add(addButton);
        add(viewButton);
        add(updateButton);
        add(deleteButton);
        add(scrollPane);

        connectDB();

        addButton.addActionListener(e -> addBook());
        viewButton.addActionListener(e -> viewBooks());
        updateButton.addActionListener(e -> updateBook());
        deleteButton.addActionListener(e -> deleteBook());
    }

    private void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarydb", "root", "your_password_here");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage());
        }
    }

    private void addBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());

            pst = con.prepareStatement("INSERT INTO books(title, author, year) VALUES (?, ?, ?)");
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setInt(3, year);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Adding Book: " + e.getMessage());
        }
    }

    private void viewBooks() {
        try {
            pst = con.prepareStatement("SELECT * FROM books");
            rs = pst.executeQuery();
            displayArea.setText("");
            while (rs.next()) {
                displayArea.append("ID: " + rs.getInt("id") +
                        ", Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") +
                        ", Year: " + rs.getInt("year") + "\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Viewing Books: " + e.getMessage());
        }
    }

    private void updateBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());

            pst = con.prepareStatement("UPDATE books SET title=?, author=?, year=? WHERE id=?");
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setInt(3, year);
            pst.setInt(4, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book ID Not Found!");
            }
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Updating Book: " + e.getMessage());
        }
    }

    private void deleteBook() {
        try {
            int id = Integer.parseInt(idField.getText());

            pst = con.prepareStatement("DELETE FROM books WHERE id=?");
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book ID Not Found!");
            }
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Deleting Book: " + e.getMessage());
        }
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}
