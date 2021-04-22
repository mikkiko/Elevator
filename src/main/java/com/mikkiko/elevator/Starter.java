package com.mikkiko.elevator;

import com.mikkiko.elevator.model.Building;
import com.mikkiko.elevator.controller.Controller;
import com.mikkiko.elevator.view.ConsoleView;

/**
 * Main class.
 */
public class Starter {

    public static void main(String[] args) {
        Building building = new Building();
        Controller controller = new Controller(new ConsoleView(building), building);
        controller.startElevating();
    }
}
