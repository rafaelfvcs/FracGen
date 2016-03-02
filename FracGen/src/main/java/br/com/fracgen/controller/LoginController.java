package br.com.fracgen.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private Label lblMessage;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private void btnLoginAction(ActionEvent event) throws IOException{
		if(txtUsername.getText().equals("admin") &&  txtPassword.getText().equals("admin")){

			((Node) (event.getSource())).getScene().getWindow().hide();

			//lblMessage.setText("Bem vindo: " + txtUsername.getText());
			Parent parent = FXMLLoader.load(getClass().getResource("/views/LayoutMain.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("NFracGen - alpha");
			stage.show();

		}else{
			lblMessage.setText("Username or Password invalid");
		}
	}

	@FXML
	private void siteLink(){

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}


}
