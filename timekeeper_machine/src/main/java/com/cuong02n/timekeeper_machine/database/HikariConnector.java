package com.cuong02n.timekeeper_machine.database;

import com.cuong02n.timekeeper_machine.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.chart.PieChart;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
