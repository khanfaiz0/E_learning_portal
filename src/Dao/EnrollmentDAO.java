package Dao;

import Models.Enrollment;
import Models.Enrollments;
import Utilities.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    private final Connection connection;

    public EnrollmentDAO() throws SQLException {
        this.connection = Dbutil.getConnection();
    }

    public void enrollStudent(int courseId, int studentId) throws SQLException {
        String query = "INSERT INTO enrollments (course_id, student_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        }
    }

    public List<Enrollments> listEnrollments() throws SQLException {
        List<Enrollments> enrollments = new ArrayList<>();
        String query1 = "SELECT \n" +
                "    a.id AS e_id, \n" +
                "    s.name AS subject_name, \n" +
                "    c.name AS student_name\n" +
                "FROM \n" +
                "    enrollments AS a\n" +
                "JOIN \n" +
                "    courses AS b ON a.course_id = b.id\n" +
                "JOIN \n" +
                "    users AS c ON a.student_id = c.id\n" +
                "JOIN \n" +
                "    subjects AS s ON b.subject_id = s.id";
        String query = "SELECT * FROM enrollments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query1)) {

                while (resultSet.next()) {
                    enrollments.add(new Enrollments(
                            resultSet.getInt("e_id"),
                            resultSet.getString("subject_name"),
                            resultSet.getString("student_name")
                    ));
                }
            if (enrollments.isEmpty()) {
                System.out.println("No enrolled students found...");
            }


        }
        return enrollments;
    }
}

