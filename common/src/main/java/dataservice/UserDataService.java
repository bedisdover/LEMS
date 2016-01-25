package dataservice;

import po.UserPO;
import utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-1-24.
 *
 * 用户数据服务接口
 * 负责对用户信息的增删改查
 */
public interface UserDataService extends Remote {

    /**
     * 新增用户
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(UserPO user) throws RemoteException;

    /**
     * 删除用户
     * @param ID 用户ID
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String ID) throws RemoteException;

    /**
     * 更新用户
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS,失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage update(UserPO user) throws RemoteException;

    /**
     * 查询用户信息
     * @param ID 用户ID
     * @return 用户持久化对象
     * @throws RemoteException 远程连接异常
     */
    public UserPO find(String ID) throws RemoteException;
}
