package data;

import java.sql.*;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 数据库连接类
 * 负责与数据库的交互
 */
public final class Connect {
    public static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DB_URL = "jdbc:mysql://" + Config.IP + ":" + Config.PORT + "/LEMS";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "123456";

    private Connection connection;
    private PreparedStatement pstm;
    private ResultSet result;

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            pstm = connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
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
}
