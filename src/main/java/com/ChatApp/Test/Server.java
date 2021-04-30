package com.ChatApp.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends Application
{
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        // Text area for displaying contents
        TextArea ta = new TextArea();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        ta.appendText("Server is running -------------------\n");
        new Thread(() ->
        {
            try
            {
                ServerSocket server = new ServerSocket(4242);
                Platform.runLater(() ->
                        ta.appendText("Server Started at : " + new Date() + "\n"));
                //accept connection to server
                Socket socket = server.accept();

                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outFromClient = new DataOutputStream(socket.getOutputStream());

                while (true)
                {
                    double r = inputFromClient.readDouble();
                    outFromClient.writeDouble(Math.PI * r * r);
                    Platform.runLater(() ->
                            ta.appendText("radius received from client : " + r + "\n"));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    }
}