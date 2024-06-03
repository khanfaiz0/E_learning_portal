package Dao;

import Models.Teacher;
import Models.User;
import Utilities.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection connection;

    public UserDao() throws SQLException {
        this.connection = Dbutil.getConnection();
    }

    public User authUser(String name) throws SQLException{
        String query = "SELECT * FROM users WHERE name = ?";
        User newUser = null;
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int i = rs.getInt("id");
                String n = rs.getString("name");
                String e = rs.getString("email");

                newUser = new User(n,e,"student");
            }else{
                System.out.println("Invalid user details");
            }

        }
        return newUser;
    }


    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET name = ?, email = ?, role = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());
            int result = statement.executeUpdate();
            if(result > 0){
                System.out.println("User Updated Successfully!");
            }
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            int result = statement.executeUpdate();
            if(result > 0){
                System.out.println("User Deleted Successfully!");
            }
        }
    }

    public List<User> listUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("role")
                ));
            }
        }
        return users;
    }
}

