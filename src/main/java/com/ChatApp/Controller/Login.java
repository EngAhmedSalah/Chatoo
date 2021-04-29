package com.ChatApp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login
{
    Security singleton = Security.getSecurityInstance();

    @FXML
    private Pane content_area;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    void showRegister(MouseEvent event)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    @FXML
    void authentication()
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + " " + password);
        if(singleton.auth(username , password))
            System.out.println("Success");
        else
            errorLabel.setVisible(true);
    }
}
