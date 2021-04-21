package com.mikkiko.elevator.building;

public enum Direction {
    UP("/\\"),
    DOWN("\\/");

    private String symbol;

    Direction(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
