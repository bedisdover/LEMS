package data.user;

import data.DatabaseConnect;
import dataservice.UserDataService;
import po.UserPO;
import po.UserRole;
import utility.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
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

    private DatabaseConnect databaseConnect;

    public UserData() throws RemoteException {
        databaseConnect = new DatabaseConnect();
    }

    /**
     * 新增用户
     *
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(UserPO user) throws RemoteException {
        String sql = "insert into user values(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstm = databaseConnect.getPreparedStatement(sql);
            pstm.setString(1, user.getID());
            pstm.setString(2, user.getName());
            pstm.setString(3, user.getRole().toString());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, TransferList.transfer(user.getBorrowList()));
            pstm.setString(6, TransferList.transfer(user.getRenewList()));
        } catch (SQLException e) {
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除用户
     *
     * @param ID 用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String ID) throws RemoteException {
        String sql = "delete from user where id = " + ID;
        try {
            PreparedStatement pstm = databaseConnect.getPreparedStatement(sql);
        } catch (SQLException e) {
            return ResultMessage.FAILURE;
        }

        return ResultMessage.SUCCESS;
    }

    /**
     * 更新用户
     *
     * @param user 用户持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage update(UserPO user) throws RemoteException {
        return delete(user.getID()).union(insert(user));
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
            ResultSet result = databaseConnect.getResultSet(sql);
            result.next();

            String id = result.getString(1);
            String name = result.getString(2);
            UserRole role = UserRole.valueOf(result.getString(3));
            String password = result.getString(4);
            String borrowList = result.getString(5);
            String renewList = result.getString(6);

            user = new UserPO(name, id, role);

            user.setPassword(password);
            user.setBorrowList(borrowList);
            user.setRenewList(renewList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
