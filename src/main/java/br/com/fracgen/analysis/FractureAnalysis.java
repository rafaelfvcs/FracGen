package br.com.fracgen.analysis;

import java.util.ArrayList;
import java.util.Collections;

public class FractureAnalysis {
    
    public double getFractureIntensity(int fractures_number, 
            double scanlineLength){
        return fractures_number/scanlineLength;
    }
    
    public double getAverageSpacing(int fractures_number, 
            double scanlineLength){
        return scanlineLength/fractures_number;
    }

    public double getScanlineLength(ArrayList<Double> values, 
            boolean hasHeader) {
        double length = 0.;
        int inc = 0;
        if (hasHeader) {
            inc++;
        }
        for (int i = inc; i < values.size() - 1; i++) {           
            length += (double) values.get(i);            
        }
        return length;
    }

    public ArrayList[][] listAndSort(ArrayList<Double> list, boolean reverse) {
        Collections.sort(list);
        ArrayList[][] arrayList = new ArrayList[list.size()][list.size()];
        if(reverse){
            int i = list.size() - 1;
            for (Double values : list) {
            arrayList[i][0] = new ArrayList();
            arrayList[i][1] = new ArrayList();
            arrayList[i][0].add(i + 1);
            arrayList[i][1].add(values);
            i--;
            }    
        } else {            
            for (int i = 0; i<list.size()-1; i++) {
            arrayList[i][0] = new ArrayList();
            arrayList[i][1] = new ArrayList();
            arrayList[i][0].add(i + 1);
            arrayList[i][1].add(list.get(i));            
            }    
        }
        
        return arrayList;
    }

    public ArrayList[][] filterAndNormalize(ArrayList[][] arrayList, double scanLength) {
        //first we will count how many lines will have the dataset filtered:
        int linesNumber = 1;
        for (int i = 1; i < arrayList.length; i++) {
            double prevValue = (double) arrayList[i - 1][1].get(0);
            double currValue = (double) arrayList[i][1].get(0);
            if (prevValue != currValue) {
                linesNumber++;
            }
        }
        //now we will count cumulative frequency and simplify the list:
        int countLines = 0;
        ArrayList[][] listValues = new ArrayList[linesNumber][3];
        for (int i = 1; i < arrayList.length; i++) {
            double prevValue = (double) arrayList[i - 1][1].get(0);
            double currValue = (double) arrayList[i][1].get(0);
            if (prevValue != currValue) {
                listValues[countLines][0] = new ArrayList();
                listValues[countLines][1] = new ArrayList();
                listValues[countLines][2] = new ArrayList();
                listValues[countLines][0].add(arrayList[i - 1][0].get(0));
                listValues[countLines][1].add(arrayList[i - 1][1].get(0));
                double aux = (double) Double.parseDouble(
                        arrayList[i - 1][0].get(0).toString());
                listValues[countLines][2].add(aux / scanLength);
                countLines++;
            }
        }
        //add the last value to the list; What happens if the last value has a pair? Will not be counted
        listValues[countLines][0] = new ArrayList();
        listValues[countLines][1] = new ArrayList();
        listValues[countLines][2] = new ArrayList();

        listValues[countLines][0].add(arrayList[arrayList.length - 1][0].get(0));
        listValues[countLines][1].add(arrayList[arrayList.length - 1][1].get(0));
        double aux2 = (double) Double.parseDouble(
                arrayList[arrayList.length - 1][0].get(0).toString());
        listValues[countLines][2].add(aux2 / scanLength);
        return listValues;
    }

    
}
