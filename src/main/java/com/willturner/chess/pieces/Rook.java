package com.willturner.chess.pieces;

import com.willturner.chess.Board;
import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    public Rook(PieceColour colour, String icon) {
        super(colour, icon);
    }

    @Override
    public ArrayList<Location> getLegalMoves(Location pieceLocation, Board chessBoard) {
        ArrayList<Location> possibleMoves = new ArrayList<>();
        Location location;
        int column = pieceLocation.getColumn();
        int row = pieceLocation.getRow();
        int newColumn = column + 1;
        int newRow = row;
        boolean blocked = false;

        while(!blocked && newColumn < 8){ // possible moves right
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;}
            newColumn++;
        }

        blocked = false;
        newColumn = column - 1;
        while(!blocked && newColumn >= 0){ // possible moves left
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;}
            newColumn--;
        }

        blocked = false;
        newColumn = column;
        newRow = row + 1;
        while(!blocked && newRow < 8){ // possible moves down
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;}
            newRow++;
        }

        blocked = false;
        newRow = row - 1;
        while(!blocked && newRow >= 0){ // possible moves up
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;}
            newRow--;
        }
        return possibleMoves;
    }
}
