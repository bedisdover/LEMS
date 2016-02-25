package blservice.admin;

import po.BookPO;

import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-22.
 *
 * 新书编号业务逻辑接口
 */
public interface NumberBLservice {
    public String numberBook(BookPO book) throws RemoteException;
}
