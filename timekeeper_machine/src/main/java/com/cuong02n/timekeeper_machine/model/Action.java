package com.cuong02n.timekeeper_machine.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Action {
    int actionId;
    int userId;
    Timestamp actionTime;
    int type;
    // 0: checkin
    // 1: checkout


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
