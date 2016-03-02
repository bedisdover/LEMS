package bl.admin;

import bl.Connect;
import blservice.admin.NumberBLservice;
import dataservice.BookDataService;
import dataservice.NumberDataService;
import po.BookPO;
import po.BookType;
import po.Category;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by 宋益明 on 16-2-25.
 * <p>
 * 新书编号任务
 * 负责为每本新书编号,同一本书的不同副本编号相同
 */
public class Number implements NumberBLservice {

    public Map<String, String> numberBook(BookPO book, Category category) throws RemoteException {
        String barCode = createBarCode();
        String label = createLabel(category);

        book.setBarCode(barCode);
        book.setLabel(label);

        System.out.println(barCode);
        System.out.println(label);

        storeBookInfo(book);

        Map<String, String> result = new HashMap<String, String>(2);
        result.put("barCode", barCode);
        result.put("label", label);

        return result;
    }

    /**
     * 创建条形码
     *
     * @return 条形码
     */
    private String createBarCode() throws RemoteException {
        NumberDataService numberDataService = Connect.getInstance().getNumberDataService();
        return numberDataService.createBarCode();
    }

    /**
     * 创建书标
     *
     * @param category 图书类别
     * @return 书标
     */
    private String createLabel(Category category) throws RemoteException {
        NumberDataService numberDataService = Connect.getInstance().getNumberDataService();
        return numberDataService.createLabel(category);
    }

    /**
     * 存储新书信息
     *
     * @param book 图书持久化对象
     */
    private void storeBookInfo(BookPO book) throws RemoteException {
        BookDataService bookDataService = Connect.getInstance().getBookDataService();
        bookDataService.insert(book);
    }

    public static void main(String[] args) throws RemoteException {
        Number number = new Number();
        List<String> authors = new ArrayList<String>();
        authors.add("丁二玉");
        authors.add("刘钦");
        BookPO book = new BookPO("软件工程与计算(卷二)", authors, "机械工业出版社",
                                "9787111407508", BookType.ORDINARY);

        number.numberBook(book, Category.T);

        System.out.println("done");
    }
}
