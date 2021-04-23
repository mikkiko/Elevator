package com.mikkiko.elevator.model;

import java.util.ArrayList;
import java.util.List;

import static com.mikkiko.elevator.model.Direction.DOWN;
import static com.mikkiko.elevator.model.Direction.UP;

/**
 * The {@link Elevator} for transporting {@link Person}.
 */
public class Elevator {

    public Elevator(int capacity, int maxFloor) {
        this.MAX_FLOOR = maxFloor;
        this.capacity = capacity;
        this.direction = UP;
        this.currentFloor = 1;
        this.targetFloor = 1;
    }

    private final int capacity;
    private final int MAX_FLOOR;
    private int currentFloor;
    private int targetFloor;
    private Direction direction;
    private final List<Person> passengers = new ArrayList<>();

    public void move() {
        refreshDirection();
        switch (direction) {
            case UP: {
                if (currentFloor != MAX_FLOOR)
                    this.currentFloor++;
                else {
                    direction = DOWN;
                    this.currentFloor--;
                }
                break;
            }
            case DOWN: {
                if (currentFloor != 1)
                    this.currentFloor--;
                else {
                    direction = UP;
                    this.currentFloor++;
                }
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    public void addPassenger(Person person) {
        if (passengers.size() < capacity) {
            passengers.add(person);
            refreshDirection();
            updateCurrentFloor(person.getTargetFloor());
        }
    }

    public Person getPassenger() {
        Person person = passengers.stream()
                .filter(p -> p.getTargetFloor() == currentFloor).findFirst()
                .orElse(null);
        passengers.remove(person);
        return person;
    }

    public boolean hasFreePlaces() {
        return passengers.size() < capacity;
    }

    private void updateCurrentFloor(int newPassengerTargetFloor) {
        switch (direction) {
            case UP: {
                if (newPassengerTargetFloor > targetFloor)
                    targetFloor = newPassengerTargetFloor;
                break;
            }
            case DOWN: {
                if (newPassengerTargetFloor < targetFloor)
                    targetFloor = newPassengerTargetFloor;
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private void refreshDirection() {
        if (!passengers.isEmpty()) {
            if (passengers.stream().allMatch(passenger -> passenger.getTargetFloor() < currentFloor))
                direction = DOWN;
            else direction = UP;
        }else{
            if(currentFloor == MAX_FLOOR)
                direction = DOWN;
            if (currentFloor == 1)
                direction = UP;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        refreshDirection();
        return direction;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(direction.getSymbol())
                .append("[");
        for (int i = 0; i < capacity; i++) {
            if (i < passengers.size())
                builder.append(passengers.get(i));
            else
                builder.append("____");
        }
        return builder.append("]").toString();
    }
}
