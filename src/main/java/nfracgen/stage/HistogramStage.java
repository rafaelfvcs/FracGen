package nfracgen.stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.ScanlineAnalysisFile;

public class HistogramStage {

    /**
     * Custom Histogram Stage
     */
    private static HistogramStage instance;

    private ScanlineAnalysisFile file;

    public HistogramStage(ScanlineAnalysisFile file) {
        instance = this;
        this.file = file;
    }

    public static HistogramStage getInstance() {
        return instance;
    }

    public ScanlineAnalysisFile getAnalysisFile() {
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
         * Get the headers of columns
         */
        if (getAnalysisFile() != null) {
            List list = new ArrayList();
            for (int i = 0; i < getAnalysisFile().getHeaderArray().size(); i++) {
                list.add(getAnalysisFile().getHeaderArray(i));
            }
            ObservableList ol = FXCollections.observableArrayList(list);
            /**
             * Put columns headers on combobox
             */
            ComboBox cbColumns = (ComboBox) parent.lookup("#cbColumnIndex");
            cbColumns.setItems(ol);
        }
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
