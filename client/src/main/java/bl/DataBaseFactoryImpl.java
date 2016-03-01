package bl;

import dataservice.BookDataService;
import dataservice.DataBaseFactory;
import dataservice.NumberDataService;
import dataservice.UserDataService;
import utility.ConnectConfig;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by 宋益明 on 16-2-25.
 */
public final class DataBaseFactoryImpl extends UnicastRemoteObject implements DataBaseFactory {

    private static DataBaseFactoryImpl instance;
    private DataBaseFactory dataBaseFactory;

    static {
        try {
            instance = new DataBaseFactoryImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private DataBaseFactoryImpl() throws RemoteException {
        super();
    }

    public static DataBaseFactoryImpl getInstance() {
        return instance;
    }

    /**
     * 获得图书处理数据服务
     *
     * @return
     * @throws RemoteException
     */
    public BookDataService getBookDataService() throws RemoteException {
        connect();
        return dataBaseFactory.getBookDataService();
    }

    /**
     * 获得用户数据接口
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public UserDataService getUserDataService() throws RemoteException {
        connect();
        return dataBaseFactory.getUserDataService();
    }

    /**
     * 获得图书编号数据服务
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public NumberDataService getNumberDataService() throws RemoteException {
        connect();
        return dataBaseFactory.getNumberDataService();
    }

    private void connect() {
        try {
            dataBaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.URL);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
