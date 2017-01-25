package nfracgen.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import nfracgen.model.PowerLawOrtega;
import nfracgen.model.Scl;
import nfracgen.stage.ExportImageStage;
import nfracgen.stage.MainStage;
import nfracgen.stage.PowerLawStage;
import nfracgen.stage.StageOpenData;
import nfracgen.statistic.Stat;
import nfracgen.util.ArrayOperation;
import nfracgen.util.DataSCL;
import nfracgen.util.PowerLaw;
import nfracgen.util.RoundUtil;
import nfracgen.util.SaveScanlineData;

public class Tab_scanlineController implements Initializable {

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
    protected Label sclCVsp;

    @FXML
    protected CheckBox checkbox_filter_scl_data;
    @FXML
    protected Button btn_scanline_clearpl;
    @FXML
    protected Button btn_scanline_findpl;
    @FXML
    protected Button btn_scanline_saveanalysis;
    @FXML
    private Button btn_scanline_plotpowerlaw;
    @FXML
    private Button btn_scanline_save;

    @FXML
    private Button btn_scanline_clear;
    @FXML
    protected TableView<Scl> scl_table_data_new;
    @FXML
    protected ComboBox<String> dataTypeScl;
    @FXML
    private TableColumn<Scl, Double> apn, spn;
    @FXML
    private TableView<Scl> scl_table;

    @FXML
    private TableColumn<Scl, Double> ap, sp;

    @FXML
    private LineChart<Number, Number> gPowerLaw;

    private ArrayList<Scl> listNew = new ArrayList<Scl>();

    @FXML
    private CheckBox check_scanline_save_ortega,
            check_scaline_new_analysis;

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

    @FXML
    protected void findPlFilter() {
        //TODO: colocar saída de coeficientes da power law

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

    //Coloca dados na outra tabela
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

    /**
     * Clear scanline informations
     */
    @FXML
    protected void sclInfoClear() {

        // so funciona se tiver sido gerado o grafico previamente
//			demo.setVisible(false); //para sumir com o grafico
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    @FXML
    protected void saveAnalysisSclFilter() {
        //TODO: salvar as an�lises feitas em arquivo .dat

        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);
        //Show save file dialog
        File file = fileChooser.showSaveDialog(new Stage());

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving in file");
            alert.setHeaderText("Sucesful");
            alert.setContentText("OK!!");

            alert.showAndWait();
        }

    }

    /**
     * Power Law Informations
     *
     * @param pl
     */
    private void setInfoPowerLaw(PowerLaw pl) {

        coefA.setText(String.valueOf(RoundUtil.round(pl.getA(), 3)));
        coefK.setText(String.valueOf(RoundUtil.round(pl.getK(), 3)));
        sclr2.setText(String.valueOf(RoundUtil.round(pl.getR2(), 2)));

        coefA.setStyle("-fx-border-style: solid;-fx-border-color:red ");
        coefK.setStyle("-fx-border-style: solid;-fx-border-color:red ");
        sclr2.setStyle("-fx-border-style: solid;-fx-border-color:red ");
    }

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
     * Create stage for Power Law plot
     *
     * @throws IOException
     */
    @FXML
    protected void plotPowerLaw() throws IOException, Exception {
        PowerLawStage stagePL = new PowerLawStage(
                MainStage.getSclAnalysisFile().getScanLine().getFracIntAnalysis());
        stagePL.createStage();
    }

    @FXML
    public void saveSclData() {
        //TODO: implementar salvar dados
        System.out.println("--------- Not implemented!!! --------");
    }

    @FXML
    protected void saveGraph() throws IOException {
        WritableImage image = gPowerLaw.snapshot(new SnapshotParameters(), null);
        ExportImageStage stage = new ExportImageStage(image);
        stage.createStage();
    }

    @FXML
    public void advancedFilter() {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Advanced Filter");
        dialog.setHeaderText("Filter for scanline data:");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("world.png").toString()));
        // Set the button types.
        ButtonType filterButtonType = new ButtonType("Filter", ButtonBar.ButtonData.OK_DONE);
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

    @FXML
    protected void clearPlFilter() {
        //TODO: fazer a limpeza dos campos das analises dos dados selecionados
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        check_scaline_new_analysis.selectedProperty()
                .bindBidirectional(checkbox_filter_scl_data.disableProperty());
        check_scaline_new_analysis.selectedProperty()
                .bind(checkbox_filter_scl_data.selectedProperty());
        
        /* O procedimento abaixo é o mesmo que checkboxChange(), neste mesmo controller
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
        scl_table.getSelectionModel().selectedItemProperty().addListener((event, oldValue, newValue) -> {
            unbindData(oldValue);
            bindData(newValue);
        });
        dataTypeScl.setValue("Load SCL");
        
        dataTypeScl.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {            
            dataTypeScl.setValue(n);
        });
    }
    
    private void unbindData(Scl scldata) {

        if (scldata != null) {
            //TODO: implementar algo para pesistir cores na tabela
        }
    }

}
