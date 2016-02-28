package data.book;

import data.book.BookData;
import org.junit.Before;
import org.junit.Test;
import po.BookPO;
import po.BookType;
import utility.ResultMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 宋益明 on 16-1-23.
 *
 */
public class BookDataTest {

    private BookData data;
    private BookPO book;

    @Before
    public void setUp() throws Exception {
        List<String> authors = new ArrayList<String>();
        authors.add("丁二玉");
        authors.add("刘钦");

        book = new BookPO("软工二", authors, "机械工业出版社", "9787111407508", BookType.ORDINARY, "");

        data = new BookData();
    }

    @Test
    public void testInsert() throws Exception {
        assertEquals(data.insert(book), ResultMessage.SUCCESS);
        assertEquals(data.insert(null), ResultMessage.FAILURE);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testFind1() throws Exception {

    }
}