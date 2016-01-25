package data;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 数据库连接测试类
 *
 * TODO 测试失败
 */
public class ConnectTest extends TestCase {

    private Connect connect;

    private String sql;

    @Before
    public void setUp() throws Exception {
        connect = new Connect();

        sql = "select * from Book";
    }

    @Test
    public void testGetPreparedStatement() throws Exception {
        assertNotEquals(connect.getPreparedStatement(sql), null);
        assertEquals(connect.getPreparedStatement(null), null);
    }

    @Test
    public void testGetResultSet() throws Exception {
        assertNotEquals(connect.getResultSet(sql), null);
        assertEquals(connect.getResultSet(null), null);
    }
}