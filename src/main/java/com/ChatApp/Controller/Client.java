package com.ChatApp.Controller;

import com.ChatApp.DAO.DAO;
import com.ChatApp.DAO.DAOImp;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client implements Initializable, Runnable
{
    DAO daoSingleton = new DAOImp();
    //streams for sending /receiving data to/from server
    DataOutputStream out;
    DataInputStream in;

    String message;
    @FXML
    private Label membersLabel;

    @FXML
    private TextArea chatBox;

    //text area for sending new message
    @FXML
    private JFXTextArea messageBox;

    /**
     *
     * @throws IOException
     */
    //action handler for sending message button
    @FXML
    void sendMessage() throws IOException
    {
        message = messageBox.getText();

        //sending the message to server via DataOutputStream
        out.writeUTF(Login.username + "," + message);


        chatBox.appendText("Me: " + message + "\n");
        messageBox.clear();

        //handling the chat ending
        if (message.trim().equalsIgnoreCase("Bye Bye"))
        {
            //get the whole chat from chat box
            String currentUserChat = chatBox.getText();

            daoSingleton.gatDumpFile(currentUserChat);
            daoSingleton.gatDumpfileStatistics(currentUserChat);
            System.exit(0);
        }
    }


    /*handling receiving new message*/
    @Override
    public void run()
    {
        String serverResponse;
        try
        {
            String user;

            while (true)
            {
                StringBuilder message = new StringBuilder();

                //receiving message from the server
                serverResponse = in.readUTF();

                if (serverResponse == null) break;

                //split the server response to an array [username , message]
                String[] data = serverResponse.split(",");
                user = data[0];
                for (int i = 1; i < data.length; ++i)
                    message.append(data[i]);

                if (user.equalsIgnoreCase(Login.username))
                    continue;

                //close the chat and generate dump files after saying Bye Bye
                else if (message.toString().trim().equalsIgnoreCase("Bye Bye"))
                {
                    messageBox.setDisable(true);
                    break;
                }

                //show message in the messages area
                chatBox.appendText(user + ": " + message);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param url
     * @param resourceBundle
     * @description initialization method that is been compiled in the first time loading the main app
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        membersLabel.setText(Login.username);
        Socket socket;
        try
        {
            //connect a new client to the Server
            socket = new Socket("localhost", 9507);

            //define dataStreams to send/receive to/from server
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        new Thread(this).start();
    }
}
