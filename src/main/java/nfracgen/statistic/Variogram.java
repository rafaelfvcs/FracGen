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
package nfracgen.statistic;

import java.util.ArrayList;
import nfracgen.model.Matrix;

/**
 *
 * @author elidioxg
 */
public class Variogram {
    /*TODO: implement tolerance angles and cutoff values
     * 
     */
    
    /**
     *
     * @param xData
     * @param yData
     * @param contentData
     * @param initialDistance
     * @param stepSize
     * @param maxDistance
     * @return
     * @throws Exception
     */
    public static Matrix variogram2D(ArrayList<Double> xData, ArrayList<Double> yData, 
            ArrayList<Double> contentData,double initialDistance, double stepSize, double maxDistance)
            throws Exception {
        Matrix result;
        if (xData.size() != yData.size()) {
            throw new Exception("Values of X and Y must have same size.");
        } else {
            double aux = ((maxDistance - initialDistance) / stepSize);
            int linesNumber = (int) aux;
            if (linesNumber <= 0) {
                throw new Exception("Invalid Values");
            } else {
                result = new Matrix(3, linesNumber);
                for (int currentLine = 0; currentLine < linesNumber; currentLine++) {
                    double value = 0.;
                    int pairsNumber = 0;
                    for (int i = 0; i < xData.size(); i++) {
                        for (int j = i + 1; j < xData.size(); j++) {
                            double distanceX
                                    = xData.get(i).doubleValue() - xData.get(j).doubleValue();
                            double distanceY
                                    = yData.get(i).doubleValue() - yData.get(j).doubleValue();
                            double distance
                                    = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
                            if (distance >= initialDistance
                                    && distance <= initialDistance + stepSize) {

                                value += Math.pow(contentData.get(i).doubleValue()
                                        - contentData.get(j).doubleValue(), 2);
                                pairsNumber++;
                            }
                        }
                    }
                    result.set(0, currentLine, initialDistance + stepSize);
                    initialDistance += stepSize;
                    value /= 2 * pairsNumber;
                    result.set(1, currentLine, value);
                    result.set(2, currentLine, pairsNumber);
                }
            }
        }
        return result;
    }
    
    /**
     *
     * @param matrix
     * @param initialDistance
     * @param stepSize
     * @param maxDistance
     * @param columnX
     * @param columnY
     * @param columnContent
     * @return
     * @throws Exception
     */
    public static Matrix variogram2D(Matrix matrix, double initialDistance,
            double stepSize, double maxDistance, int columnX, int columnY,
            int columnContent) throws Exception {
        double aux = ((maxDistance - initialDistance) / stepSize);
        int linesNumber = (int) aux;
        Matrix result = new Matrix(3, linesNumber);
        if (matrix.getColumnsCount() != matrix.getLinesCount()) {
            throw new Exception("Must be a square matrix");
        } else {

            for (int currentLine = 0; currentLine < linesNumber; currentLine++) {
                double value = 0.;
                int pairsNumber = 0;
                for (int i = 0; i < matrix.getLinesCount(); i++) {
                    for (int j = i + 1; j < matrix.getColumnsCount(); j++) {
                        double distanceX
                                = matrix.get(columnX, i).doubleValue()
                                - matrix.get(columnX, j).doubleValue();
                        double distanceY
                                = matrix.get(columnY, i).doubleValue()
                                - matrix.get(columnY, j).doubleValue();
                        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
                        if (distance >= initialDistance
                                && distance <= initialDistance + stepSize) {
                            value += Math.pow(matrix.get(columnContent, i).doubleValue()
                                    - matrix.get(columnContent, j).doubleValue(),
                                    2);
                            pairsNumber++;
                        }
                    }
                }
                result.set(0, currentLine, initialDistance + stepSize);
                initialDistance += stepSize;
                value /= 2 * pairsNumber;
                result.set(1, currentLine, value);
                result.set(2, currentLine, pairsNumber);
            }
        }
        return result;
    }
}
