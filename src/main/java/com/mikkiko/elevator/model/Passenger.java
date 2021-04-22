package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.Objects;

public class Passenger implements Comparable<Passenger>{

    private final int MAX_FLOOR;
    private int currentFloor;
    private int targetFloor;

    public Passenger(int currentFloor, int maxFloor) {
        this.MAX_FLOOR = maxFloor;
        this.currentFloor = currentFloor;
        this.targetFloor = randomizeFloor();
    }

    public Integer getTargetFloor() {
        return targetFloor;
    }

    public void update(int currentFloor){
        this.currentFloor = currentFloor;
        targetFloor = randomizeFloor();
    }

    private int randomizeFloor(){
        int random = RandomHelper.getInt(MAX_FLOOR);
        return random == currentFloor ? randomizeFloor() : random;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return targetFloor == passenger.targetFloor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetFloor);
    }

    @Override
    public int compareTo(Passenger o) {
        return this.getTargetFloor() - o.getTargetFloor();
    }

    @Override
    public String toString() {
        return "(" +
                targetFloor +
                (targetFloor > 9 ? ")" : ")_");
    }
}
