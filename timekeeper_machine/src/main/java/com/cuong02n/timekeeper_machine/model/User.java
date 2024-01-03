package com.cuong02n.timekeeper_machine.model;

public class User {

    private int userId;
    private String username;
    private transient String password;
    private String fullName;
    private User(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.fullName = builder.fullName;
        this.role = builder.role;
        this.roomId = builder.roomId;
    }
    private int role; // 0: staff, 1: worker, 2: room_manager, 3:admin
    public static final int OFFICER_ROLE = 0;
    public static final int WORKER_ROLE = 1;
    public static final int ROOM_MANAGER_ROLE = 2;
    public static final int ADMIN_ROLE = 3;
    private int roomId;

    public static class Builder {
        private int userId;
        private String username;
        private transient String password;
        private String fullName;
        private int role;
        private int roomId;

        // Các phương thức setter cho các trường của User
        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public Builder roomId(int roomId){
            this.roomId=roomId;
            return this;
        }

        public Builder role(int role) {
            this.role = role;
            return this;
        }

        // Phương thức để xây dựng đối tượng User
        public User build() {
            return new User(this);
        }
    }

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
