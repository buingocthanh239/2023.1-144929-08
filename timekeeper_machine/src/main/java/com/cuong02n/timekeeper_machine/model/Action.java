package com.cuong02n.timekeeper_machine.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Action {
    int actionId;
    int userId;
    Timestamp actionTime;



    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }
}
