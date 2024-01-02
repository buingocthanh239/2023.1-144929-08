package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.Action;
import com.cuong02n.timekeeper_machine.model.Room;
import com.cuong02n.timekeeper_machine.model.TimekeepingRequest;
import com.cuong02n.timekeeper_machine.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Vector;

@SuppressWarnings("all")
public class HikariConnector implements IDBConnector {
    private static HikariConnector instance = null;
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

    static IDBConnector getInstance() {
        if (instance == null) {
            instance = new HikariConnector();
        }
        return instance;
    }

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
        return actions;
    }

    @Override
    public Vector<Integer> getListUserIdByRoomId(int roomId) throws Exception {
        String sql = "SELECT user.user_id FROM `user` WHERE room_id = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, roomId);
        ResultSet rs = st.executeQuery();
        Vector<Integer> ids = new Vector<>();
        while (rs.next()) {
            ids.add(rs.getInt("user_id"));
        }
        return ids;
    }

    @Override
    public Vector<Room> getListRoom() throws Exception {
        String sql = "SELECT * FROM `room`";
        PreparedStatement st = getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        Vector<Room> rooms = new Vector<>();
        while (rs.next()) {
            rooms.add(new Room(rs.getInt("room_id"), rs.getString("description")));
        }
        return rooms;
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
            action.setStatus(rs.getString("status"));
            action.setDescription(rs.getString("description"));
            requests.add(action);
        }
        System.out.println(requests.size());
        return requests;
    }

    @Override
    public void insertTimekeepingRequest(TimekeepingRequest timekeepingRequest) throws Exception {
        String sql = "INSERT INTO `timekeeping_request` (`user_id`, `request_time`, `content`,`description`) VALUES (?,?,?,?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, timekeepingRequest.getUserId());
        st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        st.setString(3, timekeepingRequest.getContent());
        st.setString(4, timekeepingRequest.getDescription());
        st.executeUpdate();
    }

    @Override
    public void setStatusByRequestId(int requestId) throws Exception {
        String sql = "UPDATE timekeeping_request SET `status`=N'Đã xử lý' WHERE request_id = ?";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setObject(1, requestId);
        st.executeUpdate();
    }

    @Override
    public Vector<Integer> getListUserId() throws Exception {
        String sql = "SELECT `user`.user_id FROM `user`";
        PreparedStatement st = getConnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        Vector<Integer> ids = new Vector<>();
        while (rs.next()) {
            ids.add(rs.getInt("user_id"));
        }
        return ids;
    }

    @Override
    public void deleteAllActionByDayAndUserId(LocalDate date, int userId) throws Exception {
        Timestamp start = Timestamp.valueOf(date.atStartOfDay());
        Timestamp end = Timestamp.valueOf(date.atStartOfDay().plusDays(1));
        String sql = "DELETE FROM timekeeping_action WHERE user_id = ? AND (action_time BETWEEN ? AND ?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, userId);
        st.setTimestamp(2, start);
        st.setTimestamp(3, end);
        st.execute();
    }

    @Override
    public void insertAction(Timestamp actionTime, int userId) throws Exception {
        String sql = "INSERT INTO `timekeeper`.`timekeeping_action` (`user_id`, `action_time`) VALUES (?,?)";
        PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, userId);
        st.setTimestamp(2, actionTime);
        st.execute();
    }

    public Connection getConnection() throws Exception {
        return hikariDataSource.getConnection();
    }

}
