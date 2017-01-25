package nfracgen.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import nfracgen.analysis.FractureIntensityAnalysis;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.statistic.linearregression.LinearRegression;

public class PowerLawStage {
    
    private final FractureIntensityAnalysis analysis;
    private Scene scene;
    private final double initX = 0.01;
    private final double endX = 10000;
    
    public PowerLawStage(FractureIntensityAnalysis analysis){
        this.analysis = analysis;
    }

    /**
     * Create a new Stage for Line Chart plot with Power Law values
     *
     * @throws IOException
     */
    public void createStage() throws IOException, Exception {

        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass().getResource("/views/stage_powerlaw.fxml"));
        Parent parent = (Parent) loader.load();
        Stage stageLine = new Stage();
        scene = new Scene(parent);
        stageLine.setTitle("Power Law");
        stageLine.setScene(scene);
        stageLine.show();
        plot();
    }        
    
    private void plot() throws Exception{
        LineChart chartAux = (LineChart) scene.getRoot().lookup("#lcAux");
        LinearRegression lr  = new LinearRegression(analysis.ap, analysis.cumLog10);                
        LineChart.Series seriesAux = new XYChart.Series();
        seriesAux.getData().add(new XYChart.Data(initX, lr.getValueAt(initX)));
        seriesAux.getData().add(new XYChart.Data(endX, lr.getValueAt(endX)));
        chartAux.getData().add(seriesAux);
        LineChart.Series series = PlotSeries.plotLineSeries(analysis.ap, analysis.cumLog10);
        LineChart chart = (LineChart) scene.getRoot().lookup("#scFractureIntensity");
        //Series series = PlotSeries.plotLineSeries(analysis.apLog10,analysis.cumLog10);
        //chartAux.getData().add(series);
        chart.getData().add(series);
    }
}
