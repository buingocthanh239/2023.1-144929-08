package com.cuong02n.timekeeper_machine.model;

public class User {

    private int userId;
    private String username;
    private transient String password;
    private String fullName;

    private int role; // 0: staff, 1: worker, 2: room_manager, 3:admin

    private int roomId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return getUserId() + " " + getUsername() + " " + getFullName() + " " + getPassword() + " " + getRole()+" "+getRoomId();
    }
}
