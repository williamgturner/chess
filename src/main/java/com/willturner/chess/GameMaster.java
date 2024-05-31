package com.willturner.chess;

import java.util.ArrayList;

public class GameMaster {
    private boolean whiteTurn = true;
    Board chessBoard = new Board();
    private Location pieceLocation;
    private Location moveLocation;
    private boolean movingPiece = false;
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

}
