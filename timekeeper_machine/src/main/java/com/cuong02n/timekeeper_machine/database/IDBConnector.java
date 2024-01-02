package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import com.cuong02n.timekeeper_machine.model.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Vector;

public interface IDBConnector {
    User getUserById(int id) throws Exception;

    void insertUser(User user)throws Exception;

    void insertAction(User user,int type) throws Exception;

    Vector<Action> getActionByTimeStampAndUserId(Timestamp start, Timestamp end, int userId) throws Exception;

    User verify(String username, String password) throws Exception;
    Vector<TimekeepingRequest> getRequest() throws Exception;

    void insertTimekeepingRequest(TimekeepingRequest timekeepingRequest) throws Exception;

    int findRoomIdByUserId(int userId) throws Exception;

    void setStatusByRequestId(int requestId) throws Exception;

    Vector<Integer> getListUserId() throws Exception;
    Vector<Integer> getListUserIdByRoomId(int roomId) throws Exception;

    void deleteAllActionByDayAndUserId(LocalDate date,int userId) throws Exception;

    void insertAction(Timestamp actionTime,int userId) throws Exception;
}
