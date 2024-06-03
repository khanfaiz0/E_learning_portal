package Dao;

import Models.Subject;
import Utilities.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private final Connection connection;

    public SubjectDAO() throws SQLException {
        this.connection = Dbutil.getConnection();
    }

    public void addSubject(Subject subject) throws SQLException {
        String query = "INSERT INTO subjects (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, subject.getName());
            statement.setString(2, subject.getDescription());
            statement.executeUpdate();
        }
    }

    public List<Subject> listSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subjects";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                subjects.add(new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }
        }
        return subjects;
    }
}

