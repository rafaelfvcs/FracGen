package br.com.fracgen.statistic;

import java.util.ArrayList;

/*
 *
 */
public abstract class Stat {

	public static double min(ArrayList<Double> array) {
		double minV = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) < minV) {
				minV = array.get(i);
			}
		}
		return minV;
	}

	public static double max(ArrayList<Double> array) {
		double maxV = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > maxV) {
				maxV = array.get(i);
			}
		}
		return maxV;
	}

	public static double sum(ArrayList<Double> array) {
		double sumv = 0;
		for (int i = 0; i < array.size(); i++) {
			sumv += array.get(i);
		}
		return sumv;
	}

	public static double calculateMean(Number[] values) {
		return calculateMean(values, true);
	}

    public static double calculateMean(Number[] values,
            boolean includeNullAndNaN) {

        double sum = 0.0;
        double current;
        int counter = 0;
        for (int i = 0; i < values.length; i++) {
            // treat nulls the same as NaNs
            if (values[i] != null) {
                current = values[i].doubleValue();
            }
            else {
                current = Double.NaN;
            }
            // calculate the sum and count
            if (includeNullAndNaN || !Double.isNaN(current)) {
                sum = sum + current;
                counter++;
            }
        }
        double result = (sum / counter);
        return result;
    }

	/*
	 *
	 */
    public static double getStdDev(Number[] data) {

    	double avg = calculateMean(data);
        double sum = 0.0;

        for (int counter = 0; counter < data.length; counter++) {
            double diff = data[counter].doubleValue() - avg;
            sum = sum + diff * diff;
        }
        return Math.sqrt(sum / (data.length - 1));
    }

    /*
     *
     */
    public static double[] getLinearFit(Number[] xData, Number[] yData) {

        if (xData.length != yData.length) {
            throw new IllegalArgumentException(
                "Statistics.getLinearFit(): array lengths must be equal.");
        }

        double[] result = new double[2];
        // slope
        result[1] = getSlope(xData, yData);
        // intercept
        result[0] = calculateMean(yData) - result[1] * calculateMean(xData);

        return result;

    }

    /*
     *
     */

    public static double getSlope(Number[] xData, Number[] yData) {

    	if (xData.length != yData.length) {
            throw new IllegalArgumentException("Array lengths must be equal.");
        }

        // ********* stat function for linear slope ********
        // y = a + bx
        // a = ybar - b * xbar
        //     sum(x * y) - (sum (x) * sum(y)) / n
        // b = ------------------------------------
        //     sum (x^2) - (sum(x)^2 / n
        // *************************************************

        // sum of x, x^2, x * y, y
        double sx = 0.0, sxx = 0.0, sxy = 0.0, sy = 0.0;
        int counter;
        for (counter = 0; counter < xData.length; counter++) {
            sx = sx + xData[counter].doubleValue();
            sxx = sxx + Math.pow(xData[counter].doubleValue(), 2);
            sxy = sxy + yData[counter].doubleValue()
                      * xData[counter].doubleValue();
            sy = sy + yData[counter].doubleValue();
        }
        return (sxy - (sx * sy) / counter) / (sxx - (sx * sx) / counter);

    }

    /*
     *
     */
    public static double getCorrelation(Number[] data1, Number[] data2) {

    	if (data1.length != data2.length) {
            throw new IllegalArgumentException(
                "'data1' and 'data2' arrays must have same length."
            );
        }
        int n = data1.length;
        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumY2 = 0.0;
        double sumXY = 0.0;
        for (int i = 0; i < n; i++) {
            double x = 0.0;
            if (data1[i] != null) {
                x = data1[i].doubleValue();
            }
            double y = 0.0;
            if (data2[i] != null) {
                y = data2[i].doubleValue();
            }
            sumX = sumX + x;
            sumY = sumY + y;
            sumXY = sumXY + (x * y);
            sumX2 = sumX2 + (x * x);
            sumY2 = sumY2 + (y * y);
        }
        return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX)
                * (n * sumY2 - sumY * sumY), 0.5);
    }



}
