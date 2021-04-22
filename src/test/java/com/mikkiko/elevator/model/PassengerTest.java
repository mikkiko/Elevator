package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    @DisplayName("Passenger target floor can not be the same as current floor.")
    void shouldProbablyUpdateTargetFloor() {
        for (int i = 0; i < 100; i++) {
            int currentFloor = RandomHelper.getInt(20);
            Passenger passenger = new Passenger(currentFloor, 20);
            assertNotEquals(passenger.getTargetFloor(), currentFloor);

            int newFloor = RandomHelper.getInt(20);
            passenger.update(newFloor);
            assertNotEquals(passenger.getTargetFloor(), newFloor);
        }
    }

    @Test
    @DisplayName("Two passenger with the same target floor are equal and vice versa.")
    void shouldProbablyEqualsPassengers() {
        for (int i = 0; i < 100; i++) {
            Passenger a = new Passenger(1, 5);
            Passenger b = new Passenger(1, 5);
            if (a.equals(b))
                assertEquals(a.getTargetFloor(), b.getTargetFloor());
            else
                assertNotEquals(a.getTargetFloor(), b.getTargetFloor());
        }
    }
}