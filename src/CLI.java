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
                updateCourse();
                Main.teacherMain();
            case 11:
                enrollStudent();
                Main.teacherMain();
                break;
            case 12:
                listEnrollments();
                Main.teacherMain();
            case 13:
                addTeacher();
                Main.teacherMain();
                break;
            case 14:
                listTeachers();
                Main.teacherMain();
                break;
            case 15:
                dltTeacher();
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
        String format = "| %-15s | %-20s | %-20s |%n";

        // Print the table header
        System.out.format("+-----------------+----------------------+----------------------+%n");
        System.out.format("| Student ID      | student Name         | Student Email        |%n");
        System.out.format("+-----------------+----------------------+----------------------+%n");

        userService.listUsers().forEach(e->{
            System.out.format(format,e.getId(),e.getName(),e.getEmail());
        });
        System.out.format("+-----------------+----------------------+----------------------+%n");

    }

    private void addSubject() {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        subjectService.addSubject(new Subject(name, description));
    }



    private void listSubjects() {
        String format = "| %-15s | %-20s | %-35s |%n";

        // Print the table header
        System.out.format("+-----------------+----------------------+-------------------------------------+%n");
        System.out.format("| Subject ID      | Subject Name         | Subject Description                 |%n");
        System.out.format("+-----------------+----------------------+-------------------------------------+%n");

        subjectService.listSubjects().forEach(e->{
            System.out.format(format,e.getId(),e.getName(),e.getDescription());
        });
        System.out.format("+-----------------+----------------------+-------------------------------------+%n");

    }

    private void addCourse() {
        listSubjects();
        System.out.print("Enter subject ID from above table: ");
        int subjectId = scanner.nextInt();
        listTeachers();
        System.out.print("Enter tutor ID from above table: ");
        int tutorId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter schedule (YYYY-MM-DD HH:MM:SS): ");
        String schedule = scanner.nextLine();
        courseService.addCourse(new Course(subjectId, tutorId, schedule));
    }

    private void updateCourse() throws SQLException{
        listCourses();
        System.out.println("Enter Course Id from above table to update it");
        int id = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Choose subject id from below table");
        listSubjects();
        int sub_id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("choose tutor id from below table");
        listTeachers();;
        int tutor_id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter schedule (YYYY-MM-DD HH:MM:SS):");
        String schedule = scanner.nextLine();
        courseService.updateCourse(new Course(id,sub_id,tutor_id,schedule));




    }

    private void dltcourse() throws SQLException{
        listCourses();
        System.out.println("Enter Course Id from above table to delete Course:");
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

    private void addTeacher() throws SQLException {
        Subject sub = null;
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter subject ID: ");
        int subjectId = scanner.nextInt();
        sub = subjectService.getSubbyid(subjectId);
        if(sub == null){
            System.out.println("Enter a valid subject Id for Teacher.");
            return;
        }
        scanner.nextLine(); // consume newline
        teacherService.addTeacher(new Teacher(name, email, subjectId));
    }

    private void dltTeacher() throws SQLException{
        listTeachers();
        System.out.println("select teacher id from above table to delete");
        int id = scanner.nextInt();
        teacherService.dltteacher(id);
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
