package data;

import utility.ConnectConfig;

import java.sql.*;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 数据库连接类
 * 负责与数据库的交互
 */
public final class DatabaseConnect {
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://" + ConnectConfig.IP + ":" + 3306 + "/LEMS";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "123456";

    private Connection connection;
    private PreparedStatement pstmt;

    public PreparedStatement execute(String sql, String... values) throws SQLException {
        loadPreparedStatement(sql, values);

        pstmt.executeUpdate();
        return pstmt;
    }

    public ResultSet getResultSet(String sql, String... values) throws SQLException {
        loadPreparedStatement(sql, values);

        return pstmt.executeQuery();
    }

    public void closeConnection() {
        try {
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载驱动程序
     *
     * @param sql sql语句
     */
    private void loadPreparedStatement(String sql, String... values) throws SQLException {
        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            pstmt = connection.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
