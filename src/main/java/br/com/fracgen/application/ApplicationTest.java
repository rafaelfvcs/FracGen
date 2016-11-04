package br.com.fracgen.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.ArrayOperation;
import br.com.fracgen.util.DataSCL;
import br.com.fracgen.util.Interval;
import br.com.fracgen.util.OpenScanlineData;

public class ApplicationTest {

	private XYSeriesCollection dataset;

	public static void main(String[] args) {

		/*
		Random r = new Random();
		ArrayList<Double> vars = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			double nr = r.nextDouble()*10;
			vars.add(nr);
			System.out.println(nr);
		}
		System.out.println("");

		System.out.println(Stat.max(vars));

		System.out.println(Stat.min(vars));

		System.out.println(Stat.sum(vars));
		 */
//		DataSCL d = new DataSCL();
//		d.setName("Nova Olinda");
//		d.setSize("20 metros");


//		DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

//		for (int i = 0; i < d.getAperture().size(); i++) {
//			System.out.print("Spacing :" + d.getSpacing().get(i));
//			System.out.print("\t");
//			System.out.println("Aperture :" + d.getAperture().get(i));
//		}

		//Funcionando para mostrar arrdondamento
		/*
		System.out.println();
		Double[] ap = ArrayOperation.arrayListToArray(d.getAperture());
		Double[] sp = ArrayOperation.arrayListToArray(d.getSpacing());

		for (int i = 0; i < sp.length; i++) {
			System.out.println(sp[i]);
		}





		Arrays.sort(sp,Collections.reverseOrder());

		System.out.println("\n");
		for (int i = 0; i < sp.length; i++) {
			System.out.println(sp[i]);
		}

		System.out.println();


		double[] lf = Stat.getLinearFit(ap, sp);

		System.out.println(lf[0] + " " + lf[1]);

		System.out.println("\n");


		double[] dt = Interval.intervArray(1, 2, 30);


		for (int i = 0; i < dt.length; i++) {
			System.out.println(dt[i]);
		}
		*/

		//System.out.println("Novos :" + d.getSpacing().get(2));

//		ArrayList<Double> v;
//		ArrayList<Double> v1;
//		ArrayList<Double> values;
//		ArrayList<Double> values2;
//
//		v = Interval.linspace(1, 10, 5);

//		v1 = Interval.interv(1, 20, 200);
//
//		for (int i = 0; i < v1.size(); i++) {
//			System.out.println(v1.get(i));
//		}
//		for (int i = 0; i < v.size(); i++) {
//			System.out.println(v.get(i));
//		}
//		values = Interval.interv(1, 1, 100);
//		values2 = Interval.interv(1, 1, 100);

//		ArrayList<Double> av = ArrayOperation.divArray(values, values2);

//		for (int i = 0; i < values.size(); i++) {
//			System.out.println(values.get(i));
//		}
		/*
		for (Double d : av) {
			System.out.println(d);
		}
		System.out.println("O tamanho do veto é = " + values.size());
		*/

		/*
		Double[] v = {12.0,32.,54.,6.,8.,89.,64.,64.,6.};

		ArrayList<Double> v2 = Interval.interv(0, 1, 4);

		Double[] vd = ArrayOperation.arrayListToArray(v2);

		double res = Stat.calculateMean(v);


		double resMed = Stat.calculateMean(vd);
		double resStd = Stat.getStdDev(vd);

		System.out.println(resMed);
		System.out.println();
		System.out.println(resStd);

		for (int i = 0; i < vd.length; i++) {
			System.out.println(vd[i]);
		}
		*/

		new ApplicationTest();

	}

	public ApplicationTest() {
        dataset = new XYSeriesCollection();

        DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");


        XYSeries data = new XYSeries("data");

        for (int i = 0; i < d.getAperture().size(); i++) {
			data.add(d.getAperture().get(i), d.getSpacing().get(i));
		}

//        data.add(3, 2); //Point 1
//        data.add(1, 1); //Point 2
//        data.add(4, 1); //Point 3
//        data.add(2, 2); //Point 4


        dataset.addSeries(data);
        showGraph();
    }

	private void showGraph() {

		final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        final ApplicationFrame frame = new ApplicationFrame("Title");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createScatterPlot(
            "Grafico",                  // chart title
            "Aperture",                      // x axis label
            "Spacing",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        // Comentando essas linhas abaixo;
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);
        // Até aqui
        return chart;
    }

}
