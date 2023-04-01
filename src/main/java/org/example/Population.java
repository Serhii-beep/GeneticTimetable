package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    private List<Individual> individuals;

    public Population() {
        individuals = new ArrayList<>();
    }

    public Population(int populationSize, List<Classroom> classrooms, List<Professor> professors, List<Group> groups, List<Timeslot> timeslots) {
        this();
        initializePopulation(populationSize, classrooms, professors, groups, timeslots);
    }

    public void initializePopulation(int populationSize, List<Classroom> classrooms, List<Professor> professors, List<Group> groups, List<Timeslot> timeslots) {
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(classrooms, professors, groups, timeslots);
            individuals.add(individual);
        }
    }

    public Individual selection(int tournamentSize) {
        Random random = new Random();
        List<Individual> tournamentIndividuals = new ArrayList<>();

        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = random.nextInt(individuals.size());
            tournamentIndividuals.add(individuals.get(randomIndex));
        }

        Individual bestIndividual = tournamentIndividuals.get(0);
        for (int i = 1; i < tournamentSize; i++) {
            if (tournamentIndividuals.get(i).getFitness() > bestIndividual.getFitness()) {
                bestIndividual = tournamentIndividuals.get(i);
            }
        }

        return bestIndividual;
    }

    public Individual crossover(Individual parent1, Individual parent2) {
        Random random = new Random();
        List<ScheduledClass> offspringScheduledClasses = new ArrayList<>();

        for (int i = 0; i < parent1.getScheduledClasses().size(); i++) {
            ScheduledClass parent1Class = parent1.getScheduledClasses().get(i);
            ScheduledClass parent2Class = parent2.getScheduledClasses().get(i);

            if (random.nextBoolean()) {
                offspringScheduledClasses.add(new ScheduledClass(parent1Class.getSubject(), parent1Class.getGroup(), parent1Class.getProfessor(), parent1Class.getClassroom(), parent1Class.getTimeslot()));
            } else {
                offspringScheduledClasses.add(new ScheduledClass(parent2Class.getSubject(), parent2Class.getGroup(), parent2Class.getProfessor(), parent2Class.getClassroom(), parent2Class.getTimeslot()));
            }
        }

        Individual offspring = new Individual();
        offspring.setScheduledClasses(offspringScheduledClasses);

        return offspring;
    }

    public void mutate(Individual individual, double mutationRate, List<Classroom> classrooms, List<Professor> professors, List<Timeslot> timeslots) {
        Random random = new Random();

        for (int i = 0; i < individual.getScheduledClasses().size(); i++) {
            if (random.nextDouble() < mutationRate) {
                ScheduledClass scheduledClass = individual.getScheduledClasses().get(i);

                scheduledClass.setProfessor(professors.get(random.nextInt(professors.size())));
                scheduledClass.setClassroom(classrooms.get(random.nextInt(classrooms.size())));
                scheduledClass.setTimeslot(timeslots.get(random.nextInt(timeslots.size())));
            }
        }
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }
}

