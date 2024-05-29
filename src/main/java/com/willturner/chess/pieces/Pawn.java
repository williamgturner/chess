package com.willturner.chess.pieces;

import com.willturner.chess.ChessPiece;
import com.willturner.chess.PieceColour;

public class Pawn extends ChessPiece {
    public Pawn(PieceColour color) {
        super(color);
    }

    @Override
    public int getMovementOptions(int column, int row) {
        return 0;
    }
}
