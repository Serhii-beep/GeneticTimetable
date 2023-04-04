package org.example;

import java.util.List;

public class Group {
    private int groupId;
    private int size;
    private String name;
    private List<Subject> subjects;

    public Group(int groupId, int size, String name, List<Subject> subjects) {
        this.groupId = groupId;
        this.size = size;
        this.name = name;
        this.subjects = subjects;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


