package com.ChatApp.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewFactory
{
    public static ViewFactory defaultFactory = new ViewFactory();
    public static Stage primaryStage = new Stage();

    public void showRegistration()
    {
        FXMLLoader loader;
        Parent parent = null;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        primaryStage.setScene(new Scene(parent , 400 , 500));
        primaryStage.show();
    }

    public void showLogin()
    {
        FXMLLoader loader;
        Parent parent = null;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        primaryStage.setScene(new Scene(parent , 800 , 600));
        primaryStage.show();
    }

    public void showMainApp()
    {
        FXMLLoader loader;
        Parent parent = null;
        primaryStage.close();
        try {
            loader = new FXMLLoader(getClass().getResource("/view/mainApp.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        primaryStage.setScene(new Scene(parent , 400 , 600));
        primaryStage.show();
    }
}
