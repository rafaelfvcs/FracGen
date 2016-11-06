package nfracgen.stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import nfracgen.analysis.Scanline;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.model.AnalysisFile;
import nfracgen.model.Scl;
import nfracgen.statistic.Mode;
import nfracgen.statistic.Stat;
import nfracgen.statistic.StdDeviation;
import nfracgen.statistic.Variance;
import nfracgen.statistic.VariationCoefficient;
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
    
    public static void refreshStats(AnalysisFile file) {
        Scanline sl = OpenScanlineData.openCSVFileToScanline(
                file.getFileName(), file.getSep(), file.getApColumn(),
                file.getSpColumn(), file.getHeader());
        file.setScanLine(sl);
                
        ObservableList<Double> olSp = FXCollections.observableArrayList(sl.getSpList());
        ObservableList<Double> olAp = FXCollections.observableArrayList(sl.getApList());
        ArrayList<Scl> list = new ArrayList<>();
        
        for (int i = 0; i < sl.getFracCount(); i++) {
            
            list.add(new Scl(RoundUtil.round(sl.getApList().get(i), 3),
                    RoundUtil.round(sl.getSpList().get(i), 3)));
        }

        //TODO: put values on tables
        //ap.setCellValueFactory(new PropertyValueFactory<>("ap"));
        //sp.setCellValueFactory(new PropertyValueFactory<>("sp"));
        ObservableList<Scl> data = FXCollections.observableArrayList(list);
        TableView<Double> scl_table = (TableView)getRoot().lookup("#scl_table");
        
        
        
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
        Label lMinValue = (Label)getRoot().lookup("#lMinValue");
        lMinValue.setText(String.valueOf(Stat.min(file.getScanLine().getApList())));
        
        Label lMaxValue = (Label)getRoot().lookup("#lMaxValue");
        lMaxValue.setText(String.valueOf(Stat.max(file.getScanLine().getApList())));
        
        Label lAvgValue = (Label)getRoot().lookup("#lAvgValue");
        lAvgValue.setText(String.valueOf(Stat.mean(file.getScanLine().getApList())));
        
        Label lModeValue = (Label) getRoot().lookup("#lModeValue");
        lModeValue.setText(String.valueOf(Mode.getMode(file.getScanLine().getApList())));
        
        Label lStdDevValue = (Label)getRoot().lookup("#lStdDevValue");
        lStdDevValue.setText(String.valueOf(StdDeviation.stdDeviation(file.getScanLine().getApList())));
        
        Label lVariance = (Label) getRoot().lookup("#lVariance");
        lVariance.setText(String.valueOf(Variance.variance(file.getScanLine().getApList())));
        
        Label lGeoAvg = (Label) getRoot().lookup("#lGeoAvg");
        lGeoAvg.setText(String.valueOf(Stat.geometricAverage(file.getScanLine().getApList())));
        
        Label lCount = (Label) getRoot().lookup("#lCount");
        lCount.setText(String.valueOf(file.getScanLine().getFracCount()));
        
        Label lVariation = (Label)getRoot().lookup("#lVariation");
        lVariation.setText(String.valueOf(
                VariationCoefficient.variationCoefficient(file.getScanLine().getApList())));
        
        /**
         * Tab Sp Statistics
         * 
         * tab_sp_statistics.fxml
         */
        Label lSpMinValue = (Label)getRoot().lookup("#lSpMinValue");
        lSpMinValue.setText(String.valueOf(Stat.min(file.getScanLine().getSpList())));
        
        Label lSpMaxValue = (Label)getRoot().lookup("#lSpMaxValue");
        lSpMaxValue.setText(String.valueOf(Stat.max(file.getScanLine().getSpList())));
        
        Label lSpAvgValue = (Label)getRoot().lookup("#lSpAvgValue");
        lSpAvgValue.setText(String.valueOf(Stat.mean(file.getScanLine().getSpList())));
        
        Label lSpModeValue = (Label) getRoot().lookup("#lSpModeValue");
        lSpModeValue.setText(String.valueOf(Mode.getMode(file.getScanLine().getSpList())));
        
        Label lSpStdDevValue = (Label)getRoot().lookup("#lSpStdDevValue");
        lSpStdDevValue.setText(String.valueOf(StdDeviation.stdDeviation(file.getScanLine().getSpList())));
        
        Label lSpVariance = (Label) getRoot().lookup("#lSpVariance");
        lSpVariance.setText(String.valueOf(Variance.variance(file.getScanLine().getSpList())));
        
        Label lSpGeoAvg = (Label) getRoot().lookup("#lSpGeoAvg");
        lSpGeoAvg.setText(String.valueOf(Stat.geometricAverage(file.getScanLine().getSpList())));
        
        Label lSpCount = (Label) getRoot().lookup("#lSpCount");
        lSpCount.setText(String.valueOf(file.getScanLine().getFracCount()));
        
        Label lSpVariation = (Label)getRoot().lookup("#lSpVariation");
        lSpVariation.setText(String.valueOf(
                VariationCoefficient.variationCoefficient(file.getScanLine().getSpList())));
    }
    
}
