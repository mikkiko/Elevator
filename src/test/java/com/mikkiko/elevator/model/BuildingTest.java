package com.mikkiko.elevator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mikkiko.elevator.model.Direction.UP;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link Building}.
 */
class BuildingTest extends AbstractBuildingTest{

    private Building building;

    @BeforeEach
    void setUp() {
        this.building = new Building(MAX_FLOOR);
    }


    @Test
    @DisplayName("Elevator should move between floors.")
    void elevatorMovingTest() {
        for (int i = 0; i < 10; i++) {
            Floor previousFloor = getCurrentFloor(building);
            building.moveTheElevator();
            Floor currentFloor = getCurrentFloor(building);
            assertNotEquals(previousFloor, currentFloor);
        }
    }

    @Test
    @DisplayName("Elevator should probably load people.")
    void loadingTest() {
        for (int i = 1; i < 5; i++) {
            building.moveTheElevator();
        }

        Elevator elevator = getElevator(building);
        List<Person> peopleInElevator = injectListPeopleInElevatorAndGet(elevator, new ArrayList<>());

        injectListOfPeopleOnFloorAndGet(getCurrentFloor(building),
                Stream.of(
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 1),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 1),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 7),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 8),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 9)
                ).collect(Collectors.toList()));

        building.loading();

        assertEquals(UP, elevator.getDirection());
        assertNotNull(peopleInElevator
                .stream()
                .filter(p -> p.getTargetFloor() == 7)
                .findFirst()
                .orElse(null));
        assertNotNull(peopleInElevator
                .stream()
                .filter(p -> p.getTargetFloor() == 8)
                .findFirst()
                .orElse(null));
        assertNotNull(peopleInElevator
                .stream()
                .filter(p -> p.getTargetFloor() == 9)
                .findFirst()
                .orElse(null));
    }

    @Test
    @DisplayName("Elevator should probably unload people.")
    void unloadingTest() {
        for (int i = 1; i < 5; i++) {
            building.moveTheElevator();
        }

        injectListPeopleInElevatorAndGet(getElevator(building),
                Stream.of(
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 7),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 6),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 5),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 5),
                        injectPersonTargetAndGet(new Person(1, MAX_FLOOR), 5)
                ).collect(Collectors.toList()));

        Floor currentFloor = getCurrentFloor(building);
        List<Person> peopleOnFloor = injectListOfPeopleOnFloorAndGet(currentFloor, new ArrayList<>());

        building.unloading();
        currentFloor.updatePeople();

        assertEquals(3, peopleOnFloor.size());
    }
}