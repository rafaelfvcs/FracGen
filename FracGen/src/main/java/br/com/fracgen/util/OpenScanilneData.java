package br.com.fracgen.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenScanilneData {

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
}