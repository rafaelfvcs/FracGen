package nfracgen.stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import nfracgen.analysis.FractureIntensityAnalysis;
import nfracgen.analysis.Scanline;
import nfracgen.analysis.plot.PlotSeries;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.Analysis;
import nfracgen.model.AnalysisFile;
import nfracgen.model.Scl;
import nfracgen.model.StatisticsModel;
import nfracgen.statistic.Stat;
import nfracgen.statistic.histogram.ClassInterval;
import nfracgen.statistic.histogram.Frequency;
import nfracgen.statistic.linearregression.LinearRegression;
import nfracgen.util.ArrayOperation;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.RoundUtil;

public class MainStage {

    private static MainStage instance;
    //private static AnalysisFile file;
    private static Analysis analysis = new Analysis();
 
    public MainStage() {
        instance = this;
    }

    public static MainStage getInstance() {
        return instance;
    }

    public static void setAnalysisFile(AnalysisFile file) {
        MainStage.analysis.setAnalysisFile(file);        
    }

    public static AnalysisFile getAnalysisFile() {        
        return MainStage.analysis.getAnalysisFile();
    }
    
    public static void setAnalysis(Analysis analysis) {
        MainStage.analysis = analysis;
    }

    public static Analysis getAnalysis() {
        return MainStage.analysis;
    }

    private static Parent root;

    public static Parent getRoot() {
        return root;
    }

    public static void showMainStage(String user) throws IOException {
        root = FXMLLoader.load(FracGenApplication.getInstance().
                getClass().getResource("/views/LayoutMain.fxml"));
        getAnalysis().setUser(user);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("NFracGen - alpha -  User: "+user);
        stage.show();
    }

