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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client implements Initializable , Runnable
{
    Thread t;
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
        out.writeUTF(Login.username + "," + message);
        chatBox.appendText("Me : " + message + "\n");
        System.out.println("from send Message" + Login.username);
        messageBox.clear();
        if(message.trim().equalsIgnoreCase("Bye Bye"))
        {
            String currentUserChat = chatBox.getText();
            String outFileName = "src/main/resources/database/dumbs/output.txt";
            Files.write(Paths.get(outFileName), currentUserChat.getBytes());


            System.exit(0);
        }


    }

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
                serverResponse = in.readUTF();
                if(serverResponse == null) break;
                System.out.println(serverResponse);
                String[] data = serverResponse.split(",");
                user = data[0];
                for (int i = 1 ; i < data.length ; ++i)
                    message.append(data[i]);
                if(user.equalsIgnoreCase(Login.username))
                    continue;
                else if(message.toString().trim().equalsIgnoreCase("Bye Bye"))
                {
                    messageBox.setDisable(true);
                    break;
                }

                chatBox.appendText(user + " : " + message);
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
        t = new Thread(this);
        t.start();
    }
}
