package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {
    private List<ScheduledClass> scheduledClasses;
    private double fitness;

    public Individual() {
        scheduledClasses = new ArrayList<>();
    }

    public Individual(List<Classroom> classrooms, List<Professor> professors, List<Group> groups, List<Timeslot> timeslots) {
        this();
        generateRandomIndividual(classrooms, professors, groups, timeslots);
    }

    public void generateRandomIndividual(List<Classroom> classrooms, List<Professor> professors, List<Group> groups, List<Timeslot> timeslots) {
        Random random = new Random();
        for(Group group : groups) {
            int subjectsPerGroup = group.getSubjects().size();
            int availableTimeslots = timeslots.size();
            int subjectsToAssign = Math.min(subjectsPerGroup, availableTimeslots);
            for(int i = 0; i < subjectsToAssign; ++i) {
                Subject subject = group.getSubjects().get(i);
                List<Professor> availableProfessors = new ArrayList<>();
                for(Professor professor : professors) {
                    if(professor.getProfessorId() == subject.getProfessorId()) {
                        availableProfessors.add(professor);
                    }
                }

                Professor selectedProfessor = availableProfessors.get(random.nextInt(availableProfessors.size()));

                List<Classroom> availableClassrooms = new ArrayList<>();
                for(Classroom classroom : classrooms) {
                    if(classroom.getCapacity() >= group.getSize()) {
                        availableClassrooms.add(classroom);
                    }
                }

                Classroom selectedClassroom = availableClassrooms.get(random.nextInt(availableClassrooms.size()));
                Timeslot selectedTimeslot = timeslots.get(random.nextInt(timeslots.size()));

                ScheduledClass scheduledClass = new ScheduledClass(subject, group, selectedProfessor, selectedClassroom, selectedTimeslot);
                scheduledClasses.add(scheduledClass);
            }
        }
    }

    public double calculateFitness() {
        int constraintViolations = 0;
        for(int i = 0; i < scheduledClasses.size() - 1; ++i) {
            ScheduledClass classA = scheduledClasses.get(i);
            if(classA.getClassroom().getCapacity() < classA.getGroup().getSize())
                ++constraintViolations;

            for(int j = i + 1; j < scheduledClasses.size(); ++j) {
                ScheduledClass classB = scheduledClasses.get(j);
                if(classA.getGroup().getGroupId() == classB.getGroup().getGroupId()
                    && classA.getTimeslot().getTimeslotId() == classB.getTimeslot().getTimeslotId()
                    && classA.getSubject().getSubjectId() != classB.getSubject().getSubjectId())
                    ++constraintViolations;

                if(classA.getProfessor().getProfessorId() == classB.getProfessor().getProfessorId()
                    && classA.getTimeslot().getTimeslotId() == classB.getTimeslot().getTimeslotId())
                    ++constraintViolations;
            }
        }
        fitness = 1.0 / (1.0 + constraintViolations);
        return fitness;
    }

    public List<ScheduledClass> getScheduledClasses() {
        return scheduledClasses;
    }

    public void setScheduledClasses(List<ScheduledClass> scheduledClasses) {
        this.scheduledClasses = scheduledClasses;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}

