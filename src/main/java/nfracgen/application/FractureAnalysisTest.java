package nfracgen.application;

import nfracgen.analysis.FractureAnalysis;
import java.util.ArrayList;
import static javafx.application.Application.launch;

public class FractureAnalysisTest {   
    
    public void test() {
        ArrayList<Double> sp = new ArrayList<>();
        sp.add(0.54);
        sp.add(1.5);
        sp.add(1.7);
        sp.add(2.0);
        sp.add(3.0);
        sp.add(6.0);
        sp.add(9.0);

        ArrayList<Double> ap = new ArrayList<>();
        ap.add(0.1);
        ap.add(0.12);
        ap.add(0.7);
        ap.add(0.4);
        ap.add(0.12);
        ap.add(0.13);
        ap.add(0.34);

        FractureAnalysis analysis = new FractureAnalysis();
        ArrayList[][] array = analysis.listAndSort(sp, true);
        for(int i =0; i<array.length-1 ; i++){
            System.out.println(array[i][0]+" "+array[i][1]);
        }        
        Double length = analysis.getScanlineLength(ap, false);
        System.out.println(length);
        ArrayList[][] values = analysis.filterAndNormalize(array, length);
        for(int i=0; i< values.length-1; i++){
            System.out.println(values[i][0]+" "+values[i][1]+" "+values[i][2]);
        }            
    }

    public static void main(String args[]) {        
        FractureAnalysisTest test = new FractureAnalysisTest();
        test.test();
    }

}
