package nfracgen.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import nfracgen.model.Scl;

public class SaveScanlineData {

    /*
     *  Save Scanline Data
     */
	public static void save(ArrayList<Scl> scldata,String fileName, String extension, String path){

		PrintWriter  writer;
		// TODO: colocar a abertura
		try {
			writer = new PrintWriter(fileName + extension);

			for (Scl scl : scldata) {
				writer.println(scl.getSp() + "\t" + scl.getAp());
			}
			writer.close();
		} catch (IOException e) {

		}
	}

public static void savePath(ArrayList<Scl> scldata, File file){

		PrintWriter  writer;
		// TODO: colocar a abertura
		try {
			writer = new PrintWriter(file);

			for (Scl scl : scldata) {
				writer.println(scl.getSp() + "\t" + scl.getAp());
			}
			writer.close();
		} catch (IOException e) {

		}
	}




	private final String strId = "id";
    private final String strSp = "Sp";
    private final String strAp = "Ap";

    private void saveCSV(String filename, String separator, ArrayList[][] data) {
        String header = strId + separator + strSp + separator + strAp + "\n";
        try (FileWriter writer = new FileWriter(filename, false)) {
            writer.append(header);
            for (int i = 0; i < data.length - 1; i++) {
                String lineValues = String.valueOf(data[i][0].get(0))
                        + separator + String.valueOf(data[i][1].get(0))
                        + separator + String.valueOf(data[i][2].get(0)) + "\n";
                writer.append(lineValues);
            }
            //adds the last value:
            String lineValue = String.valueOf(data[data.length - 1][0].get(0))
                    + separator + String.valueOf(data[data.length - 1][1].get(0))
                    + separator + String.valueOf(data[data.length - 1][2].get(0));
            writer.append(lineValue);
            writer.flush();

        } catch (IOException e) {
        }
    }




}
