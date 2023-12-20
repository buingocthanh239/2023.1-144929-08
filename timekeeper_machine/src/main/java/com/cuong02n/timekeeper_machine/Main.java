package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;

import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) throws Exception {
        IDBConnector connector = new HikariConnector();
    }
}
