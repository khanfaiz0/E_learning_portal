package Models;

public class Enrollments {
    int id ;
    String sub_name;
    String Student_name;

    public Enrollments() {
    }

    public Enrollments(int id, String sub_name, String student_name) {
        this.id = id;
        this.sub_name = sub_name;
        Student_name = student_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }

    @Override
    public String toString() {
        return "Enrollments{" +
                "id=" + id +
                ", sub_name='" + sub_name + '\'' +
                ", Student_name='" + Student_name + '\'' +
                '}';
    }
}
