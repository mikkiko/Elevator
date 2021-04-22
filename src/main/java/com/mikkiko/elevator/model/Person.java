package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.Objects;

/**
 * {@link Elevator} user.
 */
public class Person implements Comparable<Person>{

    private final int MAX_FLOOR;
    private int currentFloor;
    private int targetFloor;

    public Person(int currentFloor, int maxFloor) {
        this.MAX_FLOOR = maxFloor;
        this.currentFloor = currentFloor;
        this.targetFloor = getRandomFloor();
    }

    public Integer getTargetFloor() {
        return targetFloor;
    }

    public void updateTarget(int currentFloor){
        this.currentFloor = currentFloor;
        targetFloor = getRandomFloor();
    }

    private int getRandomFloor(){
        int random = RandomHelper.getInt(MAX_FLOOR);
        return random == currentFloor ? getRandomFloor() : random;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return targetFloor == person.targetFloor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetFloor);
    }

    @Override
    public int compareTo(Person o) {
        return this.getTargetFloor() - o.getTargetFloor();
    }

    @Override
    public String toString() {
        return "(" +
                targetFloor +
                (targetFloor > 9 ? ")" : ")_");
    }
}
