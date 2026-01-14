import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    // === НАСТРОЙКИ ПОДКЛЮЧЕНИЯ ===
    // 1. Убедись, что имя базы данных (library_db) совпадает с тем, что ты создал в pgAdmin
    // 2. Впиши свой пароль вместо YOUR_PASSWORD
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
            System.out.println("✅ User saved to database!");

        } catch (SQLException e) {
            System.out.println("❌ Error adding user: " + e.getMessage());
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
            System.out.println("❌ Error loading users: " + e.getMessage());
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
                System.out.println("✅ User deleted.");
            } else {
                System.out.println("⚠️ User not found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error deleting user: " + e.getMessage());
        }
    }
}