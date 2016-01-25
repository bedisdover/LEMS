package data;

import dataservice.UserDataService;
import po.UserPO;
import utility.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 宋益明 on 16-1-24.
 *
 * 用户信息管理类
 * 用户信息(ID, 姓名, 身份, 密码, 借阅列表, 预约列表)
 * 负责对用户信息的增删改查
 */
public class UserData extends UnicastRemoteObject implements UserDataService {

    private Connect connect;

    public UserData() throws RemoteException {
        connect = new Connect();
    }

    /**
     * 新增用户
     *
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(UserPO user) throws RemoteException {
        return null;
    }

    /**
     * 删除用户
     *
     * @param ID 用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String ID) throws RemoteException {
        return null;
    }

    /**
     * 更新用户
     *
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage update(UserPO user) throws RemoteException {
        return null;
    }

    /**
     * 查询用户信息
     *
     * @param ID 用户ID
     * @return 用户持久化对象
     * @throws RemoteException 远程连接异常
     */
    public UserPO find(String ID) throws RemoteException {
        String sql = "select * from user where id = " + ID;
        UserPO user = null;

        try {
            ResultSet result = connect.getResultSet(sql);
            result.next();

            String id = result.getString(1);
            String name = result.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
