package br.com.fracgen.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fracgen.model.PowerLawOrtega;
import br.com.fracgen.model.Scl;
import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.ArrayOperation;
import br.com.fracgen.util.DataSCL;
import br.com.fracgen.util.OpenScanlineData;
import br.com.fracgen.util.PowerLaw;
import br.com.fracgen.util.RoundUtil;
import br.com.fracgen.util.XYChartDataUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.NumberStringConverter;

public class Controller {

	/*
	 * Main application
	 */
	@FXML
	BorderPane mainPane;

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

	/*
	 * CheckBox
	 */
	@FXML
	private CheckBox checkbox_filter_scl_data;

	/*
	 *
	 */

	// Others
	@FXML
	private ComboBox<String> dataTypeScl;

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

	@FXML
	private Button btn_scanline_findpl;

	@FXML
	private Button btn_scanline_clearpl;

	@FXML
	private Button btn_scanline_saveanalysis;


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
	 * ---------------------------------- Elements ------------------------------------
	 */


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
		//TODO: implementar salvar dados
	}

	public void findPlFilter(){
		//TODO: colocar saída de coeficientes da pawer law
		PowerLawOrtega.findFreqApertureLogOrtega(listNew);
		setInfoScanline(listNew);
	}

	public void clearPlFilter(){
		//TODO: fazer a limpeza dos campos das analises dos dados selecionados
	}

	public void saveAnalysisSclFilter(){
		//TODO: salvar as análises feitas em arquivo .dat
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
	public void advancedFilter(){

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
				"Var1", "Var2","Var3");
		cb.setValue("Var1");


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

	@FXML
	public void load2(){

		/*
		for (int i = 0; i < listNew.size(); i++) {
			System.out.println(listNew.get(i).getAp());

		}
		System.out.println("\n");
		System.out.println(listNew.size());
		*/
	}

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
		//*****

		setInfoScanline(d);

//		sclName.setText("Teste SCL");
//		sclNumData.setText(String.valueOf(d.getAperture().size()));
//
//		double sclapmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getAperture()));
//		double sclapstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getAperture()));
//		sclApStd.setText(String.valueOf(RoundUtil.round(sclapstd, 3)));
//		sclApMean.setText(String.valueOf(RoundUtil.round(sclapmean, 3)));
//
//		double sclspmean = Stat.calculateMean(ArrayOperation.arrayListToArray(d.getSpacing()));
//		double sclspstd = Stat.getStdDev(ArrayOperation.arrayListToArray(d.getSpacing()));
//		sclSpStd.setText(String.valueOf(RoundUtil.round(sclspstd, 3)));
//		sclSpMean.setText(String.valueOf(RoundUtil.round(sclspmean, 3)));
//
//		double sclcvap = sclapstd/sclapmean;
//		double sclcvsp = sclspstd/sclspmean;
//
//		sclCVap.setText(String.valueOf(RoundUtil.round(sclcvap, 3)));
//		sclCVsp.setText(String.valueOf(RoundUtil.round(sclcvsp, 3)));
//		sclName.setStyle("-fx-background-color: #FF2");

	}

	// scanline informations clear
	@FXML
	public void sclInfoClear(){

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Clear data analysis");
		alert.setContentText("Are you sure, about that?");

		//	alert.showAndWait();
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

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

	//Calculate and plot power law
	public void plotPowerLaw(){
		//TODO: Colocar no formato de carregamento - mudar o nome do botão
		//DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");
		//File file = dialogOpenFile("*.dat");
		//PowerLaw pl = PowerLawOrtega.findCoefficients(fileOpen.toString());

		PowerLaw pl;

		if(dataTypeScl.getSelectionModel().getSelectedItem().equals("Raw SCL")){
			pl = PowerLawOrtega.findCoefficients("src/main/resources/data.dat");
		}else{
			pl = PowerLawOrtega.findCoefficients(listNew);
		}

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

        //Set the data for the chart
        ObservableList<XYChart.Series<Number,Number>> chartData =
                        XYChartDataUtil.getPowerLaw();
        gPowerLaw.setData(chartData);
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

	// melhorar depois com polimorfismo
	private void setInfoScanline(DataSCL d){

		//TODO: colocar info sobre a power law
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
		sclName.setStyle("-fx-background-color: #FF2"); //mudar para css
	}

	//overload setInfoScanline method
	private void setInfoScanline(ArrayList<Scl> scldata){

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

	/*
	 *
	 */
	private void unbindData(Scl scldata){

		if(scldata !=null){
			//TODO: implementar algo para pesistir cores na tabela
		}
	}

	/*
	 * New scanline data selected
	 */
	ArrayList<Scl> listNew = new ArrayList<Scl>();
	//TableRow<Scl> currentRow = new TableRow<>();

	private void bindData(Scl scldata){

		if(scldata !=null){

			if(listNew.isEmpty()){
				listNew.add(scldata);
			}

			if(!listNew.contains(scldata)){
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


	// **********  ******  ******  Teste aplication Bind() ***** ****** *********   ******
	// Apagar:
	@FXML
	Slider slider;

	@FXML
	TextField text;

	@FXML
	Label label;

	@FXML
	CheckBox checkbox;

	//---------------------+----------------------+-------------------------+-----------------------------+--------
	/*
	 * ******************************************** Initialize Method *********************************************
	 */
	//---------------------+----------------------+-------------------------+-----------------------------+--------
	@FXML
	public <T> void initialize(){

		//Pane init
		tabPane_main.getSelectionModel().select(tab_main_scanline);
		//Buttons disable
		btn_scanline_plotpowerlaw.setDisable(true);
		btn_scanline_save.setDisable(true);
		btn_scanline_clear.setDisable(true);

		btn_scanline_saveanalysis.setDisable(true);
		btn_scanline_clearpl.setDisable(true);
		btn_scanline_findpl.setDisable(true);
		scl_table_data_new.setDisable(true);

		/*
		 * bind checkbox
		 */
		checkbox_filter_scl_data.selectedProperty().addListener((event, oldValue, newValue) -> {
			if(checkbox_filter_scl_data.isSelected()){
				btn_scanline_saveanalysis.setDisable(false);
				btn_scanline_clearpl.setDisable(false);
				btn_scanline_findpl.setDisable(false);
				scl_table_data_new.setDisable(false);
			}else{
				btn_scanline_saveanalysis.setDisable(true);
				btn_scanline_clearpl.setDisable(true);
				btn_scanline_findpl.setDisable(true);
				scl_table_data_new.setDisable(true);
			}
		});

		/*
		 * Listener scl_table
		 */
		scl_table.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
			unbindData(oldValue);
			bindData(newValue);
		});

		// ComboBox initial options
		dataTypeScl.getItems().addAll(
				"Raw SCL", "Choose SCL");
		dataTypeScl.setValue("Raw SCL");

		dataTypeScl.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {
			String value = n; 						 //for debug only - apagar depois
			System.out.println("Valor nudado " + n); //for debug only - apagar depois
			dataTypeScl.setValue(n);
		});

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
