package com.willturner.chess;

import com.willturner.chess.pieces.King;
import com.willturner.chess.pieces.PieceColour;

import java.util.ArrayList;

public class GameMaster {
    private boolean whiteTurn = true;
    private boolean movingPiece = false;
    Board chessBoard = new Board();
    private Location pieceLocation;
    private Location moveLocation;
    private ChessPiece pieceToMove = null;

    public Board getBoard(){return chessBoard;}

    public boolean getMovingPiece(){return movingPiece;}

    public ChessPiece getPieceToMove(){return pieceToMove;}

    /**
     * Handles logic necessary for moving pieces around the chess board
     * @param location (x,y) location of square that user has clicked on
     */
    public void movePiece(Location location){
        if (chessBoard.getPiece(location) != null || movingPiece){
            if (!movingPiece){
                pieceLocation = location;
                pieceToMove = chessBoard.getPiece(pieceLocation);
                movingPiece = true;
            } else {
                moveLocation = location;
                ArrayList<Location> legalMoves;
                legalMoves = pieceToMove.getLegalMoves(pieceLocation, chessBoard);
                // if move is legal
                if(legalMoves.contains(moveLocation)){
                    pieceToMove.setHasMoved();
                    chessBoard.pieceLocations[moveLocation.getColumn()][moveLocation.getRow()] = pieceToMove;
                    chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()] = null;
                    //System.out.println(getThreated(moveLocation, pieceToMove.getColor()));
                    pieceToMove = null;
                    movingPiece = false;
                // cancel movement if user selects the same piece
                } else if (chessBoard.getPiece(moveLocation) == pieceToMove) {
                    pieceToMove = null;
                    movingPiece = false;
                }
                // if player selects a different piece to move
                else if (chessBoard.getPiece(moveLocation) != null && chessBoard.getPiece(moveLocation).getColor() == pieceToMove.getColor()) {
                    pieceToMove = chessBoard.getPiece(moveLocation);
                    pieceLocation = moveLocation;
                }
                // if player selects an empty, illegal square to move to
                else {
                    pieceToMove = null;
                    movingPiece = false;
                }
            }
        }
    }

    /**
     * Checks if a given location is threated by the given colour
     * @param colour Colour to check if it is threated by
     * @return true if threated, false otherwise
     */
    public ArrayList<Location> getThreated(PieceColour colour){
        if (colour == PieceColour.BLACK){
            colour = PieceColour.WHITE;
        } else {
            colour = PieceColour.BLACK;
        }
        ArrayList<Location> possibleOpponentMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece pieceToCheck = chessBoard.getPiece(new Location(i, j));
                if (pieceToCheck != null && pieceToCheck.getColor() == colour) {
                    Location pieceLocation = new Location(i, j);
                    possibleOpponentMoves.addAll(pieceToCheck.getLegalMoves(pieceLocation, chessBoard));
                }
            }
        }

        return possibleOpponentMoves;
    }

}
