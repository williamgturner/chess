package com.willturner.chess.pieces;

import com.willturner.chess.Board;
import com.willturner.chess.ChessPiece;
import com.willturner.chess.Location;
import com.willturner.chess.PieceColour;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    public Queen(PieceColour colour, String icon){
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
        boolean blocked = false;

        // Diagonal left up
        while(!blocked && newColumn > 0 && newRow > 0) {
            newColumn--;
            newRow--;
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;
            }
        }

        newColumn = column;
        newRow = row;
        blocked = false;
        // Diagonal right up
        while(!blocked && newColumn < 7 && newRow > 0) {
            newColumn++;
            newRow--;
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;
            }
        }

        newColumn = column;
        newRow = row;
        blocked = false;
        // Diagonal left down
        while(!blocked && newColumn > 0 && newRow < 7) {
            newColumn--;
            newRow++;
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;
            }
        }

        newColumn = column;
        newRow = row;
        blocked = false;
        // Diagonal right down
        while(!blocked && newColumn < 7 && newRow < 7) {
            newColumn++;
            newRow++;
            location = new Location(newColumn, newRow);
            if (chessBoard.getPiece(location) == null){
                possibleMoves.add(location);
            } else{
                if (chessBoard.getPiece(location).getColor() != this.getColor()){
                    possibleMoves.add(location);
                }
                blocked = true;
            }
        }

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
