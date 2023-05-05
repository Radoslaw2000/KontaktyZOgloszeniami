package com.example.kck;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

import java.io.IOException;

public class OgloszenieGridPane extends GridPane {

    public OgloszenieGridPane() {
        // ustawienie stylu z FXML-a
        getStyleClass().add("kontakt");

        // dodanie kolumny
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        getColumnConstraints().add(column1);

        // dodanie wierszy
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        row1.setVgrow(Priority.NEVER);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10.0);
        row2.setPrefHeight(30.0);
        row2.setValignment(javafx.geometry.VPos.TOP);
        row2.setVgrow(Priority.NEVER);

        getRowConstraints().addAll(row1, row2);

        // dodanie elementów
        HBox hbox = new HBox();
        hbox.setPrefHeight(100.0);
        hbox.setPrefWidth(200.0);
        hbox.setSpacing(20.0);
        hbox.setAlignment(Pos.CENTER_LEFT);

        Text title = new Text("Mechanik");
        title.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        title.setStrokeWidth(0.0);
        title.setFont(Font.font("System Bold", 18.0));
        title.setWrappingWidth(200.0);
        HBox.setMargin(title, new Insets(0, 0, 0, 10));
        hbox.getChildren().add(title);

        Text address = new Text("Adres");
        address.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        address.setStrokeWidth(0.0);
        address.setFont(Font.font(18.0));
        hbox.getChildren().add(address);

        Text description = new Text("Opis");
        description.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        description.setStrokeWidth(0.0);
        description.setFont(Font.font(14.0));
        description.setWrappingWidth(36.899658203125);
        GridPane.setMargin(description, new Insets(0, 0, 0, 10));
        GridPane.setRowIndex(description, 1);
        getChildren().addAll(hbox, description);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
                double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAnnouncmentDetailsWindow.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Scene scene = new Scene(root, sceneWidth, sceneHeight);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
