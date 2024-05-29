package com.willturner.chess;

public abstract class ChessPiece {
    private PieceColour color;
    private int column;
    private int row;

    public ChessPiece(PieceColour color) {
        this.color = color;
    }

    public abstract int getMovementOptions(int column, int row);
}
