package nfracgen.application;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public class CanvasStudy {

	public static void main(String[] args) {




		Group root = new Group();
		Scene s = new Scene(root, 300, 300, Color.BLACK);

		final Canvas canvas = new Canvas(250,250);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.BLUE);
		gc.fillRect(75,75,100,100);

		root.getChildren().add(canvas);

	}

}
