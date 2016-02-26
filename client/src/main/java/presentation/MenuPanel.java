package presentation;

import presentation.util.Portrait;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-2-3.
 * <p>
 * 菜单面板
 * 包含头像和功能列表
 */
public class MenuPanel extends JPanel {

    /**
     * 头像直径
     */
    private static final int PORTRAIT_DIAMETER = 60;

    /**
     * 头像文件
     */
    private static final File PORTRARIT = new File("portrait.png");

    /**
     * 原始图片
     */
    private BufferedImage image;

    /**
     * 头像图片
     */
    private BufferedImage portraitImage;

    /**
     * 头像选择器
     */
    private Portrait portrait;

    /**
     * 记录是否已选择头像
     */
    private boolean selected;

    public MenuPanel() {
        this.setLayout(null);

        this.createUIComponents();

        loadPortrait();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        // 消除锯齿
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(new Color(121, 106, 75));
        graphics2D.fillRect(0, 0, this.getWidth() / 4, this.getHeight());
        graphics2D.setColor(new Color(11, 8, 18));
        graphics2D.fillRect(0, 0, this.getWidth() / 4, this.getHeight() / 6);
        graphics2D.setColor(Color.gray);
        graphics2D.fillOval(20, 10, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER);

        graphics2D.drawImage(portraitImage, 20, 10, PORTRAIT_DIAMETER, PORTRAIT_DIAMETER, null);
    }

    private void createUIComponents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() >= 20 && e.getX() <= 80 && e.getY() >= 10 && e.getY() <= 70) {
                    chooseImage();
                }
            }
        });
    }

    /**
     * 选择原始图片
     */
    private void chooseImage() {
        try {
            //修改文件选择器风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(MainFrame.getMainFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                image = ImageIO.read(chooser.getSelectedFile());
                portrait = new Portrait(this, image);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获得经头像选择器处理过的头像
     */
    public void getPortrait() {
        portraitImage = portrait.getPortrait();

        if (portraitImage != null) {
            //存储更改后头像
            storePortrait();
        } else {
            loadPortrait();
        }

        repaint();
    }

    /**
     * 加载当前头像
     */
    private void loadPortrait() {
        try {
            portraitImage = ImageIO.read(PORTRARIT);
        } catch (IOException e) {
            portraitImage = null;
        }
    }

    /**
     * 存储更改后头像
     */
    private void storePortrait() {
        try {
            //删除原头像
            PORTRARIT.delete();

            ImageIO.write(portraitImage, "png", PORTRARIT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
