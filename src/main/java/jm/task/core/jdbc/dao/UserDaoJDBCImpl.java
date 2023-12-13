package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name" +
                    " VARCHAR(40), lastName VARCHAR(40), age TINYINT)");
        } catch (SQLException e) {
            System.out.println("Issues while creating DB");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            System.out.println("Issues while Drop DB");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user(name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Issues while add saveUser");
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM user WHERE id = id");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Issues while removing user from table");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (ResultSet resultSet = connection.prepareStatement("SELECT*from user").executeQuery("SELECT*from" +
                " user")){
            while (resultSet.next()){
                list.add(new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e){
            System.out.println("Issues while getAllUsers method");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE user");
        } catch (SQLException e) {
            System.out.println("Issues while cleaning table");
        }
    }
}
