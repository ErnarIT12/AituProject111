package com.aitu.library.repository;

import com.aitu.library.exception.DatabaseOperationException;
import com.aitu.library.model.EBook;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostgresBookRepository implements BookRepository {

    @Override
    public void addBook(EBook book) {
        String query = "INSERT INTO books (title, author, isbn, available) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setBoolean(4, true); // Новая книга всегда доступна
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error adding book with ISBN: " + book.getIsbn(), e);
        }
    }

    @Override
    public List<EBook> getAllBooks() {
        List<EBook> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error retrieving all books", e);
        }
        return books;
    }

    @Override
    public Optional<EBook> getBookByIsbn(String isbn) {
        String query = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error finding book with ISBN: " + isbn, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteBook(String isbn) {
        String query = "DELETE FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting book with ISBN: " + isbn, e);
        }
    }

    @Override
    public boolean updateBook(String isbn, String newTitle, String newAuthor) {
        String query = "UPDATE books SET title = ?, author = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, newAuthor);
            stmt.setString(3, isbn);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating book with ISBN: " + isbn, e);
        }
    }

    @Override
    public List<EBook> searchBooks(String keyword) {
        List<EBook> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE LOWER(title) LIKE LOWER(?) OR LOWER(author) LIKE LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapRowToBook(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error searching books with keyword: " + keyword, e);
        }
        return books;
    }

    // --- НОВЫЕ МЕТОДЫ ---
    @Override
    public boolean isBookAvailable(String isbn) {
        String query = "SELECT available FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getBoolean("available");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error checking availability for book: " + isbn, e);
        }
    }

    @Override
    public void setBookAvailability(String isbn, boolean available) {
        String query = "UPDATE books SET available = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, available);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating availability for book: " + isbn, e);
        }
    }

    @Override
    public int countBooksBorrowedByUser(int userId) {
        String query = "SELECT COUNT(*) FROM borrowed_books WHERE user_id = ? AND return_date IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error counting borrowed books for user: " + userId, e);
        }
    }

    @Override
    public void recordBorrow(int userId, String isbn) {
        String query = "INSERT INTO borrowed_books (user_id, book_isbn) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error recording borrow for user: " + userId, e);
        }
    }

    @Override
    public void recordReturn(int userId, String isbn) {
        String query = "UPDATE borrowed_books SET return_date = CURRENT_TIMESTAMP WHERE user_id = ? AND book_isbn = ? AND return_date IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error recording return for user: " + userId, e);
        }
    }

    private EBook mapRowToBook(ResultSet rs) throws SQLException {
        String title = rs.getString("title");
        String author = rs.getString("author");
        String isbn = rs.getString("isbn");
        EBook book = new EBook(title, isbn, author);
        book.setAvailable(rs.getBoolean("available"));
        return book;
    }
}