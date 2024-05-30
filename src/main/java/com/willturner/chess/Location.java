package com.willturner.chess;

public class Location {
    private int column;
    private int row;
    public Location(int x, int y) {
        this.column = x;
        this.row = y;
    }

    public int getColumn() {return this.column;}
    public int getRow() {return this.row;}

    public String toString(){
        return "Column: " + column + " Row: " + row;
    }
}
