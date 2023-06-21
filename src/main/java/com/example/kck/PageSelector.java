package com.example.kck;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PageSelector extends GridPane {

    private Text increse, decrese;

    public PageSelector() {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.SOMETIMES);
        column2.setHalignment(HPos.CENTER);
        column2.setPrefWidth(40.0);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setHgrow(Priority.SOMETIMES);
        column3.setHalignment(HPos.CENTER);
        column3.setPrefWidth(50.0);

        ColumnConstraints column4 = new ColumnConstraints();
        column4.setHgrow(Priority.SOMETIMES);
        column4.setHalignment(HPos.CENTER);
        column4.setPrefWidth(40.0);

        ColumnConstraints column5 = new ColumnConstraints();
        column5.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(column1, column2, column3, column4, column5);

        RowConstraints row = new RowConstraints();
        row.setMinHeight(10.0);
        row.setPrefHeight(30.0);
        row.setValignment(VPos.CENTER);
        row.setVgrow(Priority.SOMETIMES);

        getRowConstraints().add(row);

        Text pageNumber = new Text(String.valueOf(Settings.getInstance().getPageNumber()));
        pageNumber.setStrokeType(StrokeType.OUTSIDE);
        pageNumber.setFont(new Font(16.0));
        setColumnIndex(pageNumber, 2);
        getChildren().add(pageNumber);

        decrese = new Text("<<");
        decrese.setStrokeType(StrokeType.OUTSIDE);
        decrese.getStyleClass().add("page-text");
        setColumnIndex(decrese, 1);
        getChildren().add(decrese);

        increse = new Text(">>");
        increse.setStrokeType(StrokeType.OUTSIDE);
        increse.getStyleClass().add("page-text");
        setColumnIndex(increse, 3);
        getChildren().add(increse);

        if(Settings.getInstance().getPageNumber() == 1)
            decrese.setVisible(false);

    }

    public void  increse(){
        Settings.getInstance().setPageNumber(Settings.getInstance().getPageNumber() + 1);
    }

    public void decrese(){
        if(Settings.getInstance().getPageNumber() > 1){
            Settings.getInstance().setPageNumber(Settings.getInstance().getPageNumber() - 1);
        }
    }
    public Text getIncrese() {
        return increse;
    }

    public Text getDecrese() {
        return decrese;
    }

    public void hideRightArrow(boolean b){
        increse.setVisible(b);
    }

}
