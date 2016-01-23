package presentation.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 宋益明 on 16-1-22.
 *
 * 主窗体配置类,负责配置主窗体
 */
public final class MainFrameConfig {
    private static Locale locale;
    private static ResourceBundle resource;

    private MainFrameConfig() {}

    static {
        locale = new Locale("zh", "CN");
        resource = ResourceBundle.getBundle("MainFrameConfig", locale);
    }

    public static String getName(String item) {
        return resource.getString(item);
    }
}
