package data;

import utility.ConnectConfig;

import java.sql.*;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 数据库连接类
 * 负责与数据库的交互
 *
 * TODO 未完成
 */
public final class DatabaseConnect {
//        public static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://" + ConnectConfig.IP + ":" + 3306 + "/LEMS";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "123456";

    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet result;

    private Statement stmt;

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            Class.forName(DB_DRIVER).newInstance();
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                    "user=root&password=123456");

        stmt = connection.createStatement();
        pstm = connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return pstm;
    }

    public ResultSet getResultSet(String sql) throws SQLException {
        this.getPreparedStatement(sql);
        result = pstm.getResultSet();

        return result;
    }

    public void closeConnection() {
        try {
            if (result != null) {
                result.close();
            }

            pstm.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            //TODO CLASSPATH
            Class.forName(DB_DRIVER);
            System.out.println("done");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
