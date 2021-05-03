package com.ChatApp.Controller;

import com.ChatApp.View.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URISyntaxException;

public class Login
{

   /* singleton object from Security class for authentication and authorization*/
    Security singleton = Security.getSecurityInstance();

    /* singleton object from ViewFactory class for defining the scenes*/
    ViewFactory viewFactory = ViewFactory.getDefaultFactory();


    public static String username;

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


    /**
     * @description for authenticate that the username and password is vaild
     */
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