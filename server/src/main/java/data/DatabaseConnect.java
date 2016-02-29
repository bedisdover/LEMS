package data;

import utility.ConnectConfig;

import java.sql.*;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 数据库连接类
 * 负责与数据库的交互
 * <p>
 * TODO 未完成
 */
public final class DatabaseConnect {
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://" + ConnectConfig.IP + ":" + 3306 + "/LEMS";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "123456";

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet result;

    public PreparedStatement getPreparedStatement(String sql, String... values) throws SQLException {
        loadDriver(sql);

        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }

        return pstmt;
    }

    public ResultSet getResultSet(String sql, String... values) throws SQLException {
        pstmt = getPreparedStatement(sql);

        for (int i = 0; i < values.length; i++) {
            pstmt.setString(i + 1, values[i]);
        }

        return pstmt.executeQuery();
    }

    public void closeConnection() {
        try {
            if (result != null) {
                result.close();
            }

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
    private void loadDriver(String sql) {
        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            pstmt = connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
