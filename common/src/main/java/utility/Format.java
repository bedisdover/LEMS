package utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 宋益明 on 16-1-21.
 * <p>
 * 格式类,定义系统数据格式
 * 包括   日期格式(yyyymmdd)
 * 及    小数格式(TODO 小数格式)
 */
public final class Format {
    public static final DateFormat DATE_FORMAT;
    public static final DecimalFormat DECIMAL_FORMAT;

    static {
        DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
        DECIMAL_FORMAT = new DecimalFormat("");
    }
}
