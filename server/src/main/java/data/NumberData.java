package data;

import dataservice.NumberDataService;
import po.Category;
import utility.Format;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 宋益明 on 16-2-28.
 * <p>
 * 图书编号数据服务
 * 负责生成图书编号
 * 条形码在当天入库图书编号基础上增加
 * 书标在同类型图书编号基础上增加
 */
public class NumberData extends UnicastRemoteObject implements NumberDataService {

    private DatabaseConnect databaseConnect;

    public NumberData() throws RemoteException {
        databaseConnect = new DatabaseConnect();
    }

    /**
     * 生成条形码, 默认当前日期
     *
     * @return 条形码
     * @throws RemoteException 远程连接异常
     */
    public String createBarCode() throws RemoteException {
        return createBarCode(new Date());
    }

    /**
     * 生成条形码(日期+5位数编号)
     *
     * @param date 日期
     * @return 条形码
     */
    public String createBarCode(Date date) {
        String prefix = Format.DATE_FORMAT.format(date);
        String sql = "select barcode from book where barcode like ?";

        List<String> list = new ArrayList<String>();
        try {
            ResultSet result = databaseConnect.getResultSet(sql, prefix + "%");

            while (result.next()) {
                list.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnect.closeConnection();

        if (list.isEmpty()) {
            return prefix + "00001";
        }

        return increase(getLast(list));
    }

    /**
     * 生成书标
     *
     * @param ISBN     ISBN编号
     * @param category 图书分类
     * @return 书标
     */
    public String createLabel(String ISBN, Category category) {
        String label = null;
        try {
            label = LabelCreater.createLabel(ISBN, category);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return label;
    }

    /**
     * 获得编号列表中最后一条记录,若列表为空,返回空字符串
     *
     * @param list 列表
     * @return 最后一条记录
     */
    private String getLast(List<String> list) {
        String result = "";

        for (String temp : list) {
            if (temp.compareTo(result) > 0) {
                result = temp;
            }
        }
        return result;
    }

    /**
     * 将以字符串形式存储的数值加一
     *
     * @param value 数值
     * @return 加一后的数值
     */
    private String increase(String value) {
        BigInteger temp = new BigInteger(value);

        temp = temp.add(new BigInteger("1"));

        return temp.toString();
    }

    public static void main(String[] args) throws ParseException, RemoteException {
        NumberData data = new NumberData();

        System.out.println(data.createBarCode(Format.DATE_FORMAT.parse("20160123")));
    }
}
