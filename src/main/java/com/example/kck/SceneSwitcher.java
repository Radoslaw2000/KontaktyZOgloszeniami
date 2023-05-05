package com.example.kck;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public SceneSwitcher(){}

    public void switchScene(String fxmlName){
        Stage stage = prepareNewStage(fxmlName);
        stage.show();
    }

    private Stage prepareNewStage(String fxmlName){
        Stage stage = Settings.getInstance().getStage();
        double sceneWidth = Settings.getInstance().getStage().getScene().getWidth();
        double sceneHeight = Settings.getInstance().getStage().getScene().getHeight();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setScene(scene);

        return stage;
    }





}
