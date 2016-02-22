package bl;

import blservice.LoginBLservice;
import presentation.LoginPanel;
import presentation.MainFrame;
import presentation.MenuPanel;
import presentation.admin.AdminMenu;
import presentation.reader.ReaderMenu;

/**
 * Created by 宋益明 on 16-2-5.
 * <p>
 * 登录类
 * 负责处理用户登录
 */
public final class Login implements LoginBLservice {

    private MenuPanel menuPanel;

    public boolean isLegal(String id, String password) {
        //TODO 判断登录信息
        return true;
    }

    public void Login(String id) {
        //隐藏登录界面
        LoginPanel.getLoginPanel().setVisible(false);
        //创建菜单界面
        createMenu(id);
        //添加菜单界面
        MainFrame.getMainFrame().setContentPane(menuPanel);
    }

    private void createMenu(String id) {
        //TODO 判断id
        if (id == "0") {
            menuPanel = new AdminMenu();
        } else {
            menuPanel = new ReaderMenu();
        }
    }
}