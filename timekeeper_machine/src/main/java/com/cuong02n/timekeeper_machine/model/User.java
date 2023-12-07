package com.cuong02n.timekeeper_machine.model;

public abstract class User {
    public enum Role {
        STAFF(0),
        WORKER(1),
        ROOM_MANAGER(2),
        ADMIN(3);
        final int id;

        Role(int id) {
            this.id = id;
        }
    }

    private int userId;
    private String username;
    private transient String password;
    private String fullName;
    private Role role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
