package br.com.fracgen.statistic;

public class Average {
    
    public double arithmeticAverage(ArrayList<Double> array) {
        double sum = 0.;
        for (int i = 0; i <= array.size() - 1; i++) {
            sum += array.get(i);
        }
        return sum / (array.size());
    }

    public double geometricAverage(ArrayList<Double> array) {
        double prod = 0.;
        for (int i = 0; i <= array.size() - 1; i++) {
            prod *= array.get(i);
        }
        double result = Math.pow(prod, (1.0 / array.size()));
        return result;
    }
    
}
