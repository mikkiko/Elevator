package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Floor implements Comparable<Floor> {

    private final int floor;
    private final List<Passenger> passengers = new ArrayList<>();
    private final List<Passenger> newPassengers = new ArrayList<>();

    public Floor(int floor, int maxFloor) {
        this.floor = floor;
        int random = RandomHelper.getInt(10);
        for (int i = 0; i < random; i++) {
            passengers.add(new Passenger(floor, maxFloor));
        }
    }

    public Passenger getPassenger(Direction direction, int freePlaces) {
        List<Passenger> resultPassengers = null;
        if (freePlaces == 5) {
            resultPassengers = passengers;
        } else {
            if (direction.equals(Direction.UP))
                resultPassengers = passengers.stream().filter(p -> p.getTargetFloor() > floor).collect(Collectors.toList());
            if (direction.equals(Direction.DOWN))
                resultPassengers = passengers.stream().filter(p -> p.getTargetFloor() < floor).collect(Collectors.toList());
        }
        if (resultPassengers == null || resultPassengers.isEmpty())
            return null;
        else {
            Passenger passenger = resultPassengers.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            passengers.remove(passenger);
            return passenger;
        }
    }

    public void addPassenger(Passenger passenger) {
        newPassengers.add(passenger);
    }

    public void updatePassengers() {
        if (!newPassengers.isEmpty()) {
            newPassengers.forEach(passenger -> passenger.update(floor));
            passengers.addAll(newPassengers);
            newPassengers.clear();
        }
    }

    @Override
    public int compareTo(Floor o) {
        return this.floor - o.floor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        passengers.forEach(builder::append);
        return builder.toString();
    }
}
