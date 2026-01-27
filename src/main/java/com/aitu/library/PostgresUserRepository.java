package com.aitu.library;

import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresUserRepository implements com.aitu.library.UserRepository {

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String newName) {
        String query = "UPDATE users SET username = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}