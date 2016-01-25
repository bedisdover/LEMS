package dataservice;

import po.BookPO;
import utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 图书信息数据服务接口
 * 负责对图书信息的增,删,改,查
 * 图书信息的查询可以根据图书关键信息中的一项或多项关键字进行检索
 */
public interface BookDataService extends Remote {

    /**
     * 新增图书信息
     *
     * @param book 图书持久化对象
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(BookPO book) throws RemoteException;

    /**
     * 删除图书信息
     *
     * @param ISBN 图书ISBN编号
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String ISBN) throws RemoteException;

    /**
     * 修改(更新)图书信息
     *
     * @param book 图书持久化对象
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage update(BookPO book) throws RemoteException;

    /**
     * 根据ISBN编号查询图书信息
     *
     * @param ISBN 图书ISBN编号
     * @return 图书持久化对象,若查询失败,返回null
     * @throws RemoteException 远程连接异常
     */
    public BookPO find(String ISBN) throws RemoteException;

    /**
     * 根据图书基本信息中的一个或多个关键字查询图书信息
     *
     * @param key 关键字,数量任意
     * @return 图书持久化对象,若查询失败,返回null
     * @throws RemoteException 远程连接异常
     */
    public BookPO find(String... key) throws RemoteException;
}
