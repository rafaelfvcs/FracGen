package br.com.fracgen.application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LineChartStage {
    
    /**
     * Create a new Stage for Line Chart plot
     *
     * @throws IOException
     */
    public void createStage() throws IOException {

        FXMLLoader loader = FXMLLoader.load(
                getClass().getResource("src/main/resources/line_chart_stage.fxml"));          
        Parent parent = (Parent) loader.load();        
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("Line Chart");
        stageLine.setScene(scene);
        stageLine.show();
    }
    
}
