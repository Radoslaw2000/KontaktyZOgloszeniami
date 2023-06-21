package com.example.kck;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

import static java.util.Objects.isNull;

public class CategoryTile extends VBox {
    private ImageView imageView;
    private Text categoryName;
    private boolean isLastPage;

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

                Settings.getInstance().setCategory(categoryText);
                //content.getChildren().clear();
                showList(content);
            }
        });
    }

    private void setEventsToPageSelector(PageSelector ps, VBox content){
        ps.getIncrese().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ps.increse();
                showList(content);
            }
        });
        ps.getDecrese().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ps.decrese();
                showList(content);
            }
        });
    }

    private void loadContacts(VBox content){
        DBMenager dbMenager = new DBMenager();
        List<Announcment> announcments = dbMenager.selectAnnouncmentFiltered(Settings.getInstance().getPageNumber(), Settings.getInstance().getContactsNumberOnPage(), "", "", "", "", "", "","");
        isLastPage = announcments.size() != Settings.getInstance().getContactsNumberOnPage();

        for (Announcment announcment : announcments) {
            content.getChildren().add(new AnnouncmentGridPane(announcment));
        }
    }
    private void showList(VBox content){
        PageSelector ps = new PageSelector();
        PageSelector ps2 = new PageSelector();
        setEventsToPageSelector(ps, content);
        setEventsToPageSelector(ps2, content);

        content.getChildren().clear();
        content.getChildren().add(ps);
/*
        if(Settings.getInstance().isFavourite()){
            favouriteHBox.getStyleClass().clear();
            favouriteHBox.getStyleClass().add("dodaj-contact-button2");
            //loadFavouriteContacts();
        }
        else */
        loadContacts(content);

        content.getChildren().add(ps2);
        ps.hideRightArrow(!isLastPage);
        ps2.hideRightArrow(!isLastPage);
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