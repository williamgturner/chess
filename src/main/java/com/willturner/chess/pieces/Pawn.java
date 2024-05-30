package com.willturner.chess.pieces;

import com.willturner.chess.Board;
import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

import java.util.ArrayList;

public class Pawn extends ChessPiece {
    private boolean hasMoved = false;

    public Pawn(PieceColour colour, String icon) {
        super(colour, icon);
    }

    @Override
    public ArrayList<Location> getLegalMoves(Location pieceLocation, Board chessBoard) {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location location;
        ChessPiece[][] pieceLocations = chessBoard.getPieceLocations();
        int column = pieceLocation.getColumn();
        int row = pieceLocation.getRow();
        int newColumn;
        int newRow;


        if(!hasMoved){ // If pawn has not moved, can move 2 squares forward
            if (getColor().equals(PieceColour.WHITE)) {
                newColumn = column;
                newRow = row - 2;
            } else {
                newColumn = column;
                newRow = row + 2;
            }
            possibleMoves.add(new Location(newColumn, newRow));
        }

        if (getColor().equals(PieceColour.WHITE)) { // White moves up the board
            location = new Location(column, row - 1); // Pawn can always move 1 square forward
            possibleMoves.add(location);
            location = new Location(column + 1, row - 1); // Diagonal right
            possibleMoves.add(location);
            location = new Location(column - 1, row - 1); // Diagonal left
            possibleMoves.add(location);
        } else { // Black moves down the board
            location = new Location(column, row + 1); // Pawn can always move 1 square forward
            possibleMoves.add(location);
            location = new Location(column + 1, row + 1); // Diagonal right
            possibleMoves.add(location);
            location = new Location(column - 1, row + 1); // Diagonal left
            possibleMoves.add(location);
        }

        ArrayList<Location> illegalMoves = new ArrayList<>();
        for (Location moveLocation : possibleMoves){ // Iterate over all possible moves
            newColumn = moveLocation.getColumn();
            newRow = moveLocation.getRow();

            if (newColumn < 0 || newColumn > 7 || newRow < 0 || newRow > 7){ // If move location is outside of board bounds
                illegalMoves.add(moveLocation);
            } else {
                if (pieceLocations[newColumn][newRow] != null){ // If move location is occupied
                    if (pieceLocations[newColumn][newRow].getColor() == getColor()){ // If move location is occupied by same colour
                        illegalMoves.add(moveLocation);
                    }
                }
            }
        }

        possibleMoves.removeAll(illegalMoves);
        return possibleMoves;
    }

    public String toString(){return "Pawn";}
}
