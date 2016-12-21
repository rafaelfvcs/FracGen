package nfracgen.util;

import nfracgen.analysis.Fracture;
import nfracgen.analysis.Scanline;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class OpenScanlineData extends DataSCL {

    /**
     * Open a data file as ArrayList with Double values
     * @param fileName
     * @param separator
     * @param column
     * @param hasHeader
     * @return 
     */
    public static ArrayList<Double> openCSVFileToDouble(String fileName, String separator,
            int column, boolean hasHeader) {
        BufferedReader br = null;
        ArrayList<Double> values = new ArrayList<>();
        String line = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            if (hasHeader) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(separator);
                values.add(Double.valueOf(lineValues[column].trim().
                        replace(",", ".").replace("\"", "")));
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
    
    public static Scanline openCSVFileToScanline(String fileName, String separator,
            int apIndex, int spIndex, boolean hasHeader) {
        BufferedReader br = null;
        ArrayList<Fracture> values = new ArrayList<>();
        
        String line = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            if (hasHeader) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(separator);
                String ap = lineValues[apIndex].trim().
                        replace(",", ".").replace("\"", "");
                Double dAp = Double.NaN;
                if(!ap.isEmpty()){
                    dAp = Double.valueOf(ap);
                }
                Double dSp = Double.NaN;
                String sp = lineValues[spIndex].trim().
                        replace(",", ".").replace("\"", "");
                if(!sp.isEmpty()){
                    dSp = Double.valueOf(sp);
                }
                Fracture f = new Fracture(dAp, dSp);
                values.add(f);
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
        Scanline sl = new Scanline(values);
        return sl;
    }

    /**
     * @author rafaelfvcs
     * @param file
     * @return dScl = data loaded =[
     */
    public static DataSCL openScl(String file) {

        // colocar se o arquivo tem ou nao cabecalho
        if (file == null) {
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
           
            while (in.hasNext()) {

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
