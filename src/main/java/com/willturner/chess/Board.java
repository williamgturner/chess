package com.willturner.chess;

import com.willturner.chess.pieces.*;

public class Board {
    private int columns = 8;
    private int rows = 8;
    ChessPiece[][] pieceLocations = new ChessPiece[columns][rows];

    public Board() {
        for (int i = 0; i < columns; i++) {
            pieceLocations[i][1] = new Pawn(PieceColour.BLACK, "♟");
            pieceLocations[i][6] = new Pawn(PieceColour.WHITE, "♙");
        }
        // initialise black  pieces
        pieceLocations[0][0] = new Rook(PieceColour.BLACK, "♜");
        pieceLocations[7][0] = new Rook(PieceColour.BLACK, "♜");
        pieceLocations[1][0] = new Knight(PieceColour.BLACK, "♞");
        pieceLocations[6][0] = new Knight(PieceColour.BLACK, "♞");
        pieceLocations[2][0] = new Bishop(PieceColour.BLACK, "♝");
        pieceLocations[5][0] = new Bishop(PieceColour.BLACK, "♝");
        pieceLocations[3][0] = new Queen(PieceColour.BLACK, "♛");

        // initialise white pieces
        pieceLocations[0][7] = new Rook(PieceColour.WHITE, "♖");
        pieceLocations[7][7] = new Rook(PieceColour.WHITE, "♖");
        pieceLocations[1][7] = new Knight(PieceColour.WHITE, "♘");
        pieceLocations[6][7] = new Knight(PieceColour.WHITE, "♘");
        pieceLocations[2][7] = new Bishop(PieceColour.WHITE, "♗");
        pieceLocations[5][7] = new Bishop(PieceColour.WHITE, "♗");
        pieceLocations[3][7] = new Queen(PieceColour.WHITE, "♕");
    }
    public ChessPiece getPiece(Location location){
        int column = location.getColumn();
        int row = location.getRow();
        if(row >= 0 && row < rows && column >= 0 && column < columns){
            return pieceLocations[column][row];
        }
        return null;
    }
}
