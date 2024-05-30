package com.willturner.chess;

import java.util.ArrayList;

public abstract class ChessPiece {
    private PieceColour color;
    private int column;
    private int row;

    public ChessPiece(PieceColour color) {
        this.color = color;
    }

    public abstract ArrayList<Location> getMovementOptions(int column, int row);
    public PieceColour getColor() {
        return color;
    }
}
