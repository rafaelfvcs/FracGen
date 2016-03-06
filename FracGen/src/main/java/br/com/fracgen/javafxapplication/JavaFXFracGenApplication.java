package br.com.fracgen.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXFracGenApplication extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/views/LayoutMain.fxml"));
		Scene scene = new Scene(root);
//		scene.getStylesheets().add("/styles/styles.css");
		stage.setScene(scene);
//		stage.setTitle("Login");
		stage.setTitle("NFracGen");
		stage.show();
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		primaryStage.setTitle("NFracGen");
//
//		Pane root = FXMLLoader.load(getClass().getResource("/views/LayoutMain.fxml"));
//
//		Scene scene = new Scene(root,700,500); // 600, 400
//		scene.getStylesheets().add("styles.css");
//
//		primaryStage.setScene(scene);
//
//		primaryStage.show();
//	}

}
