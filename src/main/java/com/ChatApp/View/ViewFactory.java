package com.ChatApp.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory
{
    public static ViewFactory defaultFactory = new ViewFactory();

    private final String MAIN_SCREEN = "/view/mainApp.fxml";
    private final String SIGNUP_SCREEN = "/view/register.fxml";
    private final String LOGIN_SCREEN = "/view/login.fxml";

    public void showRegistration()
    {
        Scene scene = new Scene(initializeScene(SIGNUP_SCREEN));
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Parent showLogin()
    {
        return initializeScene(LOGIN_SCREEN);
    }

    public Parent showMainApp()
    {
        return initializeScene(MAIN_SCREEN);
    }

    private Parent initializeScene(String fxmlPath)
    {
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            parent = loader.load();
        } catch (Exception e) {
            return null;
        }

        return parent;
    }
}
