package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.User;

public interface IDBConnector {
    User getUserById(String id);

}
