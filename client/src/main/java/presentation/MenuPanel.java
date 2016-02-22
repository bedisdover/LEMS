package presentation;

import presentation.util.Portrait;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-2-3.
 *
 * 菜单面板
 * 包含头像和功能列表
 */
public class MenuPanel extends JPanel {
    private JPanel portraitPanel;

    private Image portrait;

    public MenuPanel() {
        this.setLayout(null);

        this.createUIComponents();
    }

    @Override
    public void paintBorder(Graphics g) {
        super.paintBorder(g);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        // 消除锯齿
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(new Color(153, 143, 66));
        graphics2D.fillRect(0, 0, this.getWidth() / 4, this.getHeight());
        graphics2D.setColor(new Color(11, 8, 18));
        graphics2D.fillRect(0, 0, this.getWidth() / 4, this.getHeight() / 6);
        graphics2D.setColor(Color.gray);
        graphics2D.fillOval(20, 10, 60, 60);

        if (portrait != null) {
//            graphics2D.drawImage(portrait, 20, 10, 60, 60, null);
            graphics2D.drawImage(Portrait.getInstance().getRenderedImage(portrait, 50), 0, 0, null);
        }
    }

    private void createUIComponents() {
        portraitPanel = new JPanel();

        portraitPanel.setBounds(20, 10, 60, 60);

        portraitPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    //修改文件选择器风格
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }

                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(MainFrame.getMainFrame());

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        portrait = ImageIO.read(chooser.getSelectedFile());
                        repaint();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        this.add(portraitPanel);
    }
}
