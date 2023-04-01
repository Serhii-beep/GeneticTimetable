package org.example;

public class Professor {
    private int professorId;
    private String name;

    public Professor(int professorId, String name) {
        this.professorId = professorId;
        this.name = name;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
