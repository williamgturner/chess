module com.willturner.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.willturner.chess to javafx.fxml;
    exports com.willturner.chess;
    exports com.willturner.chess.pieces;
    opens com.willturner.chess.pieces to javafx.fxml;
}