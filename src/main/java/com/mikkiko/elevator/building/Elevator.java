package com.mikkiko.elevator.building;

import java.util.ArrayList;
import java.util.List;

import static com.mikkiko.elevator.building.Direction.DOWN;
import static com.mikkiko.elevator.building.Direction.UP;

public class Elevator {

    public Elevator() {
        this.direction = UP;
        this.currentFloor = 1;
        this.targetFloor = 1;
    }

    private int currentFloor;
    private int targetFloor;
    private Direction direction;
    private final List<Passenger> passengers = new ArrayList<>();

    public void move() {
        refreshDirection();
        switch (direction) {
            case UP: {
                if (currentFloor != Building.NUMBER_OF_FLOORS)
                    this.currentFloor++;
                else
                    direction = DOWN;
                break;
            }
            case DOWN: {
                if (currentFloor > 1)
                    this.currentFloor--;
                else
                    direction = UP;
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < 5) {
            passengers.add(passenger);
            refreshDirection();
            updateCurrentFloor(passenger.getTargetFloor());
        }
    }

    public Passenger getPassenger() {
        Passenger passenger = passengers.stream()
                .filter(p -> p.getTargetFloor() == currentFloor).findFirst()
                .orElse(null);
        passengers.remove(passenger);
        return passenger;
    }

    public boolean hasFreePlace() {
        return passengers.size() < 5;
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
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append(direction.getSymbol())
                .append("[");
        for (int i = 0; i < 5; i++) {
            if (i < passengers.size())
                builder.append(passengers.get(i));
            else
                builder.append("____");
        }
        return builder.append("]").toString();
    }
}
