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

public class HistogramStage {

    /**
     * Custom Histogram Stage
     */
    private static HistogramStage instance;

    private AnalysisFile file;

    public HistogramStage(AnalysisFile file) {
        instance = this;
        this.file = file;
    }

    public static HistogramStage getInstance() {
        return instance;
    }

    public AnalysisFile getAnalysisFile() {
        return file;
    }

    /**
     * Create a stage for plotting custom histogram.
     *
     * @throws IOException
     */
    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(FracGenApplication.getInstance().
                getClass().getResource(
                        "/views/stage_histogram.fxml"));
        Parent parent = (Parent) loader.load();

        /**
         * Put columns headers on combobox
         */
        ComboBox cbColumns = (ComboBox) parent.lookup("#cbColumnIndex");
        cbColumns.setItems(FXCollections.observableArrayList(
                file.getHeaderArray()));
        /**
         * Create and show stage
         */
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("Custom Histogram");
        stageLine.setScene(scene);
        stageLine.show();
    }
}
