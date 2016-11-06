package nfracgen.analysis.plot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

public class PlotSeries {
    /**
     * Return a line chart serie for two vectors represented as ArrayLists 
     * with Double value.
     * @param columnX
     * @param columnY
     * @return
     * @throws Exception 
     */
    public static XYChart.Series plotLineSeries(ArrayList<Double> columnX, 
            ArrayList<Double> columnY) throws Exception{
        if(columnX.size()!=columnY.size()){
            throw new Exception("Columns X and Y must have same size.");            
        }
        XYChart.Series<Number, Number> series = new XYChart.Series();        
        Task<List<XYChart.Data<Number, Number>>> task;
        task = new Task<List<XYChart.Data<Number, Number>>>(){
            @Override
            protected List<XYChart.Data<Number, Number>> call() throws Exception {
                List<XYChart.Data<Number, Number>> chartData = new ArrayList<>();
                for(int i =0; i<columnX.size(); i++){
                    chartData.add(new XYChart.Data(columnX.get(i),
                            columnY.get(i)));
                }
                return chartData;
            }
        };        
        task.setOnSucceeded(e -> series.getData().addAll(task.getValue()));
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        return series;        
    }
    
    /**
     * 
     * @param filename
     * @param separator
     * @param serieLabel
     * @param columnX
     * @param columnY
     * @return 
     */
    public XYChart.Series plotLineSeries(String filename, String separator,
            String serieLabel, int columnX, int columnY) {
        
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName(serieLabel);

        Task<List<XYChart.Data<Number, Number>>> task;
        task = new Task<List<XYChart.Data<Number, Number>>>() {
            @Override
            protected List<XYChart.Data<Number, Number>> call() throws Exception {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                List<XYChart.Data<Number, Number>> chartData = new ArrayList<>();
                String dataLine;
                while ((dataLine = br.readLine()) != null) {
                    final String[] dataValues = dataLine.split(separator);
                    chartData.add(new XYChart.Data(dataValues[columnX].trim(),
                            dataValues[columnY].trim()));
                }
                return chartData;
            }
        };
        task.setOnSucceeded(e -> series.getData().addAll(task.getValue()));

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        return series;
    }
    
    public XYChart.Series<Number, String> plotBarSeries(String filename, String separator, 
            String serieName, int columnX, int columnY){
        XYChart.Series<Number, String> series = new XYChart.Series();
        series.setName(serieName);

        Task<List<XYChart.Data<Number, String>>> task;
        task = new Task<List<XYChart.Data<Number, String>>>() {
            @Override
            protected List<XYChart.Data<Number, String>> call() throws Exception {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                List<XYChart.Data<Number, String>> chartData = new ArrayList<>();
                String dataLine;
                while ((dataLine = br.readLine()) != null) {
                    final String[] dataValues = dataLine.split(separator);
                    chartData.add(new XYChart.Data(
                            Double.valueOf(dataValues[columnX]),
                            dataValues[columnY]));
                }
                return chartData;
            }
        };
        task.setOnSucceeded(e -> series.getData().addAll(task.getValue()));

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        return series;
    }
    
}
