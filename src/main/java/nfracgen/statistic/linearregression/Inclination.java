
package nfracgen.statistic.linearregression;

import java.util.ArrayList;
import nfracgen.statistic.Stat;

public class Inclination {
    /**
     * 
     * @param arrayX
     * @param arrayY
     * @return
     * @throws Exception 
     */
    public static double getInclination(ArrayList<Double> arrayX, 
            ArrayList<Double> arrayY) throws Exception {
        if (arrayX.size() != arrayY.size()) {
            throw new Exception("X and Y arrays must have same size.");
        }
        double sumX2 = 0.;
        double sumXY = 0.;
        for (int i = 0; i < arrayX.size(); i++) {
            sumX2 += Math.pow(arrayX.get(i), 2);
            sumXY += arrayX.get(i)* arrayY.get(i);
        }        
        double avgX = Stat.mean(arrayX);
        double avgY = Stat.mean(arrayY);
        double sXX = sumX2 - (arrayX.size() * Math.pow(avgX, 2));
        double sXY = sumXY - (arrayX.size() * avgX * avgY);
        return sXY / sXX;
    }

    /**
     * 
     * @param arrayX
     * @param arrayY
     * @param averageX
     * @param averageY
     * @return
     * @throws Exception 
     */
    public static double getInclination(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY, double averageX,
            double averageY) throws Exception {
        if (arrayX.size() != arrayY.size()) {
            throw new Exception("X and Y arrays must have same size.");
        }
        double sumX2 = 0.;
        double sumXY = 0.;
        for (int i = 0; i < arrayX.size(); i++) {
            sumX2 += Math.pow(arrayX.get(i), 2);
            sumXY += arrayX.get(i)
                    * arrayY.get(i);
        }
        double sXX = sumX2 - (arrayX.size() * Math.pow(averageX, 2));
        double sXY = sumXY - (arrayX.size() * averageX * averageY);
        return sXY / sXX;
    }
    
}
