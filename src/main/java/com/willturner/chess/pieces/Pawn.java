package com.willturner.chess.pieces;

import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

public class Pawn extends ChessPiece {
    private PieceColour colour;
    public Pawn(PieceColour colour) {
        super(colour);
    }

    @Override
    public Location getMovementOptions(int column, int row) {
        Location location;
        if (getColor().equals(PieceColour.WHITE)) {
            location = new Location(column, row - 1);
        } else {
            location = new Location(column, row + 1);
        }
        return location;
    }
}
