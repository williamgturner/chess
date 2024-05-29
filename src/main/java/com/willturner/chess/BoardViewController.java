package com.willturner.chess;

import com.willturner.chess.pieces.Pawn;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardViewController {
    @FXML
    private GridPane gridPane;

    private int rows = 8;
    private int columns = 8;
    private Board chessBoard;

    public BoardViewController(Board chessBoard) {
        this.chessBoard = chessBoard;
    }
    @FXML
    private void initialize() {
        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                Pane pane = new Pane();
                if ((columnIndex + rowIndex) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #FFFFFF");
                } else {
                    pane.setStyle("-fx-background-color: #000000");
                    pane.setCursor(javafx.scene.Cursor.HAND);

                }
                if (chessBoard.pieceLocations[columnIndex][rowIndex] instanceof Pawn){
                    pane.setStyle("-fx-background-color: #FF0000");
                }
                gridPane.add(pane, columnIndex, rowIndex);
            }
        }
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        double width = gridPane.getWidth();
        double height = gridPane.getHeight();
        double cellHeight = height / 8;
        double cellWidth = width / 8;
        int rowSelected = (int) (y / cellHeight);
        int colSelected = (int) (x / cellWidth);
        Pane pane = (Pane) getGridPaneNode(colSelected, rowSelected);
        pane.setStyle("-fx-background-color: #FFFFFF");
        pane.setCursor(javafx.scene.Cursor.DEFAULT);

        if(chessBoard.pieceLocations[colSelected][rowSelected] != null) {
            System.out.println(chessBoard.pieceLocations[colSelected][rowSelected].getMovementOptions(colSelected, rowSelected));
        }
    }

    private Node getGridPaneNode(int column, int row) {
        for(Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

}