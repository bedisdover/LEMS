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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                x = e.getX();
                y = e.getY();

                System.out.println(x);

                repaint();
            }

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

        g.drawImage(getRenderedImage(image, 400), x, y, getWidth(), getHeight(), null);
    }

    /**
     * 将矩形图片切割为圆形
     *
     * @param image
     * @return
     */
    public BufferedImage getRenderedImage(Image image, int radius) {
        BufferedImage result = new BufferedImage(image.getWidth(this), image.getHeight(this), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, radius, radius, null);
        Ellipse2D round = new Ellipse2D.Double(0, 0, radius, radius);
        Area clear = new Area(new Rectangle2D.Double(0, 0, radius, radius));
        clear.subtract(new Area(round));
        g.setComposite(AlphaComposite.Clear);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.fill(clear);
        g.dispose();

        return result;
    }
}
