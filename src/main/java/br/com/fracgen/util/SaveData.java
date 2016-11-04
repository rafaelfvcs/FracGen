package br.com.fracgen.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveData {

	public static void saveValues(ArrayList<Double> values1, String nameValues1,
			ArrayList<Double> values2, String nameValues2, String fileName, String extension){

		PrintWriter  writer;
		// TODO: colocar a abertura
		try {
			writer = new PrintWriter(fileName + extension);

			for (int i = 0; i < values1.size(); i++) {
				writer.println(values1.get(i) + "\t" + values2.get(i) );
			}
			writer.close();
		} catch (IOException e) {

		}
	}
}
