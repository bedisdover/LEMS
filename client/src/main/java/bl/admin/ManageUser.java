package bl.admin;

import bl.Connect;
import blservice.admin.ManageUserBLservice;
import po.UserPO;
import utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by 宋益明 on 16-3-2.
 * <p>
 * 用户管理类
 * 支持新建、删除用户以及重置用户密码功能
 */
public class ManageUser implements ManageUserBLservice {

    /**
     * 新建用户
     *
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage create(UserPO user) throws RemoteException {
        return Connect.getInstance().getUserDataService().insert(user);
    }

    /**
     * 删除用户
     *
     * @param userID 用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String userID) throws RemoteException {
        return Connect.getInstance().getUserDataService().delete(userID);
    }

    /**
     * 重置用户密码
     *
     * @param userID 用户ID
     * @return 重置后密码
     * @throws RemoteException 远程连接异常
     */
    public String resetPass(String userID) throws RemoteException {
        return Connect.getInstance().getUserDataService().resetPass(userID);
    }
}
