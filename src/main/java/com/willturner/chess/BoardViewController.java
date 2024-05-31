package com.willturner.chess;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

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

    /**
     * Initialises the GUI view by constructing and styling chess board
     */
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

                DoubleProperty fontSize = new SimpleDoubleProperty(40);
                fontSize.bind(pane.heightProperty()); // Bind font size to window size
                pieceIcon.prefWidthProperty().bind(pane.widthProperty()); // Force square aspect ratio
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

    /**
     * Handles mouse click events on chess board by retrieving coordinates and passing
     * to the GameMaster object for operations
     * @param event Mouse click event anywhere on scene
     */
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
        gameMaster.movePiece(location); // call to model to move piece at selected location
        refreshUI(); // clear any highlighted squares, update piece positions graphically
        // display legal moves if player is moving a piece
        if (gameMaster.getMovingPiece()){refreshUI(location);}

    }

    /**
     * Refreshes the UI to reflect changes in piece location
     */
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
                } else { // Clears highlighted legal moves of previously selected piece
                    if ((columnIndex + rowIndex) % 2 == 0) {
                        pane.setStyle("-fx-background-color: #e4ecc7");
                    } else {
                        pane.setStyle("-fx-background-color: #68a04f");
                    }
                    pane.setCursor(javafx.scene.Cursor.DEFAULT);
                    pieceIcon.setText("");
                }
            }
        }
    }

    /**
     * Method overloading for method refreshUI(), to be called when a user selects a piece
     * to move. This method displays the legal squares for a piece to move to
     * @param location the location of the piece to be moved.
     */
    private void refreshUI(Location location){ // Method overloading
        refreshUI();
        ArrayList<Location> legalMoves;
        legalMoves = gameMaster.getPieceToMove().getLegalMoves(location, chessBoard);
        for (Location legalMove : legalMoves) {
            int column = legalMove.getColumn();
            int row = legalMove.getRow();
            Pane pane = (Pane) getGridPaneNode(column, row);
            pane.setStyle("-fx-background-color: #ADD8E6");
            pane.setCursor(javafx.scene.Cursor.HAND);
        }
    }

    /**
     * Retrieve the grid pane node at a specified index
     * @param column column of grid pane node to find
     * @param row row of grid pane node to find
     * @return node at specified index, null if it does not exist
     */
    private Node getGridPaneNode(int column, int row) {
        for(Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Retrieve the Label child of a specified Pane
     * @param pane pane to retrieve label from
     * @return Label child of specified pane, null if it does not exist
     */
    private Node getPaneLabel(Pane pane){
        for(Node node : pane.getChildren()) {
            if (node instanceof Label){
                return node;
            }
        }
        return null;
    }
}