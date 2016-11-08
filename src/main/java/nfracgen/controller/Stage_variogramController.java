package nfracgen.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.model.AnalysisFile;
import nfracgen.model.Matrix;
import nfracgen.stage.VariogramStage;
import nfracgen.statistic.Variogram;
import nfracgen.statistic.VariogramModels;
import nfracgen.util.OpenScanlineData;

/**
 * FXML Controller class
 */
public class Stage_variogramController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    protected ComboBox cbDatasets, cbContent, cbX, cbY;
    
    @FXML 
    protected TextField tfInitial, tfStep, tfMax, tfMinAngle, tfMaxAngle;
            
    @FXML
    protected LineChart lcVariogram;
    
    /**
     * Plot the a variogram serie on Variogram Stage using the defined 
     * properties by the user. 
     * The properties the user can define are: which columns represent the
     * vectors X and Y, minimum and maximum angle of tolerance, 
     * the step size, initial distance and maximum distance.
     * 
     * @throws Exception 
     */
    @FXML
    protected void plot() throws Exception{
        AnalysisFile file = VariogramStage.getInstance().getAnalysisFile();        
        
        int xIndex = cbX.getSelectionModel().getSelectedIndex();
        ArrayList<Double> x = OpenScanlineData.openCSVFileToDouble(file.getFileName(), 
                file.getSep(), xIndex,file.getHeader());
        int yIndex = cbY.getSelectionModel().getSelectedIndex();
        ArrayList<Double> y = OpenScanlineData.openCSVFileToDouble(file.getFileName(), 
                file.getSep(), yIndex, file.getHeader());        
        int contentIndex = cbContent.getSelectionModel().getSelectedIndex();        
        ArrayList<Double> content = OpenScanlineData.openCSVFileToDouble(file.getFileName(), 
                file.getSep(), contentIndex, file.getHeader());
        
        double initDist = Double.valueOf(tfInitial.getText().trim());
        double stepSize = Double.valueOf(tfStep.getText().trim());
        double maxDist = Double.valueOf(tfMax.getText().trim());
        //TODO: implement tolerance angles
        //double minAngle = Double.valueOf(tfMinAngle.getText().trim());
        //double maxAngle = Double.valueOf(tfMaxAngle.getText().trim());        
        Matrix result = Variogram.variogram2D(x, y, content, initDist, 
                stepSize, maxDist);        
        
        //Matrix result = Variogram.variogram2D(matrix, initDist, stepSize, 
          //      maxDist, xIndex, yIndex, contentIndex);
          ArrayList<Double> alX = new ArrayList<>();
          ArrayList<Double> alY = new ArrayList<>();
        for(int i=0; i<result.getLinesCount(); i++){
            alX.add(result.get(0, i).doubleValue());
            alY.add(result.get(1, i).doubleValue());            
        }        
        lcVariogram.getData().addAll(PlotSeries.plotLineSeries(alX, alY));
        
    }
    
    @FXML
    protected ComboBox cbModel;
    
    @FXML
    protected TextField tfFitSill, tfFitRange, tfFitStep;    
    
    @FXML
    protected void plotModel() throws Exception{
        if(cbModel.getSelectionModel().getSelectedIndex()>=0){
            if(!tfFitSill.getText().isEmpty()){
                if(!tfFitRange.getText().isEmpty()){
                    if(!tfFitStep.getText().isEmpty()){
                        double vSill = Double.valueOf(tfFitSill.getText());
                        double vRange = Double.valueOf(tfFitRange.getText());
                        double vStep = Double.valueOf(tfFitStep.getText());
                        Matrix matrix =null;
                        String serieName = "";
                        switch(cbModel.getSelectionModel().getSelectedIndex()){
                            case 0:
                                matrix = VariogramModels.spherical(vSill, vRange, vStep);
                                serieName = "Spherical";
                                break;
                            case 1:
                                matrix = VariogramModels.exponential(vSill, vRange, vStep);
                                serieName = "Exponential";
                                break;
                            case 2:
                                matrix = VariogramModels.gaussian(vSill, vRange, vStep);
                                serieName = "Gaussian";
                                break;
                            default:
                                break;
                        }
                        lcVariogram  = (LineChart) cbModel.getScene().lookup("#lcVariogram");                                
                        lcVariogram.getData().add(
                                PlotSeries.plotVariogramModel(matrix, 0, 1, serieName));
                    }
                }
            }
        }
        
    }
    
}
