package com.mikkiko.elevator.view;

import com.mikkiko.elevator.model.Building;
import com.mikkiko.elevator.utils.ConsoleHelper;

/**
 * Implementation of {@link View}.
 */
public class ConsoleView implements View {

    private final Building building;
    private int step = 0;

    public ConsoleView(Building building) {
        this.building = building;
    }

    @Override
    public void refresh() {
        String builder = "\n     *****Step " +
                ++step +
                "*****\n" +
                building.toString();
        ConsoleHelper.print(builder);
    }



    @Override
    public void starting() {
        ConsoleHelper.print("<--- Program started. --->");
    }

    @Override
    public void finishing() {
        ConsoleHelper.print(String.format("\n<--- Program stopped on %d step. --->", step));
    }
}
