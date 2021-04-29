package com.ChatApp.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainAppController
{
    @FXML
    private Label membersLabel;

    @FXML
    private AnchorPane messageArea;

    @FXML
    private JFXTextArea messageBox;

    @FXML
    void sendMessage(ActionEvent event) {

    }
}
