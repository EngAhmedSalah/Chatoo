package com.ChatApp.Controller;

import com.ChatApp.Model.ServerHandler;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable , Runnable
{
    DataOutputStream out;
    DataInputStream in ;
    ServerHandler serverHandler;
    String message;
    @FXML
    private Label membersLabel;

    @FXML
    private AnchorPane messageArea;

    @FXML
    private JFXTextArea messageBox;

    @FXML
    void sendMessage(ActionEvent event) throws IOException
    {
        message = messageBox.getText();
        out.writeUTF(message);
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
            socket = new Socket("localhost", 9507);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            serverHandler = new ServerHandler(socket);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            ServerHandler serverHandler = new ServerHandler(socket);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        new Thread(serverHandler).start();
    }
}
