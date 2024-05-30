package com.willturner.chess;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.column, column) == 0 &&
                Double.compare(location.row, row) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.column, this.row);
    }
}
