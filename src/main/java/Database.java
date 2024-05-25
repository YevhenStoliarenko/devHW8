import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String BD_URL = "jdbc:mysql://localhost:3306/person";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "password";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static Database database;
    String migration = "db/migration";


    private Database() {
        config.setJdbcUrl(BD_URL);
        config.setUsername(BD_USER);
        config.setPassword(BD_PASSWORD);
        ds = new HikariDataSource(config);

        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations(migration)
                .load();
        flyway.migrate();

    }



    public static Database getInstance() throws SQLException {
        if(database == null){
            database = new Database();
        }
        return database;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
