package nfracgen.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FractureIntensityAnalysis {
    
    private double fractureIntensity = 0.;
    private double averageSpacing = 0.;
    private ArrayList<Fracture> cumulativeDistribution;

    /**
     * This constructor defines the Fracture Intensity and Average Spacing 
     * from scanline data, also estimate the cumulative distribution of
     * the fractures. 
     * 
     * @param scanline 
     */
    public FractureIntensityAnalysis(Scanline scanline) {
        setFractureIntensity(scanline.getFracCount(),                
                scanline.getLenght());
        setAverageSpacing(scanline.getFracCount(),
                scanline.getLenght());
        estimateDistribution(generateFractures(scanline), 
                scanline.getLenght());
    }

    /**
     * Fractures number and scanline lenght must be defined for Fracture 
     * Intensity parameter.
     * The Fracture Intensity parameter is divide fractures number by the 
     * scanline lenght.
     * 
     * @param fractures_number
     * @param scanlineLength 
     */
    private void setFractureIntensity(int fractures_number,
            double scanlineLength) {
        this.fractureIntensity = fractures_number / scanlineLength;
    }

    
    /**
     * The Fracture Intensity parameter is defined on constructor.     
     * 
     * @return 
     */
    public double getFractureIntensity() {
        return this.fractureIntensity;
    }

    /**
     * Set value of Average Spacing for this Analysis.
     * The Average Spacing is the reverse of Fracture Intensity.
     * 
     * @param fractures_number
     * @param scanlineLength 
     */
    private void setAverageSpacing(int fractures_number,
            double scanlineLength) {
        this.averageSpacing = scanlineLength / fractures_number;
    }

    /**
     * Get the Average Spacing of the fractures from the scanline. 
     * The Average Spacing of the analysis is defined on constructor
     * of this class.
     * 
     * @return 
     */
    public double getAverageSpacing() {
        return this.averageSpacing;
    }
    
    /**
     * This function returns a ArrayList with the Cumulative Distribution 
     * of the fractures in the scanline defined on constructor of
     * this class.
     * 
     * @return 
     */
    public ArrayList<Fracture> getArrayDistribution(){
        return this.cumulativeDistribution;
    }

    /**
     * Use the scanline to generate the list of fractures. This list 
     * will be used to estime cumulative distribution of the fractures
     * in the scanline defined on constructor of this class.
     * 
     * @param scanline
     * @return 
     */
    private ArrayList<Fracture> generateFractures(Scanline scanline){
        ArrayList<Double> sp = scanline.getSpList();
        ArrayList<Double> ap = scanline.getApList();
        ArrayList<Fracture> array = new ArrayList();
        for(int i=0; i< scanline.getFracCount(); i++){
            Fracture frac = new Fracture(ap.get(i), sp.get(i));
            array.add(frac);
        }
        return array;
    }
    
    /**
     * Follow some steps to estimate the cumulative distribution of the 
     * fractures on scanlines. The scanline is parameter on constructor of
     * this class, and this procedure is called on construction.
     * 
     * @param array List of fractures from scanline
     * @param scanlineLenght The sum of all aperture and spacement values 
     */
    private void estimateDistribution(ArrayList<Fracture> array,
            double scanlineLenght) {        
        //list
        Collections.sort(array, new ApertureComparator());
        //sort
        for (int i = 0; i < array.size(); i++) {       
            array.get(i).setCumulativeNumber(i + 1);
        }        
        //simplify
        ArrayList<Fracture> toRemove = new ArrayList<>();
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i).getAperture() == array.get(i + 1).getAperture()) {
                toRemove.add(array.get(i));               
            }            
        }
        for(int i = 0; i < toRemove.size() ; i++){            
            array.remove(toRemove.get(i));
        }        
        //normalize
        for (int i = 0; i < array.size(); i++) {
            array.get(i).setNormalizedValue(array.get(i).getCumulativeNumber() / scanlineLenght);
        }        
        this.cumulativeDistribution = array;
    }

    /**
     * Sort the Fractures by Aperture size, from bigger to smaller.
     */
    private class ApertureComparator implements Comparator<Fracture> {
        @Override
        public int compare(Fracture o1, Fracture o2) {
            return o1.getAperture() < o2.getAperture() ? 
                    1 : o1.getAperture() == o2.getAperture() ? 0 : -1;
        }
    }
}
