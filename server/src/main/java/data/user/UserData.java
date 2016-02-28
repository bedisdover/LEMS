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
            PreparedStatement pstmt = databaseConnect.getPreparedStatement(sql);

            pstmt.setString(1, user.getID());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getRole().toString());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, TransferList.transfer(user.getBorrowList()));
            pstmt.setString(6, TransferList.transfer(user.getRenewList()));

            pstmt.executeUpdate();
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
        String sql = "delete from user where id = " + ID;
        try {
            PreparedStatement pstmt = databaseConnect.getPreparedStatement(sql);

            pstmt.executeUpdate();
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
        // 在原有记录前添加新的记录
        String sql_concat = "select concat(?, borrowlist) from user where id = ?";
        String borrowList = concat(sql_concat, userID, barCode);
        //更新记录
        return update("borrowlist", userID, borrowList);
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
        // 在原有记录前添加新的记录
        String sql_concat = "select concat(?, renewlist) from user where id = ?";
        String renewList = concat(sql_concat, userID, barCode);
        //更新记录
        return update("renewlist", userID, renewList);
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
        StringBuffer borrowList = new StringBuffer(getData("borrowlist", userID));

        int start = borrowList.indexOf(barCode);
        int end = start;

        while (borrowList.charAt(end++) != ' ');

        borrowList.delete(start, end);

        return update("borrowlist", userID, borrowList.toString());
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
        StringBuffer renewList = new StringBuffer(getData("renewlist", userID));

        int start = renewList.indexOf(barCode);
        int end = start;

        while (renewList.charAt(end++) != ' ');

        renewList.delete(start, end);

        return update("renewlist", userID, renewList.toString());
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

        databaseConnect.closeConnection();
        return user;
    }

    /**
     * 更新数据
     *
     * @param field 表项
     * @param userID  用户ID
     * @param content 更新内容
     * @return 结果信息
     */
    private ResultMessage update(String field, String userID, String content) {
        String sql = "update user set " + field + " = ? where id = ?";

        try {
            PreparedStatement pstmt = databaseConnect.getPreparedStatement(sql);

            pstmt.setString(1, content);
            pstmt.setString(2, userID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }
        databaseConnect.closeConnection();

        return ResultMessage.SUCCESS;
    }

    /**
     * 在用户现有记录(借阅, 预约)前添加新的记录
     * 不同记录之间以空格分隔
     *
     * @param sql     sql语句
     * @param userID  用户ID
     * @param barCode 待添加条形码
     * @return 添加后的记录
     * TODO
     */
    private String concat(String sql, String userID, String barCode) {
        String list = "";
        try {
            PreparedStatement pstmt = databaseConnect.getPreparedStatement(sql);
            pstmt.setString(1, barCode + " ");
            pstmt.setString(2, userID);

            ResultSet result = pstmt.executeQuery();
            result.next();
            list = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 获得数据库中指定ID的某一项数据
     *
     * @param field 表项
     * @param userID 用户ID
     * @return 对应内容
     */
    private String getData(String field, String userID) {
        String data = "";
        String sql = "select " + field + " from user where id = ?";
        try {
            PreparedStatement pstmt = databaseConnect.getPreparedStatement(sql);

            pstmt.setString(1, userID);

            ResultSet result = pstmt.executeQuery();
            result.next();
            data = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        databaseConnect.closeConnection();
        return data;
    }

    public static void main(String[] args) throws RemoteException {
        UserPO user = new UserPO("张三", "131110032", UserRole.UNDERGRADUATE);

        UserData userData = new UserData();
//        userData.insert(user);
        userData.back(user.getID(), "2016012300002");
//
//        userData.cancelRenew(user.getID(), "2016012300002");


        System.out.println("done");
        System.exit(0);
    }
}
