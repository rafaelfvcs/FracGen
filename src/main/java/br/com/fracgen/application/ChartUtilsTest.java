package br.com.fracgen.application;

import br.com.fracgen.util.ChartUtils;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChartUtilsTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ChartUtils Test");
        GridPane grid = new GridPane();
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> chart = new 
                LineChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("Chart Title");
        ChartUtils utils = new ChartUtils();
        ArrayList<Double> x = new ArrayList<>();
        x.add(2.3);
        x.add(6.4);
        x.add(16.2);
        x.add(25.7);
        x.add(35.2);
        x.add(43.9);        
        ArrayList<Double> y = new ArrayList<>();
        y.add(562.3);
        y.add(26.4);
        y.add(4316.2);
        y.add(2125.7);        
        y.add(435.2);
        y.add(243.9);
        XYChart.Series series = utils.plotLineSeries(x, y);
        chart.getData().addAll(series);
        grid.add(chart, 0, 0);
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
