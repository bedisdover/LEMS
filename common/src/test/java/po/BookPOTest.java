package po;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 图书持久化对象测试类
 * 测试getAuthor()方法
 * TODO JUnit4
 * 测试成功
 */
public class BookPOTest extends TestCase {
    private static BookPO book;
    private static List<String> authors;

    @Before
    public void setUp() {
        authors = new ArrayList<String>();
        authors.add("丁二玉");
        authors.add("刘钦");

        book = new BookPO("软工二", authors, "机械工业出版社", "9787111407508", BookType.ORDINARY, "");
    }

    @Test
    public void testGetAuthor() {
        assertEquals(book.getAuthor(), authors);
    }
}