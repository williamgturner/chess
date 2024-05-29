package com.willturner.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApplication.class.getResource("board-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        /*
        stage.widthProperty().addListener((obs, oldValue, newValue) -> {
            stage.setHeight((double) newValue);
        });

        stage.heightProperty().addListener((obs, oldValue, newValue) -> {
            stage.setWidth((double) newValue);
        });
         */
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