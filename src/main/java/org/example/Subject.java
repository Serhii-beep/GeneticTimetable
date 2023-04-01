package org.example;

public class Subject {
    private int subjectId;
    private String name;
    private boolean isLecture;
    private int professorId;

    public Subject(int subjectId, String name, boolean isLecture, int professorId) {
        this.subjectId = subjectId;
        this.name = name;
        this.isLecture = isLecture;
        this.professorId = professorId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLecture() {
        return isLecture;
    }

    public void setLecture(boolean lecture) {
        isLecture = lecture;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}
