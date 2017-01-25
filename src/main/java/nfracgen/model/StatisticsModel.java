package nfracgen.model;

import java.util.ArrayList;
import nfracgen.statistic.Mode;
import nfracgen.statistic.Stat;
import nfracgen.statistic.StdDeviation;
import nfracgen.statistic.Variance;
import nfracgen.statistic.VariationCoefficient;

public class StatisticsModel {

    private final double min;
    private final double max;
    private final double avg;
    private final double mode;
    private final double stdDev;
    private final double variance;
    private final double geoAvg;
    private final double variation;
    private final double count;

    public StatisticsModel(ArrayList<Double> vector) {
        this.min = Stat.min(vector);
        this.max = Stat.max(vector);
        this.avg = Stat.mean(vector);
        this.mode = Mode.getMode(vector);
        this.stdDev = StdDeviation.stdDeviation(vector);
        this.variance = Variance.variance(vector);
        this.geoAvg = Stat.geometricAverage(vector);
        this.variation = VariationCoefficient.variationCoefficient(vector);
        this.count = vector.size();
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getAvg() {
        return this.avg;
    }

    public double getMode() {
        return this.mode;
    }

    public double getStdDev() {
        return this.stdDev;
    }

    public double getVariance() {
        return this.variance;
    }

    public double getGeoAvg() {
        return this.geoAvg;
    }
    
    public double getVariation(){
        return this.variation;
    }

    public double getCount() {
        return this.count;
    }

}
