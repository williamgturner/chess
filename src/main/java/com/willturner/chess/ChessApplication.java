package com.willturner.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameMaster gameMaster = new GameMaster();
        BoardViewController controller = new BoardViewController(gameMaster);

        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("board-view.fxml"));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.minWidthProperty().bind(scene.heightProperty());
        stage.minHeightProperty().bind(scene.widthProperty());
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}