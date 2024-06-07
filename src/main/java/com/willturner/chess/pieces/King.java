package com.willturner.chess.pieces;


import com.willturner.chess.Board;
import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;

import java.util.ArrayList;

public class King extends ChessPiece {
    public King(PieceColour colour, String icon) {
        super(colour, icon);
    }

    public ArrayList<Location> getLegalMoves(Location pieceLocation, Board chessBoard) {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location moveLocation;
        int column = pieceLocation.getColumn();
        int row = pieceLocation.getRow();
        int newColumn;
        int newRow;

        for(int i = -1; i <= 1; i++) {
            newRow = row - 1;
            newColumn = column + i;
            moveLocation = new Location(newColumn, newRow);
            possibleMoves.add(moveLocation);
            newRow = row + 1;
            moveLocation = new Location(newColumn, newRow);
            possibleMoves.add(moveLocation);
        }

        moveLocation = new Location(column - 1, row);
        possibleMoves.add(moveLocation);
        moveLocation = new Location(column + 1, row);
        possibleMoves.add(moveLocation);

        ArrayList<Location> threatedSquares = chessBoard.getThreated(this.getColor());
        possibleMoves.removeAll(threatedSquares);

        ArrayList<Location> illegalMoves = new ArrayList<>();
        for (Location possibleMove : possibleMoves) {
            newColumn = possibleMove.getColumn();
            newRow = possibleMove.getRow();
            if (newColumn < 0 || newColumn > 7 || newRow < 0 || newRow > 7){ // If move location is outside of board bounds
                illegalMoves.add(possibleMove);
            } else {
                if (chessBoard.getPiece(possibleMove) != null){ // If move location is occupied
                    if (chessBoard.getPiece(possibleMove).getColor() == getColor()){ // If move location is occupied by same colour
                        illegalMoves.add(possibleMove);
                    }
                }
            }
        }
        possibleMoves.removeAll(illegalMoves);
        return possibleMoves;
    }
}
