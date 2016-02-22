package po;

import com.sun.org.apache.xpath.internal.SourceTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by 宋益明 on 16-1-26.
 *
 * 用户持久化对象测试类
 * 测试 借阅列表和预约列表的get方法
 * 由于二者采用的数据结构几乎相同,故仅测试借阅列表
 *
 * 测试成功
 */
public class UserPOTest extends TestCase {

    private UserPO user;
    private Stack<String> bookList;

    public void setUp() {
        bookList = new Stack<String>();
        bookList.push("10004");
        bookList.push("10003");
        bookList.push("10002");
        bookList.push("10001");

        user = new UserPO("test", "1234", UserRole.UNDERGRADUATE);

        for (String book : bookList) {
            user.setBorrowList(book);
        }
    }

    @Test
    public void testGetBorrowList() throws Exception {
        Iterator<String> iterator = user.getBorrowList();

        while (iterator.hasNext()) {
            assertEquals(iterator.next(), bookList.pop());
        }
    }
}