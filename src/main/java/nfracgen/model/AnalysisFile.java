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
    
    private String name = "nFracGen Analysis";
    private String user = "nFracGen User";
    private String references = "";
    private String comments = "";
    private String outputFilename = "";
    
    /**
     * Creates a null scanline.
     */
    public AnalysisFile(){
        
    }
    
    /**
     * Set the scanline for this class
     * @param scanline 
     */
    public void setScanLine(Scanline scanline){
        this.scanline = scanline;        
    }
    
    public Scanline getScanLine(){
        return this.scanline;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public void setRef(String ref) {
        this.references = ref;
    }

    public String getRef() {
        return this.references;
    }

    public void setOutputFilename(String filename) {
        this.outputFilename = filename;
    }

    public String getOutputFilename() {
        return this.outputFilename;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return this.comments;
    }
    
}
