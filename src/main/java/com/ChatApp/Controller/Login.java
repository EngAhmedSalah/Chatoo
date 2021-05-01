package com.ChatApp.Controller;

import com.ChatApp.View.ViewFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URISyntaxException;

public class Login
{
    Security singleton = Security.getSecurityInstance();
    ViewFactory viewFactory = new ViewFactory();

    @FXML
    private AnchorPane mainContentArea;

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

    public Login() throws URISyntaxException { }

    @FXML
    void showRegister(MouseEvent event)
    {
        viewFactory.showRegistration();
    }


    @FXML
    void authentication() throws IOException
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username + " " + password);
        if(singleton.auth(username , password))
            {
                mainContentArea.getChildren().clear();
                mainContentArea.getChildren().add(viewFactory.showMainApp());
            }
        else
            errorLabel.setVisible(true);
    }
}