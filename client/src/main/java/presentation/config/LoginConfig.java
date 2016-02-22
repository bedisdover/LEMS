package presentation.config;

import presentation.LoginPanel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by 宋益明 on 16-2-2.
 *
 * 登录配置类
 * 程序启动时载入用户名,密码
 * 点击登录后存储用户名,密码
 */
public final class LoginConfig {
    private LoginPanel loginPanel;

    private Properties properties;

    private static final String fileName = "client/src/main/resources/login.xml";

    public String loadID() throws IOException {
        this.load();

        return properties.getProperty("ID");
    }

    public String loadPass() throws IOException {
        this.load();

        return properties.getProperty("Password");
    }

    public boolean loadRemember() throws IOException {
        this.load();

        return new Boolean(properties.getProperty("Remember"));
    }

    public boolean loadAutoLogin() throws IOException {
        this.load();

        return new Boolean(properties.getProperty("AutoLogin"));
    }

    public void store() throws IOException {
        loginPanel = (LoginPanel) LoginPanel.getLoginPanel();

        properties.setProperty("ID", loginPanel.getID());
        properties.setProperty("Password", loginPanel.getPassword());
        properties.setProperty("Remember", loginPanel.getRemember() + "");
        properties.setProperty("AutoLogin", loginPanel.getAutoLogin() + "");

        properties.storeToXML(new FileOutputStream(fileName), "登录配置");
    }

    public LoginConfig() {
        properties = new Properties();
    }

    private void load() throws IOException {
        properties.loadFromXML(new FileInputStream(fileName));
    }
}
