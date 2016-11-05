package br.com.fracgen.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXFracGenApplication extends Application {
    
    private static JavaFXFracGenApplication instance;
    private Parent root;
    
    public JavaFXFracGenApplication(){
        instance = this;    
    }
    
    public static JavaFXFracGenApplication getInstance(){
        return instance;
    }
    
    public Parent getRoot(){
        return root;
    }
    
    public Stage stageOpenData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scene = new Scene(root);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
	stage.setTitle("Login");
        stage.setTitle("NFracGen");
        stage.show();
    }
}
