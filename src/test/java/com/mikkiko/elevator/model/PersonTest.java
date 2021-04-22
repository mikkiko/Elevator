package com.mikkiko.elevator.model;

import com.mikkiko.elevator.utils.RandomHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link Person}.
 */
class PersonTest {

    @Test
    @DisplayName("Person target floor can not be the same as current floor.")
    void shouldProbablyUpdateTargetFloor() {
        for (int i = 0; i < 100; i++) {
            int currentFloor = RandomHelper.getInt(20);
            Person person = new Person(currentFloor, 20);
            assertNotEquals(currentFloor, person.getTargetFloor());

            int newFloor = RandomHelper.getInt(20);
            person.updateTarget(newFloor);
            assertNotEquals(newFloor, person.getTargetFloor());
        }
    }

    @Test
    @DisplayName("Two people with the same target floor are equal and vice versa.")
    void shouldProbablyEqualsPeople() {
        for (int i = 0; i < 100; i++) {
            Person a = new Person(1, 5);
            Person b = new Person(1, 5);
            if (a.equals(b))
                assertEquals(a.getTargetFloor(), b.getTargetFloor());
            else
                assertNotEquals(a.getTargetFloor(), b.getTargetFloor());
        }
    }
}