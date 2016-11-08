package nfracgen.statistic;

import java.util.ArrayList;

public class Variance {

    /**
     * Calculate the Variance of a vector
     *
     * @param vector
     * @return
     */
    public static double variance(ArrayList<Double> vector) {
        double result = 0.;
        double averageValue = Stat.mean(vector);
        for (int i = 0; i < vector.size(); i++) {
            result += Math.pow(vector.get(i) - averageValue, 2);
        }
        return result / vector.size();
    }

    /**
     * Calculate the Variance of a vector with already known average value
     *     
     * @param vector
     * @param average
     * @return
     */
    public static double variance(ArrayList<Double> vector, double average) {
        double result = 0.;
        for (int i = 0; i < vector.size(); i++) {
            result += Math.pow(vector.get(i) - average, 2);
        }
        return result / vector.size();
    }

}
