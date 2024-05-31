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

    public void movePiece(Location location){
        if (chessBoard.pieceLocations[location.getColumn()][location.getRow()] != null || movingPiece){
            if (!movingPiece){
                pieceLocation = location;
                pieceToMove = chessBoard.getPiece(pieceLocation);
                movingPiece = true;
            } else {
                moveLocation = location;
                ArrayList<Location> legalMoves;
                legalMoves = pieceToMove.getLegalMoves(pieceLocation, chessBoard);
                moveLocation = location;
                if(legalMoves.contains(moveLocation)){
                    chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()].setHasMoved();
                    chessBoard.pieceLocations[moveLocation.getColumn()][moveLocation.getRow()] = chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()];
                    chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()] = null;
                    pieceToMove = null;
                    movingPiece = false;
                } else {
                    pieceToMove = null;
                    movingPiece = false;
                }
            }
        }
    }

}
