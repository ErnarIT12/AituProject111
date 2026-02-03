package com.aitu.library.repository;

import com.aitu.library.exception.DatabaseOperationException;
import com.aitu.library.model.LibraryUser;
import com.aitu.library.model.Student;
import com.aitu.library.model.Teacher;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostgresUserRepository implements UserRepository {

    @Override
    public void addUser(LibraryUser user) {
        String query = "INSERT INTO users (username, user_type, year_of_study, department) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
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
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error adding user: " + user.getName(), e);
        }
    }

    @Override
    public List<LibraryUser> getAllUsers() {
        List<LibraryUser> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error retrieving all users", e);
        }
        return users;
    }

    @Override
    public Optional<LibraryUser> getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error finding user with ID: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting user with ID: " + id, e);
        }
    }

    @Override
    public boolean updateUser(int id, String newName) {
        String query = "UPDATE users SET username = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating user with ID: " + id, e);
        }
    }

    private LibraryUser mapRowToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("username");
        String type = rs.getString("user_type");

        if ("STUDENT".equals(type)) {
            int year = rs.getInt("year_of_study");
            return new Student(id, name, year);
        } else if ("TEACHER".equals(type)) {
            String dept = rs.getString("department");
            return new Teacher(id, name, dept);
        }
        // Если тип неизвестен, выбрасываем ошибку, так как создать абстрактный LibraryUser нельзя
        throw new DatabaseOperationException("Unknown user type in database: " + type, null);
    }
}