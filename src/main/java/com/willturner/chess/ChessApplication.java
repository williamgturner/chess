package com.willturner.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.Taskbar;
import java.awt.Toolkit;

import java.io.IOException;

public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameMaster gameMaster = new GameMaster();
        BoardViewController controller = new BoardViewController(gameMaster);
        //Image appIcon = new Image(getClass().getResourceAsStream("/com/willturner/chess/Rook.png"));
        //stage.getIcons().add(appIcon);

        /*
        java.awt.Image dockIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/willturner/chess/Rook.png"));
        Taskbar taskbar = Taskbar.getTaskbar();
        if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
            taskbar.setIconImage(dockIcon);
        }
        */

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