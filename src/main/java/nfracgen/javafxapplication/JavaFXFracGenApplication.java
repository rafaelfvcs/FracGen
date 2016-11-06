package nfracgen.javafxapplication;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nfracgen.analysis.Scanline;
import nfracgen.model.AnalysisFile;
import nfracgen.model.Scl;
import nfracgen.statistic.Stat;
import nfracgen.util.ArrayOperation;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.RoundUtil;

public class JavaFXFracGenApplication extends Application {
    
    private static JavaFXFracGenApplication instance;
    
    
    public JavaFXFracGenApplication(){
        instance = this;    
    }
    
    public static JavaFXFracGenApplication getInstance(){
        return instance;
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
        setUserAgentStylesheet(STYLESHEET_MODENA);
        stage.setScene(scene);
	stage.setTitle("Login");
        stage.setTitle("NFracGen");
        stage.show();
    }        
}
