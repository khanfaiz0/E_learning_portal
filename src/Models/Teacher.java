package Models;

public class Teacher {
    private int id;
    private String name;
    private String email;
    private int subjectId;

    public Teacher() {}

    public Teacher(String name, String email, int subjectId) {
        this.name = name;
        this.email = email;
        this.subjectId = subjectId;
    }

    public Teacher(int id, String name, String email, int subjectId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subjectId = subjectId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subjectId=" + subjectId +
                '}';
    }
}

