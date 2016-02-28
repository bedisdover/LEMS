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

/**
 * Created by 宋益明 on 16-2-25.
 */
public final class DataBaseFactoryImpl implements DataBaseFactory {

    private static DataBaseFactoryImpl instance = new DataBaseFactoryImpl();

    private DataBaseFactoryImpl() {
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
        BookDataService dataService = null;

        try {
            DataBaseFactory dataBaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.IP);
            dataService = dataBaseFactory.getBookDataService();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            throw e;
        }

        return dataService;
    }

    /**
     * 获得用户数据接口
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public UserDataService getUserDataService() throws RemoteException {
        UserDataService dataService = null;

        try {
            DataBaseFactory dataBaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.IP);
            dataService = dataBaseFactory.getUserDataService();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            throw e;
        }

        return dataService;
    }

    /**
     * 获得图书编号数据服务
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public NumberDataService getNumberDataService() throws RemoteException {
        NumberDataService dataService = null;

        try {
            DataBaseFactory dataBaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.IP);
            dataService = dataBaseFactory.getNumberDataService();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            throw e;
        }

        return dataService;
    }
}
