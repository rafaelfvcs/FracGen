package nfracgen.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.model.AnalysisFile;
import nfracgen.stage.LineChartStage;

/**
 * FXML Controller class
 *
 */
public class Stage_linechartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    protected TextField tfGraphLabel, tfXLabel, tfYLabel, tfSerieLabel;

    @FXML
    private ComboBox comboBoxX, comboBoxY;

    @FXML
    protected LineChart lineChart;
    
    @FXML
    protected Button btnCloseLine;
    
    /**
     * Plot in Line Chart using the parameter defined by the user on
     * Line Chart Stage.
     */
    @FXML
    protected void plotLine() {
        AnalysisFile file = LineChartStage.getInstance().getAnalysisFile();       
        int indexX = comboBoxX.getSelectionModel().getSelectedIndex();
        int indexY = comboBoxY.getSelectionModel().getSelectedIndex();
        lineChart.setTitle(tfGraphLabel.getText());
        lineChart.getXAxis().setLabel(tfXLabel.getText());
        lineChart.getYAxis().setLabel(tfYLabel.getText());        
        lineChart.getData().add(PlotSeries.plotLineSeries(file.getFileName(),
                file.getSep(), tfSerieLabel.getText(), indexX, indexY));
    }
    
    /**
     * Clear the Line Chart. This procedure is used for the button 'clear'.
     */
    @FXML
    protected void clearLineChart() {
        lineChart.getData().clear();
    }
    
    /**
     * Close the Line Chart Stage.
     * @throws IOException 
     */
    @FXML
    protected void closeLineStage() throws IOException {
        Stage stageLine = (Stage) btnCloseLine.getScene().getWindow();
        stageLine.close();
    }
    
}
