package bl.admin;

import bl.DataBaseFactoryImpl;
import blservice.admin.NumberBLservice;
import dataservice.BookDataService;
import dataservice.NumberDataService;
import po.BookPO;
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

    public Iterator<Map.Entry<String, String>> numberBook(BookPO book, Category category) throws RemoteException {
        String barCode = createBarCode();
        String label = createLabel(category);

        book.setBarCode(barCode);
        book.setLabel(label);

        storeBookInfo(book);

        Map<String, String> result = new HashMap<String, String>(2);
        result.put("barCode", barCode);
        result.put("label", label);
        Set<Map.Entry<String, String>> set = result.entrySet();

        return set.iterator();
    }

    /**
     * 创建条形码
     *
     * @return 条形码
     */
    private String createBarCode() throws RemoteException {
        NumberDataService numberDataService = DataBaseFactoryImpl.getInstance().getNumberDataService();
        return numberDataService.createBarCode(new Date());
    }

    /**
     * 创建书标
     *
     * @param category 图书类别
     * @return 书标
     */
    private String createLabel(Category category) throws RemoteException {
        NumberDataService numberDataService = DataBaseFactoryImpl.getInstance().getNumberDataService();
        return numberDataService.createLabel(category);
    }

    /**
     * 存储新书信息
     *
     * @param book 图书持久化对象
     */
    private void storeBookInfo(BookPO book) throws RemoteException {
        BookDataService bookDataService = DataBaseFactoryImpl.getInstance().getBookDataService();
        bookDataService.insert(book);
    }
}
