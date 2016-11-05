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
package  nfracgen.analysis;

/**
 *
 * @author elidioxg
 */
public class Fracture {
    final private double aperture;
    final private double spacement;
    
    /**
     * The constructor must define the Aperture and Spacement of the fracture.
     * The Aperture and Spacement can't be changed after this constructor
     * by considering fractures are imutable on scanline study.
     * 
     * @param ap
     * @param sp 
     */
    public Fracture(double ap, double sp){
        this.aperture=ap;
        this.spacement=sp;
    }
    
    /**     
     * This procedure returns the final variable 'aperture'. This variable 
     * represents the lenght between the first moment the scanline reach 
     * the fracture and the last.
     * 
     * @return 
     */
    public double getAperture(){
        return this.aperture;
    }
    
    /**
     * Return the final variable 'spacement'. This variable represents the 
     * distance of the fracture from the fracture before of it on scanline.
     * @return 
     */
    public double getSpacement(){
        return this.spacement;
    }
}
