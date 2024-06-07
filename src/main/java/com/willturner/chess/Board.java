package com.willturner.chess;

import com.willturner.chess.pieces.*;

import java.util.ArrayList;

public class Board {
    private int columns = 8;
    private int rows = 8;
    ChessPiece[][] pieceLocations = new ChessPiece[columns][rows];

    /**
     * Constructor for class Board, initialises the starting location of chess pieces
     */
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
        pieceLocations[4][3] = new King(PieceColour.WHITE, "♔");
    }

    public ArrayList<Location> getThreated(PieceColour colour){
        if (colour == PieceColour.BLACK){
            colour = PieceColour.WHITE;
        } else {
            colour = PieceColour.BLACK;
        }
        ArrayList<Location> possibleOpponentMoves = new ArrayList<>();
        ArrayList<Location> pieceMoves;
        ArrayList<Location> pawnStraightMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece pieceToCheck = getPiece(new Location(i, j));
                if (pieceToCheck != null && pieceToCheck.getColor() == colour) {
                    Location pieceLocation = new Location(i, j);
                    pieceMoves = pieceToCheck.getLegalMoves(pieceLocation, this);
                    if(pieceToCheck instanceof Pawn){
                        for (Location move : pieceMoves) {
                            if (move.getColumn() == pieceLocation.getColumn()){
                                pawnStraightMoves.add(move);
                            }
                        }
                    }
                    pieceMoves.removeAll(pawnStraightMoves);
                    possibleOpponentMoves.addAll(pieceMoves);
                }
            }
        }

        return possibleOpponentMoves;
    }

    /**
     * Returns the piece at the specified location on the board
     * @param location Location to search
     * @return ChessPiece object, null if there is not one
     */
    public ChessPiece getPiece(Location location){
        int column = location.getColumn();
        int row = location.getRow();
        if(row >= 0 && row < rows && column >= 0 && column < columns){
            return pieceLocations[column][row];
        }
        return null;
    }

    /**
     * Returns the location of a given piece
     * @param piece Piece to find location of
     * @return Location object of the given piece, null if the piece does not exist
     */
    public Location getLocation(ChessPiece piece){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                if(pieceLocations[i][j] == piece){
                    return new Location(i, j);
                }
            }
        }
        return null;
    }
}
