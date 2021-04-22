package com.mikkiko.elevator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mikkiko.elevator.model.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link Floor}.
 */
class FloorTest extends AbstractBuildingTest {

    final int currentFloor = 5;
    Floor floor;
    List<Person> peopleOnFloor;

    @BeforeEach
    void setUp() {
        this.floor = new Floor(currentFloor, MAX_FLOOR);

        List<Person> mockList = new ArrayList<>();
        for (int i : new int[]{1, 3, 3, 3, 4, 6, 7, 8, 8, 8}) {
            Person person = new Person(currentFloor, MAX_FLOOR);
            injectPersonTarget(person, i);
            mockList.add(person);
        }

        this.peopleOnFloor = injectListOfPeopleOnFloorAndGet(floor, mockList);
    }

    @Test
    @DisplayName("Should probably return person with target floor in provided direction.")
    void shouldProbablyReturnPerson() {
        Person person = floor.getPerson(DOWN);
        assertTrue(person != null && person.getTargetFloor() < currentFloor);

        person = floor.getPerson(UP);
        assertTrue(person != null && person.getTargetFloor() > currentFloor);
    }

    @Test
    @DisplayName("Should probably return person with most frequent target floor in provided direction.")
    void shouldReturnPersonWithMostFrequentTarget() {
        Person person = floor.getPerson(DOWN);
        assertTrue(person != null && person.getTargetFloor() == 3);

        person = floor.getPerson(UP);
        assertTrue(person != null && person.getTargetFloor() == 8);
    }

    @Test
    @DisplayName("Should return person and remove he from the floor.")
    void shouldRemoveSelectedPerson() {
        for (int i = 9; i >= 5; i--) {
            floor.getPerson(DOWN);
            assertEquals(i, peopleOnFloor.size());
        }
    }

    @Test
    @DisplayName("Should add person and update new people target floors.")
    void shouldAddAndUpdatePeople() {
        for (int i = 0; i < 5; i++) {
            Person person = new Person(currentFloor, MAX_FLOOR);
            injectPersonTarget(person, 5);
            floor.addPerson(person);
        }
        floor.updatePeople();

        assertEquals(15, peopleOnFloor.size());
        assertTrue(peopleOnFloor.stream().noneMatch(p -> p.getTargetFloor() == currentFloor));
    }
}