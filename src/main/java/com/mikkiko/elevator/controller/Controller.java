package com.mikkiko.elevator.controller;

import com.mikkiko.elevator.building.Building;
import com.mikkiko.elevator.view.View;

public class Controller{

    private View view;
    private Building building;

    public Controller(View view, Building building) {
        this.view = view;
        this.building = building;
        view.introducing();
    }

    public void startElevating() {
        while (true) {
            try {
                view.refresh();
                building.moveTheElevator();
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                break;
            }
        }
        view.finishing();
        System.exit(0);
    }
}
