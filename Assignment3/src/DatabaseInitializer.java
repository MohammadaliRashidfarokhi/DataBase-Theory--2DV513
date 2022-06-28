import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.ibatis.jdbc.ScriptRunner;

public class DatabaseInitializer {
    public static void run(String[] args) throws ClassNotFoundException,
            SQLException {

        String aSQLScriptFilePath = "src/meta.sql";


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "root", "root");
        Statement stmt = null;

        try {

            ScriptRunner sr = new ScriptRunner(con);


            Reader reader = new BufferedReader(
                    new FileReader(aSQLScriptFilePath));


            sr.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath
                    + " The error is " + e.getMessage());
        }
    }
    }

