package data;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 数据库连接测试类
 *
 * TODO 测试不成功
 */
public class DatabaseConnectTest extends TestCase {

    private DatabaseConnect databaseConnect;

    private String sql;
    private String sql2;
    private String sql3;


    @Before
    public void setUp() throws Exception {
        databaseConnect = new DatabaseConnect();

        sql = "select * from book;";
        sql2 = "select * from user;";
        sql3 = "select * from Book;";
    }

    @Test
    public void testGetPreparedStatement() throws Exception {
        assertNotNull(databaseConnect.execute(sql));
        assertNotNull(databaseConnect.execute(sql2));
        assertNull(databaseConnect.execute(sql3));
    }

    @Test
    public void testGetResultSet() throws Exception {
        //TODO 因数据库中尚无数据,留待检验
//        assertNotNull(databaseConnect.getResultSet(sql));
//        assertNotNull(databaseConnect.getResultSet(sql2));
//        assertNull(databaseConnect.getResultSet(sql3));
    }
}