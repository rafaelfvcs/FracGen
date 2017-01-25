package nfracgen.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.model.ScanlineAnalysisFile;
import nfracgen.stage.ScatterChartStage;
import nfracgen.util.OpenScanlineData;

/**
 * FXML Controller class
 *
 */
public class Stage_scatterchartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    protected Button btnCloseLine;

    @FXML
    protected ComboBox cbX, cbY;
    
    @FXML
    protected TextField tfGraphLabel, tfXLabel, tfYLabel;
    
    @FXML
    protected ScatterChart scatterChart;

    /**
     * Plot a chart serie on Scatter Chart Stage
     * @throws Exception 
     */
    @FXML
    protected void plot() throws Exception {        
        ScanlineAnalysisFile file = ScatterChartStage.getInstance().getAnalysisFile();
        int indexX = cbX.getSelectionModel().getSelectedIndex();
        int indexY = cbY.getSelectionModel().getSelectedIndex();
        scatterChart.setTitle(tfGraphLabel.getText());
        scatterChart.getXAxis().setLabel(tfXLabel.getText());
        scatterChart.getYAxis().setLabel(tfYLabel.getText());    
        
        ArrayList<Double> x = OpenScanlineData.openCSVFileToDouble(file.getFileName(), 
                file.getSep(), indexX, file.getHeader());
        ArrayList<Double> y = OpenScanlineData.openCSVFileToDouble(file.getFileName(), 
                file.getSep(), indexY, file.getHeader());
        scatterChart.getData().add(PlotSeries.plotLineSeries(x, y));
    }

    /**
     * Clear all series from chart
     */
    @FXML
    protected void clear() {
        scatterChart.getData().clear();
    }

    /**
     * Close the Scatter Chart Stage
     */
    @FXML
    protected void close() {
        Stage stage = (Stage) btnCloseLine.getScene().getWindow();
        stage.close();
    }
    
}
