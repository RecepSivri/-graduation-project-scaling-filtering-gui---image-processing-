package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 31.05.2017.
 */
public class GrayScale {
    public BufferedImage convertGrayScale(BufferedImage img ) throws Exception {
        Color c;
        BufferedImage MaxImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        int total;
        int i,j;
        for(i=0;i<img.getHeight();++i)
        {
            for(j=0;j<img.getWidth();++j)
            {
                c=new Color(img.getRGB(j,i));
                total=c.getBlue()+c.getGreen()+c.getAlpha();
                total=total/3;
                MaxImage.setRGB(j,i,new Color(total,total,total).getRGB());
            }
        }

        return MaxImage;
    }
}
