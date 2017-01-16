package nfracgen.controller;

import nfracgen.analysis.Scanline;
import nfracgen.javafxapplication.FracGenApplication;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import nfracgen.model.PowerLawOrtega;
import nfracgen.model.Scl;
import nfracgen.statistic.Stat;
import nfracgen.util.ArrayOperation;
import nfracgen.util.DataSCL;
import nfracgen.util.Line;
import nfracgen.util.LogLog;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.PowerLaw;
import nfracgen.util.RoundUtil;
import nfracgen.util.SaveFracturesData;
import nfracgen.util.SaveScanlineData;
import nfracgen.util.StatsSCL;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import nfracgen.model.AnalysisFile;
import nfracgen.stage.ExportImageStage;
import nfracgen.stage.HistogramStage;
import nfracgen.stage.LineChartStage;
import nfracgen.stage.MainStage;
import nfracgen.stage.PowerLawStage;
import nfracgen.stage.ScatterChartStage;
import nfracgen.stage.StageOpenData;
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
    Tab tab_modeling_2d;

    @FXML
    Tab tab_modeling_3d;

    @FXML
    Tab tab_main_outputs;

    @FXML
    private TabPane tabPane_main;

    @FXML
    private TabPane tabPane_modeling;

    @FXML
    private TabPane tabPane_analysis;

    @FXML
    private Canvas canvas;

    @FXML
    private Canvas canvas1;

    /*
	 * Instancia de Arquivo aberto
     */
    File fileSclOpen;

    //----------- Scanlines-----------

    /*
	 * CheckBox
     */
    @FXML
    private CheckBox check_scanline_save_ortega;

    @FXML
    private CheckBox checkbox_filter_scl_data;

    @FXML
    private CheckBox check_scaline_new_analysis;

    // Others
    @FXML
    private ComboBox<String> dataTypeScl;

    /*
	 * RaioButtons
     */
    //-------------Modeling----------
    @FXML
    private ToggleGroup radioModelingLoad;
    @FXML
    private RadioButton radio_modeling_2d_loadpl;
    @FXML
    private RadioButton radio_modeling_2d_loadexternal;
    @FXML
    private RadioButton radio_modeling_setdefault;


    /*
	 * Buttons
     */
    @FXML
    private Button btn_modeling_2d_load;

    @FXML
    private Button btn_modeling_2d_edit;

    @FXML
    private Button btn_modeling_2d_clear;

    @FXML
    private Button btn_modeling_2d_save;


    /*
	 * Main Toolbar
     */
    @FXML
    private Button btn_toolbar_geo;

    /*
	 * ---------------------------- Buttons-Vars-----------------------------
     */
    @FXML
    private Button btn_scanline_plotpowerlaw;
    /*
	 * Data base model
     */
    @FXML
    private Button btn_scanline_save;

    @FXML
    private Button btn_scanline_clear;

    @FXML
    private Button btn_scanline_findpl;

    @FXML
    private Button btn_scanline_clearpl;

    @FXML
    private Button btn_scanline_saveanalysis;
    
    @FXML
    private Button btn_save_graph;


    /*
	 * ----------------------------- Scanlines - Tab --------------------------------
     */
    @FXML
    private Label coefA;
    @FXML
    private Label coefK;
    @FXML
    private Label sclr2;
    @FXML

    private Label sclName;
    @FXML
    private Label sclNumData;
    @FXML
    private Label sclApMean;
    @FXML
    private Label sclApStd;
    @FXML
    private Label sclSpMean;
    @FXML
    private Label sclSpStd;
    @FXML
    private Label sclCVap;
    @FXML
    private Label sclCVsp;

    /*
	 * Tables
     */
    @FXML
    private TableView<Scl> scl_table;

    @FXML
    private TableColumn<Scl, Double> ap;

    @FXML
    private TableColumn<Scl, Double> sp;

    @FXML
    private TableView<Scl> scl_table_data_new;

    @FXML
    private TableColumn<Scl, Double> apn;

    @FXML
    private TableColumn<Scl, Double> spn;

    /*
	 * Graphs
     */
    @FXML
    private LineChart<Number, Number> gPowerLaw; // = new LineChart<Number,Number>(xAxis,yAxis);;

    /*
	 * ----------------------------- Modeling - Tab --------------------------------
     */
    @FXML
    private Label modeling_2d_nfrac_se1;

    @FXML
    private CheckBox check_modeling_refine_2d;

    /*
	 * --------------------------------- Methods TOOLBAR ------------------------------
     */
    @FXML
    public void goToGeoModeling() {
        tabPane_main.getSelectionModel().select(tab_main_scanline);
    }

    @FXML
    public void goToStatistic() {
        tabPane_main.getSelectionModel().select(tab_main_analysis);
        tabPane_analysis.getSelectionModel().select(tab_analysis_statistics);
    }

    @FXML
    public void goTo3D() {
        tabPane_main.getSelectionModel().select(tab_main_modeling);
        tabPane_modeling.getSelectionModel().select(tab_modeling_3d);
    }

    @FXML
    public void goTo2D() {
        tabPane_main.getSelectionModel().select(tab_main_modeling);
        tabPane_modeling.getSelectionModel().select(tab_modeling_2d);
    }

    @FXML
    public void goToMC() {
        tabPane_main.getSelectionModel().select(tab_main_analysis);
        tabPane_analysis.getSelectionModel().select(tab_analysis_montecarlo);
    }

    @FXML
    public void goToAndroid() {
//		tabPane_main.getSelectionModel().select(tab_main_analysis);
//		tabPane_analysis.getSelectionModel().select(tab_analysis_montecarlo);
    }

    /*
	 * ------------------------------------ Methods--------------------------------------
     */

 /*
	 * -------------------------------------MODELING------------------------------------
     */
    @FXML
    ToggleButton modeling_2d_saveanalysis; //para salvar analysis multiplas

    @FXML
    Tab tab_modeling_2d_view;

    @FXML
    TextField txtfield_modeling_numfrat_set1;

    @FXML
    TextField txtfield_modeling_numfrat_set2;

    @FXML
    TextField txtfield_modeling_numfrat_set3;

    @FXML
    TextField txtfield_modeling_maxL_set1;
    @FXML
    TextField txtfield_modeling_minL_set1;
    @FXML
    TextField txtfield_modeling_meanL_set1;
    @FXML
    TextField txtfield_modeling_az_set1;

    @FXML
    TextField txtfield_modeling_maxAp_set1;
    @FXML
    TextField txtfield_modeling_minAp_set1;
    @FXML
    TextField txtfield_modeling_meanAp_set1;
    @FXML
    TextField txtfield_modeling_CVsp_set1;

    @FXML
    TextField txtfield_modeling_maxL_set2;
    @FXML
    TextField txtfield_modeling_minL_set2;
    @FXML
    TextField txtfield_modeling_meanL_set2;
    @FXML
    TextField txtfield_modeling_az_set2;

    @FXML
    TextField txtfield_modeling_maxAp_set2;
    @FXML
    TextField txtfield_modeling_minAp_set2;
    @FXML
    TextField txtfield_modeling_meanAp_set2;
    @FXML
    TextField txtfield_modeling_CVsp_set2;

    @FXML
    TextField txtfield_modeling_maxL_set3;
    @FXML
    TextField txtfield_modeling_minL_set3;
    @FXML
    TextField txtfield_modeling_meanL_set3;
    @FXML
    TextField txtfield_modeling_az_set3;

    @FXML
    TextField txtfield_modeling_maxAp_set3;
    @FXML
    TextField txtfield_modeling_minAp_set3;
    @FXML
    TextField txtfield_modeling_meanAp_set3;
    @FXML
    TextField txtfield_modeling_CVsp_set3;

    @FXML
    CheckBox check_modeling_2d_holdonresults;

    @FXML
    Tab tab_modeling_2d_modeling;

    @FXML
    TabPane pane_modeling;

    @FXML
    ComboBox combo_modeling_dataset;

    // Nova instancia;
    File newFileSclOpen;

    @FXML
    public void loadModeling2D() {

        // Carrega modelos baseados em power law ou scanlines para geração de fraturas
//		combo_modeling_dataset.getSelectionModel().selectedItemProperty();
        String comboSet = (String) combo_modeling_dataset.getSelectionModel().selectedItemProperty().get();

        if (radio_modeling_2d_loadexternal.isSelected()) {

            newFileSclOpen = dialogOpenFile("*.dat");
            //TODO: Fazer estruturas de arquivo para salvar no formato numfrats, max, min...

            //opcao Load Use Power Law Data
        } else if (radio_modeling_2d_loadpl.isSelected()) {

            Scanline scanline = OpenScanlineData.openCSVFileToScanline(comboSet,
                    comboSet, 0, 1, true);

            StatsSCL statscl = new StatsSCL(scanline.getApList(),
                    scanline.getSpList());

            int nfrat = statscl.getNumFrat();

            double maxL = 0;
            double minL = 0;
            double meanL = 0;

            double meanAp = statscl.getMeanAp();
            double maxAp = statscl.getMaxAp();
            double minAp = statscl.getMinAp();
            double CVsp = statscl.getCvSp();

            if (comboSet.equals("SET1")) {
                System.out.println("SET 1");
                // usar um polimorfismo aqui !!!!!!

                txtfield_modeling_numfrat_set1.setText(String.valueOf(nfrat));

                txtfield_modeling_maxL_set1.setText(String.valueOf(maxL));
                txtfield_modeling_minL_set1.setText(String.valueOf(minL));
                txtfield_modeling_meanL_set1.setText(String.valueOf(meanL));

                txtfield_modeling_maxAp_set1.setText(String.valueOf(maxAp));
                txtfield_modeling_minAp_set1.setText(String.valueOf(minAp));
                txtfield_modeling_meanAp_set1.setText(String.valueOf(meanAp));

                txtfield_modeling_CVsp_set1.setText(String.valueOf(CVsp));

            } else if (comboSet.equals("SET2")) {
                System.out.println("SET 2");
                txtfield_modeling_numfrat_set2.setText(String.valueOf(nfrat));

                txtfield_modeling_maxL_set2.setText(String.valueOf(maxL));
                txtfield_modeling_minL_set2.setText(String.valueOf(minL));
                txtfield_modeling_meanL_set2.setText(String.valueOf(meanL));

                txtfield_modeling_maxAp_set2.setText(String.valueOf(maxAp));
                txtfield_modeling_minAp_set2.setText(String.valueOf(minAp));
                txtfield_modeling_meanAp_set2.setText(String.valueOf(meanAp));

                txtfield_modeling_CVsp_set2.setText(String.valueOf(CVsp));

            } else {
                System.out.println("SET 3");
                txtfield_modeling_numfrat_set3.setText(String.valueOf(nfrat));

                txtfield_modeling_maxL_set3.setText(String.valueOf(maxL));
                txtfield_modeling_minL_set3.setText(String.valueOf(minL));
                txtfield_modeling_meanL_set3.setText(String.valueOf(meanL));

                txtfield_modeling_maxAp_set3.setText(String.valueOf(maxAp));
                txtfield_modeling_minAp_set3.setText(String.valueOf(minAp));
                txtfield_modeling_meanAp_set3.setText(String.valueOf(meanAp));

                txtfield_modeling_CVsp_set3.setText(String.valueOf(CVsp));
            }


            /*
			txtfield_modeling_numfrat_set1.setText(String.valueOf(dscl.getAperture().size())); // <<<====************
			txtfield_modeling_maxAp_set1.setText(String.valueOf(RoundUtil.round(Stat.max(dscl.getAperture()),3)));
			txtfield_modeling_minAp_set1.setText(String.valueOf(RoundUtil.round(Stat.min(dscl.getAperture()),3)));
			txtfield_modeling_meanAp_set1.setText(String.valueOf(RoundUtil.round(Stat.mean(dscl.getAperture()),3)));


			double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(dscl.getSpacing()));
			double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(dscl.getSpacing()));
			double sclcvsp = sclspstd/sclspmean;

			txtfield_modeling_CVsp_set1.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
             */
        } else {
            txtfield_modeling_numfrat_set2.setText("0");
            txtfield_modeling_minL_set2.setText("0");
            txtfield_modeling_maxL_set2.setText("0");

            txtfield_modeling_numfrat_set3.setText(String.valueOf(0));

            txtfield_modeling_maxL_set3.setText(String.valueOf(0));
            txtfield_modeling_minL_set3.setText(String.valueOf(0));
            txtfield_modeling_meanL_set3.setText(String.valueOf(0));

            txtfield_modeling_maxAp_set3.setText(String.valueOf(0));
            txtfield_modeling_minAp_set3.setText(String.valueOf(0));
            txtfield_modeling_meanAp_set3.setText(String.valueOf(0));

            txtfield_modeling_CVsp_set3.setText(String.valueOf(0));
        }

        if (fileSclOpen != null) {
            btn_modeling_2d_edit.setDisable(false);
        }

//		radio_modeling_loadpl.setToggleGroup(radioModelingLoad);
////	radio_modeling_loadpl.setSelected(true);
//		radio_modeling_loadexternal.setToggleGroup(radioModelingLoad);
//		radio_modeling_setdefault.setToggleGroup(radioModelingLoad);
    }

    @FXML
    public void drawFratures2d() {

        /*
		 * Get modeling properties by user
         */
        int numfrat1 = Integer.parseInt(txtfield_modeling_numfrat_set1.getText());
        int numfrat2 = Integer.parseInt(txtfield_modeling_numfrat_set2.getText());
        int numfrat3 = Integer.parseInt(txtfield_modeling_numfrat_set3.getText());

        double maxL1 = Double.parseDouble(txtfield_modeling_maxL_set1.getText());
        double minL1 = Double.parseDouble(txtfield_modeling_minL_set1.getText());
        double az1 = Double.parseDouble(txtfield_modeling_az_set1.getText());

//
        double maxL2 = Double.parseDouble(txtfield_modeling_maxL_set2.getText());
        double minL2 = Double.parseDouble(txtfield_modeling_minL_set2.getText());
        double az2 = Double.parseDouble(txtfield_modeling_az_set2.getText());

        double maxL3 = Double.parseDouble(txtfield_modeling_maxL_set3.getText());
        double minL3 = Double.parseDouble(txtfield_modeling_minL_set3.getText());
        double az3 = Double.parseDouble(txtfield_modeling_az_set3.getText());

        System.out.println("Set 1: " + maxL1 + " " + minL1 + " " + az1);
        /* funcionando bem abaixo
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

        ArrayList<double[]> df = new ArrayList<>();

        for (int i = 0; i < 300; i++) {

        	double[] f1 = Line.horizontal(Math.random()*400, Math.random()*400, Math.random()*100);
        	double[] f2 = Line.oblique(Math.random()*400, Math.random()*400, Math.random()*100,90);

        	gc.strokeLine(f1[0], f1[1], f1[2], f1[3]);
        	gc.strokeLine(f2[0], f2[1], f2[2], f2[3]);
        	df.add(f1);

		}

//        for (double[] ds : df) {
//			System.out.println("pontos = [ " + RoundUtil.round(ds[0],2) +" , "+ds[1] +" , "+ds[2] +" , "+ds[3] +" ] ");
//		}

        SaveFracturesData.save2D(df, "Fraturas77", ".dat","");
        modeling_2d_nfrac_se1.setText("300");

         */

//		drawSetFractures2D(int numfrat, double meanL, double maxL,
//				double minL, double az, int set, double[] region)
        // TODO: colocar a opcao de entrada da regiao - reservatorio
        double[] regiao = new double[]{10, 10., 400, 400.};

        if (check_modeling_2d_holdonresults.isSelected()) {

            //double[] regiao = new double[]{10, 10., 400, 400.};
//			drawSetFractures2D(0,10,70,0, 0,1,regiao);
            drawSetFractures2D(numfrat1, 10, maxL1, minL1, az1, 1, regiao);
            drawSetFractures2D(numfrat2, 10, maxL2, minL2, az2, 2, regiao);
            drawSetFractures2D(numfrat3, 10, maxL3, minL3, az3, 3, regiao);

        } else {

            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            GraphicsContext gc1 = canvas1.getGraphicsContext2D();
            gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());

            drawSetFractures2D(numfrat1, 10, maxL1, minL1, az1, 1, regiao);
            drawSetFractures2D(numfrat2, 10, maxL2, minL2, az2, 2, regiao);
            drawSetFractures2D(numfrat3, 10, maxL3, minL3, az3, 3, regiao);

        }

        /*
        Random rn1 = new Random();
        Random rn2 = new Random();
        Random rn3 = new Random();

        for (int i = 0; i < 100; i++) {
        	int vh = rn3.nextInt(300);
			gc.strokeLine(rn1.nextInt(100), vh, rn2.nextInt(100), vh);
		}
         */
 /*
		int width = 600;
		int height = 400;
		boolean sharp = true;

		GraphicsContext gc = canvas.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        for (int x = 0; x < width; x+=10) {
            double x1 ;
            if (sharp) {
                x1 = x + 0.5 ;
            } else {
                x1 = x ;
            }
            gc.moveTo(x1, 0);
            gc.lineTo(x1, height);
            gc.stroke();
        }

        for (int y = 0; y < height; y+=10) {
            double y1 ;
            if (sharp) {
                y1 = y + 0.5 ;
            } else {
                y1 = y ;
            }
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }
         */
