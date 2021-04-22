package com.mikkiko.elevator.controller;

import com.mikkiko.elevator.model.Building;
import com.mikkiko.elevator.utils.ConsoleHelper;
import com.mikkiko.elevator.view.View;

/**
 * Controller for handling user inputs and controlling the app run.
 */
public class Controller{

    private View view;
    private Building building;

    public Controller(View view, Building building) {
        this.view = view;
        this.building = building;
        view.starting();
    }

    public void startElevating() {
        int iterations = ConsoleHelper.readInt("Input count of iterations:",1, 1000);
        int tick = ConsoleHelper
                .readInt("Input print frequency in milliseconds (from 10 to 5_000):",10, 5000);
        while (iterations-- > 0) {
            try {
                view.refresh();
                building.unloading();
                building.loading();
                building.moveTheElevator();
                Thread.sleep(tick);
            } catch (InterruptedException e) {
                break;
            }
        }
        view.finishing();
        System.exit(0);
    }
}
