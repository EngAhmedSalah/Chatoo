package com.ChatApp.Controller;

import com.ChatApp.View.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class Login
{
    Security singleton = Security.getSecurityInstance();
    ViewFactory viewFactory = new ViewFactory();
    public static String username;

    @FXML
    private AnchorPane mainContentArea;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXPasswordField passwordField;

    public Login() throws URISyntaxException { }

    @FXML
    void showRegister()
    {
        viewFactory.showRegistration();
    }


    @FXML
    void authentication()
    {
        username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + " " + password);
        if(singleton.auth(username , password))
            {
                viewFactory.showMainApp();
            }
        else
            errorLabel.setVisible(true);
    }
}