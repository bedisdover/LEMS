package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-1-22.
 *
 * 客户端主窗体
 */
public final class MainFrame extends JFrame {

    private static final int width = 700;
    private static final int height = 600;

    private static final MainFrame frame;

    private MainFrame() {}


    static {
        frame = new MainFrame();

        init();
    }

    public static JFrame getMainFrame() {
        return frame;
    }

    /**
     * 初始化窗体
     */
    private static void init() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setBounds(
                (int) (dimension.getWidth() - width) / 2,
                (int) (dimension.getHeight() - height) / 2,
                width, height
        );
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LoginPanel());

    }
}
