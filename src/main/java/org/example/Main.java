package org.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Classroom> classrooms = createClassrooms();
        List<Professor> professors = createProfessors();
        List<Group> groups = createGroups();
        List<Timeslot> timeslots = createTimeslots();

        int populationSize = 100;
        int maxGenerations = 1000;
        int tournamentSize = 5;
        double crossoverRate = 0.8;
        double mutationRate = 0.2;

        Population population = new Population(populationSize, classrooms, professors, groups, timeslots);

        int generation = 1;
        while (generation <= maxGenerations) {
            Population newPopulation = new Population();

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
            System.out.println("Generation: " + generation);
            int c = 1;
            for(Individual i : population.getIndividuals()) {
                System.out.println("Individual " + c + ", fitness: " + i.getFitness());
                ++c;
            }
            System.out.println("\n==========================================================\n");
            generation++;
        }

        Individual bestSolution = population.getIndividuals().stream().max((i1, i2) -> Double.compare(i1.getFitness(), i2.getFitness())).orElse(null);
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        if (bestSolution != null) {
            out.println("Best timetable solution found with fitness: " + bestSolution.getFitness());
            printTimetable(bestSolution, out);
        } else {
            out.println("No timetable solution found.");
        }
    }

    private static void printTimetable(Individual bestIndividual, PrintStream out) {
        out.println("Timetable:");

        List<ScheduledClass> sortedScheduledClasses = new ArrayList<>(bestIndividual.getScheduledClasses());
        sortedScheduledClasses.sort(Comparator.comparing(scheduledClass -> scheduledClass.getTimeslot().getDayOfWeek()));

        out.printf("%-45s %-12s %-20s %-15s %-20s %-20s%n", "Subject", "Type", "Professor", "Group", "Classroom", "Timeslot");
        out.println("--------------------------------------------------------------------------------------------------------------------------------");

        for (ScheduledClass scheduledClass : sortedScheduledClasses) {
            String subjectName = scheduledClass.getSubject().getName();
            String subjectType = scheduledClass.getSubject().isLecture() ? "lecture" : "practice";
            String professorName = scheduledClass.getProfessor().getName();
            String groupName = "Group " + scheduledClass.getGroup().getName();
            String classroomName = "Classroom " + scheduledClass.getClassroom().getName();
            String timeslotInfo = scheduledClass.getTimeslot().getDayOfWeek() + " " + scheduledClass.getTimeslot().getStartTime() + "-" + scheduledClass.getTimeslot().getEndTime();

            out.printf("%-45s %-12s %-20s %-15s %-20s %-20s%n", subjectName, subjectType, professorName, groupName, classroomName, timeslotInfo);
        }
    }



    private static List<Classroom> createClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom(1, "306", 100));
        classrooms.add(new Classroom(2, "43", 70));
        classrooms.add(new Classroom(3, "306", 40));
        classrooms.add(new Classroom(4, "221", 40));
        classrooms.add(new Classroom(5, "01", 100));
        classrooms.add(new Classroom(6, "203", 32));
        classrooms.add(new Classroom(7, "202", 32));
        return classrooms;
    }

    private static List<Professor> createProfessors() {
        List<Professor> professors = new ArrayList<>();
        professors.add(new Professor(1, "Verhunova I.M."));
        professors.add(new Professor(2, "Bohuslavskii O.V."));
        professors.add(new Professor(3, "Panchenko T.V."));
        professors.add(new Professor(4, "Bohdan I.A."));
        professors.add(new Professor(5, "Hlybovets M.M."));
        professors.add(new Professor(6, "Tkachenko O.M."));
        professors.add(new Professor(7, "Fedorus O.M."));
        professors.add(new Professor(8, "Kryvolap A.V."));
        professors.add(new Professor(9, "Shyshatska O.V."));
        professors.add(new Professor(10, "Nikitchenko M.S."));
        return professors;
    }

    private static List<Group> createGroups() {
        List<Group> groups = new ArrayList<>();
        List<Subject> subjects = createSubjects();

        List<Subject> group1Subjects = new ArrayList<>();
        group1Subjects.addAll(subjects);
        Group g1 = new Group(1, 27, "TTP-41", group1Subjects);
        groups.add(g1);

        List<Subject> group2Subjects = new ArrayList<>();
        group2Subjects.addAll(subjects);
        Group g2 = new Group(2, 30, "TTP-42", group2Subjects);
        groups.add(g2);

        return groups;
    }

    private static List<Subject> createSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, "Informatsiini technolohii menedzmentu", true, 1));
        subjects.add(new Subject(2, "Vybranu rozdily trudovoho prava", true, 2));
        subjects.add(new Subject(3, "Osnovy pidpryiemnytskoi diialnosti", true, 4));
        subjects.add(new Subject(4, "Kompozytsiina semantyka SQL-podibnych mov", true, 10));
        subjects.add(new Subject(5, "Rozrobka biznes-analitychnych system", true, 3));
        subjects.add(new Subject(6, "Intelektualni systemy", true, 5));
        subjects.add(new Subject(7, "Intelektualni systemy", false, 7));
        subjects.add(new Subject(8, "Korektnist prohram ta lohiky prohramuvannia", false, 6));
        subjects.add(new Subject(9, "Osnovy Data Mining", true, 8));
        subjects.add(new Subject(10, "Metody specyficatsii prohram", true, 9));
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