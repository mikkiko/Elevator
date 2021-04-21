package com.mikkiko.elevator.building;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.Map;
import java.util.TreeMap;

public class Building {

    public static final int NUMBER_OF_FLOORS = RandomHelper.getInt(5, 20);
    public String emptyElevator = initEmptyElevator();

    private final Map<Integer, Floor> floors = new TreeMap<>();
    private final Elevator elevator;

    public Building() {
        this.elevator = new Elevator();
        for (int i = 1; i <= NUMBER_OF_FLOORS; i++) {
            floors.put(i, new Floor(i));
        }
    }

    public void moveTheElevator() {
        Floor currentFloor = floors.get(elevator.getCurrentFloor());

        for (Passenger passenger; (passenger = elevator.getPassenger()) != null;){
            currentFloor.addPassenger(passenger);
        }

        while (elevator.hasFreePlace()) {
            Passenger passenger = currentFloor.getPassenger(elevator.getDirection());
            if (passenger != null)
                elevator.addPassenger(passenger);
            else break;
        }

        currentFloor.updatePassengers();
        elevator.move();
    }

    private String initEmptyElevator() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            str.append(" ");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = floors.size(); i > 0; i--) {
            builder.append(i < 10 ? i + " " : i)
                    .append('|')
                    .append(i != elevator.getCurrentFloor() ? emptyElevator : elevator)
                    .append('|')
                    .append(floors.get(i))
                    .append("\n");
        }
        return builder.toString();
    }
}
