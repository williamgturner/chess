package com.willturner.chess;

import java.util.ArrayList;

public abstract class ChessPiece {
    private PieceColour colour;
    private int column;
    private int row;

    public ChessPiece(PieceColour colour) {
        this.colour = colour;
    }

    public abstract ArrayList<Location> getLegalMoves(int column, int row, Board ChessBoard);
    public PieceColour getColor() {
        return colour;
    }
}
