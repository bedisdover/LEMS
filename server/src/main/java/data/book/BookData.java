package data.book;

import data.DatabaseConnect;
import dataservice.BookDataService;
import po.BookPO;
import po.BookType;
import utility.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 宋益明 on 16-1-23
 * <p>
 * 图书信息操作类
 * 负责对图书信息的增,删,改,查
 * 图书信息的查询可以根据图书关键信息中的一项或多项关键字进行检索
 */
public class BookData extends UnicastRemoteObject implements BookDataService {
    private DatabaseConnect databaseConnect;

    public BookData() throws RemoteException {
        super();

        databaseConnect = new DatabaseConnect();
    }

    /**
     * 新增图书信息
     * 若已存在相同ISBN图书(不同副本)
     * 原有ISBN不再更改,新增ISBN后添加编号
     * 编号从1开始,如"9787111407508(1)"
     *
     * @param book 图书持久化对象
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage insert(BookPO book) throws RemoteException {
        String sql = "insert into book values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            String[] values = new String[]{
                    book.getName(), book.getAuthor(), book.getPublisher(),
                    book.getISBN(), book.getType().toString(), book.getBarCode(),
                    book.getLabel(), book.getBorrower(), book.getRenewer()
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
     * 删除图书信息
     *
     * @param barCode 图书条形码
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage delete(String barCode) throws RemoteException {
        String sql = "delete from book where barcode = ?";

        try {
            databaseConnect.execute(sql, barCode);
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }
        databaseConnect.closeConnection();

        return ResultMessage.SUCCESS;
    }

    /**
     * 更新借阅者
     *
     * @param barCode 条形码
     * @param userID  借阅用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage borrow(String barCode, String userID) throws RemoteException {
        String sql = "update book set borrower = ? where barcode = ?";

        return update(sql, barCode, userID);
    }

    /**
     * 更新预约者
     *
     * @param barCode 条形码
     * @param userID  预约用户ID
     * @return 成功返回SUCCESS, 失败返回FAILURE
     * @throws RemoteException 远程连接异常
     */
    public ResultMessage renew(String barCode, String userID) throws RemoteException {
        String sql = "update book set renewer = ? where barcode = ?";

        return update(sql, barCode, userID);
    }

    /**
     * 根据ISBN编号查询图书信息
     * 因同一本图书可能有多个不同副本,且副本ISBN编号相同
     * 故查询结果返回所有副本信息
     *
     * @param ISBN 图书ISBN编号
     * @return 图书所有副本持久化对象列表的迭代器, 若查询失败, 返回null
     * @throws RemoteException 远程连接异常
     */
    public Iterator<BookPO> find(String ISBN) throws RemoteException {
        String sql = "select * from book where ISBN = ?";
        List<BookPO> list = new ArrayList<BookPO>();

        try {
            ResultSet result = databaseConnect.getResultSet(sql, ISBN);
            while (result.next()) {
                String name = result.getString(1);
                String author = result.getString(2);
                String publisher = result.getString(3);
                String type = result.getString(5);
                String barCode = result.getString(6);
                String label = result.getString(7);
                String borrower = result.getString(8);
                String renewer = result.getString(9);

                BookPO book = new BookPO(name, author, publisher, ISBN, BookType.valueOf(type), label);
                book.setBarCode(barCode);
                book.setBorrower(borrower);
                book.setRenewer(renewer);

                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnect.closeConnection();

        return list.iterator();
    }

    /**
     * 根据图书基本信息中的一个或多个关键字查询图书信息
     *
     * @param key 关键字,数量任意
     * @return 图书持久化对象列表, 若查询失败, 返回null
     * @throws RemoteException 远程连接异常
     */
    public List<BookPO> find(String... key) throws RemoteException {
        //TODO 多条件搜索,暂时不予考虑
        return null;
    }

    /**
     * 更新数据
     *
     * @param sql   sql语句
     * @param item  项目
     * @param value 更新后的值
     * @return 结果信息
     */
    private ResultMessage update(String sql, String item, String value) {
        try {
            //更新语句均为 "update book set ... = ?(<value>) where ... = ?(<item>)"
            //故传入参数顺序为value, item
            databaseConnect.execute(sql, value, item);
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnect.closeConnection();
            return ResultMessage.FAILURE;
        }
        databaseConnect.closeConnection();

        return ResultMessage.SUCCESS;
    }

    public static void main(String[] args) throws RemoteException {
        List<String> authors = new ArrayList<String>();
        authors.add("丁二玉");
        authors.add("刘钦");

        BookPO book = new BookPO("软件工程与计算(卷二)", authors, "机械工业出版社", "9787111407508", BookType.ORDINARY, "TP123:123");
        book.setBarCode("2016012300002");
//        book.setBorrower(new UserPO("teacher", "1", UserRole.TEACHER));
//        book.setRenewer(new UserPO("student", "2", UserRole.UNDERGRADUATE));

//        new BookData().insert(book);
//        List<BookPO> list = new BookData().find("9787111407508");
//        for (BookPO temp : list) {
//            System.out.println(temp.getISBN());
//        }
//        new BookData().delete("2016012300002");
//        new BookData().update(book);
        new BookData().borrow("2016012300001", "131110032");
//        new BookData().renew("2016012300002", "131110034");
        System.out.println("done");
        System.exit(0);
    }
}
