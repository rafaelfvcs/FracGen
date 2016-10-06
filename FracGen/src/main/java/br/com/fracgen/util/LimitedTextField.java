package br.com.fracgen.util;

public class LimitedTextField {

    private String fileName = "";
    private String Separator = ",";
    private String[] headerValues;
    private boolean hasHeader = true;

    public void setFilename(String filename) {
        this.fileName = filename;
    }

    public String getFilename() {
        return this.fileName;
    }

    public void setSeparator(String sep) {
        this.Separator = sep;
    }

    public String getSeparator() {
        return this.Separator;
    }

    public void setHeaderValues(String[] strings) {
        this.headerValues = strings;
    }

    public String[] getHeaderValues() {
        return this.headerValues;
    }

    public void setHeader(boolean header) {
        this.hasHeader = header;
    }

    public boolean getHeader() {
        return this.hasHeader;
    }

}
