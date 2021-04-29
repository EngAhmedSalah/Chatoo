

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.text.ViewFactory;

public class App extends Application
{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}