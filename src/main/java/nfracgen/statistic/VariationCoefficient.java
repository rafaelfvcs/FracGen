package nfracgen.statistic;

import java.util.ArrayList;

public class VariationCoefficient {
    /**
     * 
     * @param stdDeviation
     * @param arithmeticAvg
     * @return 
     */
    public static double variationCoefficient(double stdDeviation, double 
            arithmeticAvg){
        return 100*stdDeviation/arithmeticAvg;    
    }
    
    /**
     * 
     * @param vector
     * @return 
     */
    public static double variationCoefficient(ArrayList<Double> vector){        
        double avgValue = Stat.mean(vector);        
        double stdDevValue = StdDeviation.stdDeviation(vector);
        return 100*stdDevValue/avgValue;
    }
    
}
