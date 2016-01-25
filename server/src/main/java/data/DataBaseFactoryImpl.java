package data;

import dataservice.BookDataService;
import dataservice.DataBaseFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by 宋益明 on 16-1-23.
 *
 *
 */
public class DataBaseFactoryImpl extends UnicastRemoteObject implements DataBaseFactory {

    public DataBaseFactoryImpl() throws RemoteException {
        super();
    }

    /**
     * 获得图书数据接口
     *
     * @return 图书数据对象
     * @throws RemoteException 远程连接异常
     */
    public BookDataService getBookDataService() throws RemoteException {
        return new BookData();
    }
}
