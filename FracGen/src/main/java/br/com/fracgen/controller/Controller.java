package br.com.fracgen.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import br.com.fracgen.model.Scl;
import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.ArrayOperation;
import br.com.fracgen.util.DataSCL;
import br.com.fracgen.util.OpenScanlineData;
import br.com.fracgen.util.RoundUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{

	/*
	 * Scanlines - fase de testes
	 */
	@FXML
	private TableView<Scl> scl_table; // = new TableView();

	@FXML
	private TableColumn<Scl, Double> ap;

	@FXML
	private TableColumn<Scl, Double> sp;


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


	@FXML public void exit() {}


	@FXML
	public void loadSclData(ActionEvent event){

		/*
		 * Versao de teste apenas
		 */
		//TODO: colocar janela de dialogo para selecionar arquivo
        DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

		ArrayList<Scl> list = new ArrayList<Scl>();

		for (int i = 0; i < d.getAperture().size(); i++) {

			list.add(new Scl(RoundUtil.round(d.getAperture().get(i),3), RoundUtil.round(d.getSpacing().get(i),3)));
		}

		ap.setCellValueFactory(new PropertyValueFactory<Scl, Double>("ap"));
		sp.setCellValueFactory(new PropertyValueFactory<Scl, Double>("sp"));

		ObservableList<Scl> data = FXCollections.observableArrayList(list);

		scl_table.setItems(data);

		sclName.setText("Testando");
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

	}


	@FXML
	public void sclClear(){
		sclName.setText("");
		sclNumData.setText("");
		sclApStd.setText("");
		sclApMean.setText("");
		sclSpStd.setText("");
		sclSpMean.setText("");
		sclCVap.setText("");
		sclCVsp.setText("");

		ObservableList<Scl> data = FXCollections.observableArrayList();
		scl_table.setItems(data);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//TODO: Implementar alguns comandos de botões de simualação

	}
}
