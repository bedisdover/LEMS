package presentation.util;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by 宋益明 on 16-2-22.
 *
 * 头像处理类
 * 实现将矩形图片转换为圆形图片
 */
public class Portrait {
    private static Portrait ourInstance = new Portrait();

    public static Portrait getInstance() {
        return ourInstance;
    }

    private Portrait() {
    }

    /**
     * 将矩形图片切割为圆形
     * @param image
     * @param radius 圆形图片半径
     * @return
     */
    public BufferedImage getRenderedImage(Image image, int radius) {
        BufferedImage result = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null);
        RoundRectangle2D round = new RoundRectangle2D.Double(0, 0, radius, radius, radius, radius);
        Area clear = new Area(new Rectangle(0, 0, radius, radius));
        clear.subtract(new Area(round));
        g.setComposite(AlphaComposite.Clear);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fill(clear);
        g.dispose();

        return result;
    }
}
