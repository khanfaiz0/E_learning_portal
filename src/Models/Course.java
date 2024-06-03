package Models;

public class Course {
    private int id;
    private int subjectId;
    private int tutorId;
    private String schedule;

    public Course() {}

    public Course(int subjectId, int tutorId, String schedule) {
        this.subjectId = subjectId;
        this.tutorId = tutorId;
        this.schedule = schedule;
    }

    public Course(int id, int subjectId, int tutorId, String schedule) {
        this.id = id;
        this.subjectId = subjectId;
        this.tutorId = tutorId;
        this.schedule = schedule;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", tutorId=" + tutorId +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}

