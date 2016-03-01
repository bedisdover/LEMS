package dataservice;

import po.Category;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by 宋益明 on 16-2-28.
 * <p>
 * 图书编号数据服务
 * 负责生成图书编号
 * 条形码在当天入库图书编号基础上增加
 * 书标在同类型图书编号基础上增加
 */
public interface NumberDataService extends Remote {

    /**
     * 生成条形码, 默认当前日期
     *
     * @return 条形码
     * @throws RemoteException 远程连接异常
     */
    String createBarCode() throws RemoteException;

    /**
     * 生成条形码(日期+5位数编号)
     *
     * @param date 日期
     * @return 条形码
     * @throws RemoteException 远程连接异常
     */
    String createBarCode(Date date) throws RemoteException;

    /**
     * 生成书标
     *
     * @param category 图书分类
     * @return 书标
     */
    String createLabel(Category category);
}
