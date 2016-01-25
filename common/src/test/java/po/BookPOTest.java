package po;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 宋益明 on 16-1-23.
 *
 * 图书持久化对象测试类
 * 测试getAuthor()方法
 * 测试成功
 */
public class BookPOTest extends TestCase {

    private BookPO book;
    private List<String> authors;


    public void setUp() throws Exception {
        authors = new ArrayList<String>();
        authors.add("丁二玉");
        authors.add("刘钦");

        //TODO 不完整的图书信息
        book = new BookPO("软工二", authors, "", "", BookType.ORDINARY);
    }

    public void testGetAuthor() throws Exception {
        assertEquals(book.getAuthor(), authors);
    }
}