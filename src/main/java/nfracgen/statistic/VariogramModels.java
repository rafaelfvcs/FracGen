package nfracgen.statistic;

import nfracgen.model.Matrix;

public class VariogramModels {

    /**
     *
     * @param c The sill
     * @param a The range
     * @param h The steps on calculation
     * @return
     * @throws java.lang.Exception
     */
    public static Matrix spherical(double c, double a, double h) throws Exception {
        if (h > a) {
            throw new Exception("'h' must be smaller than 'a'");
        }
        int nSteps = (int) (a / h) + 5;
        Matrix result = new Matrix(2, nSteps + 1);
        double aux = 0.1;
        int index = 0;
        while (aux <= a) {
            double value = c * (((3 * aux) / 2 * a) - ((Math.pow(h, 3) / (2 * Math.pow(a, 3)))));
            result.set(0, index, aux);
            result.set(1, index, value);
            aux += h;
            index++;
        }
        for (int i = index; i < index + 4; i++) {
            result.set(0, i, aux);
            result.set(1, i, c);
            aux += h;
        }
        return result;
    }

    /**
     * Curve fitting for Exponential Model on Variogram
     *
     * @param c The sill
     * @param a The range
     * @param h Steps of calculation
     * @return
     * @throws Exception
     */
    public static Matrix exponential(double c, double a, double h) throws Exception {
        if (h > a) {
            throw new Exception("'h' must be smaller than 'a'");
        }
        int nSteps = (int) (a / h) + 5;
        Matrix result = new Matrix(2, nSteps + 1);
        double aux = 0.1;
        int index = 0;
        while (index <= nSteps) {
            double value = c * (1 - Math.exp(-aux / a));
            result.set(0, index, aux);
            result.set(1, index, value);
            aux += h;
            index++;
        }
        return result;

    }

    public static Matrix gaussian(double c, double a, double h) throws Exception {
        if (h > a) {
            throw new Exception("'h' must be smaller than 'a'");
        }
        int nSteps = (int) (a / h) + 5;
        Matrix result = new Matrix(2, nSteps + 1);
        double aux = 0.1;
        int index = 0;
        while (index <= nSteps) {
            double value = c * (1 - Math.exp(-Math.pow(aux, 2) / Math.pow(a, 2)));
            result.set(1, index, aux);
            result.set(1, index, value);
            aux += h;
            index++;
        }
        return result;

    }

}
