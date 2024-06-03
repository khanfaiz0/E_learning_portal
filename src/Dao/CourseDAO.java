package Dao;

import Models.Course;
import Utilities.Dbutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private final Connection connection;

    public CourseDAO() throws SQLException {
        this.connection = Dbutil.getConnection();
    }

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (subject_id, tutor_id, schedule) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, course.getSubjectId());
            statement.setInt(2, course.getTutorId());
            statement.setString(3, course.getSchedule());
            statement.executeUpdate();
        }
    }

    public void deleteCourse(int courseId) throws SQLException {
        String deleteEnrollmentsQuery = "DELETE FROM enrollments WHERE course_id = ?";
        String deleteCourseQuery = "DELETE FROM courses WHERE id = ?";

        try (PreparedStatement deleteEnrollmentsStatement = connection.prepareStatement(deleteEnrollmentsQuery);
             PreparedStatement deleteCourseStatement = connection.prepareStatement(deleteCourseQuery)) {

            // First delete related records from enrollments table
            deleteEnrollmentsStatement.setInt(1, courseId);
            deleteEnrollmentsStatement.executeUpdate();

            // Then delete the course record
            deleteCourseStatement.setInt(1, courseId);
            int result = deleteCourseStatement.executeUpdate();
            if(result>0){
                System.out.println("Course and related enrollments deleted successfully!");
            } else if (result == 0) {
                System.out.println("Course id is not found...." +
                        "\n Please try with another id.."
                );
            }
        }
    }


    public List<Course> listCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getInt("subject_id"),
                        resultSet.getInt("tutor_id"),
                        resultSet.getString("schedule")
                ));
            }
        }
        return courses;
    }
}

