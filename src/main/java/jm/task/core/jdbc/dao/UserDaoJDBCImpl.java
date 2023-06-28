package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private final String sqlCreateTable = "CREATE TABLE IF NOT EXISTS USERS" +
            "(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            "NAME VARCHAR(45) NOT NULL," +
            "LASTNAME VARCHAR(45) NOT NULL," +
            "AGE TINYINT(255) NOT NULL)";
    private final String sqlDropTable = "DROP table if exists users";
    private final String sqlSaveUser = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
    private final String sqlDeleteById = "DELETE FROM users WHERE id = ?";
    private final String sqlSelectAll = "SELECT * FROM users";
    private final String sqlCleanTable = "DELETE FROM users";


    public void createUsersTable() {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateTable);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlDropTable);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();
        try (connection) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlSaveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = getConnection();
        try (connection) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectAll)) {
            ResultSet resultSet = preparedStatement.executeQuery(sqlSelectAll);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = getConnection();
        try (connection) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlCleanTable);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
