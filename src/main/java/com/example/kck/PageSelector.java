package com.example.kck;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageSelector extends GridPane {

    private Text pageNumber;

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

        pageNumber = new Text("1");
        pageNumber.setStrokeType(StrokeType.OUTSIDE);
        pageNumber.setFont(new Font(16.0));
        setColumnIndex(pageNumber, 2);
        getChildren().add(pageNumber);

        Text text2 = new Text("<<");
        text2.setStrokeType(StrokeType.OUTSIDE);
        text2.getStyleClass().add("page-text");
        setColumnIndex(text2, 1);
        getChildren().add(text2);

        Text text3 = new Text(">>");
        text3.setStrokeType(StrokeType.OUTSIDE);
        text3.getStyleClass().add("page-text");
        setColumnIndex(text3, 3);
        getChildren().add(text3);
    }

    public int getPageNumber() {
        String pageText = pageNumber.getText();
        return Integer.parseInt(pageText);
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber.setText(String.valueOf(pageNumber));
    }
}
