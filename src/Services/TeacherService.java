package Services;

import Dao.TeacherDAO;
import Models.Teacher;
import Models.Teachers;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TeacherService {
    private final TeacherDAO teacherDAO;
    Scanner sc = new Scanner(System.in);

    public TeacherService() {
        try {
            this.teacherDAO = new TeacherDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authT() throws  SQLException{
        TeacherDAO tu = new TeacherDAO();

        System.out.println("enter name for Teacher login...");
        String name = sc.nextLine();

        try{
            Teacher rs = tu.authTeacher(name);
            System.out.println("");
            if(rs!=null){
                System.out.println("Welcome"+rs.getName()+"...!");
                return true;
            }
            return false;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }


    }

    public void addTeacher(Teacher teacher) {
        try {
            teacherDAO.addTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teachers> listTeachers() {
        try {
            return teacherDAO.listTeachers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

