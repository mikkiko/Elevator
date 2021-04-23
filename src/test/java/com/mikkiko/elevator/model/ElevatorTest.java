package com.mikkiko.elevator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.mikkiko.elevator.model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link Elevator}.
 */
class ElevatorTest extends AbstractBuildingTest {

    private Elevator elevator;

    @BeforeEach
    void setUp() {
        this.elevator = new Elevator(5, MAX_FLOOR);
    }

    @Test
    @DisplayName("Testing empty elevator moving.")
    void elevatorMoveTest() {
        Set<Integer> setFloors = new TreeSet<>();
        for (int i = 1; i <= 30; i++) {
            setFloors.add(elevator.getCurrentFloor());
            elevator.move();
        }
        assertTrue(setFloors.stream().allMatch(i -> 1 <= i && i <= MAX_FLOOR));
        assertTrue(setFloors.containsAll(IntStream.rangeClosed(1, MAX_FLOOR).boxed().collect(Collectors.toSet())));
    }

    @Test
    @DisplayName("Should add new person and update direction.")
    void shouldProbablyAddPerson() {
        injectElevatorCurrentFloor(elevator, 5);

        Person person = new Person(5, MAX_FLOOR);
        injectPersonTarget(person, 4);
        elevator.addPassenger(person);
        assertEquals(DOWN, elevator.getDirection());
    }

    @Test
    @DisplayName("Should return person with target floor equally current and remove from elevator.")
    void shouldProbablyGetPerson() {
        injectElevatorCurrentFloor(elevator, 5);
        List<Person> peopleInElevator = injectListPeopleInElevatorAndGet(
                elevator, getInitPeopleList());

        Person person = elevator.getPassenger();
        assertTrue(person != null && person.getTargetFloor() == 5);
        assertEquals(4, peopleInElevator.size());
        assertNull(elevator.getPassenger());
    }

    @Test
    @DisplayName("Should probably check free place in elevator.")
    void hasFreePlacesTest() {
        injectListPeopleInElevatorAndGet(elevator, getInitPeopleList());
        assertFalse(elevator.hasFreePlaces());
    }

    @Test
    @DisplayName("Should probably return direction with empty elevator on max floor.")
    void getDirOnMaxFloor() {
        injectElevatorCurrentFloor(elevator, MAX_FLOOR);
        assertEquals(DOWN, elevator.getDirection());
    }

    @Test
    @DisplayName("Should probably return direction with empty elevator on max floor.")
    void getDirOnFirstFloor() {
        injectElevatorCurrentFloor(elevator, 1);
        setElevatorDirection(elevator, DOWN);
        assertNotEquals(DOWN, elevator.getDirection());
    }

    @Test
    @DisplayName("Should probably return direction with empty elevator on not extreme floors.")
    void getDirOnMidFloor() {
        injectElevatorCurrentFloor(elevator, 5);
        assertEquals(UP, elevator.getDirection());

        elevator.move();
        assertEquals(UP, elevator.getDirection());
    }

    @Test
    @DisplayName("Should probably return direction based on peoples targets.")
    void getDirBasedOnPeopleTarget() {
        injectElevatorCurrentFloor(elevator, 7);
        injectListPeopleInElevatorAndGet(elevator, getInitPeopleList());
        assertEquals(DOWN, elevator.getDirection());
    }

    List<Person> getInitPeopleList() {
        return Stream.of(new Person[]{
                injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 1),
                injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 2),
                injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 3),
                injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 4),
                injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 5)
        }).collect(Collectors.toList());
    }
}