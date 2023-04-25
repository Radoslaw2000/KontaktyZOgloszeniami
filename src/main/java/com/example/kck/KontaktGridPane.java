package com.example.kck;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class KontaktGridPane extends GridPane {

    public KontaktGridPane() {
        this.getStyleClass().add("kontakt");
        this.setGridLinesVisible(true);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.NEVER);
        col1.setMinWidth(30.0);
        col1.setPrefWidth(30.0);
        col1.setHalignment(javafx.geometry.HPos.CENTER);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(100.0);

        col2.setMaxWidth(300.0);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(100.0);

        col3.setMaxWidth(300.0);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col4.setMinWidth(10.0);
        this.getColumnConstraints().addAll(col1, col2, col3, col4);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        this.getRowConstraints().add(row1);

        Text imie = new Text("Imie");
        imie.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        imie.setStrokeWidth(0.0);
        this.add(imie, 1, 0);
        GridPane.setMargin(imie, new Insets(0, 0, 0, 10));

        Text nazwisko = new Text("Nazwisko");
        nazwisko.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        nazwisko.setStrokeWidth(0.0);
        this.add(nazwisko, 2, 0);
        GridPane.setMargin(nazwisko, new Insets(0, 0, 0, 10));

        Text adres = new Text("Adres");
        adres.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        adres.setStrokeWidth(0.0);
        this.add(adres, 3, 0);
        GridPane.setMargin(adres, new Insets(0, 0, 0, 10));

        ImageView imageView = new ImageView(new Image(getClass().getResource("/com/example/kck/R.png").toExternalForm()));
        imageView.setFitHeight(25.0);
        imageView.setFitWidth(25.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        this.add(imageView, 0, 0);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
                double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("WyswietlKontaktWindow.fxml"));
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