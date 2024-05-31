package com.willturner.chess.pieces;

import com.willturner.chess.Board;
import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    public Knight(PieceColour colour, String icon){
        super(colour, icon);
    }

    @Override
    public ArrayList<Location> getLegalMoves(Location pieceLocation, Board chessBoard) {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location location;
        int column = pieceLocation.getColumn();
        int row = pieceLocation.getRow();
        int newColumn = column;
        int newRow = row;

        // all possible movement deltas
        int[] rowDeltas = {-2, -2, -1, -1, 1, 1, 2 ,2};
        int[] columnDeltas = {-1, 1, -2, 2, -2, 2, -1 , 1};
        for (int i = 0; i < rowDeltas.length; i++) {
            newColumn += columnDeltas[i];
            newRow += rowDeltas[i];
            location = new Location(newColumn, newRow);
            if (newColumn >= 0 && newColumn < 8 && newRow >= 0 && newRow < 8){ // If move location is inside of board bounds
                // Empty square or opponent piece, then is a valid move
                if (chessBoard.getPiece(location) == null || chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
            }
            newColumn = column;
            newRow = row;
        }
        return possibleMoves;
    }
}
