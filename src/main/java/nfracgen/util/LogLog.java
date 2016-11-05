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
import org.jfree.ui.ApplicationFrame;


public class LogLog extends ApplicationFrame{

	public LogLog(String title, XYSeries series)
	   {
	      super(title);
	      /*
	      final XYSeries series = new XYSeries("data");
	      series.add(1, 1000.000);
	      series.add(2,  100.000);
	      series.add(3,   10.000);
	      series.add(4,    1.000);
	      series.add(5,    0.100);
	      series.add(6,    0.010);
	      series.add(7,    0.001);
	      series.add(8,    0.001/2);
	      */

	      final XYSeriesCollection dataset = new XYSeriesCollection(series);

	     // final ValueAxis       xAxis    = new NumberAxis("X");
	      //final ValueAxis xAxis    = new LogarithmicAxis("X");
	      final LogarithmicAxis xAxis    = new LogarithmicAxis("X");
	      final LogarithmicAxis yAxis = new LogarithmicAxis("Y");
	      yAxis.setAllowNegativesFlag(true);


	      /*
	      yAxis.setUpperBound(2000);
	      yAxis.setLowerBound(0.001);
	      */

	      yAxis.setUpperBound(2);
	      yAxis.setLowerBound(0);

	      xAxis.setUpperBound(10);
	      xAxis.setLowerBound(0);


	      final XYItemRenderer  renderer = new XYLineAndShapeRenderer(false, true);
	      final XYPlot          plot     = new XYPlot(dataset, xAxis, yAxis, renderer);

	      plot.setOrientation(PlotOrientation.VERTICAL);

	      final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);

//	      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

//	      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//	      setDefaultCloseOperation(ApplicationFrame.DO_NOTHING_ON_CLOSE);


	      ChartPanel chartPanel = new ChartPanel(chart, true);
	      chartPanel.setPreferredSize(new Dimension(650, 500));
//	     setDefaultCloseOperation(chartPanel.DISPOSE_ON_CLOSE);
	      setContentPane(chartPanel);

//			setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
//
//		      pack();
	   }

//	 public static void main(String[] args)
//	   {
//	      final LogLog demo = new LogLog("testing");
//	      demo.pack();
//	      RefineryUtilities.centerFrameOnScreen(demo);
//	      demo.setVisible(true);
//	   }

}
