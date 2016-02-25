package bl.admin;

import bl.DataBaseFactoryImpl;
import blservice.admin.NumberBLservice;
import dataservice.BookDataService;
import dataservice.DataBaseFactory;
import po.BookPO;

import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-2-25.
 *
 * 新书编号任务
 * 负责为每本新书编号,同一本书的不同副本编号相同
 */
public class Number implements NumberBLservice {
    public String numberBook(BookPO book) throws RemoteException {
        storeBookInfo(book);
        return null;
    }

    /**
     * 存储新书信息
     * @param book 图书持久化对象
     */
    private void storeBookInfo(BookPO book) throws RemoteException {
        BookDataService bookDataService = DataBaseFactoryImpl.getInstance().getBookDataService();
        bookDataService.insert(book);
    }
}
