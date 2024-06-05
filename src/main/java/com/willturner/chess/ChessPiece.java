package com.willturner.chess;

import com.willturner.chess.pieces.PieceColour;

import java.util.ArrayList;

public abstract class ChessPiece {
    public boolean hasMoved;
    private PieceColour colour;
    private int column;
    private int row;
    protected String icon;

    public ChessPiece(PieceColour colour, String icon) {
        this.colour = colour;
        this.icon = icon;
        this.hasMoved = false;
    }

    public abstract ArrayList<Location> getLegalMoves(Location pieceLocation, Board chessBoard);

    public PieceColour getColor() {return colour;}
    public String getIcon() {return icon;}
    public void setHasMoved(){this.hasMoved = true;}
}
