/*
 * Copyright (C) 2016 elidioxg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nfracgen.model;

import javafx.scene.image.WritableImage;
import nfracgen.analysis.Scanline;

/**
 *
 * @author elidioxg
 */
public class AnalysisFile  extends DatasetModel {
    
    /**
     * The AnalysisFile works with a Scanline class. The Scanline is represented
     * as a list of fractures and their properties. 
     * This class extends the DatasetModel class, that is a handler of a
     * specific dataset file, with all their properties.
     */
    private Scanline scanline = new Scanline(null);        
    
    /**
     * Save the Power Law Graph as WritableImage
     * Can be exported to PNG image 
     */
    private WritableImage powerLaw = null;
    
    /**
     * Descriptive Statistics for columns 
     * Considering a Scanline file with two columns: Ap, Sp
     */
    private StatisticsModel apS, spS;
    
    /**
     * Creates a null scanline.
     */
    public AnalysisFile(){
        
    }
    
    /**
     * Set the image of Power Law
     * @param image 
     */
    public void setPLGraph(WritableImage image){
        this.powerLaw = image;
    }
    
    /**
     * Get the Power Law graph as WritableImage
     * @return 
     */
    public WritableImage getPLGraph(){
        return this.powerLaw;
    }
    
    /**
     * Set the scanline for this class
     * @param scanline 
     */
    public void setScanLine(Scanline scanline){
        this.scanline = scanline;      
        apS = new StatisticsModel(scanline.getApList());
        spS = new StatisticsModel(scanline.getSpList());
    }
    
    public Scanline getScanLine(){
        return this.scanline;
    }
    
    /**
     * Get the descriptive statistics for Ap data
     * @return 
     */
    public StatisticsModel getApStatistics(){
        return this.apS;
    }
    
    /**
     * Get the descriptive statistics for Sp data
     * @return 
     */
    public StatisticsModel getSpStatistics(){
        return this.spS;
    }
    
}
