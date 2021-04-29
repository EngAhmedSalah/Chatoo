package com.ChatApp.Controller;

import com.ChatApp.DAO.DAO;
import com.ChatApp.DAO.DAOImp;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;

public class Register
{
    Security singleton = Security.getSecurityInstance();
    DAO dao = new DAOImp();
    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField confirmationPasswordField;

    @FXML
    private Label successLabel , errorLabel;

    @FXML
    void registerNewUser(ActionEvent event) throws IOException, URISyntaxException
    {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmationPasswordField.getText();
        if(singleton.confirmPassword(password , confirmPassword) != null)
        {
            dao.saveUser(username  + "," + singleton.hashing(password + username));
            successLabel.setVisible(true);
        }
        else
            errorLabel.setVisible(true);
    }

}
