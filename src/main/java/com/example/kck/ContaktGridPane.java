package com.example.kck;

import com.example.kck.controllers.ShowContactDetailsController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ContaktGridPane extends GridPane {

    public ContaktGridPane(Contact contact) {
        this.getStyleClass().add("kontakt");
        this.setGridLinesVisible(true);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(30.0);
        col1.setPrefWidth(30.0);
        col1.setHalignment(javafx.geometry.HPos.CENTER);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setPercentWidth(24.0);
        col2.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setPercentWidth(24.0);
        col3.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col4.setPercentWidth(24.0);
        col4.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col5 = new ColumnConstraints();
        col5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col5.setMaxWidth(Double.MAX_VALUE);
        this.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        this.getRowConstraints().add(row1);

        Text nazwisko = new Text(contact.getSurname());
        nazwisko.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        nazwisko.setStrokeWidth(0.0);
        this.add(nazwisko, 1, 0);
        GridPane.setMargin(nazwisko, new Insets(0, 0, 0, 10));

        Text imie = new Text(contact.getName());
        imie.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        imie.setStrokeWidth(0.0);
        this.add(imie, 2, 0);
        GridPane.setMargin(imie, new Insets(0, 0, 0, 10));

        Text miasto = new Text(contact.getTown());
        miasto.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        miasto.setStrokeWidth(0.0);
        this.add(miasto, 3, 0);
        GridPane.setMargin(miasto, new Insets(0, 0, 0, 10));

        Text ulica = new Text(contact.getStreet());
        ulica.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        ulica.setStrokeWidth(0.0);
        this.add(ulica, 4, 0);
        GridPane.setMargin(ulica, new Insets(0, 0, 0, 10));

        LetterCircle imageView = new LetterCircle(contact.getSurname().charAt(0));
        this.add(imageView, 0, 0);




        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
                double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

                Platform.runLater(() -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowContactDetailsWindow.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root, sceneWidth, sceneHeight);
                    stage.setScene(scene);

                    ShowContactDetailsController controller = loader.getController();
                    controller.initialize(contact);
                    stage.show();
                });
            }
        });
    }

    public ContaktGridPane() {
        this.setGridLinesVisible(true);
        this.getStyleClass().add("kontakt-header");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(30.0);
        col1.setPrefWidth(30.0);
        col1.setHalignment(javafx.geometry.HPos.CENTER);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setPercentWidth(24.0);
        col2.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setPercentWidth(24.0);
        col3.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col4.setPercentWidth(24.0);
        col4.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints col5 = new ColumnConstraints();
        col5.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col5.setMaxWidth(Double.MAX_VALUE);
        this.getColumnConstraints().addAll(col1, col2, col3, col4, col5);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        row1.setMinHeight(10.0);
        row1.setPrefHeight(30.0);
        this.getRowConstraints().add(row1);

        Text nazwisko = new Text("Nazwisko");
        nazwisko.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        nazwisko.setStrokeWidth(0.0);
        this.add(nazwisko, 1, 0);
        GridPane.setMargin(nazwisko, new Insets(0, 0, 0, 10));

        Text imie = new Text("Imie");
        imie.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        imie.setStrokeWidth(0.0);
        this.add(imie, 2, 0);
        GridPane.setMargin(imie, new Insets(0, 0, 0, 10));

        Text miasto = new Text("Miejscowość");
        miasto.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        miasto.setStrokeWidth(0.0);
        this.add(miasto, 3, 0);
        GridPane.setMargin(miasto, new Insets(0, 0, 0, 10));

        Text ulica = new Text("Ulica");
        ulica.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        ulica.setStrokeWidth(0.0);
        this.add(ulica, 4, 0);
        GridPane.setMargin(ulica, new Insets(0, 0, 0, 10));
    }

}