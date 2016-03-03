package br.com.fracgen.statistic;

import java.util.ArrayList;

public class LinearRegression {
    public double getInitialValue(double averageX, double averageY,
            double inclination) {
        return averageY - inclination * averageX;
    }

    public double getInitialValue(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY, double inclination) {
        Average average = new Average();
        double avgY = average.arithmeticAverage(arrayY);
        double avgX = average.arithmeticAverage(arrayX);
        return avgY - inclination * avgX;
    }

    public double getInclination(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY) throws Exception {
        if (arrayX.size() != arrayY.size()) {
            throw new Exception("X and Y arrays must have same size.");
        }
        double sumX2 = 0.;
        double sumXY = 0.;
        for (int i = 0; i < arrayX.size(); i++) {
            sumX2 += Math.pow(Double.valueOf(arrayX.get(i).toString()), 2);
            sumXY += Double.valueOf(arrayX.get(i).toString())
                    * Double.valueOf(arrayY.get(i).toString());
        }
        Average avg = new Average();
        double avgX = avg.arithmeticAverage(arrayX);
        double avgY = avg.arithmeticAverage(arrayY);
        double sXX = sumX2 - (arrayX.size() * Math.pow(avgX, 2));
        double sXY = sumXY - (arrayX.size() * avgX * avgY);
        return sXY / sXX;
    }

    public double getInclination(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY, double averageX,
            double averageY) throws Exception {
        if (arrayX.size() != arrayY.size()) {
            throw new Exception("X and Y arrays must have same size.");
        }
        double sumX2 = 0.;
        double sumXY = 0.;
        for (int i = 0; i < arrayX.size(); i++) {
            sumX2 += Math.pow(Double.valueOf(arrayX.get(i).toString()), 2);
            sumXY += Double.valueOf(arrayX.get(i).toString())
                    * Double.valueOf(arrayY.get(i).toString());
        }
        double sXX = sumX2 - (arrayX.size() * Math.pow(averageX, 2));
        double sXY = sumXY - (arrayX.size() * averageX * averageY);
        return sXY / sXX;
    }

    public ArrayList<Double> linearRegression(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY) throws Exception {
        double inc = getInclination(arrayX, arrayY);
        double initialValue = getInitialValue(arrayX, arrayY, inc);
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < arrayX.size(); i++) {
            result.add(initialValue + (inc * Double.valueOf(
                    arrayX.get(i).toString())));
        }
        return result;
    }

    //TODO: values must be reversed
    public ArrayList<Double> linearRegression(ArrayList<Double> arrayX,
            ArrayList<Double> arrayY, double minValue,
            double maxValue, double stepValue) throws Exception {
        double inc = getInclination(arrayX, arrayY);
        double initialValue = getInitialValue(arrayX, arrayY, inc);
        double steps = (maxValue - minValue) / stepValue;
        ArrayList<Double> result = new ArrayList<>();
        int j = (int) steps;
        for (int i = 0; i < j; i++) {
            result.add(initialValue + (inc * minValue));
            minValue += steps;
        }
        return result;
    }

    public double getValueAt(double initialValue, double inclination,
            double valueAt) {
        return (initialValue + (inclination * valueAt));
    }
}
