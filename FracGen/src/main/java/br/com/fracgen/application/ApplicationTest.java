package br.com.fracgen.application;


import br.com.fracgen.util.LimitedTextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationTest extends Application {

    public LimitedTextField csv = new LimitedTextField();
    
    private static ApplicationTest instance;    

    public ApplicationTest() {
        instance = this;
    }

    public static ApplicationTest getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("NFracGen");

        Pane root = FXMLLoader.load(getClass().getResource("/LayoutMain.fxml"));

        Scene scene = new Scene(root, 700, 500); // 600, 400
        scene.getStylesheets().add("styles.css");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

}