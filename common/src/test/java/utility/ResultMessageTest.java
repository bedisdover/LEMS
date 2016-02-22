package utility;

import junit.framework.TestCase;

/**
 * Created by 宋益明 on 16-1-26.
 *
 * 返回信息测试类
 *
 */
public class ResultMessageTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testUnion() throws Exception {
        assertEquals(ResultMessage.SUCCESS.union(ResultMessage.SUCCESS), ResultMessage.SUCCESS);
        assertEquals(ResultMessage.SUCCESS.union(ResultMessage.FAILURE), ResultMessage.FAILURE);
        assertEquals(ResultMessage.FAILURE.union(ResultMessage.SUCCESS), ResultMessage.FAILURE);
        assertEquals(ResultMessage.FAILURE.union(ResultMessage.FAILURE), ResultMessage.FAILURE);
    }
}