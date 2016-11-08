package nfracgen.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nfracgen.model.AnalysisFile;
import nfracgen.stage.HistogramStage;
import nfracgen.statistic.Stat;
import nfracgen.statistic.histogram.ClassInterval;
import nfracgen.statistic.histogram.Frequency;
import nfracgen.util.OpenScanlineData;

/**
 * FXML Controller class
 */
public class Stage_histogramController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    protected ComboBox cbColumnIndex;

    @FXML
    protected TextField tfIntervals, tfMaxValue, tfMinValue;

    @FXML
    protected BarChart bcHistogram;

    /**
     * Handle action for Column Index ComboBox. When this combobox is changed
     * the TextView with cutoff values must be updated. The cutoff values
     * limit the maximum and minimum values used for the histogram ploting.
     * @throws Exception 
     */
    @FXML
    protected void cbColumnAction() throws Exception {
        if (cbColumnIndex.getSelectionModel().getSelectedIndex() >= 0) {            
            int columnIndex = cbColumnIndex.getSelectionModel().getSelectedIndex();
            AnalysisFile file = HistogramStage.getInstance().getAnalysisFile();
            ArrayList<Double> vector = OpenScanlineData.openCSVFileToDouble(
                    file.getFileName(),
                    file.getSep(), columnIndex, file.getHeader());
            double min = Stat.min(vector);
            System.out.println("Minimum: " + min);
            double max = Stat.max(vector);
            tfMinValue.setText(String.valueOf(min));
            tfMaxValue.setText(String.valueOf(max));
        } else {
            throw new Exception("Combobox selected index < 0");
        }
    }

    /**
     * Handle action for Plot Button on Histogram Stage
     * 
     * @throws Exception 
     */
    @FXML
    protected void plot() throws Exception {
        if (cbColumnIndex.getSelectionModel().getSelectedIndex() >= 0) {

            if (!tfIntervals.getText().isEmpty()) {                
                int columnIndex = cbColumnIndex.getSelectionModel().getSelectedIndex();
                AnalysisFile file = HistogramStage.getInstance().getAnalysisFile();
                ArrayList<Double> vector = OpenScanlineData.openCSVFileToDouble(
                        file.getFileName(),
                        file.getSep(), columnIndex, file.getHeader());
                double min = Double.valueOf(tfMinValue.getText());
                double max = Double.valueOf(tfMaxValue.getText());
                int intervals = Integer.valueOf(tfIntervals.getText());
                ArrayList<ClassInterval> list = Frequency.classIntervals(min, max, intervals);
                Frequency.countObsFrequency(vector, list);
                XYChart.Series series = new XYChart.Series();
                series.setName(cbColumnIndex.getEditor().getText());
                for (int i = 0; i < list.size(); i++) {
                    series.getData().add(
                            new XYChart.Data(list.get(i).getLabel(),
                                    list.get(i).getObsFrequency()));
                }
                bcHistogram.getData().clear();
                bcHistogram.getData().addAll(series);

            } else {
                throw new Exception("TextField empty");
            }
        } else {
            throw new Exception("Combobox selected index < 0");
        }
    }

    /**
     * Clear all data from the chart on Histogram Stage.
     */
    @FXML
    protected void clear() {
        bcHistogram.getData().clear();
    }
    
}
