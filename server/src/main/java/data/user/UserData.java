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
 * <p>
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
            String[] values = new String[]{
                    user.getID(), user.getName(),
                    user.getRole().toString(), user.getPassword(),
                    TransferList.transfer(user.getBorrowList()),
                    TransferList.transfer(user.getRenewList())
            };

            databaseConnect.execute(sql, values);
        } catch (SQLException e) {
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }

        databaseConnect.closeConnection();
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
        String sql = "delete from user where id = ?";
        try {
            databaseConnect.execute(sql, ID);
        } catch (SQLException e) {
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }

        databaseConnect.closeConnection();
        return ResultMessage.SUCCESS;
    }

    /**
     * 借阅图书
     *
     * @param userID  用户ID
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage borrow(String userID, String barCode) throws RemoteException {
        return addBook("borrowlist", userID, barCode);
    }

    /**
     * 预约图书
     *
     * @param userID  用户ID
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage renew(String userID, String barCode) throws RemoteException {
        return addBook("renewlist", userID, barCode);
    }

    /**
     * 归还图书
     *
     * @param userID  用户ID
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage back(String userID, String barCode) throws RemoteException {
        return deleteBook("borrowlist", userID, barCode);
    }

    /**
     * 取消预约图书
     *
     * @param userID  用户ID
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage cancelRenew(String userID, String barCode) throws RemoteException {
        return deleteBook("renewlist", userID, barCode);
    }

    /**
     * 查询用户信息
     *
     * @param ID 用户ID
     * @return 用户持久化对象
     * @throws RemoteException 远程连接异常
     */
    public UserPO find(String ID) throws RemoteException {
        String sql = "select * from user where id = ?";
        UserPO user = null;

        try {
            ResultSet result = databaseConnect.getResultSet(sql, ID);
            result.next();

            String name = result.getString(2);
            UserRole role = UserRole.valueOf(result.getString(3));
            String password = result.getString(4);
            String borrowList = result.getString(5);
            String renewList = result.getString(6);

            user = new UserPO(name, ID, role);
            user.setPassword(password);
            user.setBorrowList(borrowList);
            user.setRenewList(renewList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnect.closeConnection();
        return user;
    }

    /**
     * 向借阅列表或预约列表中添加图书
     *
     * @param field   表项
     * @param userID  用户ID
     * @param barCode 条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     */
    private ResultMessage addBook(String field, String userID, String barCode) {
        // 获得原有记录
        String list = getData(field, userID);
        //添加新的记录
        list = barCode + " " + list;
        //更新记录
        return update(field, userID, list);
    }

    /**
     * 从借阅列表或预约列表中删除图书
     *
     * @param field   表项
     * @param userID  用户ID
     * @param barCode 条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     */
    private ResultMessage deleteBook(String field, String userID, String barCode) {
        StringBuffer list = new StringBuffer(getData(field, userID));

        int start = list.indexOf(barCode);
        int end = start;
        while (list.charAt(end++) != ' ');
        list.delete(start, end);

        return update(field, userID, list.toString());
    }

    /**
     * 更新数据
     *
     * @param field   表项
     * @param userID  用户ID
     * @param content 更新内容
     * @return 结果信息
     */
    private ResultMessage update(String field, String userID, String content) {
        String sql = "update user set " + field + " = ? where id = ?";

        try {
            databaseConnect.execute(sql, content, userID);
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }
        databaseConnect.closeConnection();

        return ResultMessage.SUCCESS;
    }

    /**
     * 获得数据库中指定ID的某一项数据
     *
     * @param field  表项
     * @param userID 用户ID
     * @return 对应内容
     */
    private String getData(String field, String userID) {
        String data = "";
        String sql = "select " + field + " from user where id = ?";
        try {
            ResultSet result = databaseConnect.getResultSet(sql, userID);
            result.next();
            data = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnect.closeConnection();
        return data;
    }

    public static void main(String[] args) throws RemoteException {
        UserData data = new UserData();
        UserPO user = new UserPO("李四", "mf14129110", UserRole.GRADUATE);
//        data.insert(user);
//        data.delete("mf14129110");
//        System.out.println(data.find("mf14129110"));
//        data.borrow(user.getID(), "2016012300003");
//        data.back(user.getID(), "2016012300003");
//        data.renew(user.getID(), "2016012300003");
        data.cancelRenew(user.getID(), "2016012300003");
        System.out.println("done");
        System.exit(0);
    }
}
