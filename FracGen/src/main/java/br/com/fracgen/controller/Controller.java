package br.com.fracgen.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import br.com.fracgen.model.PowerLawOrtega;
import br.com.fracgen.model.Scl;
import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.ArrayOperation;
import br.com.fracgen.util.DataSCL;
import br.com.fracgen.util.OpenScanlineData;
import br.com.fracgen.util.PowerLaw;
import br.com.fracgen.util.RoundUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Controller {

	/*
	 * Main application
	 */
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
	BorderPane mainPane;

	/*
	 *
	 */
	File fileOpen;

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

	/*
	 * Graphs
	 */
	@FXML
	private LineChart<Number, Number> gPowerLaw; // = new LineChart<Number,Number>(xAxis,yAxis);;


	/*
	 * --------------------------------- Methods TOOLBAR ------------------------------
	 */
	@FXML
	public void goToGeoModeling(){
		tabPane_main.getSelectionModel().select(tab_main_scanline);
	}

	@FXML
	public void goToStatistic(){
		tabPane_main.getSelectionModel().select(tab_main_analysis);
		tabPane_analysis.getSelectionModel().select(tab_analysis_statistics);
	}

	@FXML
	public void goTo3D(){
		tabPane_main.getSelectionModel().select(tab_main_modeling);
		tabPane_modeling.getSelectionModel().select(tab_modeling_3d);

	}

	@FXML
	public void goTo2D(){
		tabPane_main.getSelectionModel().select(tab_main_modeling);
		tabPane_modeling.getSelectionModel().select(tab_modeling_2d);
	}

	@FXML
	public void goToMC(){
		tabPane_main.getSelectionModel().select(tab_main_analysis);
		tabPane_analysis.getSelectionModel().select(tab_analysis_montecarlo);
	}

	@FXML
	public void goToAndroid(){
//		tabPane_main.getSelectionModel().select(tab_main_analysis);
//		tabPane_analysis.getSelectionModel().select(tab_analysis_montecarlo);
	}

	/*
	 * ------------------------------------ Methods--------------------------------------
	 */
	@FXML
	public void saveSclData(){

	}

	/*
	 * Logout
	 */

	@FXML
	public void exit() {

	}

	/*
	 * Tab Scanline Methods
	 */

	// load scanline data
	@FXML
	public void loadSclData(){

		/*
		fileOpen = dialogOpenFile("*.dat");

		if(fileOpen != null){
			btn_scanline_plotpowerlaw.setDisable(false);
			btn_scanline_save.setDisable(false);
			btn_scanline_clear.setDisable(false);

		}
		*/

		btn_scanline_plotpowerlaw.setDisable(false);
		btn_scanline_save.setDisable(false);
		btn_scanline_clear.setDisable(false);
		DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

		//DataSCL d = OpenScanlineData.openScl(fileOpen.toString());

		ArrayList<Scl> list = new ArrayList<Scl>();

		for (int i = 0; i < d.getAperture().size(); i++) {

			list.add(new Scl(RoundUtil.round(d.getAperture().get(i),3), RoundUtil.round(d.getSpacing().get(i),3)));
		}

		ap.setCellValueFactory(new PropertyValueFactory<Scl, Double>("ap"));
		sp.setCellValueFactory(new PropertyValueFactory<Scl, Double>("sp"));

		ObservableList<Scl> data = FXCollections.observableArrayList(list);

		scl_table.setItems(data);

		sclName.setText("Teste SCL");
		sclNumData.setText(String.valueOf(d.getAperture().size()));

		double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getAperture()));
		double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getAperture()));
		sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
		sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));

		double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getSpacing()));
		double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getSpacing()));
		sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
		sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));

		double sclcvap = sclapstd/sclapmean;
		double sclcvsp = sclspstd/sclspmean;

		sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
		sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
		sclName.setStyle("-fx-background-color: #FF2");

	}


	// scanline informations clear
	@FXML
	public void sclInfoClear(){

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Clear data analysis");
		alert.setContentText("Are you sure, about that?");
//
//		alert.showAndWait();
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

			sclName.setText("-");
			sclNumData.setText("-");
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

			ObservableList<Scl> data = FXCollections.observableArrayList();
			scl_table.setItems(data);

		} else {
		    // ... user chose CANCEL or closed the dialog
		}






	}

	//Calculate and plot power law
	public void plotPowerLaw(){

//		DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

//		File file = dialogOpenFile("*.dat");

		//PowerLaw pl = PowerLawOrtega.findCoefficients(fileOpen.toString());

		PowerLaw pl = PowerLawOrtega.findCoefficients("src/main/resources/data.dat");

		coefA.setText(String.valueOf(RoundUtil.round(pl.getA(),3)));
		coefK.setText(String.valueOf(RoundUtil.round(pl.getK(),3)));
		sclr2.setText(String.valueOf(RoundUtil.round(pl.getR2(),2)));

		coefA.setStyle("-fx-border-style: solid;-fx-border-color:red ");
		coefK.setStyle("-fx-border-style: solid;-fx-border-color:red ");
		sclr2.setStyle("-fx-border-style: solid;-fx-border-color:red ");
		//System.out.println(pl.getA());
        gPowerLaw.setTitle("Scanline, 2010");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Year");

        // Customize the x-axis, so points are scattred uniformly
        xAxis.setAutoRanging(true);

        xAxis.setLowerBound(1900);
        xAxis.setUpperBound(2300);
        xAxis.setTickUnit(50);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Population (in millions)");

//		 Set the data for the chart
        ObservableList<XYChart.Series<Number,Number>> chartData =
                        XYChartDataUtil.getPowerLaw();

        gPowerLaw.setData(chartData);
	}


	public void saveDataScl() {
		//TODO: implements save scanline data
	}

	/*
	 * Inner Methods
	 */
	private File dialogOpenFile(String format){
		if(format==null){
			format = "*.dat";
		}
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Scanline File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.dat"));
		 File selectedFile = fileChooser.showOpenDialog(stage);

		 return selectedFile;
	}

	// Teste aplication Bind()
	@FXML
	Slider slider;

	@FXML
	TextField text;

	@FXML
	Label label;

	@FXML
	CheckBox checkbox;

	/*
	 * Initialize Method
	 */
	public <T> void initialize(){

		//Pane init
		tabPane_main.getSelectionModel().select(tab_main_scanline);
		//Buttons disable
		btn_scanline_plotpowerlaw.setDisable(true);
		btn_scanline_save.setDisable(true);
		btn_scanline_clear.setDisable(true);

		//label.textProperty().bind(slider.valueProperty().asString());
		slider.valueProperty().addListener((o, ov, nv) -> {
			Double value = (Double) nv;
			if(value < 30){
				label.setText("BAIXO");
			}else if(value > 70.0){
				label.setText("ALTO");
			}else{
				label.setText("MEDIO");
			}
		});
		//text.textProperty().bind(slider.valueProperty().asString());
		text.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());

		checkbox.selectedProperty().bind(slider.valueProperty().greaterThanOrEqualTo(50));


	}
}
