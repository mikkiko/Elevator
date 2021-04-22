package com.mikkiko.elevator;

import com.mikkiko.elevator.model.Building;
import com.mikkiko.elevator.controller.Controller;
import com.mikkiko.elevator.view.ConsoleView;
import com.mikkiko.elevator.view.View;

public class Starter {

    public static void main(String[] args) {
        Building building = new Building();
        View view = new ConsoleView(building);
        Controller controller = new Controller(view, building);
        controller.startElevating();
    }
}
