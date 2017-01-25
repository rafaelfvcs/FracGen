package nfracgen.stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import nfracgen.database.InternalDatabase;
import nfracgen.javafxapplication.FracGenApplication;

public class LoadAnalysisStage {
    
    public void createStage() throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass()
                        .getResource("/views/stage_loadanalysis.fxml"));
        Parent parent= (Parent) loader.load();
        
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        
        /**
         * Get analysis saved on database and add to the listview
         */
        ListView lv = (ListView) parent.getScene().lookup("#tfAnalysisNames");
        //linha abaixo nao coloquei o nome do database
        //talvez nao precise se o aplicativo usar apenas um
        InternalDatabase db = new InternalDatabase("");
        ArrayList<String> al = db.loadAnalysisNames();
        ObservableList ol = FXCollections.observableArrayList(al);
        lv.setItems(ol);
        
        stage.setTitle("Load Analysis");
        stage.setScene(scene);
        stage.show();
    }
    
}
