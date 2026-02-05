package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to create new table", e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to drop a table", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format(
                "INSERT INTO users (name, lastName, age) VALUES ('%s', '%s', %d)",
                name, lastName, age
        );
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save user", e);
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove user by id", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";

        try (
                Connection connection = Util.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get all users", e);
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to clean Users table", e);
        }
    }
}
