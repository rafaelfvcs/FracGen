package nfracgen.statistic.linearregression;
import java.util.ArrayList;
import nfracgen.statistic.Stat;

public class InitialValue {
    
    /**
     * 
     * @param averageX
     * @param averageY
     * @param inclination
     * @return 
     */
    public static double getInitialValue(double averageX, double averageY,
            double inclination) {
        return averageY - inclination * averageX;
    }

    /**
     * 
     * @param arrayX
     * @param arrayY
     * @param inclination
     * @return 
     */
    public static double getInitialValue(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY, double inclination) {
        double avgY = Stat.mean(arrayY);
        double avgX = Stat.mean(arrayX);
        return avgY - inclination * avgX;
    }
    
}
