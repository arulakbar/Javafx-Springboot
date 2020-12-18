package com.example.JavafxSpringboot.controller;

import com.example.JavafxSpringboot.application.JavaFxApplication;
import com.example.JavafxSpringboot.service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LoginController {

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXPasswordField passField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Text userText;

    @FXML
    private Text passText;

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = userField.getText();
        String pass = passField.getText();

        if (userService.authentication(username, pass)){

            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(MainController.class.getResource("/fxml/main-stage.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginButton.getScene().getWindow().hide();
        } else {
            userText.setText("");
            passText.setText("");
            passText.setText("User/Password salah");
            passField.setText("");
        }

        if (username.isBlank()){
            userText.setText("User tidak boleh kosong");
        }
        if (pass.isBlank()){
            passText.setText("Password tidak boleh kosong");
        }


    }

}

