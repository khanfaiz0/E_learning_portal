package Models;

public class Enrollment {
    private int id;
    private int courseId;
    private int studentId;

    public Enrollment() {}

    public Enrollment(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Enrollment(int id, int courseId, int studentId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}

