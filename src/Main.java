import Services.TeacherService;
import Services.UserService;
import Utilities.Dbutil;
import java.util.Scanner;

import java.sql.SQLException;

public class Main {

    public static void teacherMain() throws SQLException {
        Scanner sc = new Scanner(System.in);
        CLI cli = new CLI();
        System.out.println("--------------------------------");
        System.out.println("1. Add User");
        System.out.println("2. Update User");
        System.out.println("3. Delete User");
        System.out.println("4. View All Users");
        System.out.println("5. Add Subject");
        System.out.println("6. View all Subjects");
        System.out.println("7. Add Courses");
        System.out.println("8. Delete Courses");

        System.out.println("9. List Courses");
        System.out.println("10. Enroll Student");
        System.out.println("11. View all Enrolled Students");

        System.out.println("12. Add Teacher");
        System.out.println("13. View all Teachers");

        System.out.println("0. Logout");

        int input1 = sc.nextInt();
        if(input1 == 0){
            Main.main(null);
        }
        cli.handleCommand(input1);
    }

    public static void studentMain() throws SQLException {
        Scanner sc = new Scanner(System.in);
        CLI cli = new CLI();
        System.out.println("--------------------------------");
        System.out.println("1. Add User");
        System.out.println("2. View all Subjects");
        System.out.println("3. View all Courses");
        System.out.println("4. Enroll Students");
        System.out.println("5. View all Teachers");


        System.out.println("0. Logout");

        int input1 = sc.nextInt();
        if(input1 == 0){
            Main.main(null);
        }
        cli.userCommand(input1);
    }


    public static void launch() throws SQLException {
        Scanner sc = new Scanner(System.in);
        TeacherService ts = new TeacherService();
        UserService us = new UserService();
        System.out.println("WELCOME TO THE LEARNING APPLICATION");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("Please enter a valid input to do the following operations:-");
        System.out.println("1. Faculty Login");
        System.out.println("2. Student Login");
        System.out.println("3. Exit the application");
        int input = sc.nextInt();
        switch (input) {
            case 1:

                if (ts.authT()) {
                    teacherMain();
                }
                while (!ts.authT()) {
                    return;
                }
                break;

            case 2:

                if (us.authU()) {
                    studentMain();
                }

                while (!us.authU()) {
//                    vendorAuthentication();
                }
                break;

            case 3:
                System.out.println("Thanks for visiting ,");
                System.out.println("Have a great day.....!!");
                System.exit(0);

            default:
                System.out.println("Please enter a valid input");
        }
    }


    public static void main(String[] args) throws SQLException {
        Main.launch();
    }
}