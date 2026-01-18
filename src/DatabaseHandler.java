import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    // === НАСТРОЙКИ ПОДКЛЮЧЕНИЯ ===
    private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "hinifi51";

    // Метод для подключения
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // === CREATE: Добавить пользователя ===
    public void addUser(LibraryUser user) {
        String query = "INSERT INTO users (username, user_type, year_of_study, department) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getName());

            if (user instanceof Student) {
                stmt.setString(2, "STUDENT");
                stmt.setInt(3, ((Student) user).getYearOfStudy());
                stmt.setNull(4, Types.VARCHAR);
            } else if (user instanceof Teacher) {
                stmt.setString(2, "TEACHER");
                stmt.setNull(3, Types.INTEGER);
                stmt.setString(4, ((Teacher) user).getDepartment());
            }

            stmt.executeUpdate();
            System.out.println(" User saved to database!");

        } catch (SQLException e) {
            System.out.println(" Error adding user: " + e.getMessage());
        }
    }

    // === READ: Получить всех пользователей ===
    public List<LibraryUser> getAllUsers() {
        List<LibraryUser> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String type = rs.getString("user_type");

                if ("STUDENT".equals(type)) {
                    int year = rs.getInt("year_of_study");
                    users.add(new Student(id, name, year));
                } else if ("TEACHER".equals(type)) {
                    String dept = rs.getString("department");
                    users.add(new Teacher(id, name, dept));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // === DELETE: Удалить пользователя по ID ===
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("User deleted.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
    // ================== BOOKS (КНИГИ - НОВОЕ!) ==================

    public void addBook(EBook book) {
        String query = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());

            stmt.executeUpdate();
            System.out.println(" Book saved to database!");
        } catch (SQLException e) {
            System.out.println(" Error adding book: " + e.getMessage());
        }
    }

    public List<EBook> getAllBooks() {
        List<EBook> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                // Создаем книгу и добавляем в список
                books.add(new EBook(title, isbn, author));
            }
        } catch (SQLException e) {
            System.out.println(" Error loading books: " + e.getMessage());
        }
        return books;
    }

    public void deleteBook(String isbn) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE isbn = ?")) {
            stmt.setString(1, isbn);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println(" Book deleted.");
            else System.out.println("️ Book not found.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // === UPDATE: Обновить имя пользователя по ID ===
    public void updateUser(int id, String newName) {
        String query = "UPDATE users SET username = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    // === UPDATE: Обновить название и автора книги по ISBN ===
    public void updateBook(String isbn, String newTitle, String newAuthor) {
        String query = "UPDATE books SET title = ?, author = ? WHERE isbn = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newTitle);
            stmt.setString(2, newAuthor);
            stmt.setString(3, isbn);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book with this ISBN not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }
}