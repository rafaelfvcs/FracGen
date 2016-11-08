package nfracgen.stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nfracgen.analysis.Fracture;
import nfracgen.analysis.FractureIntensityAnalysis;
import nfracgen.analysis.Scanline;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.AnalysisFile;
import nfracgen.model.Scl;
import nfracgen.statistic.Mode;
import nfracgen.statistic.Stat;
import nfracgen.statistic.StdDeviation;
import nfracgen.statistic.Variance;
import nfracgen.statistic.VariationCoefficient;
import nfracgen.statistic.histogram.ClassInterval;
import nfracgen.statistic.histogram.Frequency;
import nfracgen.statistic.linearregression.LinearRegression;
import nfracgen.util.ArrayOperation;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.RoundUtil;

public class MainStage {

    private static MainStage instance;

    public MainStage() {
        instance = this;
    }

    public static MainStage getInstance() {
        return instance;
    }

    private static Parent root;

    public static Parent getRoot() {
        return root;
    }

    public static void showMainStage() throws IOException {
        root = FXMLLoader.load(FracGenApplication.getInstance().
                getClass().getResource("/views/LayoutMain.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("NFracGen - alpha");
        stage.show();
    }

    public static void refreshStats(AnalysisFile file) throws Exception {
        Scanline sl = OpenScanlineData.openCSVFileToScanline(
                file.getFileName(), file.getSep(), file.getApColumn(),
                file.getSpColumn(), file.getHeader());
        file.setScanLine(sl);
                
               
        /**
         * Populate table with Ap and Sp values from dataset
         * 
         * this table is here -> tab_scanline.fxml
         */
        TableColumn ap  = new TableColumn("ap");
        TableColumn sp  = new TableColumn("sp");
        ap.setCellValueFactory(new PropertyValueFactory<>("ap"));
        sp.setCellValueFactory(new PropertyValueFactory<>("sp"));        
        
        ArrayList<Scl> list = new ArrayList<>();
        for (int i = 0; i < sl.getFracCount(); i++) {
            list.add(new Scl(RoundUtil.round(sl.getApList().get(i), 3),
                    RoundUtil.round(sl.getSpList().get(i), 3)));
        }
        ObservableList<Scl> data = FXCollections.observableArrayList(list); 
        
        TableView scl_table = (TableView) getRoot().lookup("#scl_table");
        scl_table.setEditable(true);
        scl_table.getColumns().addAll(ap,sp);
        scl_table.setItems(data);
        
        /**
         * Set statistics and properties of dataset
         */
        
        Label sclName = (Label) getRoot().lookup("#sclName");
        ComboBox dataTypeScl = (ComboBox) getRoot().lookup("#dataTypeScl");
        //TODO: fix this
        //sclName.setText(dataTypeScl.getSelectionModel().getSelectedItem());
        Label sclNumData = (Label) getRoot().lookup("#sclNumData");
        sclNumData.setText(String.valueOf(sl.getFracCount()));

        double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(sl.getApList()));
        double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(sl.getApList()));

        Label sclApStd = (Label) getRoot().lookup("#sclApStd");
        Label sclApMean = (Label) getRoot().lookup("#sclApMean");
        sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
        sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));

        double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(sl.getSpList()));
        double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(sl.getSpList()));

        Label sclSpStd = (Label) getRoot().lookup("#sclSpStd");
        Label sclSpMean = (Label) getRoot().lookup("#sclSpMean");
        sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
        sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));

        double sclcvap = sclapstd / sclapmean;
        double sclcvsp = sclspstd / sclspmean;

        Label sclCVap = (Label) getRoot().lookup("#sclCVap");
        Label sclCVsp = (Label) getRoot().lookup("#sclCVsp");
        sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
        sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
        sclName.setStyle("-fx-background-color: #FF2"); //mudar para css

        /**
         * Tab Ap Statistics
         *
         * tab_ap_statistics.fxml
         */
        Label lMinValue = (Label) getRoot().lookup("#lMinValue");
        lMinValue.setText(String.valueOf(Stat.min(file.getScanLine().getApList())));

        Label lMaxValue = (Label) getRoot().lookup("#lMaxValue");
        lMaxValue.setText(String.valueOf(Stat.max(file.getScanLine().getApList())));

        Label lAvgValue = (Label) getRoot().lookup("#lAvgValue");
        lAvgValue.setText(String.valueOf(Stat.mean(file.getScanLine().getApList())));

        Label lModeValue = (Label) getRoot().lookup("#lModeValue");
        lModeValue.setText(String.valueOf(Mode.getMode(file.getScanLine().getApList())));

        Label lStdDevValue = (Label) getRoot().lookup("#lStdDevValue");
        lStdDevValue.setText(String.valueOf(StdDeviation.stdDeviation(file.getScanLine().getApList())));

        Label lVariance = (Label) getRoot().lookup("#lVariance");
        lVariance.setText(String.valueOf(Variance.variance(file.getScanLine().getApList())));

        Label lGeoAvg = (Label) getRoot().lookup("#lGeoAvg");
        lGeoAvg.setText(String.valueOf(Stat.geometricAverage(file.getScanLine().getApList())));

        Label lCount = (Label) getRoot().lookup("#lCount");
        lCount.setText(String.valueOf(file.getScanLine().getFracCount()));

        Label lVariation = (Label) getRoot().lookup("#lVariation");
        lVariation.setText(String.valueOf(
                VariationCoefficient.variationCoefficient(file.getScanLine().getApList())));

        /**
         * Tab Sp Statistics
         *
         * tab_sp_statistics.fxml
         */
        Label lSpMinValue = (Label) getRoot().lookup("#lSpMinValue");
        lSpMinValue.setText(String.valueOf(Stat.min(file.getScanLine().getSpList())));

        Label lSpMaxValue = (Label) getRoot().lookup("#lSpMaxValue");
        lSpMaxValue.setText(String.valueOf(Stat.max(file.getScanLine().getSpList())));

        Label lSpAvgValue = (Label) getRoot().lookup("#lSpAvgValue");
        lSpAvgValue.setText(String.valueOf(Stat.mean(file.getScanLine().getSpList())));

        Label lSpModeValue = (Label) getRoot().lookup("#lSpModeValue");
        lSpModeValue.setText(String.valueOf(Mode.getMode(file.getScanLine().getSpList())));

        Label lSpStdDevValue = (Label) getRoot().lookup("#lSpStdDevValue");
        lSpStdDevValue.setText(String.valueOf(StdDeviation.stdDeviation(file.getScanLine().getSpList())));

        Label lSpVariance = (Label) getRoot().lookup("#lSpVariance");
        lSpVariance.setText(String.valueOf(Variance.variance(file.getScanLine().getSpList())));

        Label lSpGeoAvg = (Label) getRoot().lookup("#lSpGeoAvg");
        lSpGeoAvg.setText(String.valueOf(Stat.geometricAverage(file.getScanLine().getSpList())));

        Label lSpCount = (Label) getRoot().lookup("#lSpCount");
        lSpCount.setText(String.valueOf(file.getScanLine().getFracCount()));

        Label lSpVariation = (Label) getRoot().lookup("#lSpVariation");
        lSpVariation.setText(String.valueOf(
                VariationCoefficient.variationCoefficient(file.getScanLine().getSpList())));

        /**
         * Plot Power Law
         */
        LineChart gPowerLaw = (LineChart) getRoot().lookup("#gPowerLaw");
        //gPowerLaw.getData().add(PlotSeries.plotLineSeries(file.getScanLine().getSpList(),
        //      file.getScanLine().getSpList());

        FractureIntensityAnalysis fi = new FractureIntensityAnalysis(
                file.getScanLine());
        Label lFracInt = (Label) getRoot().lookup("#lFracInt");
        Label lAvgSpacing = (Label) getRoot().lookup("#lAvgSpacing");
        Label lScanLen = (Label) getRoot().lookup("#lScanLen");
        lFracInt.setText(String.valueOf(fi.getFractureIntensity()));
        lAvgSpacing.setText(String.valueOf(fi.getAverageSpacing()));
        lScanLen.setText(String.valueOf(file.getScanLine().getLenght()));

        ArrayList<Fracture> al = fi.getArrayDistribution();
        ArrayList<Double> cumulative = new ArrayList<>();
        ArrayList<Double> aperture = new ArrayList<>();
        for (Fracture values : al) {
            cumulative.add(Math.log10(Double.valueOf(values.getCumulativeNumber())));
            aperture.add(Math.log10(values.getAperture()));
//            cumulative.add(Double.valueOf(values.getCumulativeNumber()));
//            aperture.add(values.getAperture());
        }
        gPowerLaw.getData().addAll(PlotSeries.plotLineSeries(aperture, cumulative));
        /**
         * Add linear regression to graph
         */
        LinearRegression lr = new LinearRegression(aperture, cumulative);
