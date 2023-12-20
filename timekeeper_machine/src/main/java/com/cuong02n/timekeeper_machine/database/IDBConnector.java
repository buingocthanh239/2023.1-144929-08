package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.User;

import java.sql.Timestamp;
import java.util.Vector;

public interface IDBConnector {
    User getUserById(int id) throws Exception;

    void insertUser(User user)throws Exception;

    void insertAction(User user,int type) throws Exception;
    Vector<Action> getActionByUserId(int id) throws Exception;

    Vector<Action> getActionByTimeStampAndUserId(Timestamp start, Timestamp end, int userId) throws Exception;
    Vector<Action> getTimeDifferent(Timestamp start, Timestamp end) throws Exception;

    User verify(String username, String password) throws Exception;
}
