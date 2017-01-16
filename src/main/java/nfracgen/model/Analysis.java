package nfracgen.model;

public class Analysis {
    
    private AnalysisFile file;
    
    //private FieldImage images;
    
    private String name = "nFracGen Analysis";
    private String user = "nFracGen User";
    private String references = "";
    private String comments = "";
    private String outputFilename = "";
    
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
