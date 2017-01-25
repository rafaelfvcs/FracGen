package nfracgen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ExternalDatabase {

    private Connection connection = null;
    private final String dbName = "//localhost:3306/nfraggen";
    private final String table = "validation";
    private final String fieldUser = "user";
    private final String fieldPass = "password";

    public ExternalDatabase() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean connect() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql:" + dbName, fieldUser, fieldPass);
        } catch (SQLException e) {
        }
        if (connection != null) {
            return true;
        }
        return false;
    }

    public boolean validateLogin(String user, String pass) {
        try {
            Statement st = connection.createStatement();
            ResultSet set = st.executeQuery("SELECT "
                    + fieldUser + ","
                    + fieldPass + " FROM " + table + " WHERE "
                    + fieldUser + "='" + user + "'");            
            if (set.getString(fieldPass).equals(encrypt(pass))) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    private String encrypt(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(string.getBytes());
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            
        }
        return "";
    }

}
