package com.ChatApp.Controller;

import com.ChatApp.View.ViewFactory;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client implements Initializable , Runnable
{
    DataOutputStream out;
    DataInputStream in ;
    String message;
    @FXML
    private Label membersLabel;

    @FXML
    private TextArea chatBox;

    @FXML
    private JFXTextArea messageBox;

    @FXML
    void sendMessage(ActionEvent event) throws IOException
    {
        message = messageBox.getText();
        out.writeUTF(message);

        chatBox.appendText("Me : " + message + "\n");
        messageBox.clear();
    }

    @Override
    public void run()
    {
        String serverResponse;
        try
        {
            while (true)
            {
                serverResponse = in.readUTF();
                if(serverResponse == null) break;
                System.out.println(serverResponse);
                chatBox.appendText(Login.username + " : " + serverResponse);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        Socket socket = null;
        try
        {
            System.out.println("this is the username" + Login.username);
            socket = new Socket("localhost", 9507);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            //serverHandler = new ServerHandler(socket);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        new Thread(this).start();
    }
}
