package presentation;

import javax.swing.*;
import java.util.TimerTask;

/**
 * Created by 宋益明 on 16-2-3.
 *
 * 测试菜单面板
 */
public class MenuPanelTest {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        JFrame mainFrame = MainFrame.getMainFrame();
        MenuPanel m = new MenuPanel();
//        mainFrame.setContentPane(m);

        mainFrame.add(m);

        m.setBounds(0, 0, mainFrame.getWidth() / 4, mainFrame.getHeight());

        mainFrame.setVisible(true);
    }
}