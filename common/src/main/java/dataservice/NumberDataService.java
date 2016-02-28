package dataservice;

import po.Category;

import java.util.Date;

/**
 * Created by 宋益明 on 16-2-28.
 *
 * 图书编号数据服务
 * 负责生成图书编号
 * 条形码在当天入库图书编号基础上增加
 * 书标在同类型图书编号基础上增加
 */
public interface NumberDataService {
    public String createBarCode(Date date);

    public String createLabel(Category category);
}
