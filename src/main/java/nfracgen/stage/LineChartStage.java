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

public class LineChartStage {

    private static LineChartStage instance;

    private static AnalysisFile file;

    /**
     *
     * @param datasets
     */
    public LineChartStage(AnalysisFile file) {
        instance = this;
        this.file = file;
    }

    /**
     *
     * @return
     */
    public static LineChartStage getInstance() {
        return instance;
    }

    /**
     *
     * @return
     */
    public AnalysisFile getAnalysisFile() {
        return file;
    }

    /**
     *
     * @throws IOException
     */
    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass().getResource(
                        "/views/stage_linechart.fxml"));
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
             * Put columns headers on comboboxes
             */
            ComboBox comboBoxX = (ComboBox) parent.lookup("#comboBoxX");
            ComboBox comboBoxY = (ComboBox) parent.lookup("#comboBoxY");
            comboBoxX.setItems(ol);
            comboBoxY.setItems(ol);
        }

        /**
         * Create and show stage
         */
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("Line Chart");
        stageLine.setScene(scene);
        stageLine.show();

    }

}
