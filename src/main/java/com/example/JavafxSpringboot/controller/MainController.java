package com.example.JavafxSpringboot.controller;

import com.example.JavafxSpringboot.application.JavaFxApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {


    @FXML
    private BorderPane borderpane;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUi("productlist");
    }

    @FXML
    private void buttonOne(ActionEvent event) throws IOException {
        loadUi("productlist");
    }

    @FXML
    private void buttonTwo(ActionEvent event) throws IOException {
        loadUi("scene2");
    }

    @FXML
    private void buttonThree(ActionEvent event) throws IOException {
        loadUi("scene3");
    }

    private void loadUi(String url) throws IOException {
        Parent root;
        try {
            ClassPathResource fxml = new ClassPathResource("fxml/"+url+".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
            fxmlLoader.setControllerFactory(JavaFxApplication.getApplicationContext()::getBean);
            root = fxmlLoader.load();
        } catch (IOException e){
            throw new IOException(e);
        }
        borderpane.setCenter(root);
    }
}
