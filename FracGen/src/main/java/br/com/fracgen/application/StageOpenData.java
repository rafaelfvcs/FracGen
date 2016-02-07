package br.com.fracgen.application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StageOpenData {

    final String strStageTitle = "Open Dataset";

    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(ApplicationTest.getInstance().
                getClass().getClassLoader().getResource("fxml/OpenDataLayout.fxml"));
        Stage stage = new Stage();
        stage.setTitle(strStageTitle);
        GridPane parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.showAndWait();        
    }
}
