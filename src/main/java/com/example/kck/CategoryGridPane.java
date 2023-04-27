package com.example.kck;

import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class CategoryGridPane extends GridPane {

    public CategoryGridPane() {
        // kolumny
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.SOMETIMES);
        col1.setMinWidth(120.0);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        col2.setMinWidth(120.0);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.SOMETIMES);
        col3.setMinWidth(120.0);

        // wiersze
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(100.0);
        row1.setVgrow(Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(100.0);
        row2.setVgrow(Priority.SOMETIMES);

        this.getColumnConstraints().addAll(col1, col2, col3);
        this.getRowConstraints().addAll(row1, row2);

        // dzieci
        CategoryTile ct1 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");
        CategoryTile ct2 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");
        CategoryTile ct3 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");
        CategoryTile ct4 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");
        CategoryTile ct5 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");
        CategoryTile ct6 = new CategoryTile("/com/example/kck/Kategorie/1.png", "Nieruchomości");

        this.add(ct1, 0, 0);
        this.add(ct2, 1, 0);
        this.add(ct3, 2, 0);
        this.add(ct4, 0, 1);
        this.add(ct5, 1, 1);
        this.add(ct6, 2, 1);

        // margines
        this.setVgap(5.0);
        this.setHgap(5.0);
        Insets margin = new Insets(5.0, 5.0, 5.0, 5.0);
        VBox.setMargin(this, margin);
    }
}
