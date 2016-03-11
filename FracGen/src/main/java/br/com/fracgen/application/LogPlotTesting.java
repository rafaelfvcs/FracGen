package br.com.fracgen.application;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.Dimension;
import java.util.TimerTask;
import java.util.Timer;

public class LogPlotTesting extends ApplicationFrame
{
   public LogPlotTesting(String title)
   {
      super(title);
      final XYSeries series = new XYSeries("data");
      series.add(1, 1000.000);
      series.add(2,  100.000);
      series.add(3,   10.000);
      series.add(4,    1.000);
      series.add(5,    0.100);
      series.add(6,    0.010);
      series.add(7,    0.001);
      series.add(8,    0.001/2);
      final XYSeriesCollection dataset = new XYSeriesCollection(series);

      //final ValueAxis       xAxis    = new NumberAxis("X");
      final ValueAxis       xAxis    = new LogarithmicAxis("X");
      final LogarithmicAxis yAxis = new LogarithmicAxis("Y");
//      yAxis.setAllowNegativesFlag(true);
      yAxis.setUpperBound(2000);
      yAxis.setLowerBound(0.001);

      final XYItemRenderer  renderer = new XYLineAndShapeRenderer(false, true);
      final XYPlot          plot     = new XYPlot(dataset, xAxis, yAxis, renderer);

      plot.setOrientation(PlotOrientation.VERTICAL);

      final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

      ChartPanel chartPanel = new ChartPanel(chart, false);
      chartPanel.setPreferredSize(new Dimension(400, 300));
      setContentPane(chartPanel);
   }


   public static void main(String[] args)
   {
      final LogPlotTesting demo = new LogPlotTesting("testing");
      demo.pack();
      RefineryUtilities.centerFrameOnScreen(demo);
      demo.setVisible(true);



   }
}
