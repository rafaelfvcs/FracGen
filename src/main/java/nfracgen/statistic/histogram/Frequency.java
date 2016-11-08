package nfracgen.statistic.histogram;

import java.util.ArrayList;

public class Frequency {
    /**
     * Define the value of class interval following Sturges expression.
     * The value is equal to (Superior limit of class interval)-(Inferior limit of class interval)
     * 
     * @param amplitude
     * @param numberOfSamples
     * @return Value of class interval
     */
    public static double sturgesExpression(Double amplitude, int numberOfSamples){
        return (amplitude/(1+(3.32*Math.log(numberOfSamples))));        
    }
    
    /**
     * Gives a list with properties of class intervals 
     * @param minValue
     * @param maxValue
     * @param classInterval
     * @return 
     */
    public static ArrayList<ClassInterval> classIntervals(double minValue, 
            double maxValue, double classInterval){
        ArrayList<ClassInterval> array = new ArrayList<>();        
        int n = (int)((maxValue-minValue)/classInterval);
        for(int i = 0; i<n ; i++){
            double min = minValue+(i*classInterval);
            ClassInterval ci = new ClassInterval(i, min, min+classInterval);
            array.add(ci);
        }
        return array;
    }
    
    /**
     * This function is used for custom histograms, which the user defines
     * the number of classes and the cutoff values.
     * 
     * @param minValue
     * @param maxValue
     * @param classesNumber
     * @return 
     */
    public static ArrayList<ClassInterval> classIntervals(double minValue, 
            double maxValue, int classesNumber){
        ArrayList<ClassInterval> array = new ArrayList<>();        
        double classInterval = ((maxValue-minValue)/classesNumber);        
        for(int i = 0; i<classesNumber ; i++){
            double min = minValue+(i*classInterval);
            ClassInterval ci = new ClassInterval(i, min, min+classInterval);
            array.add(ci);
        }
        return array;
    }
    
    /**
     * Count observed frequency of a interval class.
     * @param values
     * @param classes 
     */
    public static void countObsFrequency(
            ArrayList<Double> values, ArrayList<ClassInterval> classes){        
        for(int i=0; i<values.size(); i++){
            for(int j = 0; j<classes.size(); j++){
                if(values.get(i)>classes.get(j).getInferiorValue()){
                    if(values.get(i)<classes.get(j).getSuperiorValue()){
                        classes.get(j).addFrequency();
                    }
                }
            }            
        }
    }
    
}
