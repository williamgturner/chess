package com.willturner.chess.pieces;

import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
    private PieceColour colour;
    private boolean hasMoved;
    public Pawn(PieceColour colour) {
        super(colour);
        hasMoved = false;
    }

    @Override
    public ArrayList<Location> getMovementOptions(int column, int row) {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location location;
        if(!hasMoved){ // If pawn has not moved, can move 2 squares
            if (getColor().equals(PieceColour.WHITE)) {
                location = new Location(column, row - 2);
                possibleMoves.add(location);
            } else {
                location = new Location(column, row + 2);
                possibleMoves.add(location);
            }
        }

        if (getColor().equals(PieceColour.WHITE)) {
            location = new Location(column, row - 1); // Pawn can always move 1 square forward
            possibleMoves.add(location);
            location = new Location(column + 1, row - 1); // Diagonal right
            possibleMoves.add(location);
            location = new Location(column - 1, row - 1); // Diagonal left
            possibleMoves.add(location);
        } else {
            location = new Location(column, row + 1); // Pawn can always move 1 square forward
            possibleMoves.add(location);
            location = new Location(column + 1, row + 1); // Diagonal right
            possibleMoves.add(location);
            location = new Location(column - 1, row + 1); // Diagonal left
            possibleMoves.add(location);
        }
        return possibleMoves;
    }
}
