package com.example.kck;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CategoryTile extends VBox {
    private ImageView imageView;
    private Text categoryName;

    /*
        String image2 = "@Kategorie/1.png";
        String text2 = "Nieruchomości";
    */

    public CategoryTile(String categoryImage, String categoryText ) {
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