package com.ChatApp.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory
{
    public static ViewFactory defaultFactory = new ViewFactory();
    public  static Stage loginStage = new Stage();
    public  static Stage registerStage = new Stage();
    public static Stage mainAppStage = new Stage();

    private ViewFactory()
    {}

    public static ViewFactory getDefaultFactory()
    {
        return defaultFactory;
    }

    public void showRegistration()
    {
        Parent parent = null;
        loginStage.close();
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        registerStage.setScene(new Scene(parent , 400 , 500));
        registerStage.show();
    }

    public void showLogin()
    {
        registerStage.close();
        Parent parent = null;
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        loginStage.setScene(new Scene(parent , 800 , 600));
        loginStage.show();
    }

    public void showMainApp()
    {
        FXMLLoader loader;
        Parent parent = null;
        loginStage.close();
        try
        {
            loader = new FXMLLoader(getClass().getResource("/view/mainApp.fxml"));
            parent = loader.load();
        } catch (Exception e)
        {
        }
        mainAppStage.setScene(new Scene(parent , 400 , 600));
        mainAppStage.show();
    }
}
