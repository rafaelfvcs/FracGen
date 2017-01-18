package nfracgen.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import nfracgen.model.Analysis;
import org.sqlite.SQLiteJDBCLoader;

public class DatabaseHelper {

    private Connection connection = null;
    private String database;

    private final String tableStatistics = "table_stats";
    private final String tableAnalysis = "table_analysis";

    private final String fieldName = "field_name";
    private final String user = "field_user";
    private final String references = "field_ref";
    private final String comments = "field_comments";
    private final String outputFilename = "field_output";

    public DatabaseHelper(String database) {        
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
    public void saveAnalysis(Analysis analysis) throws Exception {
        /**
         * Create table if not exists Insert values from Analysis Object to
         * Database
         */
        try {
            Statement st = getConection().createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS " + tableAnalysis + " (" + fieldName + " TEXT)");
            //st.execute("CREATE TABLE IF NOT EXISTS "+tableStatistics+"("+fieldName+" TEXT)");

            //st.execute("INSERT INTO "+tableAnalysis+" ("+fieldName+") VALUES ('"+analysis.getName()+"')");            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
