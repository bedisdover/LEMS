package data.user;

import java.util.Iterator;

/**
 * Created by 宋益明 on 16-1-26.
 * 集合转换类
 * <p>
 * 负责将用户的借阅列表和预约列表转换为字符串
 */
public class TransferList {
    public static String transfer(Iterator<String> iterator) {
        String result = "";

        while (iterator.hasNext()) {
            result = iterator.next() + " " + result;
        }

        return result;
    }
}
