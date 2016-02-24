package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 宋益明 on 16-1-22.
 *
 * 客户端主窗体
 */
public final class MainFrame extends JFrame {

    private static final int width;//683
    private static final int height;//512

    private static final MainFrame frame;

    private static Dimension screen;

    private MainFrame() {}

    static {
        screen = Toolkit.getDefaultToolkit().getScreenSize();

        width = screen.width / 2;
        height = width * 3 / 4;

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
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    }
}
