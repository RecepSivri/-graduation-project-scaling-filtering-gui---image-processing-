package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 2.05.2017.
 */
public class Not extends Logical {
    @Override
    public BufferedImage returnlogicalResult() {
        BufferedImage image=getInput1();
        if(image.getData().getNumBands()==3) {
            BufferedImage img2 = new BufferedImage(image.getWidth(), image.getHeight(), image.TYPE_3BYTE_BGR);
            for (int i = 0; i < image.getHeight(); ++i) {
                for (int j = 0; j < image.getWidth(); ++j) {
                    Color c = new Color(image.getRGB(j, i));
                    Color c2 = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
                    img2.setRGB(j, i, c2.getRGB());
                }
            }
            return img2;
        }
        else
        if(image.getData().getNumBands()==1) {
            BufferedImage img2 = new BufferedImage(image.getWidth(), image.getHeight(), image.TYPE_BYTE_GRAY);
            for (int i = 0; i < image.getHeight(); ++i) {
                for (int j = 0; j < image.getWidth(); ++j) {
                    Color c = new Color(image.getRGB(j, i));
                    Color c2 = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
                    img2.setRGB(j, i, c2.getRGB());
                }
            }
            return img2;
        }
        else
            return null;
    }
}
