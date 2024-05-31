package com.willturner.chess;

import com.willturner.chess.pieces.Pawn;

public class Board {
    private int columns = 8;
    private int rows = 8;
    ChessPiece[][] pieceLocations = new ChessPiece[columns][rows];

    public Board() {
        for (int i = 0; i < columns; i++) {
            pieceLocations[i][1] = new Pawn(PieceColour.BLACK, "♟");
            pieceLocations[i][6] = new Pawn(PieceColour.WHITE, "♙");
        }
        pieceLocations[2][5] = new Pawn(PieceColour.WHITE, "♙");
        pieceLocations[3][5] = new Pawn(PieceColour.WHITE, "♙");
        pieceLocations[4][5] = new Pawn(PieceColour.WHITE, "♙");
    }
    public ChessPiece getPiece(Location location){
        int column = location.getColumn();
        int row = location.getRow();
        if(row >= 0 && row < rows && column >= 0 && column < columns){
            return pieceLocations[column][row];
        }
        return null;
    }
    public ChessPiece[][] getPieceLocations() {return pieceLocations;}
}
