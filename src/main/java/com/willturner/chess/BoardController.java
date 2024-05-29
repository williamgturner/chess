package com.willturner.chess;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardController {
    @FXML
    private GridPane gridPane;

    private int rows = 8;
    private int columns = 8;

    @FXML
    private void initialize() {
        gridPane.requestFocus();
        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                Pane pane = new Pane();
                if ((columnIndex + rowIndex) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #FFFFFF");
                } else {
                    pane.setStyle("-fx-background-color: #000000");
                    pane.setCursor(javafx.scene.Cursor.HAND);

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