package Services;

import Dao.EnrollmentDAO;
import Models.Enrollment;
import Models.Enrollments;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentService() {
        try {
            this.enrollmentDAO = new EnrollmentDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void enrollStudent(int courseId, int studentId) {
        try {
            enrollmentDAO.enrollStudent(courseId, studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enrollments> listEnrollments() {
        try {
            return enrollmentDAO.listEnrollments();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

