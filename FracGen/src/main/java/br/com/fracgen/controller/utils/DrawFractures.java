package br.com.fracgen.controller.utils;

import java.util.ArrayList;
import java.util.Random;

import br.com.fracgen.util.Line;
import br.com.fracgen.util.SaveFracturesData;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public class DrawFractures {


	public static void drawSetFractures2D(ToggleButton modeling_2d_saveanalysis, Canvas canvas,
			Canvas canvas1,
			int numfrat,
			double meanL, double maxL,
			double minL, double az, int set, double[] region){

		if(numfrat == 0){
			//GraphicsContext gc = canvas.getGraphicsContext2D();
			System.out.println("Estou aqui dentro");

		}else{

			// region = diagonais da regiao - region[0] = minX, region[1] = minY
			//		region[2] = maxX, region[3] = maxY
			double lenght = meanL;
			double aperture;

			GraphicsContext gc = canvas.getGraphicsContext2D();
	        gc.setStroke(Color.BLUE);

	        GraphicsContext gc1 = canvas1.getGraphicsContext2D();
	        gc1.setStroke(Color.BLUE);

	        Random rnd = new Random();

	        // colocar relaçao tamanho abertura
	        gc.setLineWidth(1);

	        gc1.setLineWidth(1);

	        ArrayList<double[]> datafrat = new ArrayList<>();;

	        for (int i = 0; i < numfrat; i++) {

	        	lenght = minL + (maxL - minL)*Math.random();
	        	//System.out.println(lenght);

	        	double xc = Math.random()*(region[2]-region[0]) + region[0];

	        	double yc = Math.random()*(region[3]-region[1]) + region[1];

	        	//System.out.println("xc= " + xc + " yc= "+yc);

	        	double[] s1 = Line.oblique(xc, yc, lenght, az);

	        	gc.strokeLine(s1[0], s1[1], s1[2], s1[3]);

	        	gc1.strokeLine(s1[0], s1[1], s1[2], s1[3]);

	        	//System.out.println(modeling_2d_saveanalysis.selectedProperty().get());

	        	if(modeling_2d_saveanalysis.selectedProperty().get()){

	        		datafrat.add(s1);
	    		}
			}

	//        System.out.println(datafrat.size());

	        if (datafrat.size() > 1) {

	    		SaveFracturesData.save2D(datafrat, "TESTANDOBOTAO", ".dat","");
			}
		}
	}

	public static void drawSetFractures3D(){

	}


}
