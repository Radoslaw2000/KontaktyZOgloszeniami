package com.example.kck;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//do usunięcia
public class AnnouncmentFilter extends VBox {

    private TextField fromPriceField;
    private TextField toPriceField;
    private CheckBox newCheckBox;
    private CheckBox usedCheckBox;
    private CheckBox damagedCheckBox;
    private TextField cityField;
    private MenuButton regionMenu;

    public AnnouncmentFilter() {

        // Cena
        Text priceLabel = new Text("Cena:");
        priceLabel.setFont(new Font(16));
        VBox.setMargin(priceLabel, new Insets(0, 0, 5, 10));

        fromPriceField = new TextField();
        fromPriceField.getStyleClass().add("text-field");
        fromPriceField.setPromptText("Od");
        toPriceField = new TextField();
        toPriceField.getStyleClass().add("text-field");
        toPriceField.setPromptText("Do");

        Text dashText = new Text("-");
        dashText.setFont(new Font(20));

        HBox priceFieldsBox = new HBox(fromPriceField, dashText, toPriceField);
        priceFieldsBox.setAlignment(javafx.geometry.Pos.CENTER);
        priceFieldsBox.setSpacing(10);
        priceFieldsBox.setPadding(new Insets(0, 10, 0, 10));
        VBox.setMargin(priceFieldsBox, new Insets(0, 0, 10, 10));

        // Stan
        Text conditionLabel = new Text("Stan:");
        conditionLabel.setFont(new Font(16));
        VBox.setMargin(conditionLabel, new Insets(0, 0, 5, 10));

        newCheckBox = new CheckBox("nowe");
        newCheckBox.setPadding(new Insets(5, 0, 0, 10));
        usedCheckBox = new CheckBox("używane");
        usedCheckBox.setPadding(new Insets(5, 0, 0, 10));
        damagedCheckBox = new CheckBox("uszkodzone");
        damagedCheckBox.setPadding(new Insets(5, 0, 0, 10));

        VBox conditionBox = new VBox(newCheckBox, usedCheckBox, damagedCheckBox);
        VBox.setMargin(conditionBox, new Insets(0, 0, 10, 10));

        // Lokalizacja
        Text locationLabel = new Text("Lokalizacja:");
        locationLabel.setFont(new Font(16));
        VBox.setMargin(locationLabel, new Insets(0, 0, 5, 10));

        cityField = new TextField();
        cityField.setPromptText("Miejscowość");
        VBox.setMargin(cityField, new Insets(0, 10, 0, 20));

        regionMenu = new MenuButton("Województwo");
        regionMenu.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        regionMenu.setMaxWidth(240);
        regionMenu.setMinWidth(170);
        String[] wojewodztwa = {"dolnośląskie", "kujawsko-pomorskie", "lubelskie", "lubuskie", "łódzkie",
                "małopolskie", "mazowieckie", "opolskie", "podkarpackie", "podlaskie",
                "pomorskie", "śląskie", "świętokrzyskie", "warmińsko-mazurskie", "wielkopolskie",
                "zachodniopomorskie"};

        for (String woj : wojewodztwa) {
            MenuItem menuItem = new MenuItem(woj);
            menuItem.setOnAction(event -> regionMenu.setText(woj));
            regionMenu.getItems().add(menuItem);
        }
        VBox.setMargin(regionMenu, new Insets(0, 10, 0, 20));

        // Dodawanie elementów do kontenera VBox
        this.getChildren().addAll(
                priceLabel,
                priceFieldsBox,
                conditionLabel,
                conditionBox,
                locationLabel,
                cityField,
                regionMenu
        );
        this.setPadding(new Insets(10, 0, 0, 0));
        this.setSpacing(5);

    }

    public TextField getFromPriceField() {
        return fromPriceField;
    }

    public void setFromPriceField(TextField fromPriceField) {
        this.fromPriceField = fromPriceField;
    }

    public TextField getToPriceField() {
        return toPriceField;
    }

    public void setToPriceField(TextField toPriceField) {
        this.toPriceField = toPriceField;
    }

    public CheckBox getNewCheckBox() {
        return newCheckBox;
    }

    public void setNewCheckBox(CheckBox newCheckBox) {
        this.newCheckBox = newCheckBox;
    }

    public CheckBox getUsedCheckBox() {
        return usedCheckBox;
    }

    public void setUsedCheckBox(CheckBox usedCheckBox) {
        this.usedCheckBox = usedCheckBox;
    }

    public CheckBox getDamagedCheckBox() {
        return damagedCheckBox;
    }

    public void setDamagedCheckBox(CheckBox damagedCheckBox) {
        this.damagedCheckBox = damagedCheckBox;
    }

    public TextField getCityField() {
        return cityField;
    }

    public void setCityField(TextField cityField) {
        this.cityField = cityField;
    }

    public MenuButton getRegionMenu() {
        return regionMenu;
    }

    public void setRegionMenu(MenuButton regionMenu) {
        this.regionMenu = regionMenu;
    }
}
