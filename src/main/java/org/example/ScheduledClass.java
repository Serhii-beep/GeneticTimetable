package org.example;

public class ScheduledClass {
    private Subject subject;
    private Group group;
    private Professor professor;
    private Classroom classroom;
    private Timeslot timeslot;

    public ScheduledClass(Subject subject, Group group, Professor professor, Classroom classroom, Timeslot timeslot) {
        this.subject = subject;
        this.group = group;
        this.professor = professor;
        this.classroom = classroom;
        this.timeslot = timeslot;
    }

    // Add getters and setters for the attributes
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }
}

