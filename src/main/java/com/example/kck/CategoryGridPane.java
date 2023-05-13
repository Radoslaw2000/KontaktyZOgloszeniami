package com.example.kck;

import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.Arrays;
import java.util.List;

public class CategoryGridPane extends GridPane {

    private List<String[]> categories = Arrays.asList(
            new String[]{"/com/example/kck/Kategorie/7.png", "Wszystko"},
            new String[]{"/com/example/kck/Kategorie/2.png", "Budownictwo"},
            new String[]{"/com/example/kck/Kategorie/3.png", "Dom i ogród"},
            new String[]{"/com/example/kck/Kategorie/16.png", "Transport"},
            new String[]{"/com/example/kck/Kategorie/5.png", "Elektronika"},
            new String[]{"/com/example/kck/Kategorie/8.png", "Edukacja"},
            new String[]{"/com/example/kck/Kategorie/9.png", "Praca"},
            new String[]{"/com/example/kck/Kategorie/10.png", "Biznes"},
            new String[]{"/com/example/kck/Kategorie/12.png", "Rolnictwo"},
            new String[]{"/com/example/kck/Kategorie/13.png", "Motoryzacja"},
            new String[]{"/com/example/kck/Kategorie/17.png", "Turystyka"},
            new String[]{"/com/example/kck/Kategorie/18.png", "Zdrowie"},
            new String[]{"/com/example/kck/Kategorie/19.png", "Uroda"},
            new String[]{"/com/example/kck/Kategorie/20.png", "Uroczystości"},
            new String[]{"/com/example/kck/Kategorie/24.png", "Poznajmy się"}

    );

    public CategoryGridPane(int columns, VBox content) {
        int kolumny = Math.min(Math.max(3, columns), 10);
        int wiersze = (int) Math.ceil((double) categories.size() / kolumny);

        // kolumny
        for (int i = 0; i < kolumny; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.SOMETIMES);
            col.setMinWidth(120.0);
            this.getColumnConstraints().add(col);
        }

        // wiersze
        for (int i = 0; i < wiersze; i++) {
            RowConstraints row = new RowConstraints();
            row.setMinHeight(100.0);
            row.setVgrow(Priority.SOMETIMES);
            this.getRowConstraints().add(row);
        }

        // dzieci
        int i = 0;
        for (String[] category : categories) {
            CategoryTile ct = new CategoryTile(category[0], category[1], content);
            this.add(ct, i % kolumny, i / kolumny);
            i++;
        }

        // margines
        this.setVgap(5.0);
        this.setHgap(5.0);
        Insets margin = new Insets(5.0, 15.0, 5.0, 5.0);
        VBox.setMargin(this, margin);
    }
}

