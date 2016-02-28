package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 数据库工厂接口
 * 负责构建数据服务
 */
public interface DataBaseFactory extends Remote {

    /**
     * 获得图书数据接口
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public BookDataService getBookDataService() throws RemoteException;

    /**
     * 获得用户数据接口
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public UserDataService getUserDataService() throws RemoteException;

    /**
     * 获得图书编号数据服务
     *
     * @return
     * @throws RemoteException 远程连接异常
     */
    public NumberDataService getNumberDataService() throws RemoteException;
}
