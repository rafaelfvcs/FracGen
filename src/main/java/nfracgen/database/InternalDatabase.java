package nfracgen.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nfracgen.model.ScanlineAnalysis;
import nfracgen.model.ScanlineAnalysisFile;
import nfracgen.stage.MainStage;
import org.sqlite.SQLiteJDBCLoader;

public class InternalDatabase {

    private Connection connection = null;
    private String database;

    private final String tableStatistics = "table_stats";
    private final String tableAnalysis = "table_analysis";

    private final String fieldName = "field_name";
    private final String fieldUser = "field_user";
    private final String fieldRef = "field_ref";
    private final String fieldCom = "field_comments";
    private final String fieldOut = "field_output";
    private final String fieldDataset = "field_dataset";
    private final String fieldSep = "field_separator";
    private final String fieldHasHeader = "field_hasheader";
    private final String fieldHeader = "field_header_strings";

    public InternalDatabase(String database) {
        this.database = database;
        try {
            Class.forName("org.sqlite.JDBC");
            final File tmp = new File(System.getProperty("java.io.tmpdir"));
            if (!tmp.exists() || !tmp.isDirectory() || !tmp.canRead() || !tmp.canWrite()) {
                throw new Exception("error with tmpDir");
            }
            SQLiteJDBCLoader.initialize();
            this.connection = getConection();
            DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
            System.out.println("Driver name: " + dm.getDriverName());
            System.out.println("Driver version: " + dm.getDriverVersion());
            System.out.println("Product name: " + dm.getDatabaseProductName());
            System.out.println("Product version: " + dm.getDatabaseProductVersion());
            connection.close();

        } catch (Exception e) {

        }
    }

    public boolean isConnected() {
        try {
            return !this.connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    public Connection getConection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null;
        }
        return this.connection;
    }

    public Connection getConection(String database) {

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
            this.database = database;
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null;
        }
        return this.connection;
    }

    public ArrayList<String> selectAll(String table, String field) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Statement st = getConection().createStatement();
            ResultSet rs = st.executeQuery("SELECT " + field + " FROM " + table);
            while (rs.next()) {
                result.add(rs.getString(field));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //todo: saveNewAnalysis()
    public void saveAnalysis(ScanlineAnalysis analysis) throws Exception {
        /**
         * Create table if not exists Insert values from Analysis Object to
         * Database
         */
        try {
            Statement st = getConection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS " + tableAnalysis + " ("
                    + fieldName + " TEXT,"
                    + fieldUser + " TEXT, "
                    + fieldCom + " TEXT, "
                    + fieldDataset + " TEXT,"
                    + fieldSep + " TEXT,"
                    + fieldHeader + " TEXT)");

            st.execute("INSERT INTO " + tableAnalysis + " ("
                    + fieldName + ","
                    + fieldUser + ","
                    + fieldCom + ","
                    + fieldDataset + ","
                    + fieldSep
                    + ") VALUES ('"
                    + analysis.getName() + "','"
                    + analysis.getUser() + "','"
                    + analysis.getComments() + "','"
                    + analysis.getAnalysisFile().getFileName() + "','"
                    + analysis.getAnalysisFile().getSep() + "')"
                    //ArrayOperation.arrayListToArray()
            );
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadAnalysis(String name) throws SQLException, Exception {
        Statement st = getConection().createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM " + tableAnalysis
                + " WHERE " + fieldName + " = '" + name + "'");
        ScanlineAnalysis analysis = new ScanlineAnalysis();
        analysis.setName(result.getString(fieldName));
        analysis.setUser(result.getString(fieldUser));
        analysis.setComments(result.getString(fieldCom));
        ScanlineAnalysisFile file = new ScanlineAnalysisFile();
        file.setFilename(fieldDataset);
        //file.setHeader();
        file.setSep(fieldSep);
        //file.setHeaderStrings();        
        analysis.setAnalysisFile(file);
        st.close();        
        MainStage.setAnalysis(analysis);
        MainStage.refreshStats();        
    }

}
