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
import nfracgen.model.AnalysisFile;

public class ScatterChartStage {

    private static ScatterChartStage instance;
    private static AnalysisFile file;

    public ScatterChartStage(AnalysisFile file) {
        instance = this;
        this.file = file;
    }

    /**
     *
     * @return
     */
    public static ScatterChartStage getInstance() {
        return instance;
    }

    public static AnalysisFile getAnalysisFile() {
        return file;
    }

    /**
     *
     * @throws IOException
     */
    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass().getResource(
                        "views/stage_scatter_chart.fxml"));
        Parent parent = (Parent) loader.load();

        /**
         * Get the headers of columns
         */
        List list = new ArrayList();
        for (int i = 0; i < getAnalysisFile().getHeaderArray().size(); i++) {
            list.add(getAnalysisFile().getHeaderArray(i));
        }
        /**
         * Put headers on comboboxes
         */
        ObservableList ol = FXCollections.observableArrayList(list);
        ComboBox comboBoxX = (ComboBox) parent.lookup("#cbX");
        ComboBox comboBoxY = (ComboBox) parent.lookup("#cbY");
        comboBoxX.setItems(ol);
        comboBoxY.setItems(ol);

        /**
         * Create and show stage
         */
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("Scatter Chart");
        stageLine.setScene(scene);
        stageLine.show();
    }

}
