package nfracgen.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import nfracgen.stage.MainStage;

public class LoginController implements Initializable{

	@FXML
	private BorderPane mainPane;

	@FXML
	private Label lblMessage;
	//comentario
	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private void btnLoginAction(ActionEvent event) throws IOException{
		if(txtUsername.getText().equals("admin") &&  txtPassword.getText().equals("admin")){

			((Node) (event.getSource())).getScene().getWindow().hide();

			//lblMessage.setText("Bem vindo: " + txtUsername.getText());
			MainStage.showMainStage();

		}else{
			lblMessage.setText("Username or Password invalid");
			txtUsername.setText("");
			txtPassword.setText("");
		}
	}

	@FXML
	Hyperlink site_nfracgen;


	//not working
	@FXML
	private void siteLink(){
		site_nfracgen = new Hyperlink("http://www.ngracgen.com");
		mainPane.getChildren().addAll(site_nfracgen);
	}


	@FXML
	private void sendToNfracgen(){

	}




	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}


}
