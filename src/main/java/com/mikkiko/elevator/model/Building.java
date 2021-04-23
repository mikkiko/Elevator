package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.Map;
import java.util.TreeMap;

/**
 * Building with an {@link Elevator} and {@link Floor}s.
 */
public class Building {

    public final int NUMBER_OF_FLOORS;
    public String emptyElevator = getEmptyElevator();

    private final Map<Integer, Floor> floors = new TreeMap<>();
    private Elevator elevator;
    private Floor currentFloor;

    public Building() {
        this.NUMBER_OF_FLOORS = RandomHelper.getInt(5, 20);
        init();
    }

    public Building(int maxFloor) {
        this.NUMBER_OF_FLOORS = maxFloor < 5 ? RandomHelper.getInt(5, 20) : maxFloor;
        init();
    }

    private void init() {
        this.elevator = new Elevator(5, NUMBER_OF_FLOORS);

        for (int i = 1; i <= NUMBER_OF_FLOORS; i++) {
            floors.put(i, new Floor(i, NUMBER_OF_FLOORS));
        }
        this.currentFloor = floors.get(elevator.getCurrentFloor());
    }

    public void moveTheElevator(){
        elevator.move();
        currentFloor = floors.get(elevator.getCurrentFloor());
    }

    public void loading() {
        while (elevator.hasFreePlaces()) {
            Person person = currentFloor.getPerson(elevator.getDirection());
            if (person != null)
                elevator.addPassenger(person);
            else break;
        }
        currentFloor.updatePeople();
    }

    public void unloading() {
        for (Person person; (person = elevator.getPassenger()) != null; ) {
            currentFloor.addPerson(person);
        }
    }

    private String getEmptyElevator(){
        String result = "";
        for (int i = 0; i < 24; i++) {
            result += (" ");
        }
        return result;
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