//        double min = MinimumValue.getMinValue(aperture);
//        double max = MaximumValue.getMaxValue(aperture);
//        double first = lr.getValueAt(min);
//        double last = lr.getValueAt(max);
        double min = 0.001;
        double max = 10;
        double first = lr.getValueAt(0.001);
        double last = lr.getValueAt(10);
        XYChart.Series serieRegression = new XYChart.Series();
        serieRegression.getData().add(new XYChart.Data<>(min, first));
        serieRegression.getData().add(new XYChart.Data<>(max, last));
        gPowerLaw.getData().add(serieRegression);
        /**
         * Plot Ap Histogram
         */
        BarChart bcApHistogram = (BarChart) getRoot().lookup("#bcApHistogram");
        double amplitude = Stat.getAmplitude(file.getScanLine().getApList());
        double classIntervals = Frequency.sturgesExpression(amplitude, file.getRowsCount());
        double apMin = Stat.min(file.getScanLine().getApList());
        double apMax = Stat.max(file.getScanLine().getApList());
        ArrayList<ClassInterval> intervals = Frequency.classIntervals(apMin, apMax, classIntervals);
        Frequency.countObsFrequency(file.getScanLine().getApList(), intervals);

        XYChart.Series series = new XYChart.Series();
        series.setName("Histogram");
        for (int i = 0; i < intervals.size(); i++) {
            series.getData().add(
                    new XYChart.Data(intervals.get(i).getLabel(), intervals.get(i).getObsFrequency()));
        }
        bcApHistogram.getData().clear();
        bcApHistogram.getData().addAll(series);
        /**
         * Plot Sp Histogram
         */
        BarChart bcSpHistogram = (BarChart) getRoot().lookup("#bcSpHistogram");        
        double amplitudeSp = Stat.getAmplitude(file.getScanLine().getSpList());                
        double classIntervalsSp = Frequency.sturgesExpression(amplitudeSp, file.getRowsCount());        
        double spMin = Stat.min(file.getScanLine().getSpList());
        double spMax = Stat.max(file.getScanLine().getSpList());
        ArrayList<ClassInterval> intervalsSp = Frequency.classIntervals(spMin, spMax, classIntervalsSp);        
        Frequency.countObsFrequency(file.getScanLine().getSpList(), intervalsSp);

        XYChart.Series seriesSp = new XYChart.Series();
        seriesSp.setName("Histogram");        
        for (int i = 0; i < intervalsSp.size(); i++) {
            seriesSp.getData().add(
                    new XYChart.Data(intervalsSp.get(i).getLabel(), intervalsSp.get(i).getObsFrequency()));            
        }
        bcSpHistogram.getData().clear();
        bcSpHistogram.getData().addAll(seriesSp);
        /**
         * Ap Cumulative Frequency Chart
         * 
         * obs: falta colocar os dados em ordem, maior para menor
         * 
         */
        LineChart lcApFreq = (LineChart) getRoot().lookup("#lcApFreq");
        lcApFreq.getData().clear();
                                
        double sum = Stat.sum(file.getScanLine().getApList());        
        double cum = 0.;
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < file.getScanLine().getFracCount(); i++) {
            cum += file.getScanLine().getApList().get(i);            
            x.add(Double.valueOf(i));            
            y.add(cum / sum * 100);            
        }
        lcApFreq.getData().addAll(PlotSeries.plotLineSeries(x, y));
        lcApFreq.getData().addAll(PlotSeries.plotLineSeries(x, y));

        /**
         * Sp Cumulative Frequency Chart
         * 
         * obs: falta colocar os dados em ordem, maior para menor
         * 
         */
        LineChart lcSpFreq = (LineChart) getRoot().lookup("#lcSpFreq");
        lcSpFreq.getData().clear();
                                
        double sumSp = Stat.sum(file.getScanLine().getSpList());        
        double cumSp = 0.;
        ArrayList<Double> xSp = new ArrayList<>();
        ArrayList<Double> ySp = new ArrayList<>();
        for (int i = 0; i < file.getScanLine().getFracCount(); i++) {
            cumSp += file.getScanLine().getSpList().get(i);            
            xSp.add(Double.valueOf(i));            
            ySp.add(cumSp / sumSp * 100);            
        }
        lcSpFreq.getData().addAll(PlotSeries.plotLineSeries(xSp, ySp));
        lcSpFreq.getData().addAll(PlotSeries.plotLineSeries(xSp, ySp));
    }

}
