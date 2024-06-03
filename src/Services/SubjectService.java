package Services;

import Dao.SubjectDAO;
import Models.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    private final SubjectDAO subjectDAO;

    public SubjectService() {
        try {
            this.subjectDAO = new SubjectDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addSubject(Subject subject) {
        try {
            subjectDAO.addSubject(subject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> listSubjects() {
        try {
            return subjectDAO.listSubjects();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