    public static void refreshStats() throws Exception {
        Scanline sl = OpenScanlineData.openCSVFileToScanline(
                getAnalysisFile().getFileName(), 
                getAnalysisFile().getSep(), 
                getAnalysisFile().getApColumn(),
                getAnalysisFile().getSpColumn(), 
                getAnalysisFile().getHeader());
                getAnalysisFile().setScanLine(sl);
        /**
         * Populate table with Ap and Sp values from dataset
         *
         * this table is here -> tab_scanline.fxml
         */
        TableColumn ap = new TableColumn("ap");
        TableColumn sp = new TableColumn("sp");
        ap.setCellValueFactory(new PropertyValueFactory<>("ap"));
        sp.setCellValueFactory(new PropertyValueFactory<>("sp"));

        ArrayList<Scl> list = new ArrayList<>();
        for (int i = 0; i < sl.getFracCount(); i++) {
            list.add(new Scl(RoundUtil.round(sl.getApList().get(i), 3),
                    RoundUtil.round(sl.getSpList().get(i), 3)));
        }
        ObservableList<Scl> data = FXCollections.observableArrayList(list);

        TableView scl_table = (TableView) getRoot().lookup("#scl_table");
        scl_table.getItems().clear();
        scl_table.setEditable(true);
        scl_table.getColumns().addAll(ap, sp);
        scl_table.setItems(data);
        /*
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
        StatisticsModel apStats = new StatisticsModel(
                getAnalysisFile().getScanLine().getApList());
        Label lMinValue = (Label) getRoot().lookup("#lMinValue");
        lMinValue.setText(String.valueOf(
                RoundUtil.round(apStats.getMin(), 3)));

        Label lMaxValue = (Label) getRoot().lookup("#lMaxValue");
        lMaxValue.setText(String.valueOf(
                RoundUtil.round(apStats.getMax(), 3)));

        Label lAvgValue = (Label) getRoot().lookup("#lAvgValue");
        lAvgValue.setText(String.valueOf(
                RoundUtil.round(apStats.getAvg(), 3)));

        Label lModeValue = (Label) getRoot().lookup("#lModeValue");
        lModeValue.setText(String.valueOf(
                RoundUtil.round(apStats.getMode(), 3)));

        Label lStdDevValue = (Label) getRoot().lookup("#lStdDevValue");
        lStdDevValue.setText(String.valueOf(
                RoundUtil.round(apStats.getStdDev(), 3)));

        Label lVariance = (Label) getRoot().lookup("#lVariance");
        lVariance.setText(String.valueOf(
                RoundUtil.round(apStats.getVariance(), 3)));

        Label lGeoAvg = (Label) getRoot().lookup("#lGeoAvg");
        lGeoAvg.setText(String.valueOf(
                RoundUtil.round(apStats.getGeoAvg(), 3)));

        Label lCount = (Label) getRoot().lookup("#lCount");
        lCount.setText(String.valueOf(apStats.getCount()));

        Label lVariation = (Label) getRoot().lookup("#lVariation");
        lVariation.setText(String.valueOf(RoundUtil.round(
                apStats.getVariation(), 3)));
        /**
         * Tab Sp Statistics
         *    Descritive Statistics
         * tab_sp_statistics.fxml
         */
        StatisticsModel spStats = new StatisticsModel(
                getAnalysisFile().getScanLine().getSpList());
        Label lSpMinValue = (Label) getRoot().lookup("#lSpMinValue");
        lSpMinValue.setText(String.valueOf(
                RoundUtil.round(spStats.getMin(), 3)));

        Label lSpMaxValue = (Label) getRoot().lookup("#lSpMaxValue");
        lSpMaxValue.setText(String.valueOf(
                RoundUtil.round(spStats.getMax(), 3)));

        Label lSpAvgValue = (Label) getRoot().lookup("#lSpAvgValue");
        lSpAvgValue.setText(String.valueOf(
                RoundUtil.round(spStats.getAvg(), 3)));

        Label lSpModeValue = (Label) getRoot().lookup("#lSpModeValue");
        lSpModeValue.setText(String.valueOf(
                RoundUtil.round(spStats.getMode(), 3)));

        Label lSpStdDevValue = (Label) getRoot().lookup("#lSpStdDevValue");
        lSpStdDevValue.setText(String.valueOf(
                RoundUtil.round(spStats.getStdDev(), 3)));

        Label lSpVariance = (Label) getRoot().lookup("#lSpVariance");
        lSpVariance.setText(String.valueOf(
                RoundUtil.round(spStats.getVariance(), 3)));

        Label lSpGeoAvg = (Label) getRoot().lookup("#lSpGeoAvg");
        lSpGeoAvg.setText(String.valueOf(
                RoundUtil.round(spStats.getGeoAvg(), 3)));

        Label lSpCount = (Label) getRoot().lookup("#lSpCount");
        lSpCount.setText(String.valueOf(spStats.getCount()));

        Label lSpVariation = (Label) getRoot().lookup("#lSpVariation");
        lSpVariation.setText(String.valueOf(
                RoundUtil.round(spStats.getVariation(), 3)));
        /**
         * Plot Power Law
         */
        LineChart gPowerLaw = (LineChart) getRoot().lookup("#gPowerLaw");
        //gPowerLaw.getData().add(PlotSeries.plotLineSeries(file.getScanLine().getSpList(),
        //      file.getScanLine().getSpList());

        FractureIntensityAnalysis fi = getAnalysisFile().getScanLine().getFracIntAnalysis();
        //new FractureIntensityAnalysis(file.getScanLine());

        Label lFracInt = (Label) getRoot().lookup("#lFracInt");
        Label lAvgSpacing = (Label) getRoot().lookup("#lAvgSpacing");
        Label lScanLen = (Label) getRoot().lookup("#lScanLen");
        lFracInt.setText(String.valueOf(fi.getFractureIntensity()));
        lAvgSpacing.setText(String.valueOf(fi.getAverageSpacing()));
        lScanLen.setText(String.valueOf(getAnalysisFile().getScanLine().getLenght()));
        
        gPowerLaw.getData().addAll(PlotSeries.plotLineSeries(getAnalysisFile().getScanLine().getApList(),
                getAnalysisFile().getScanLine().getFracIntAnalysis().getCumulativeList()));
        //gPowerLaw.getData().addAll(PlotSeries.plotLineSeries(fi.apLog10, fi.cumLog10));

        /**
         * Save the Power Law graph to Writable Image
         */        
        WritableImage imagePL = gPowerLaw.snapshot(new SnapshotParameters(), null);       
        getAnalysisFile().setPLGraph(imagePL);
        
        /**
         * Add linear regression to graph
         */
        LinearRegression lr = new LinearRegression(getAnalysisFile().getScanLine().getApList(),
                getAnalysisFile().getScanLine().getFracIntAnalysis().getCumulativeList());
//        double min = MinimumValue.getMinValue(aperture);
//        double max = MaximumValue.getMaxValue(aperture);
//        double first = lr.getValueAt(min);
//        double last = lr.getValueAt(max);
        double min = 0.001;
        double max = 100;
        double first = lr.getValueAt(0.001);
        double last = lr.getValueAt(100);
        XYChart.Series serieRegression = new XYChart.Series();
        serieRegression.getData().add(new XYChart.Data<>(min, first));
        serieRegression.getData().add(new XYChart.Data<>(max, last));
        gPowerLaw.getData().add(serieRegression);
        /**
         * Plot Ap Histogram
         */
        BarChart bcApHistogram = (BarChart) getRoot().lookup("#bcApHistogram");
        double amplitude = Stat.getAmplitude(getAnalysisFile().getScanLine().getApList());
        double classIntervals = Frequency.sturgesExpression(amplitude, getAnalysisFile().getRowsCount());
        double apMin = Stat.min(getAnalysisFile().getScanLine().getApList());
        double apMax = Stat.max(getAnalysisFile().getScanLine().getApList());
        ArrayList<ClassInterval> intervals = Frequency.classIntervals(apMin, apMax, classIntervals);
        Frequency.countObsFrequency(getAnalysisFile().getScanLine().getApList(), intervals);

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
        double amplitudeSp = Stat.getAmplitude(getAnalysisFile().getScanLine().getSpList());
        double classIntervalsSp = Frequency.sturgesExpression(amplitudeSp, getAnalysisFile().getRowsCount());
        double spMin = Stat.min(getAnalysisFile().getScanLine().getSpList());
        double spMax = Stat.max(getAnalysisFile().getScanLine().getSpList());
        ArrayList<ClassInterval> intervalsSp = Frequency.classIntervals(spMin, spMax, classIntervalsSp);
        Frequency.countObsFrequency(getAnalysisFile().getScanLine().getSpList(), intervalsSp);

        XYChart.Series seriesSp = new XYChart.Series();
        seriesSp.setName("Histogram");
        for (int i = 0; i < intervalsSp.size(); i++) {
            seriesSp.getData().add(
                    new XYChart.Data(intervalsSp.get(i).getLabel(), intervalsSp.get(i).getObsFrequency()));
        }
        bcSpHistogram.getData().clear();
        bcSpHistogram.getData().addAll(seriesSp);

        /*
        * Tab Geostatistics
        *
        * Plot scatter chart 
         */
        ScatterChart scDispersion = (ScatterChart) getRoot().lookup("#scDispersion");
        scDispersion.getData().add(
                PlotSeries.plotLineSeries(0, getAnalysisFile().getScanLine().getDistanceList()));

    }

}
