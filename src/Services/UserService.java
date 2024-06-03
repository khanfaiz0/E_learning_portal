package Services;

import Dao.TeacherDAO;
import Dao.UserDao;
import Models.Teacher;
import Models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private final UserDao userDao;
    Scanner sc = new Scanner(System.in);

    public boolean authU() throws  SQLException{

        UserDao su = new UserDao();
        System.out.println("enter name for Student login...");
        String name = sc.nextLine();

        try{
            User rs = su.authUser(name);
            System.out.println("");
            System.out.println("Welcome "+rs.getName()+"...!");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }


    }



    public UserService() {
        try {
            this.userDao = new UserDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dltUser(int id) throws SQLException{
        userDao.deleteUser(id);

    }

    public void updateUser(User user) throws SQLException{
        userDao.updateUser(user);
    }

    public List<User> listUsers() {
        try {
            return userDao.listUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

