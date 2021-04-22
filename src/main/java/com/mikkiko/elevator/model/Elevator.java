package com.mikkiko.elevator.model;

import java.util.ArrayList;
import java.util.List;

import static com.mikkiko.elevator.model.Direction.DOWN;
import static com.mikkiko.elevator.model.Direction.UP;

/**
 * The {@link Elevator} for transporting {@link Person}.
 */
public class Elevator {

    public Elevator(int maxFloor) {
        this.MAX_FLOOR = maxFloor;
        this.direction = UP;
        this.currentFloor = 1;
        this.targetFloor = 1;
    }

    private final int MAX_FLOOR;
    private int currentFloor;
    private int targetFloor;
    private Direction direction;
    private final List<Person> people = new ArrayList<>();

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

    public void addPerson(Person person) {
        if (people.size() < 5) {
            people.add(person);
            refreshDirection();
            updateCurrentFloor(person.getTargetFloor());
        }
    }

    public Person getPerson() {
        Person person = people.stream()
                .filter(p -> p.getTargetFloor() == currentFloor).findFirst()
                .orElse(null);
        people.remove(person);
        return person;
    }

    public boolean hasFreePlaces() {
        return people.size() < 5;
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
        if (!people.isEmpty()) {
            if (people.stream().allMatch(passenger -> passenger.getTargetFloor() < currentFloor))
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
        for (int i = 0; i < 5; i++) {
            if (i < people.size())
                builder.append(people.get(i));
            else
                builder.append("____");
        }
        return builder.append("]").toString();
    }
}
