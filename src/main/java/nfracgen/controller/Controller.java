package nfracgen.controller;

import nfracgen.analysis.Scanline;
import nfracgen.javafxapplication.FracGenApplication;
import java.io.File;
import java.util.ArrayList;
import nfracgen.util.OpenScanlineData;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nfracgen.model.ScanlineAnalysisFile;
import nfracgen.stage.HistogramStage;
import nfracgen.stage.LineChartStage;
import nfracgen.stage.MainStage;
import nfracgen.stage.SaveAnalysisStage;
import nfracgen.stage.ScatterChartStage;
import nfracgen.stage.VariogramStage;
import nfracgen.util.DatasetUtils;

public class Controller {

    /*
	 * Main application
     */
    @FXML
    BorderPane mainPane;

    @FXML
    static BorderPane mainPane2;

    @FXML
    Stage stage;

    @FXML
    Tab tab_main_scanline;

    @FXML
    Tab tab_main_modeling;

    @FXML
    Tab tab_main_analysis;

    @FXML
    Tab tab_analysis_statistics;

    @FXML
    Tab tab_analysis_montecarlo;    

    @FXML
    Tab tab_main_outputs;    

    @FXML
    public void exit() {
        Platform.exit();
    }
    /**
     * Button for close Open Data Stage
     */
    @FXML
    protected Button btnClose;

    /**
     * Action for button Close the Stage for opening data
     */
    @FXML
    protected void close_openDataStage() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public <T> void initialize() {
                 
    }
    
    /**
     * Stage Dialog for opening data
     */
    @FXML
    protected TextField tfSeparator;

    @FXML
    protected RadioButton rbTab, rbComma, rbSemicolon, rbOther;

    @FXML
    private TextField tfFilename;

    @FXML
    private CheckBox cbHeader;

    @FXML
    protected void rbTabAction() {
        rbTab.setSelected(true);
        rbComma.setSelected(false);
        rbSemicolon.setSelected(false);
        rbOther.setSelected(false);
    }

    @FXML
    protected void rbCommaAction() {
        rbTab.setSelected(false);
        rbComma.setSelected(true);
        rbSemicolon.setSelected(false);
        rbOther.setSelected(false);
    }

    @FXML
    protected void rbSemicolonAction() {
        rbTab.setSelected(false);
        rbComma.setSelected(false);
        rbSemicolon.setSelected(true);
        rbOther.setSelected(false);
    }

    @FXML
    protected void rbOtherAction() {
        rbTab.setSelected(false);
        rbComma.setSelected(false);
        rbSemicolon.setSelected(false);
        rbOther.setSelected(true);
    }

    /**
     * Open a dialog for choose the file to be opened as dataset.
     *
     * @throws IOException
     */
    @FXML
    protected void dialogOpen() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(FracGenApplication.getInstance().stageOpenData);
        if (file != null) {
            if (file.exists()) {
                if (file.getAbsolutePath() != null) {
                    tfFilename.setText(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Load and set the program dataset
     *
     * @throws Exception
     */
    @FXML
    protected void setDatafile() throws Exception {
        boolean hasHeader = cbHeader.isSelected();
        String sep = "\t";
        if (rbSemicolon.isSelected()) {
            sep = ";";
        } else if (rbComma.isSelected()) {
            sep = ",";
        } else if (rbOther.isSelected()) {
            String aux = tfSeparator.getCharacters().toString();
            if (aux.length() > 0) {
                sep = aux;
            } else {
                throw new Exception("Invalid Separator");
            }
        }
        if (!tfFilename.getText().trim().isEmpty()) {
            ScanlineAnalysisFile file = new ScanlineAnalysisFile();
            file.setFilename(tfFilename.getText());
            file.setSep(sep);
            file.setApColumn(0);
            file.setSpColumn(1);
            file.setHeader(hasHeader);
            if (hasHeader) {
                file.setHeaderStrings(DatasetUtils.getHeaders(file.getFileName(), sep));
            } else {
                ArrayList<String> al = new ArrayList<>(file.getColumnsCount());
                for (int i = 0; i < file.getColumnsCount(); i++) {
                    if (i == 0) {
                        al.add("Ap");
                    }
                    if (i == 1) {
                        al.add("Sp");
                    }
                    if (i > 1) {
                        al.add("Column " + String.valueOf(i));
                    }
                }
                file.setHeaderStrings(al);
            }
            Scanline sl = OpenScanlineData.openCSVFileToScanline(tfFilename.getText(),
                    sep, 0, 1, hasHeader);
            file.setScanLine(sl);
            file.setRowsCount(sl.getFracCount());//TODO fix this
            Stage s = (Stage) tfFilename.getScene().getWindow();
            s.close();
            MainStage.setSclAnalysisFile(file);
            MainStage.refreshStats();
            MainStage.enableButtons();
        }
    }

    /*
     * Handle actions for Menu Item "Plot"
     * TODO: check if getAnalysisFile() is not null before create stage
     */
    @FXML
    protected void linechartStage() throws IOException {
        LineChartStage s
                = new LineChartStage(MainStage.getSclAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void scatterchartStage() throws IOException {
        ScatterChartStage s
                = new ScatterChartStage(MainStage.getSclAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void histogramStage() throws IOException {
        HistogramStage s
                = new HistogramStage(MainStage.getSclAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void variogramStage() throws IOException {
        VariogramStage s
                = new VariogramStage(MainStage.getSclAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void saveAnalysis() throws IOException {
        SaveAnalysisStage s
                = new SaveAnalysisStage(MainStage.getScanlineAnalysis());
        s.createStage();
    }
}
