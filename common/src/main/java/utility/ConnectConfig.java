package utility;

import java.io.*;
import java.util.Properties;

/**
 * Created by 宋益明 on 16-1-20.
 *
 * 负责RMI连接配置,包括 IP地址和端口号
 */
public final class ConnectConfig {
    public static final String IP;
    public static final String PORT;
    public static final String URL;

    static {
        Properties pro = new Properties();

        try {
            pro.loadFromXML(new FileInputStream("common/src/main/resources/connect.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        IP = pro.getProperty("IP");
        PORT = pro.getProperty("PORT");
        URL = "rmi://" + IP + ":" + PORT + "/data";
    }
}
