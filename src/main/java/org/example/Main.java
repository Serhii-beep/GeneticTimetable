package org.example;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize problem data (classrooms, professors, groups, timeslots, and subjects)
        List<Classroom> classrooms = createClassrooms();
        List<Professor> professors = createProfessors();
        List<Group> groups = createGroups();
        List<Timeslot> timeslots = createTimeslots();

        // Genetic algorithm parameters
        int populationSize = 100;
        int maxGenerations = 1000;
        int tournamentSize = 5;
        double crossoverRate = 0.8;
        double mutationRate = 0.2;

        // Create initial population
        Population population = new Population(populationSize, classrooms, professors, groups, timeslots);

        // Iterate through the genetic algorithm steps
        int generation = 1;
        while (generation <= maxGenerations) {
            Population newPopulation = new Population();

            // Selection, crossover, and mutation
            for (int i = 0; i < populationSize; i++) {
                Individual parent1 = population.selection(tournamentSize);
                Individual parent2 = population.selection(tournamentSize);

                if (Math.random() < crossoverRate) {
                    Individual offspring = population.crossover(parent1, parent2);
                    population.mutate(offspring, mutationRate, classrooms, professors, timeslots);
                    offspring.calculateFitness();

                    newPopulation.getIndividuals().add(offspring);
                } else {
                    newPopulation.getIndividuals().add(parent1);
                }
            }

            population = newPopulation;
            generation++;
        }

        // Find the best timetable solution in the final population
        Individual bestSolution = population.getIndividuals().stream().max((i1, i2) -> Double.compare(i1.getFitness(), i2.getFitness())).orElse(null);

        if (bestSolution != null) {
            System.out.println("Best timetable solution found with fitness: " + bestSolution.getFitness());
            printTimetable(bestSolution);
        } else {
            System.out.println("No timetable solution found.");
        }
    }

    private static void printTimetable(Individual bestIndividual) {
        System.out.println("Timetable:");
        List<ScheduledClass> sortedScheduledClasses = new ArrayList<>(bestIndividual.getScheduledClasses());
        sortedScheduledClasses.sort(Comparator.comparing(scheduledClass -> scheduledClass.getTimeslot().getDayOfWeek()));

        for (ScheduledClass scheduledClass : sortedScheduledClasses) {
            String subjectName = scheduledClass.getSubject().getName();
            String subjectType = scheduledClass.getSubject().isLecture() ? "lecture" : "practice";
            String professorName = scheduledClass.getProfessor().getName();
            String groupName = "Group " + scheduledClass.getGroup().getGroupId();
            String classroomName = "Classroom " + scheduledClass.getClassroom().getRoomId();
            String timeslotInfo = scheduledClass.getTimeslot().getDayOfWeek() + " " + scheduledClass.getTimeslot().getStartTime() + "-" + scheduledClass.getTimeslot().getEndTime();

            System.out.println(String.format("%s (%s) - %s - %s - %s - %s", subjectName, subjectType, professorName, groupName, classroomName, timeslotInfo));
        }
    }


    private static List<Classroom> createClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1, 30));
        classrooms.add(new Classroom(2, 25));
        classrooms.add(new Classroom(3, 40));
        classrooms.add(new Classroom(4, 20));
        return classrooms;
    }

    private static List<Professor> createProfessors() {
        List<Professor> professors = new ArrayList<>();
        professors.add(new Professor(1, "Alice"));
        professors.add(new Professor(2, "Bob"));
        professors.add(new Professor(3, "Charlie"));
        professors.add(new Professor(4, "David"));
        return professors;
    }

    private static List<Group> createGroups() {
        List<Group> groups = new ArrayList<>();
        List<Subject> subjects = createSubjects();

        List<Subject> group1Subjects = new ArrayList<>();
        group1Subjects.add(subjects.get(0));
        group1Subjects.add(subjects.get(1));
        Group g1 = new Group(1, 25, group1Subjects);
        groups.add(g1);

        List<Subject> group2Subjects = new ArrayList<>();
        group2Subjects.add(subjects.get(2));
        group2Subjects.add(subjects.get(3));
        Group g2 = new Group(2, 30, group2Subjects);
        groups.add(g2);

        return groups;
    }

    private static List<Subject> createSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, "Mathematics", true, 1));
        subjects.add(new Subject(2, "Physics", false, 2));
        subjects.add(new Subject(3, "Chemistry", true, 3));
        subjects.add(new Subject(4, "Biology", false, 4));
        return subjects;
    }

    private static List<Timeslot> createTimeslots() {
        List<Timeslot> timeslots = new ArrayList<>();
        timeslots.add(new Timeslot(1, DayOfWeek.MONDAY, "9:00", "10:00"));
        timeslots.add(new Timeslot(2, DayOfWeek.MONDAY, "10:00", "11:00"));
        timeslots.add(new Timeslot(3, DayOfWeek.MONDAY, "11:00", "12:00"));
        timeslots.add(new Timeslot(4, DayOfWeek.TUESDAY, "9:00", "10:00"));
        timeslots.add(new Timeslot(5, DayOfWeek.TUESDAY, "10:00", "11:00"));
        timeslots.add(new Timeslot(6, DayOfWeek.TUESDAY, "11:00", "12:00"));
        return timeslots;
    }

}