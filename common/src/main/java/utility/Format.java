package utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by 宋益明 on 16-1-21.
 *
 * 格式类,定义系统数据格式
 * 包括   日期格式(yyyymmdd)
 * 及    小数格式(
 *
 */
public final class Format {
    public static final DateFormat DATE_FORMAT;
    public static final DecimalFormat DECIMAL_FORMAT;

    static {
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        DECIMAL_FORMAT = new DecimalFormat("");
    }
}
