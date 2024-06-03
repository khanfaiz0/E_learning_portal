package Services;

import Dao.CourseDAO;
import Models.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseService {
    private CourseDAO courseDAO;

    public CourseService() {
        try {
            this.courseDAO = new CourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCourse(Course course) {
        try {
            courseDAO.addCourse(course);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dltcourse(int id) throws SQLException{
        courseDAO.deleteCourse(id);
    }

    public List<Course> listCourses() {
        try {
            return courseDAO.listCourses();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

