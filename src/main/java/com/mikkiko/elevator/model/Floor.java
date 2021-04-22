package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Floors between {@link Elevator} transporting {@link Person}.
 */
public class Floor implements Comparable<Floor> {

    private final int floor;
    private final List<Person> people = new ArrayList<>();
    private final List<Person> newPeople = new ArrayList<>();

    public Floor(int floor, int maxFloor) {
        this.floor = floor;
        int random = RandomHelper.getInt(10);
        for (int i = 0; i < random; i++) {
            people.add(new Person(floor, maxFloor));
        }
    }

    public Person getPerson(Direction direction) {
        List<Person> resultPeople = null;

        if (direction.equals(Direction.UP))
            resultPeople = people.stream().filter(p -> p.getTargetFloor() > floor).collect(Collectors.toList());
        if (direction.equals(Direction.DOWN))
            resultPeople = people.stream().filter(p -> p.getTargetFloor() < floor).collect(Collectors.toList());

        if (resultPeople == null || resultPeople.isEmpty())
            return null;
        else {
            Person person = resultPeople.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            people.remove(person);
            return person;
        }
    }

    public void addPerson(Person person) {
        newPeople.add(person);
    }

    public void updatePeople() {
        if (!newPeople.isEmpty()) {
            newPeople.forEach(passenger -> passenger.updateTarget(floor));
            people.addAll(newPeople);
            newPeople.clear();
        }
    }

    @Override
    public int compareTo(Floor o) {
        return this.floor - o.floor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        people.forEach(builder::append);
        return builder.toString();
    }
}
