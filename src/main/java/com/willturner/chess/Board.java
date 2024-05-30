package com.willturner.chess;

import com.willturner.chess.pieces.Pawn;

public class Board {
    private int columns = 8;
    private int rows = 8;
    ChessPiece[][] pieceLocations = new ChessPiece[rows][columns];

    public Board() {
        for (int i = 0; i < columns; i++) {
            pieceLocations[i][1] = new Pawn(PieceColour.BLACK);
            pieceLocations[i][6] = new Pawn(PieceColour.WHITE);
        }
        pieceLocations[1][5] = new Pawn(PieceColour.WHITE);
    }

    public ChessPiece[][] getPieceLocations() {return pieceLocations;}
}
