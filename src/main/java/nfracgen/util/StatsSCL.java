package nfracgen.util;

import nfracgen.analysis.Scanline;
import java.util.ArrayList;

import nfracgen.statistic.Stat;

public class StatsSCL {
    
    public StatsSCL(Scanline scanline) {
        //TODO: colocar info sobre a power law
        numFrat = scanline.getFracCount();

        meanAp = RoundUtil.round(Stat.calculateMean(ArrayOperation.arrayListToArray(
                scanline.getApList())), 3);
        minAp = RoundUtil.round(Stat.min(scanline.getApList()), 3);
        maxAp = RoundUtil.round(Stat.max(scanline.getApList()), 3);
        stdAp = RoundUtil.round(Stat.getStdDev(ArrayOperation.arrayListToArray(
                scanline.getApList())), 3);
        cvAp = RoundUtil.round(stdAp / meanAp, 3);

        meanSp = RoundUtil.round(Stat.calculateMean(ArrayOperation.arrayListToArray(
                scanline.getSpList())), 3);
        minSp = RoundUtil.round(Stat.min(scanline.getSpList()), 3);
        maxSp = RoundUtil.round(Stat.max(scanline.getSpList()), 3);
        stdSp = RoundUtil.round(Stat.getStdDev(ArrayOperation.arrayListToArray(
                scanline.getSpList())), 3);
        cvSp = RoundUtil.round(stdSp / meanSp, 3);
    }

    public StatsSCL(ArrayList<Double> ap, ArrayList<Double> sp) {
        //TODO: colocar info sobre a power law
        numFrat = ap.size();

        meanAp = RoundUtil.round(Stat.calculateMean(ArrayOperation.arrayListToArray(ap)), 3);
        minAp = RoundUtil.round(Stat.min(ap), 3);
        maxAp = RoundUtil.round(Stat.max(ap), 3);
        stdAp = RoundUtil.round(Stat.getStdDev(ArrayOperation.arrayListToArray(ap)), 3);
        cvAp = RoundUtil.round(stdAp / meanAp, 3);

        meanSp = RoundUtil.round(Stat.calculateMean(ArrayOperation.arrayListToArray(ap)), 3);
        minSp = RoundUtil.round(Stat.min(ap), 3);
        maxSp = RoundUtil.round(Stat.max(ap), 3);
        stdSp = RoundUtil.round(Stat.getStdDev(ArrayOperation.arrayListToArray(ap)), 3);
        cvSp = RoundUtil.round(stdSp / meanSp, 3);
    }

    private int numFrat;
    private double meanAp;
    private double maxAp;
    private double minAp;
    private double stdAp;
    private double cvAp;

    private double meanSp;
    private double maxSp;
    private double minSp;
    private double stdSp;
    private double cvSp;

    public int getNumFrat() {
        return numFrat;
    }

    public double getMeanAp() {
        return meanAp;
    }

    public double getMaxAp() {
        return maxAp;
    }

    public double getMinAp() {
        return minAp;
    }

    public double getStdAp() {
        return stdAp;
    }

    public double getCvAp() {
        return cvAp;
    }

    public double getMeanSp() {
        return meanSp;
    }

    public double getMaxSp() {
        return maxSp;
    }

    public double getMinSp() {
        return minSp;
    }

    public double getStdSp() {
        return stdSp;
    }

    public double getCvSp() {
        return cvSp;
    }

}
