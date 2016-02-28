package br.com.fracgen.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class OpenScanlineData extends DataSCL{

	/**
	 * @author elidio
	 * @param fileName
	 * @param separator
	 * @param column
	 * @return
	 */
    public List<Double> openCSVFileToDouble(String fileName, String separator,
            int column) {
        BufferedReader br = null;
        List<Double> values = new ArrayList<>();
        String line = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(separator);
                values.add(Double.valueOf(lineValues[column]));
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return values;
    }

    /**
     * @author rafaelfvcs
     * @param file
     * @return dScl = data loaded =[
     */
    public static DataSCL openScl(String file){

    	// colocar se o arquivo tem ou nao cabecalho

		if(file == null){
			file = "data.dat";
		}

		DataSCL dScl = new DataSCL();
		int cont = 0;

		ArrayList<Double> aperture = new ArrayList<>();
		ArrayList<Double> spacing = new ArrayList<>();

		//Leitura de arquivo .dat contendo dados de espaçamentos, aberturas
		Scanner in;

		try {
			in = new Scanner(new FileReader(file));
			while (in.hasNext()){

				String sp = in.next();
				String ap = in.next();
				aperture.add(Double.parseDouble(ap));
				spacing.add(Double.parseDouble(sp));
				cont++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		dScl.setAperture(aperture);
		dScl.setSpacing(spacing);

		return dScl;
	}




}