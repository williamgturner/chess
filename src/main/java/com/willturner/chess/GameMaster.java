package com.willturner.chess;

public class GameMaster {
    private boolean whiteTurn = true;
    Board chessBoard = new Board();
    private Location pieceLocation;
    private Location moveLocation;
    private boolean movingPiece = false;

    public GameMaster(){
    }

    public Board getBoard(){return chessBoard;}

    public boolean getMovingPiece(){return movingPiece;}

    public void movePiece(Location location){
        if (chessBoard.pieceLocations[location.getColumn()][location.getRow()] != null || movingPiece){
            if (!movingPiece){
                pieceLocation = location;
                movingPiece = true;
            } else {
                moveLocation = location;
                if(chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()].getLegalMoves(pieceLocation, chessBoard).contains(moveLocation)){
                    chessBoard.pieceLocations[moveLocation.getColumn()][moveLocation.getRow()] = chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()];
                    chessBoard.pieceLocations[pieceLocation.getColumn()][pieceLocation.getRow()] = null;
                    movingPiece = false;
                }
            }
        }
    }

}
