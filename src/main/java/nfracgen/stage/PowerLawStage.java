package nfracgen.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import nfracgen.analysis.FractureIntensityAnalysis;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.javafxapplication.FracGenApplication;

public class PowerLawStage {
    
    private final FractureIntensityAnalysis analysis;
    private Scene scene;
    
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
        stageLine.setTitle("Line Chart");
        stageLine.setScene(scene);
        stageLine.show();
        plot();
    }
    
    private void plot() throws Exception{
        LineChart chart = (LineChart) scene.getRoot().lookup("#scFractureIntensity");
        Series series = PlotSeries.plotLineSeries(analysis.apLog10,analysis.cumLog10);
        chart.getData().add(series);
    }
}
