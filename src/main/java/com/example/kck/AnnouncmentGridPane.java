package com.example.kck;

import com.example.kck.controllers.ShowAnnouncmentDetailsController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AnnouncmentGridPane extends GridPane {

    public AnnouncmentGridPane(Announcment announcment) {
        // ustawienia siatki
        setPrefHeight(60);
        getStyleClass().add("ogloszenie");
        setPadding(new Insets(0, 5, 0, 5));


        // definicja kolumn
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        col1.setMinWidth(10);
        col1.setPrefWidth(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setPrefWidth(120);
        getColumnConstraints().addAll(col1, col2);

        // definicja wierszy
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10);
        row1.setPrefHeight(30);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10);
        row2.setPrefHeight(30);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        getRowConstraints().addAll(row1, row2);

        // dodanie dzieci
        Text titleText = new Text(announcment.getTitle());
        titleText.setFont(new Font(18));
        Text priceText = new Text(announcment.getPrice() + " zł");
        priceText.setFont(new Font(18));
        Text locationText = new Text(announcment.getTown());
        add(titleText, 0, 0);
        add(priceText, 1, 0);
        add(locationText, 0, 1);

        // marginesy
        GridPane.setMargin(titleText, new Insets(0, 0, 0, 5));
        GridPane.setMargin(priceText, new Insets(0, 0, 0, 5));
        GridPane.setMargin(locationText, new Insets(0, 0, 0, 5));
        GridPane.setMargin(this, new Insets(0, 25, 0, 5));

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double sceneWidth = ((Node) event.getSource()).getScene().getWidth();
                double sceneHeight = ((Node) event.getSource()).getScene().getHeight();

                Platform.runLater(() -> {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAnnouncmentDetailsWindow.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root, sceneWidth, sceneHeight);
                    stage.setScene(scene);

                    ShowAnnouncmentDetailsController controller = loader.getController();
                    controller.initialize(announcment);
                    stage.show();
                });
            }
        });
    }
}
