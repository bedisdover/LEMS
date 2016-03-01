package dataservice;

import po.BookPO;
import utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-1-23.
 * <p>
 * 图书信息数据服务接口
 * 负责对图书信息的增,删,改,查
 * 图书信息的查询可以根据图书关键信息中的一项或多项关键字进行检索
 */
public interface BookDataService extends Remote {

    /**
     * 新增图书信息
     *
     * @param book 图书持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(BookPO book) throws RemoteException;

    /**
     * 删除图书信息
     *
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String barCode) throws RemoteException;

    /**
     * 更新借阅者
     *
     * @param barCode 条形码
     * @param userID  借阅用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage borrow(String barCode, String userID) throws RemoteException;

    /**
     * 更新预约者
     *
     * @param barCode 条形码
     * @param userID  预约用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage renew(String barCode, String userID) throws RemoteException;

    /**
     * 根据ISBN编号查询图书信息
     * 因同一本图书可能有多个不同副本,且副本ISBN编号相同
     * 故查询结果返回所有副本信息
     *
     * @param ISBN 图书ISBN编号
     * @return 图书所有副本持久化对象列表的迭代器, 若查询失败, 返回null
     * @throws RemoteException 远程连接异常
     */
    public Iterator<BookPO> find(String ISBN) throws RemoteException;

    /**
     * 根据图书基本信息中的一个或多个关键字查询图书信息
     *
     * @param key 关键字,数量任意
     * @return 图书持久化对象列表, 若查询失败, 返回null
     * @throws RemoteException 远程连接异常
     */
    public List<BookPO> find(String... key) throws RemoteException;
}
