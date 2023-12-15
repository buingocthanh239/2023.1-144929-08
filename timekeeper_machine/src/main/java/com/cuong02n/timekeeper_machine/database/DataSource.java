package com.cuong02n.timekeeper_machine.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private static final HikariConfig hikariConfig = new HikariConfig();
    private static final HikariDataSource hikariDataSource;

    static {
        hikariConfig.setJdbcUrl( "jdbc:mysql://localhost:" );
        hikariConfig.setUsername( "database_username" );
        hikariConfig.setPassword( "database_password" );
        hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
        hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}