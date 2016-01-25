package data;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by 宋益明 on 16-1-23.
 *
 * 检测网络配置类
 * 检测成功
 */
public class ConfigTest extends TestCase {
    @Test
    public void test() {
        assertEquals(Config.IP, "127.0.0.1");
        assertEquals(Config.PORT, "1099");
    }
}