package nfracgen.statistic.linearregression;

import java.util.ArrayList;

/**
 * 
 * @author elidioxg
 */

public class LinearRegression {
    /**
     * This class handle the Linear Regression analysis.
     * The class receives the vectors X and Y on constructor and handle the
     * properties of Linear Regression analysis as two variables: 'initialValue'
     * and 'inclination'.
     */

    private double initialValue = 0.;
    private double inclination = 0.;   

    /**
     * 
     * @param arrayX
     * @param arrayY 
     */    
    public LinearRegression(ArrayList<Double> arrayX, ArrayList<Double> arrayY) {        
        try {
            this.inclination = Inclination.getInclination(arrayX, arrayY);
        } catch (Exception e) {

        }
        this.initialValue = InitialValue.getInitialValue(arrayX, arrayY, inclination);
    }

    /**
     * 
     * @param firstValue
     * @param lastValue
     * @param stepValue
     * @return 
     */
    public ArrayList<Double> linearRegressionPointsList(double firstValue,
            double lastValue, double stepValue) {
        ArrayList<Double> result = new ArrayList();
        for (double i = firstValue; i <= lastValue; i += stepValue) {
            result.add(this.initialValue + (this.inclination * i));            
        }
        return result;
    }

    /**
     * Estimate the value of Y at specific value of X.
     * @param valueAt Value of X
     * @return
     */
    public double getValueAt(double valueAt) {        
        return (this.initialValue + (this.inclination * valueAt));
    }
    
    /**
     * 
     * @return 
     */
    public double getInclination(){
        return this.inclination;
    }
    
    /**
     * 
     * @return 
     */
    public double getInitialValue(){
        return this.initialValue;
    }

}
