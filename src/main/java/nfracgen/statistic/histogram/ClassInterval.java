package nfracgen.statistic.histogram;

public class ClassInterval {
    private int index;
    private double inferior;
    private double superior;
    private double centralTeor;
    private int obsFrequency =0;
    private String label= "";
    
    /**
     * Defines the interval classes of the histogram.
     * 
     * @param index Index of the Class Interval
     * @param inferior Inferior limit of Class Interval 
     * @param superior Superior limit of Class Interval
     */
    public ClassInterval(int index, double inferior, double superior){
        this.index=index;
        this.inferior=inferior;
        this.superior=superior;
        this.centralTeor = (superior - inferior);
        this.label= String.format("%.1f", inferior)+" - "+
                String.format("%.1f", superior);
    }
    
    /**
     * Increment the frequency count of the class interval
     */
    public void addFrequency(){
        this.obsFrequency+=1;
    }
    
    public int getObsFrequency(){
        return this.obsFrequency;
    }
    
    public void changeFrequency(int num){
        this.obsFrequency=num;
    }
    public double getInferiorValue(){
        return this.inferior;
    }
    public double getSuperiorValue(){
        return this.superior;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    
}
