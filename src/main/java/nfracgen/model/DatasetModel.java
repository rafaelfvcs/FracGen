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

import java.util.ArrayList;

/**
 *
 * @author elidioxg
 */
public class DatasetModel {

    private String datasetName = "";
    private String datasetFilename = "";
    private String separator = "\t";
    private boolean hasHeader = true;
    private ArrayList<String> headerStrings = new ArrayList<>();
    private int columnsCount = 0;
    private int rowsCount = 0;
    private int spColumn = -1;
    private int apColumn = -1;

    public DatasetModel() {
        
    }

    public DatasetModel(String datasetName, String filename, String separator,
            boolean hasHeader, ArrayList<String> headerStrings, int columns, int rows) {
        this.datasetName = datasetName;
        this.datasetFilename = filename;
        this.separator = separator;
        this.hasHeader = hasHeader;
        this.headerStrings = headerStrings;
        this.columnsCount = columns;
        this.rowsCount = rows;

    }

    /**
     * Set the index of aperture column
     *
     * @param column
     */
    public void setApColumn(int column) {
        this.apColumn = column;
    }

    /**
     * Get the index of aperture column
     *
     * @return
     */
    public int getApColumn() {
        return this.apColumn;
    }

    /**
     * Set the spacement index column of the dataset
     *
     * @param column
     */
    public void setSpColumn(int column) {
        this.spColumn = column;
    }

    public int getSpColumn() {
        return this.spColumn;
    }

    public void setDatasetName(String name) {
        this.datasetName = name;
    }

    public String getDatasetName() {
        return this.datasetName;
    }

    public void setFilename(String filename) {
        this.datasetFilename = filename;
    }

    public String getFileName() {
        return this.datasetFilename;
    }
    
    public void setSep(String sep){
        this.separator = sep;
    }
    
    public String getSep(){
        return this.separator;
    }

    public void setHeader(boolean header) {
        this.hasHeader = header;
    }

    public boolean getHeader() {
        return this.hasHeader;
    }

    public void setHeaderStrings(ArrayList<String> strHeader) {
        this.headerStrings = strHeader;
    }

    public ArrayList<String> getHeaderArray() {
        return this.headerStrings;
    }

    public String getHeaderArray(int colIndex) {
        return this.headerStrings.get(colIndex);
    }

    public void setColumnsCount(int number) {
        this.columnsCount = number;
    }

    public int getColumnsCount() {
        return this.columnsCount;
    }

    public void setRowsCount(int count) {
        this.rowsCount = count;
    }

    public int getRowsCount() {
        return this.rowsCount;
    }

}