//		gc.setFill(Color.BLUE);
//		gc.fillRect(50,50,100,100);
//
//		gc.setFill(Color.GREEN);
//		gc.fillRect(0,0,25,50);
//
//
//		gc.setFill(Color.BLACK);
//		gc.fillRect(0,0,5,5);
        pane_modeling.getSelectionModel().select(tab_modeling_2d_view);

    }

    private void drawSetFractures2D(int numfrat, double meanL, double maxL,
            double minL, double az, int set, double[] region) {

        if (numfrat == 0) {

            //GraphicsContext gc = canvas.getGraphicsContext2D();
            System.out.println("Estou aqui dentro");

        } else {

            // region = diagonais da regiao - region[0] = minX, region[1] = minY
            //		region[2] = maxX, region[3] = maxY
            double lenght = meanL;
            double aperture;

            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setStroke(Color.BLUE);

            GraphicsContext gc1 = canvas1.getGraphicsContext2D();
            gc1.setStroke(Color.BLUE);

            Random rnd = new Random();

            // colocar rela�ao tamanho abertura
            gc.setLineWidth(1);

            gc1.setLineWidth(1);

            ArrayList<double[]> datafrat = new ArrayList<>();;

            for (int i = 0; i < numfrat; i++) {

                lenght = minL + (maxL - minL) * Math.random();
                //System.out.println(lenght);

                double xc = Math.random() * (region[2] - region[0]) + region[0];

                double yc = Math.random() * (region[3] - region[1]) + region[1];

                //System.out.println("xc= " + xc + " yc= "+yc);
                double[] s1 = Line.oblique(xc, yc, lenght, az);

                gc.strokeLine(s1[0], s1[1], s1[2], s1[3]);

                gc1.strokeLine(s1[0], s1[1], s1[2], s1[3]);

                //System.out.println(modeling_2d_saveanalysis.selectedProperty().get());
                if (modeling_2d_saveanalysis.selectedProperty().get()) {

                    datafrat.add(s1);
                }
            }

            //        System.out.println(datafrat.size());
            //        System.out.println(datafrat.size()>1);
            if (datafrat.size() > 1) {

                SaveFracturesData.save2D(datafrat, "TESTANDOBOTAO", ".dat", "");
            }
        }

    }

    @FXML
    private void modelingSaveStudy2D() {

    }

    @FXML
    private void btn_modeling_back() {
        //pane_modeling.getSelectionModel().select(tab_modeling_2d_view);
        tabPane_modeling.getSelectionModel().select(tab_modeling_2d);
    }

    private Canvas createCanvasGrid(int width, int height, boolean sharp) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1.0);
        for (int x = 0; x < width; x += 10) {
            double x1;
            if (sharp) {
                x1 = x + 0.5;
            } else {
                x1 = x;
            }
            gc.moveTo(x1, 0);
            gc.lineTo(x1, height);
            gc.stroke();
        }

        for (int y = 0; y < height; y += 10) {
            double y1;
            if (sharp) {
                y1 = y + 0.5;
            } else {
                y1 = y;
            }
            gc.moveTo(0, y1);
            gc.lineTo(width, y1);
            gc.stroke();
        }
        return canvas;
    }

    @FXML
    public void saveSclData() {
        //TODO: implementar salvar dados
        System.out.println("--------- Not implemented!!! --------");
    }

    public void findPlFilter() {
        //TODO: colocar sa�da de coeficientes da pawer law

//		dataTypeScl.selectionModelProperty().set(value);
        dataTypeScl.setValue("Selected SCL"); //("Load SCL");

        if (!listNew.isEmpty()) {
            btn_scanline_saveanalysis.setDisable(false);
            PowerLawOrtega.findFreqApertureLogOrtega(listNew);

            PowerLaw pl = PowerLawOrtega.findCoefficients(listNew);

            setInfoScanline(listNew);
            setInfoPowerLaw(pl);
        } else {
            btn_scanline_findpl.setDisable(true);
            btn_scanline_clearpl.setDisable(true);
        }

    }

    public void clearPlFilter() {
        //TODO: fazer a limpeza dos campos das analises dos dados selecionados
    }

    @FXML
    public void saveAnalysisSclFilter() {
        //TODO: salvar as an�lises feitas em arquivo .dat

        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // SaveFile(Santa_Claus_Is_Coming_To_Town, file);

            if (check_scanline_save_ortega.isSelected()) {
                SaveScanlineData.savePath(listNew, file);

                //Double[][] freqAperture = PowerLawOrtega.findFreqApertureLogOrtega(newScl);
                //SaveData.saveValues(Values1, nameValues1, values2, nameValues2, fileName, extension);
            } else {
                System.out.println("Tamanho do " + listNew.size());

                SaveScanlineData.save(listNew, "SCL_Filter", ".dat", "");
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Saving in file");
            alert.setHeaderText("Sucesful");
            alert.setContentText("OK!!");

            alert.showAndWait();
        }

    }


    /*
	 * --------------------------- Menu methods -----------------------------
     */
    @FXML
    public void exit() {
        Platform.exit();
    }


    /*
	 * ----------------------------  Tab SCANLINE Methods -------------------
     */
    @FXML
    public void advancedFilter() {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Advanced Filter");
        dialog.setHeaderText("Filter for scanline data:");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("world.png").toString()));
        // Set the button types.
        ButtonType filterButtonType = new ButtonType("Filter", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(filterButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField ap_min = new TextField();
        ap_min.setPromptText("Min Aperture");

        TextField ap_max = new TextField();
        ap_max.setPromptText("Max Aperture");

        TextField sp_min = new TextField();
        sp_min.setPromptText("Min Spacing");

        TextField sp_max = new TextField();
        sp_max.setPromptText("Max Spacing");

        ComboBox<String> cb = new ComboBox<>();
        cb.getItems().addAll(
                "InterativeSave", "AutoSave", "ManualSave");
        cb.setValue("InterativeSave");

        grid.add(new Label("Ap Min:"), 0, 0);
        grid.add(ap_min, 1, 0);
        grid.add(new Label("Sp Min:"), 0, 1);
        grid.add(sp_min, 1, 1);

        grid.add(new Label("Ap Max:"), 2, 0);
        grid.add(ap_max, 3, 0);

        grid.add(new Label("Sp Max:"), 2, 1);
        grid.add(sp_max, 3, 1);

        grid.add(cb, 3, 2);

        //TODO: fazer o disable para os outros campos
        // Enable/Disable login button depending on whether a username was entered.
        Node filterButton = dialog.getDialogPane().lookupButton(filterButtonType);
        filterButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        ap_min.textProperty().addListener((observable, oldValue, newValue) -> {
            filterButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> ap_min.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == filterButtonType) {
                return new Pair<>(ap_min.getText(), sp_min.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Ap Min=" + usernamePassword.getKey() + ", Sp Min=" + usernamePassword.getValue());
        });

    }

//	public void mouse(){
//		sclName.setStyle("-fx-background-color: #682");
//		sclApStd.setStyle("-fx-background-color: #682");
//		sclApMean.setStyle("-fx-background-color: #682");
//	}
    /**
     * Button for load dataset
     */
    @FXML
    protected Button btn_scanline_load;

    /**
     * Button for close Open Data Stage
     */
    @FXML
    protected Button btnClose;

    /**
     * Action for button load scanline data
     */
    @FXML
    public void loadSclData() throws IOException {

        /**
         * Open stage for selection of data file
         *
         */
        StageOpenData stageOpen = new StageOpenData();
        stageOpen.createStage();

    }

    /**
     * Action for button Close the Stage for opening data
     */
    @FXML
    protected void close_openDataStage() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    LogLog demo = null; // = new LogLog("Teste");
    // scanline informations clear

    @FXML
    public void sclInfoClear() {

        // so funciona se tiver sido gerado o grafico previamente
//			demo.setVisible(false); //para sumir com o grafico
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Clear data analysis");
        alert.setContentText("Are you sure, about that?");

        //	alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            sclName.setText("-");

            sclApStd.setText("-");
            sclApMean.setText("-");
            sclSpStd.setText("-");
            sclSpMean.setText("-");
            sclCVap.setText("-");
            sclCVsp.setText("-");

            coefA.setText("a");
            coefK.setText("-k");
            sclr2.setText("00");

            btn_scanline_plotpowerlaw.setDisable(true);
            btn_scanline_save.setDisable(true);
            btn_scanline_clear.setDisable(true);

            gPowerLaw.setData(null);

            ObservableList<Scl> data = FXCollections.observableArrayList();
            scl_table.setItems(data);

            scl_table_data_new.setItems(data);
            listNew.clear();

        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    /**
     * Create stage for Power Law plot
     *
     * @throws IOException
     */
    @FXML
    protected void plotPowerLaw() throws IOException, Exception {
        PowerLawStage stagePL = new PowerLawStage(
                MainStage.getAnalysisFile().getScanLine().getFracIntAnalysis());
        stagePL.createStage();
    }

    // Informações da Lei de potencia
    private void setInfoPowerLaw(PowerLaw pl) {

        coefA.setText(String.valueOf(RoundUtil.round(pl.getA(), 3)));
        coefK.setText(String.valueOf(RoundUtil.round(pl.getK(), 3)));
        sclr2.setText(String.valueOf(RoundUtil.round(pl.getR2(), 2)));

        coefA.setStyle("-fx-border-style: solid;-fx-border-color:red ");
        coefK.setStyle("-fx-border-style: solid;-fx-border-color:red ");
        sclr2.setStyle("-fx-border-style: solid;-fx-border-color:red ");
    }

    /*
	 * Inner Methods
     */
    private File dialogOpenFile(String format) {
        if (format == null) {
            format = "*.dat";
        }
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Open Scanline File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.dat"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        return selectedFile;
    }

    // melhorar depois com polimorfismo
    private void setInfoScanline(DataSCL d) {

        //TODO: colocar info sobre a power law
        sclName.setText(dataTypeScl.getSelectionModel().getSelectedItem());
        sclNumData.setText(String.valueOf(d.getAperture().size()));

        double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getAperture()));
        double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getAperture()));
        sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
        sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));

        double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getSpacing()));
        double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getSpacing()));
        sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
        sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));

        double sclcvap = sclapstd / sclapmean;
        double sclcvsp = sclspstd / sclspmean;

        sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
        sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
        sclName.setStyle("-fx-background-color: #FF2"); //mudar para css
    }

    //overload setInfoScanline method
    private void setInfoScanline(ArrayList<Scl> scldata) {

        ArrayList<Double> aplist = new ArrayList<>();
        ArrayList<Double> splist = new ArrayList<>();

        for (int i = 0; i < scldata.size(); i++) {
            aplist.add(scldata.get(i).getAp());
            splist.add(scldata.get(i).getSp());
        }

        sclName.setText(dataTypeScl.getSelectionModel().getSelectedItem());
        sclNumData.setText(String.valueOf(aplist.size()));

        double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(aplist));
        double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(aplist));
        sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
        sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));

        double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(splist));
        double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(splist));
        sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
        sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));

        double sclcvap = sclapstd / sclapmean;
        double sclcvsp = sclspstd / sclspmean;

        sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
        sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
        sclName.setStyle("-fx-background-color: #FF2");
    }


    /*
	private void setInfoScanline(ArrayList<Scl> sclnew){

		Double[][] freqAperture = findFreqApertureLogOrtega(sclnew);

		Double[] x = new Double[freqAperture.length];
		Double[] y = new Double[freqAperture.length];
		for (int i = 0; i < freqAperture.length; i++) {
			y[i] = freqAperture[i][0];
			x[i] = freqAperture[i][1];
		}

		ArrayList<Double> aplist = new ArrayList<>();
		ArrayList<Double> splist = new ArrayList<>();

		for (int i = 0; i < scldata.size(); i++) {
			aplist.add(scldata.get(i).getAp());
			splist.add(scldata.get(i).getSp());
		}

		sclName.setText("Selected New");
		sclNumData.setText(String.valueOf(aplist.size()));

		double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(aplist));
		double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(aplist));
		sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
		sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));

		double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(splist));
		double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(splist));
		sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
		sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));

		double sclcvap = sclapstd/sclapmean;
		double sclcvsp = sclspstd/sclspmean;

		sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
		sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
		sclName.setStyle("-fx-background-color: #FF2");
	}

     */

 /*
	 *
     */
    private void unbindData(Scl scldata) {

        if (scldata != null) {
            //TODO: implementar algo para pesistir cores na tabela
        }
    }

    /*
	 * New scanline data selected
     */
    ArrayList<Scl> listNew = new ArrayList<Scl>();
    //TableRow<Scl> currentRow = new TableRow<>();

    //Colocar dados na nova tabela
    private void bindData(Scl scldata) {

        if (check_scaline_new_analysis.isSelected()) {
            if (!listNew.isEmpty()) {
                listNew.clear();
                checkbox_filter_scl_data.setSelected(false);
            }
            ArrayList<Scl> listNew = new ArrayList<Scl>();
            check_scaline_new_analysis.setSelected(false);

        }

        if (scldata != null) {
            if (checkbox_filter_scl_data.isSelected()) {
                btn_scanline_findpl.setDisable(false);
            }

            if (listNew.isEmpty()) {
                listNew.add(scldata);
            }

            if (!listNew.contains(scldata)) {
                listNew.add(scldata);
            }

            apn.setCellValueFactory(new PropertyValueFactory<Scl, Double>("ap"));
            spn.setCellValueFactory(new PropertyValueFactory<Scl, Double>("sp"));

            //currentRow.getBackground()    //setStyle("-fx-background-color:lightcoral");
            scl_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ObservableList<Scl> dataNew = FXCollections.observableArrayList(listNew);

            scl_table_data_new.setItems(dataNew);
        }
    }

    //---------------------+----------------------+-------------------------+-----------------------------+--------

    /*
	 * ******************************************** Initialize Method *********************************************
     */
    //---------------------+----------------------+-------------------------+-----------------------------+--------
    @FXML
    public <T> void initialize() {

        //Binds
//		check_scaline_new_analysis.selectedProperty()
//		.bindBidirectional(checkbox_filter_scl_data.disableProperty());
//		check_scaline_new_analysis.selectedProperty()
//		.bind(checkbox_filter_scl_data.selectedProperty());
////        check_output_adv_study.selectedProperty().addListener((v, oldv, newv) -> {
//            if (newv == true) {
//                grid_output_adv_study.setDisable(false);
//            } else {
//                grid_output_adv_study.setDisable(true);
//            }
//        });
//
//        check_output_comments.selectedProperty().addListener((v, oldv, newv) -> {
//            if (newv == true) {
//                textarea_output_comments.setDisable(false);
//            } else {
//                textarea_output_comments.setDisable(true);
//            }
//        });
        //check_output_adv_study.selectedProperty().bind(grid_output_adv_study.setDisable(false));
        //Pane init
        // tabPane_main.getSelectionModel().select(tab_main_scanline);
        //tabPane_main.getSelectionModel().select(tab_main_modeling);
//		pane_modeling.getSelectionModel().select(tab_modeling_2d_view);
        //Buttons disable
/*        btn_scanline_plotpowerlaw.setDisable(true);
        btn_scanline_save.setDisable(true);
        btn_scanline_clear.setDisable(true);

        btn_scanline_saveanalysis.setDisable(true);
        btn_scanline_clearpl.setDisable(true);
        btn_scanline_findpl.setDisable(true);
        scl_table_data_new.setDisable(true);
         */
 /*        btn_modeling_2d_edit.setDisable(true);

        check_modeling_refine_2d.setSelected(true);//feito

        check_modeling_2d_holdonresults.setSelected(true); //feito

        check_scanline_save_ortega.setSelected(true);

        checkbox_filter_scl_data.setDisable(true); //feito
         */
        //-------------Modeling----------------
//        modeling_2d_saveanalysis.setSelected(true); //feito
        /*
		 * RadioButtons
         
       //combobox
        combo_modeling_dataset.getItems().addAll( 
                "SET1", "SET2", "SET3");  //feito
        combo_modeling_dataset.setValue("SET1");
         */
 /*
		 * bind checkbox
         */
 /*      tab_modeling_2d_modeling.disableProperty().bind(check_modeling_refine_2d.selectedProperty().not());

        //check_modeling_refine_2d.selectedProperty().bind(tab_modeling_2d_modeling.disabledProperty());
        checkbox_filter_scl_data.selectedProperty().addListener((event, oldValue, newValue) -> {
            if (checkbox_filter_scl_data.isSelected()) {//procedimento feito, listener nao
                //btn_scanline_saveanalysis.setDisable(false);
                btn_scanline_clearpl.setDisable(false);
                btn_scanline_findpl.setDisable(false);
                scl_table_data_new.setDisable(false);
            } else {
                btn_scanline_saveanalysis.setDisable(true);
                btn_scanline_clearpl.setDisable(true);
                btn_scanline_findpl.setDisable(true);
                scl_table_data_new.setDisable(true);
            }
        });
         */
 /*
		 * Listener scl_table
         */
 /*       scl_table.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
            unbindData(oldValue);
            bindData(newValue);
        });
         */
        // ComboBox initial options
        /*       dataTypeScl.getItems().addAll( //feito
                "Load SCL", "Selected SCL");
        dataTypeScl.setValue("Load SCL");

        dataTypeScl.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {
            String value = n; 						 //for debug only - apagar depois
            System.out.println("Valor nudado " + n); //for debug only - apagar depois
            dataTypeScl.setValue(n);
        });
         */
 /*
        //label.textProperty().bind(slider.valueProperty().asString());
        slider.valueProperty().addListener((o, ov, nv) -> {
            Double value = (Double) nv;
            if (value < 30) {
                label.setText("BAIXO");
            } else if (value > 70.0) {
                label.setText("ALTO");
            } else {
                label.setText("MEDIO");
            }
        });
        //text.textProperty().bind(slider.valueProperty().asString());
        text.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());

        checkbox.selectedProperty().bind(slider.valueProperty().greaterThanOrEqualTo(50));
         */
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
            AnalysisFile file = new AnalysisFile();
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
            MainStage.setAnalysisFile(file);
            MainStage.refreshStats();
            enableButtons();
        }
    }

    /*
     * Handle actions for Menu Item "Plot"
     * TODO: check if getAnalysisFile() is not null before create stage
     */
    @FXML
    protected void linechartStage() throws IOException {
        LineChartStage s
                = new LineChartStage(MainStage.getAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void scatterchartStage() throws IOException {
        ScatterChartStage s
                = new ScatterChartStage(MainStage.getAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void histogramStage() throws IOException {
        HistogramStage s
                = new HistogramStage(MainStage.getAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void variogramStage() throws IOException {
        VariogramStage s
                = new VariogramStage(MainStage.getAnalysisFile());
        s.createStage();
    }

    @FXML
    protected void saveGraph() throws IOException {
        //TODO
        ExportImageStage stage = new ExportImageStage();
        stage.createStage();
    }

    /**
     * Handle action for a checkbox on Tab Scanline
     */
    @FXML
    protected void checkboxChange() {
        if (checkbox_filter_scl_data.isSelected()) {
            //btn_scanline_saveanalysis.setDisable(false);
            btn_scanline_clearpl.setDisable(false);
            btn_scanline_findpl.setDisable(false);
            scl_table_data_new.setDisable(false);
        } else {
            btn_scanline_saveanalysis.setDisable(true);
            btn_scanline_clearpl.setDisable(true);
            btn_scanline_findpl.setDisable(true);
            scl_table_data_new.setDisable(true);
        }
    }

    private void enableButtons() {
        btn_scanline_save = (Button) MainStage.getRoot().lookup("#btn_scanline_save");
        btn_scanline_save.setDisable(false);
        btn_scanline_clear = (Button) MainStage.getRoot().lookup("#btn_scanline_clear");
        btn_scanline_clear.setDisable(false);
        btn_scanline_saveanalysis = (Button) MainStage.getRoot().lookup("#btn_scanline_saveanalysis");
        btn_scanline_saveanalysis.setDisable(false);
        btn_scanline_clearpl = (Button) MainStage.getRoot().lookup("#btn_scanline_clearpl");
        btn_scanline_clearpl.setDisable(false);
        btn_scanline_findpl = (Button) MainStage.getRoot().lookup("#btn_scanline_findpl");
        btn_scanline_findpl.setDisable(false);
        btn_save_graph = (Button)MainStage.getRoot().lookup("#btn_save_graph");
        btn_save_graph.setDisable(false);
        btn_scanline_plotpowerlaw= (Button)MainStage.getRoot().lookup("#btn_scanline_plotpowerlaw");
        btn_scanline_plotpowerlaw.setDisable(false);
        scl_table_data_new = (TableView) MainStage.getRoot().lookup("#scl_table_data_new");
        scl_table_data_new.setDisable(false);
    }
}
