package blservice.admin;

import po.BookPO;
import po.Category;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by 宋益明 on 16-1-22.
 * <p>
 * 新书编号业务逻辑接口
 */
public interface NumberBLservice {

    /**
     * 对图书进行编号(条形码, 书标)
     *
     * @param book     图书持久化对象
     * @param category 图书类别
     * @return 迭代器, 指向包含条形码及书标的键值对
     * @throws RemoteException
     */
    Map<String, String> numberBook(BookPO book, Category category) throws RemoteException;
}
