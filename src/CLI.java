import Models.Course;
import Models.Subject;
import Models.Teacher;
import Models.User;
import Services.*;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CLI {
    private final UserService userService = new UserService();
    private final SubjectService subjectService = new SubjectService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final TeacherService teacherService = new TeacherService();
    private final Scanner scanner = new Scanner(System.in);

    public void handleCommand(int command) throws SQLException {
        switch (command) {
            case 1:
                addUser();
                Main.teacherMain();
                break;
            case 2:
                updateUser();
                Main.teacherMain();
                break;
            case 3:
                dltUser();
                Main.teacherMain();
                break;

            case 4:
                listUsers();
                Main.teacherMain();
                break;
            case 5:
                addSubject();
                Main.teacherMain();
                break;
            case 6:
                listSubjects();
                Main.teacherMain();
                break;
            case 7:
                addCourse();
                Main.teacherMain();
                break;
            case 8:
                dltcourse();
                Main.teacherMain();
            case 9:
                listCourses();
                Main.teacherMain();
                break;
            case 10:
                enrollStudent();
                Main.teacherMain();
                break;
            case 11:
                listEnrollments();
                Main.teacherMain();
            case 12:
                addTeacher();
                Main.teacherMain();
                break;
            case 13:
                listTeachers();
                Main.teacherMain();
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    public void userCommand(int command) throws SQLException {
        switch (command) {
            case 1:
                addUser();
                Main.studentMain();
                break;
            case 2:
                listSubjects();
                Main.studentMain();
                break;
            case 3:
                listCourses();
                Main.studentMain();
                break;
            case 4:
                enrollStudent();
                Main.studentMain();
                break;
            case 5:
                listTeachers();
                Main.studentMain();
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private void addUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter role (STUDENT/TUTOR): ");
        String role = scanner.nextLine();
        userService.addUser(new User(name, email, role));
    }

    private void dltUser() throws SQLException{
        System.out.println("Enter id of the user:");
        int id = parseInt(scanner.nextLine());
        userService.dltUser(id);
    }

    private void updateUser() throws SQLException {
        System.out.println("Enter details to update:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter role (STUDENT/TUTOR): ");
        String role = scanner.nextLine();
        userService.updateUser(new User(name, email, role));

    }

    private void listUsers() {
        userService.listUsers().forEach(System.out::println);
    }

    private void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        subjectService.addSubject(new Subject(name, description));
    }

    private void listSubjects() {
        subjectService.listSubjects().forEach(System.out::println);
    }

    private void addCourse() {
        listSubjects();
        System.out.print("Enter subject ID from above table: ");
        int subjectId = scanner.nextInt();
        System.out.print("Enter tutor ID: ");
        int tutorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter schedule (YYYY-MM-DD HH:MM:SS): ");
        String schedule = scanner.nextLine();
        courseService.addCourse(new Course(subjectId, tutorId, schedule));
    }

    private void dltcourse() throws SQLException{
        System.out.println("Enter Course Id to delete Course:");
        int id = scanner.nextInt();
        courseService.dltcourse(id);
    }

    private void listCourses() {
        // Define the format for the table
        String format = "| %-5s | %-10s | %-15s | %-20s |%n";

        // Print the table header
        System.out.format("+-------+------------+-----------------+----------------------+%n");
        System.out.format("| ID    | Sub_ID     |  Tutor_ID       | Schedule             |%n");
        System.out.format("+-------+------------+-----------------+----------------------+%n");

        courseService.listCourses().forEach(e->{
            System.out.format(format, e.getId(), e.getSubjectId(), e.getTutorId(), e.getSchedule());
        });
        System.out.format("+-------+------------+-----------------+----------------------+%n");

    }

    private void enrollStudent() {
        System.out.println("-------Course Table---------");
        listCourses();

        System.out.print("Choose Course id from above Course table: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter your student ID: ");
        int studentId = scanner.nextInt();
        enrollmentService.enrollStudent(courseId, studentId);
    }

    private void listEnrollments(){
        String format = "| %-15s | %-20s | %-20s |%n";

        // Print the table header
        System.out.format("+-----------------+----------------------+----------------------+%n");
        System.out.format("| Enrollment ID   | Course Name          | Student Name         |%n");
        System.out.format("+-----------------+----------------------+----------------------+%n");

        enrollmentService.listEnrollments().forEach(e ->{
            System.out.format(format,e.getId(),e.getSub_name(),e.getStudent_name());
        });
        System.out.format("+-----------------+----------------------+----------------------+%n");

    }

    private void addTeacher() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter subject ID: ");
        int subjectId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        teacherService.addTeacher(new Teacher(name, email, subjectId));
    }

    private void listTeachers() {
        // Define the format for the table
        String format = "| %-5s | %-12s | %-15s | %-18s |%n";

        // Print the table header
        System.out.format("+-------+--------------+-----------------+--------------------+%n");
        System.out.format("| ID    | Teacher Name | Email           | Subject            |%n");
        System.out.format("+-------+--------------+-----------------+--------------------+%n");


        teacherService.listTeachers().forEach( e -> {
            System.out.format(format, e.getId(), e.getName(), e.getEmail(), e.getSubject());

        });
        System.out.format("+-------+--------------+-----------------+--------------------+%n");

    }
}
