package nfracgen.util;

import java.awt.Dimension;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import nfracgen.model.PowerLawOrtega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

@SuppressWarnings("unchecked")
public class XYChartDataUtil {
        public static ObservableList<XYChart.Series<Number, Number>> getCountrySeries() {
               XYChart.Series<Number, Number> seriesChina = new XYChart.Series<>();
               seriesChina.setName("China");
               seriesChina.getData().addAll(new XYChart.Data<>(1950, 555),
                                            new XYChart.Data<>(2000, 1275),
                                            new XYChart.Data<>(2050, 1395),
                                            new XYChart.Data<>(2100, 1182),
                                            new XYChart.Data<>(2150, 1149));

               XYChart.Series<Number, Number> seriesIndia = new XYChart.Series<>();
               seriesIndia.setName("India");
               seriesIndia.getData().addAll(new XYChart.Data<>(1950, 358),
                                            new XYChart.Data<>(2000, 1017),
                                            new XYChart.Data<>(2050, 1531),
                                            new XYChart.Data<>(2100, 1458),
                                            new XYChart.Data<>(2150, 1308));

               XYChart.Series<Number, Number> seriesUSA = new XYChart.Series<>();
               seriesUSA.setName("USA");
               seriesUSA.getData().addAll(new XYChart.Data<>(1950, 158),
                                          new XYChart.Data<>(2000, 285),
                                          new XYChart.Data<>(2050, 409),
                                          new XYChart.Data<>(2100, 437),
                                          new XYChart.Data<>(2150, 453));
               ObservableList<XYChart.Series<Number, Number>> data =
                       FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
               data.addAll(seriesChina, seriesIndia, seriesUSA);

               return data;
        }

        public static ObservableList<XYChart.Series<String, Number>> getYearSeries() {
               XYChart.Series<String, Number> series1950 = new XYChart.Series<>();
               series1950.setName("1950");
               series1950.getData().addAll(new XYChart.Data<>("China", 555),
                                           new XYChart.Data<>("India", 358),
                                           new XYChart.Data<>("Brazil", 54),
                                           new XYChart.Data<>("UK", 50),
                                           new XYChart.Data<>("USA", 158));

               XYChart.Series<String, Number> series2000 = new XYChart.Series<>();
               series2000.setName("2000");
               series2000.getData().addAll(new XYChart.Data<>("China", 1275),
                                           new XYChart.Data<>("India",1017),
                                           new XYChart.Data<>("Brazil", 172),
                                           new XYChart.Data<>("UK", 59),
                                           new XYChart.Data<>("USA", 285));

               XYChart.Series<String, Number> series2050 = new XYChart.Series<>();
               series2050.setName("2050");
               series2050.getData().addAll(new XYChart.Data<>("China", 1395),
                                           new XYChart.Data<>("India",1531),
                                           new XYChart.Data<>("Brazil", 233),
                                           new XYChart.Data<>("UK", 66),
                                           new XYChart.Data<>("USA", 409));

               ObservableList<XYChart.Series<String, Number>> data =
                       FXCollections.<XYChart.Series<String, Number>>observableArrayList();
               data.addAll(series1950, series2000, series2050);

               return data;
        }

        public static ObservableList<XYChart.Series<Number, Number>> getPowerLaw() {

        	XYChart.Series<Number, Number> pl = new XYChart.Series<>();
        	XYChart.Series<Number, Number> dl = new XYChart.Series<>();

            pl.setName("Linear");
            dl.setName("Linear");

           // DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

            Double[][] data = PowerLawOrtega.findFreqApertureLogOrtega("src/main/resources/data.dat");

            for (int i = 0; i < data.length; i++) {
            	pl.getData().add(new XYChart.Data<>(Math.pow(data[i][0], 10), Math.pow(data[i][1],10)));
            	dl.getData().add(new XYChart.Data<>(data[i][0], data[i][1]));
			}


            ObservableList<XYChart.Series<Number, Number>> dataSerie =
                    FXCollections.<XYChart.Series<Number, Number>>observableArrayList();

            dataSerie.addAll(pl,dl);
//            dataSerie.add(pl);

            return dataSerie;
     }




}
