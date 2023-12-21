package com.cuong02n.timekeeper_machine.database;

public class DatabaseManager {
    static private final IDBConnector hikariConnector = HikariConnector.getInstance();

    public static IDBConnector getDBNow() {
        return hikariConnector;
    }
}
