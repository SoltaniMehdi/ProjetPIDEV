package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyDatabase {

    private static MyDatabase instance;
    private final HikariDataSource dataSource;

    private MyDatabase() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/gestion_commandes");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10); // Adjust the pool size as needed

        dataSource = new HikariDataSource(config);
    }

    public static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
