package bl.admin;

import bl.DataBaseFactoryImpl;
import blservice.admin.ManageUserBLservice;
import dataservice.DataBaseFactory;
import dataservice.UserDataService;
import po.UserPO;
import po.UserRole;
import utility.ConnectConfig;
import utility.ResultMessage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
        return DataBaseFactoryImpl.getInstance().getUserDataService().insert(user);
    }

    /**
     * 删除用户
     *
     * @param userID 用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String userID) throws RemoteException {
        return getDataService().delete(userID);
    }

    /**
     * 重置用户密码
     *
     * @param userID 用户ID
     * @return 重置后密码
     * @throws RemoteException 远程连接异常
     */
    public String resetPass(String userID) throws RemoteException {
        return getDataService().resetPass(userID);
    }

    private UserDataService getDataService() throws RemoteException {

        UserDataService userDataService = null;

        try {
            //获得数据库的引用
            DataBaseFactory databaseFactory = (DataBaseFactory) Naming.lookup(ConnectConfig.URL);

            userDataService = databaseFactory.getUserDataService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            throw e;
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        return userDataService;
    }

    public static void main(String[] args) throws RemoteException {
        UserPO user = new UserPO("王五", "314110", UserRole.TEACHER);
//        System.out.println(new ManageUser().create(user));
        System.out.println(new ManageUser().delete("mf14129110"));
    }
}
