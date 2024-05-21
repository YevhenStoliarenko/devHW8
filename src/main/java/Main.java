import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
         Connection connection = Database.getInstance().getConnection();



//        DatabaseInit.initDB();
        //DatabaseInit.insertDB();

    }
}
