package nfracgen.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.ScanlineAnalysis;

public class SaveAnalysisStage {
    
    private final ScanlineAnalysis analysis;
    
    public SaveAnalysisStage(ScanlineAnalysis analysis){
        this.analysis = analysis;
    }
    
    public void createStage() throws IOException{
        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass()
                        .getResource("/views/stage_saveanalysis.fxml"));
        Parent parent = (Parent) loader.load();
        
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Save Analysis");
        stage.setScene(scene);
        stage.show();
    }
    
}
