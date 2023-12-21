package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
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
        String sql = "INSERT INTO `timekeeper`.`user` (`username`, `fullname`, `password`, `role`, `room_id`) VALUES (?,?,?,?,?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setObject(1, user.getUsername());
        st.setObject(2, user.getFullName());
        st.setObject(3, user.getPassword());
        st.setObject(4, user.getRole());
        st.setObject(5, user.getRoomId());
        st.executeUpdate();
    }

    @Override
    public void insertAction(User user, int type) throws Exception {
        String sql = "INSERT INTO `timekeeper`.`timekeeping_action` (`user_id`, `action_time`, `type`) VALUES (?,?,?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, user.getUserId());
        st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        st.setInt(3, type);
        st.executeUpdate();
    }

    @Override
    public Vector<Action> getActionByTimeStampAndUserId(Timestamp start, Timestamp end, int userId) throws Exception {
        String sql = "SELECT timekeeping_action.*,user.fullname FROM `timekeeping_action`,`user` WHERE action_time BETWEEN ? AND ? AND timekeeping_action.user_id = ? AND timekeeping_action.user_id = user.user_id ORDER BY `action_time` ASC";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setTimestamp(1, start);
        st.setTimestamp(2, end);
        st.setInt(3, userId);
        ResultSet rs = st.executeQuery();
        Vector<Action> actions = new Vector<>();
        while (rs.next()) {
            Action action = new Action();
            action.setUserId(rs.getInt("user_id"));
            action.setActionId(rs.getInt("action_id"));
            action.setActionTime(rs.getTimestamp("action_time"));
            action.setName(rs.getString("fullname"));
            actions.add(action);
        }
        System.out.println(actions.size());
        return actions;
    }

    @Override
    public Vector<Integer> getUserByRoomId(int roomId) throws Exception {
        String sql = "SELECT user.user_id FROM `user` WHERE room_id = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1,roomId);
        ResultSet rs = st.executeQuery();
        Vector<Integer> ids = new Vector<>();
        while (rs.next()) {
            ids.add(rs.getInt("user_id"));
        }
        return ids;
    }

    @Override
    public User verify(String username, String password) throws Exception {
        String sql = "SELECT * FROM `user` where username = ? AND password = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, password);
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setRole(resultSet.getInt("role"));
            user.setUsername(resultSet.getString("username"));
            user.setFullName(resultSet.getString("fullname"));
            user.setRoomId(resultSet.getInt("room_id"));
            return user;
        }
        return null;
    }

    @Override
    public Vector<TimekeepingRequest> getRequest() throws Exception {
        String sql = "SELECT timekeeping_request.*,`user`.fullname FROM timekeeping_request,`user` WHERE `user`.user_id = timekeeping_request.user_id";
        PreparedStatement st = getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        Vector<TimekeepingRequest> requests = new Vector<>();
        while (rs.next()) {
            TimekeepingRequest action = new TimekeepingRequest();
            action.setUserId(rs.getInt("user_id"));
            action.setRequestId(rs.getInt("request_id"));
            action.setRequestTime(rs.getTimestamp("request_time"));
            action.setContent(rs.getString("content"));
            action.setFullName(rs.getString("fullname"));
            requests.add(action);
        }
        System.out.println(requests.size());
        return requests;
    }

    @Override
    public void insertTimekeepingRequest(TimekeepingRequest timekeepingRequest) throws Exception {
        String sql = "INSERT INTO `timekeeping_request` (`user_id`, `request_time`, `content`) VALUES (?,?,?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, timekeepingRequest.getUserId());
        st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        st.setString(3, timekeepingRequest.getContent());
        st.executeUpdate();
    }

    @Override
    public int findRoomIdByUserId(int userId) throws Exception {
        String sql = "SELECT room.room_id FROM `room`,`user` where room.room_id = user.room_id and user.user_id = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setObject(1, userId);
        ResultSet resultSet = st.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("room_id");
        }
        return -1;
    }


    private static HikariConnector instance = null;

    public static IDBConnector getInstance() {
        if (instance == null) {
            instance = new HikariConnector();
        }
        return instance;
    }


    final HikariConfig hikariConfig = new HikariConfig();
    final HikariDataSource hikariDataSource;

    private HikariConnector() {
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
