package nfracgen.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nfracgen.analysis.Scanline;
import nfracgen.util.Line;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.SaveFracturesData;
import nfracgen.util.StatsSCL;

public class Tab_modellingController implements Initializable {

    /*
	 * Instancia de Arquivo aberto
     */
    File fileSclOpen;
    // Nova instancia;
    File newFileSclOpen;

    @FXML
    private Canvas canvas;

    @FXML
    private Canvas canvas1;

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

    @FXML
    private Label modeling_2d_nfrac_se1;

    @FXML
    private CheckBox check_modeling_refine_2d;

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
    protected Tab tab_modeling_2d_modeling;

    @FXML
    TabPane pane_modeling;

    @FXML
    protected ComboBox combo_modeling_dataset;

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
                new FileChooser.ExtensionFilter("Text Files", "*.dat"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        return selectedFile;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //combo_modeling_dataset.setValue("SET1");
        //tab_modeling_2d_modeling.disableProperty().bind(check_modeling_refine_2d.selectedProperty().not());
        /*label.textProperty().bind(slider.valueProperty().asString());
        slider.valueProperty().addListener((o, ov, nv) -> {
            Double value = (Double) nv;
            if (value < 30) {
                label.setText("BAIXO");
            } else if (value > 70.0) {
                label.setText("ALTO");
            } else {
                label.setText("MEDIO");
            }
        });*/
        //text.textProperty().bind(slider.valueProperty().asString());
        //text.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());

        //checkbox.selectedProperty().bind(slider.valueProperty().greaterThanOrEqualTo(50));
    }

}
