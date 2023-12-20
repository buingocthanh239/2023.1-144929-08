package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Vector;

@SuppressWarnings("all")
public class HikariConnector implements IDBConnector {
    @Override
    public User getUserById(int id) throws Exception {
        String sql = "SELECT * FROM `user` where user_id = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setObject(1, id);
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setUserId(id);
            user.setRole(resultSet.getInt("role"));
            user.setUsername(resultSet.getString("username"));
            user.setFullName(resultSet.getString("fullname"));
            user.setRoomId(resultSet.getInt("room_id"));
            return user;
        }
        return null;
    }

    @Override
    public void insertUser(User user) throws Exception {
        String sql = """
                 INSERT INTO `timekeeper`.`user` (`username`, `fullname`, `password`, `role`, `room_id`)
                 VALUES (?,?,?,?,?)
                 """;
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setObject(1,user.getUsername());
        st.setObject(2,user.getFullName());
        st.setObject(3,user.getPassword());
        st.setObject(4,user.getRole());
        st.setObject(5,user.getRoomId());
        st.executeUpdate();
    }

    @Override
    public void insertAction(User user,int type) throws Exception {
        String sql = """
                INSERT INTO `timekeeper`.`timekeeping_action` (`user_id`, `action_time`, `type`) 
                VALUES (?,?,?);
                """;
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1,user.getUserId());
        st.setTimestamp(2,new Timestamp(System.currentTimeMillis()));
        st.setInt(3,type);
        st.executeUpdate();
    }

    @Override
    public Vector<Action> getActionByUserId(int id) throws Exception {
        Vector<Action> actions = new Vector<>();
        String sql = """
                SELECT * FROM `timekeeper`.`timekeeping_action` ORDER BY `action_time`;
                """;
        PreparedStatement st = getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Action action = new Action();
            action.setActionId(rs.getInt("action_id"));
            action.setUserId(rs.getInt("user_id"));
            action.setActionTime(rs.getTimestamp("action_time"));
            action.setType(rs.getType());
            actions.add(action);
        }
        return actions;
    }

    @Override
    public Vector<Action> getTimeDifferentAndUserId(Timestamp start, Timestamp end,int userId) throws Exception {
        String sql = """
                SELECT * FROM `timekeeping_action` WHERE action_time BETWEEN ? AND ? AND user_id = ?
                """;
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setTimestamp(1,start);
        st.setTimestamp(2,end);
        st.setInt(3,userId);
        ResultSet rs = st.executeQuery();
        Vector<Action> actions = new Vector<>();
        while (rs.next()){
            Action action = new Action();
            action.setUserId(rs.getInt("user_id"));
            action.setActionId(rs.getInt("action_id"));
            action.setActionTime(rs.getTimestamp("action_time"));
            action.setType(rs.getInt("type"));
            actions.add(action);
        }
        return actions;
    }
    @Override
    public Vector<Action> getTimeDifferent(Timestamp start, Timestamp end) throws Exception {
        String sql = """
                SELECT * FROM `timekeeping_action` WHERE action_time BETWEEN ? AND ?
                """;
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setTimestamp(1,start);
        st.setTimestamp(2,end);
        ResultSet rs = st.executeQuery();
        Vector<Action> actions = new Vector<>();
        while (rs.next()){
            Action action = new Action();
            action.setUserId(rs.getInt("user_id"));
            action.setActionId(rs.getInt("action_id"));
            action.setActionTime(rs.getTimestamp("action_time"));
            action.setType(rs.getInt("type"));
            actions.add(action);
        }
        return actions;
    }


    final HikariConfig hikariConfig = new HikariConfig();
    final HikariDataSource hikariDataSource;

    public HikariConnector() {
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/timekeeper");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("ahasuwemeia");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() throws Exception {
        return hikariDataSource.getConnection();
    }
}
