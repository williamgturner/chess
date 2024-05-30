package com.willturner.chess;

import java.util.ArrayList;

public abstract class ChessPiece {
    private PieceColour colour;
    private int column;
    private int row;
    protected String icon;

    public ChessPiece(PieceColour colour, String icon) {
        this.colour = colour;
        this.icon = icon;
    }

    public abstract ArrayList<Location> getLegalMoves(int column, int row, Board ChessBoard);

    public PieceColour getColor() {return colour;}
    public String getIcon() {return icon;}
}
