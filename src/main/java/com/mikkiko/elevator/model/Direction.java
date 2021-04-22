package com.mikkiko.elevator.model;

public enum Direction {
    UP("/\\"),
    DOWN("\\/");

    private final String symbol;

    Direction(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
