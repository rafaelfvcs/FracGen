package nfracgen.stage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.AnalysisFile;

public class VariogramStage {

    private static VariogramStage instance;
    private static AnalysisFile file;

    public static VariogramStage getInstance() {
        return instance;
    }

    public VariogramStage(AnalysisFile file) {
        instance = this;
        this.file = file;
    }

    public AnalysisFile getAnalysisFile() {
        return file;
    }

    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass().getResource(
                        "/views/stage_variogram.fxml"));
        Parent parent = (Parent) loader.load();

        /**
         * Put columns headers on comboboxes
         */
        ComboBox cbXColumn = (ComboBox) parent.lookup("#cbX");
        cbXColumn.setItems(FXCollections.observableArrayList(
                file.getHeaderArray()));
        ComboBox cbYColumn = (ComboBox) parent.lookup("#cbY");
        cbYColumn.setItems(FXCollections.observableArrayList(
                file.getHeaderArray()));
        ComboBox cbContentColumn = (ComboBox) parent.lookup("#cbContent");
        cbContentColumn.setItems(FXCollections.observableArrayList(
                file.getHeaderArray()));

        /**
         * Create and show stage
         */
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("2D Variogram");
        stageLine.setScene(scene);
        stageLine.show();
    }

}
