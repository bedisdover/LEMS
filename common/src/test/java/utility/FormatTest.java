package utility;

import java.util.Date;

/**
 * Created by 宋益明 on 16-1-21.
 *
 * TODO JUnit依然用不了
 */
public class FormatTest {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(new Date());
        System.out.println(Format.DATE_FORMAT.format(date));
    }
}
