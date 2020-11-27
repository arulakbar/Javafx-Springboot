package com.example.JavafxSpringboot.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.function.Function;

public class ActionButtonTableCell<S> extends TableCell<S, JFXButton> {

    private final JFXButton actionButton;

    public ActionButtonTableCell(String label, Function< S, S> function) {
        this.getStylesheets().add("/css/styles.css");
        this.setAlignment(Pos.CENTER);

        this.actionButton = new JFXButton(label);
        this.actionButton.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
        });
//        this.actionButton.setAlignment(Pos.CENTER);
        this.actionButton.setMaxWidth(100);
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, JFXButton>, TableCell<S, JFXButton>> forTableColumn(String label, Function< S, S> function) {
        return param -> new ActionButtonTableCell<>(label, function);
    }

    @Override
    public void updateItem(JFXButton item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(actionButton);
        }
    }
}
