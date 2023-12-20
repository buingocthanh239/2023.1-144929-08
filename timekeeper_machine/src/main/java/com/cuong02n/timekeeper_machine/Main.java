package com.cuong02n.timekeeper_machine;

import com.cuong02n.timekeeper_machine.database.HikariConnector;
import com.cuong02n.timekeeper_machine.database.IDBConnector;
import com.cuong02n.timekeeper_machine.model.User;

public class Main {

    public static void main(String[] args) throws Exception{
        IDBConnector connector = new HikariConnector();
        User user = new User();
        user.setUsername("2");
        user.setFullName("111");
        user.setPassword("111");
        user.setRole(0);
        user.setRoomId(0);
        connector.insertUser(user);
    }
}
