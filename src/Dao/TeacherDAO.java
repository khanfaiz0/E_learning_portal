package Dao;

import Models.Teacher;
import Models.Teachers;
import Utilities.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private final Connection connection;

    public TeacherDAO() throws SQLException {
        this.connection = Dbutil.getConnection();
    }

    public Teacher authTeacher(String name) throws SQLException{
        String query = "SELECT * FROM teachers WHERE name = ?";
        Teacher newTecher = null;
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int i = rs.getInt("id");
                String n = rs.getString("name");
                String e = rs.getString("email");

                newTecher = new Teacher(n,e,i);
            }else{
                System.out.println("Invalid user details");
            }

        }
        return newTecher;
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        String query = "INSERT INTO teachers (name, email, subject_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getEmail());
            statement.setInt(3, teacher.getSubjectId());
            statement.executeUpdate();
        }
    }

    public List<Teachers> listTeachers() throws SQLException {
        List<Teachers> teachers = new ArrayList<>();
        String query = "SELECT a.id, a.name,a.email,b.name AS subject FROM teachers AS a, subjects AS b WHERE a.subject_id = b.id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                teachers.add(new Teachers(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("subject")
                ));
            }
        }
        return teachers;
    }
}

