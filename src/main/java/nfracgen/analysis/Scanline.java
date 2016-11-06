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

import java.util.ArrayList;

/**
 *
 * @author elidioxg
 */
public class Scanline {

    private final ArrayList<Double> ap = new ArrayList();
    private final ArrayList<Double> sp = new ArrayList();
    private double lenght = Double.NaN;
    private int fracturesCount = 0;
    //distance from scanline origin:
    private final ArrayList<Double> distance = new ArrayList();

    /**
     * The constructor creates a scanline with a list of fractures.
     * The scanline data file must have the fields of Aperture and Spacement
     * representing the value measured from each fracture.
     * 
     * @param fractures 
     */
    public Scanline(ArrayList<Fracture> fractures) {
        if (fractures != null) {
            this.lenght = 0.;
            for (int i = 0; i < fractures.size(); i++) {
                ap.add(fractures.get(i).getAperture());
                sp.add(fractures.get(i).getSpacement());                
                this.lenght += fractures.get(i).getAperture() +
                        fractures.get(i).getSpacement();
                this.distance.add(this.lenght);
                this.fracturesCount += 1;
            }
        }
    }

    /**
     * Get the lenght of the scanline. The lenght of scanline is the
     * sum of all aperture and spacement values measured from the fractures.
     * 
     * @return 
     */
    public double getLenght() {
        return this.lenght;
    }

    /**
     * Get a list of all aperture values of the fractures used as parameter
     * on constructor of this class.
     * 
     * @return 
     */
    public ArrayList<Double> getApList() {
        return this.ap;
    }

    /**
     * Get a list with all spacement values from scanline.
     * @return 
     */
    public ArrayList<Double> getSpList() {
        return this.sp;
    }

    /**
     * In some cases it's usefull have a list with the X value of each fractures.
     * X beeing the first point from origin the scanline 'touches' the fracture.
     * 
     * @return 
     */
    public ArrayList<Double> getDistanceList() {
        return this.distance;
    }

    /**
     * Get the number of fractures in the scanline. The fractures list is
     * used as parameter for contruction of this class.
     * 
     * @return 
     */
    public int getFracCount() {
        return this.fracturesCount;
    }

}
