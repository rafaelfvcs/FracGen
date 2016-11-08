package nfracgen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class ControllerCanvas {
	@FXML
    private ScrollPane scrollPane;

    Canvas canvas;
    @FXML
    protected void draw(ActionEvent event) {

    canvas = new Canvas(500,500);
    scrollPane.setContent(canvas);

    GraphicsContext gc = canvas.getGraphicsContext2D();
        Random r = new Random();

        for(int i = 0; i<=50; i++) {
            for(int j = 0; j<=50; j++) {
                if (r.nextBoolean()) {
                    gc.setFill(Color.BLACK);
                } else {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(i, j, i+1, j+1);
            }
        }
    }
}