package nfracgen.statistic;

import java.util.ArrayList;

public class StdDeviation {
    /**
     * Calculate standard deviation for a vector.     
     * @param vector
     * @return 
     */
    public static double stdDeviation(ArrayList<Double> vector){        
        double averageValue = Stat.mean(vector);
        double sum = 0.;
        for(int i= 0; i<vector.size(); i++){
            sum += Math.pow((averageValue - vector.get(i).doubleValue()), 2);
        }
        double result = Math.sqrt(sum/(vector.size()-1));
        return result;
    }
    
    /**
     * Calculate logarithmic standard deviation for a vector.
     * The variable baseLog usually receives 10 as input. 
     * @param vector
     * @param baseLog 
     * @return 
     */
    public static double logStdDeviation(ArrayList<Double> vector, int baseLog){        
        double avrValue = Stat.mean(vector);
        double sum = 0.;
        for(int i = 0; i< vector.size(); i++){
            sum += Math.pow ((Math.log(sum) - Math.log(avrValue) ), 2);
        }
        double sqrtValue = Math.sqrt(sum/(vector.size()-1));
        double result = Math.pow(baseLog, sqrtValue);
        return result;
    }

}
