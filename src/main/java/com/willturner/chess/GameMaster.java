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
        if (chessBoard.getPiece(location) != null || movingPiece){
            if (!movingPiece){
                pieceLocation = location;
                pieceToMove = chessBoard.getPiece(pieceLocation);
                movingPiece = true;
                // System.out.println("wee");
            } else {
                moveLocation = location;
                ArrayList<Location> legalMoves;
                legalMoves = pieceToMove.getLegalMoves(pieceLocation, chessBoard);
                moveLocation = location;
                if(legalMoves.contains(moveLocation)){
                    pieceToMove.setHasMoved();
                    chessBoard.pieceLocations[moveLocation.getColumn()][moveLocation.getRow()] = pieceToMove;
                    chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()] = null;
                    // System.out.println("Poo");
                    pieceToMove = null;
                    movingPiece = false;
                } else if (chessBoard.getPiece(moveLocation) != null && chessBoard.getPiece(moveLocation).getColor() == pieceToMove.getColor()) {
                    pieceToMove = chessBoard.getPiece(moveLocation);
                    pieceLocation = moveLocation;
                    // System.out.println(movingPiece);
                } else {
                    pieceToMove = null;
                    movingPiece = false;
                }
            }
        }
    }

}
