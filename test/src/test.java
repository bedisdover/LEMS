import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by 宋益明 on 16-2-22.
 *
 */
public class test extends JPanel {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setContentPane(new test());
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setLocationByPlatform();
        mainFrame.setVisible(true);
    }

    private Image image;

    private int x;
    private int y;

    public test() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));

        x = this.getWidth() / 2;
        y = this.getHeight() / 2;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseDragged(e);

                x = e.getX();
                y = e.getY();

                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(getRenderedImage(image, x, y, 200, 0.7f), 0, 0, null);
    }

    /**
     * 预处理源图片,调节切割范围之外图片的透明度,区分切割部分
     * 为方便观察,绘制等距离虚线
     *
     * @param image 源图片
     * @param x x坐标
     * @param y y坐标
     * @param diameter 切割直径
     * @param transparency 透明度
     * @return 目标图片
     */
    public BufferedImage getRenderedImage(Image image, int x, int y, int diameter, float transparency) {
        BufferedImage result = new BufferedImage(
                image.getWidth(this), image.getHeight(this), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();

        g.drawImage(image, 0, 0, null);
        Ellipse2D round = new Ellipse2D.Double(x - diameter / 2, y - diameter / 2, diameter, diameter);
        Area clear = new Area(new Rectangle2D.Double(0, 0, image.getWidth(this), image.getHeight(this)));
        clear.subtract(new Area(round));

        //设置透明度
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
        g.setComposite(ac);

        //消除锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fill(clear);

        //绘制虚线
        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(5));
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j <= image.getWidth(this) / 20; j++) {
                g.drawLine(j * 20, image.getHeight(this) / 4 * i, j * 20 + 10, image.getHeight(this) / 4 * i);
            }
            for (int j = 0; j <= image.getHeight(this) / 20; j++) {
                g.drawLine(image.getWidth(this) / 4 * i, j * 20, image.getWidth(this) / 4 * i, j * 20 + 10);
            }
        }

        g.dispose();

        return result;
    }

    /**
     * 切割图片,获得以光标所在坐标为圆心,具有指定直径的圆形图片
     *
     * @param image 源图片
     * @param x x坐标
     * @param y y坐标
     * @param diameter 直径
     * @return 目标图片
     */
    public BufferedImage getSegmentedImage(Image image, int x, int y, int diameter) {
        BufferedImage result = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, diameter, diameter, x - diameter / 2, y - diameter / 2, x + diameter / 2, y + diameter / 2, null);

        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, diameter, diameter);
        Ellipse2D round = new Ellipse2D.Double(0, 0, diameter, diameter);
        Area clear = new Area(rectangle);
        clear.subtract(new Area(round));

        g.setComposite(AlphaComposite.Clear);

        //消除锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fill(clear);
        g.dispose();

        return result;
    }
}