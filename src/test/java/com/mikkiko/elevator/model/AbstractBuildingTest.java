package com.mikkiko.elevator.model;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Abstract class with tools for testing {@link Building}, {@link Elevator}, {@link Floor}, {@link Person}.
 */
public abstract class AbstractBuildingTest {

    final int MAX_FLOOR = 10;

    void injectPersonTarget(Person person, int target) {
        getPrivateFieldWithValue(person, "targetFloor", target);
    }

    Person injectPersonTargetAndGet(Person person, int target) {
        injectPersonTarget(person, target);
        return person;
    }


    List<Person> injectListOfPeopleOnFloorAndGet(Floor floor, List<Person> listOfPeople) {
        return (List<Person>) getPrivateFieldWithValue(floor, "people", listOfPeople);
    }

    void injectElevatorCurrentFloor(Elevator elevator, int newFloor) {
        getPrivateFieldWithValue(elevator, "currentFloor", newFloor);
    }

    List<Person> injectListPeopleInElevatorAndGet(Elevator elevator, List<Person> personList) {
        return (List<Person>) getPrivateFieldWithValue(elevator, "passengers", personList);
    }

    void setElevatorDirection(Elevator elevator, Direction direction) {
        getPrivateFieldWithValue(elevator, "direction", direction);
    }

    Elevator getElevator(Building building){
        return (Elevator) getPrivateFieldWithValue(building, "elevator", null);
    }

    Floor getCurrentFloor(Building building){
        return (Floor) getPrivateFieldWithValue(building, "currentFloor", null);
    }

    /**
     * Method to injecting provided {@param value} to the field with {@param fieldName} of provided {@param object}.
     *
     * @param object provided {@link Object} which contains needed field.
     * @param fieldName provided {@link String} with name of wanted field.
     * @param value provided value to inject or null if needed unchanged object.
     * @return {@link Object} which was encapsulated in {@param fieldName} with provided {@param value}.
     * or in case null return unchanged object from field of provided {@param object}.
     */
    private Object getPrivateFieldWithValue(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            if (value != null)
                field.set(object, value);
            return field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
            return null;
        }
    }
}
