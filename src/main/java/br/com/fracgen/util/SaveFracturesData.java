package br.com.fracgen.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveFracturesData {


	public static void save2D(ArrayList<double[]> frats, String fileName, String extension, String path){

		PrintWriter  writer;
		// TODO: colocar a abertura

		System.out.println("quantidade da fratura = "+frats.size());
		try {
			writer = new PrintWriter(fileName + extension);

			for (double[] ds : frats) {
				writer.println(ds[0] +"\t"+ds[1] +"\t"+ds[2] +"\t"+ds[3]);
			}
			writer.close();
		} catch (IOException e) {

		}
	}

	public static void save3D(){

	}



}
