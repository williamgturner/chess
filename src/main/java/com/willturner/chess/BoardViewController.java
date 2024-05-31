package com.willturner.chess;

import com.willturner.chess.pieces.Pawn;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class BoardViewController {
    @FXML
    private GridPane gridPane;

    private GameMaster gameMaster;
    private int rows = 8;
    private int columns = 8;
    private Board chessBoard;

    public BoardViewController(GameMaster game) { // Set chess board
        this.chessBoard = game.getBoard();
        this.gameMaster = game;
    }

    @FXML
    private void initialize() {
        for (int columnIndex = 0; columnIndex < columns; columnIndex++) { // Iterate over all squares
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                Pane pane = new Pane();
                Label pieceIcon = new Label();

                if ((columnIndex + rowIndex) % 2 == 0) {
                    pane.setStyle("-fx-background-color: #e4ecc7");
                } else {
                    pane.setStyle("-fx-background-color: #68a04f");
                }

                //pieceIcon.setStyle("-fx-background-color: #00FF00");
                DoubleProperty fontSize = new SimpleDoubleProperty(40);
                fontSize.bind(pane.heightProperty());
                pieceIcon.prefWidthProperty().bind(pane.widthProperty());
                pieceIcon.prefHeightProperty().bind(pane.heightProperty());
                pieceIcon.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
                pieceIcon.setAlignment(Pos.CENTER);
                pane.getChildren().add(pieceIcon);

                gridPane.add(pane, columnIndex, rowIndex);
                gridPane.setHgrow(pane, Priority.ALWAYS);
                gridPane.setVgrow(pane, Priority.ALWAYS);
            }
        }
        refreshUI();
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

        Location location = new Location(colSelected, rowSelected);
        gameMaster.movePiece(location); // call to model view to move piece
        if (gameMaster.getMovingPiece()){displayMoveOptions(location);} // If moving a piece, display options

        refreshUI();
        Pane pane = (Pane) getGridPaneNode(colSelected, rowSelected);
        pane.setCursor(javafx.scene.Cursor.DEFAULT);
    }

    private void refreshUI(){
        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                Pane pane = (Pane) getGridPaneNode(columnIndex, rowIndex);
                Label pieceIcon = (Label) getPaneLabel(pane);
                if (!gameMaster.getMovingPiece()){
                    if ((columnIndex + rowIndex) % 2 == 0) {
                        pane.setStyle("-fx-background-color: #e4ecc7");
                    } else {
                        pane.setStyle("-fx-background-color: #68a04f");
                    }
                }
                if (chessBoard.pieceLocations[columnIndex][rowIndex] != null){
                    pane.setCursor(javafx.scene.Cursor.HAND);
                    pieceIcon.setText(chessBoard.pieceLocations[columnIndex][rowIndex].getIcon());
                } else {
                    pieceIcon.setText("");
                }
            }
        }
    }

    private void displayMoveOptions(Location location){
        ArrayList<Location> legalMoves;
        legalMoves = gameMaster.getPieceToMove().getLegalMoves(location, chessBoard);
        for (Location legalMove : legalMoves) {
            int column = legalMove.getColumn();
            int row = legalMove.getRow();
            Pane pane = (Pane) getGridPaneNode(column, row);
            pane.setStyle("-fx-background-color: #ADD8E6");
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

    private Node getPaneLabel(Pane pane){
        for(Node node : pane.getChildren()) {
            if (node instanceof Label){
                return node;
            }
        }
        return null;
    }

}