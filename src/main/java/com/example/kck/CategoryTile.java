package com.example.kck;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryTile extends VBox {
    private ImageView imageView;
    private Text categoryName;

    /*
        String image2 = "@Kategorie/1.png";
        String text2 = "Nieruchomości";
    */

    public CategoryTile(String categoryImage, String categoryText, VBox content ) {
        // Set style class
        this.getStyleClass().add("category-tile");

        // Set VBox properties
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(5));

        // Create ImageView with image
        imageView = new ImageView(new Image(getClass().getResource(categoryImage).toExternalForm()));
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // Create Text with category name
        categoryName = new Text(categoryText);
        categoryName.getStyleClass().add("category-text");

        // Add children to VBox
        this.getChildren().addAll(imageView, categoryName);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            OgloszenieGridPane ogloszenieGridPane;
            for(int i = 0; i<10; i++) {
                ogloszenieGridPane = new OgloszenieGridPane();
                content.getChildren().add(ogloszenieGridPane);
            }

            }
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Text getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String text) {
        this.categoryName = new Text(text);
    }
}