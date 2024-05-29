package com.willturner.chess;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private GridPane gridPane;

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