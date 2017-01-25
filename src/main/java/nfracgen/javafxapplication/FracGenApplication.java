package nfracgen.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nfracgen.stage.MainStage;


public class FracGenApplication extends Application {
    
    private static FracGenApplication instance;
    private MainStage main = new MainStage();
    
    public FracGenApplication(){
        instance = this;    
    }
    
    public static FracGenApplication getInstance(){
        return instance;
    }
    
    public MainStage getMainStage(){
        return this.main;
    }
    
    public Stage stageOpenData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));        
        Scene scene = new Scene(root);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        //setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
	stage.setTitle("Login");
        stage.setTitle("NFracGen");
        stage.show();
    }        
}
