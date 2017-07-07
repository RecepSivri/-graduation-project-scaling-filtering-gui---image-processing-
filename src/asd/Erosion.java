package asd;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Arrays;

/**
 * Created by Recep Sivri on 19.05.2017.
 */
public class Erosion {
    int KernelSize=3;
    public BufferedImage ImplementFilter(BufferedImage img ) throws Exception {

        int w = img.getWidth(null);
        int h = img.getHeight(null);
        if (img.getData().getNumBands() == 1) {

            BufferedImage img2 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
            int k, l, m = 0, n = 0,i,j,counter=0;
            for (i = 0; i < h; ++i)
                for (j = 0; j < w; ++j)
                    img2.setRGB(j,i,img.getRGB(j,i));




            for (i = 0; i < h; ++i) {
                for (j = 0; j < w; ++j) {
                    m = 0;
                    n = 0;
                    for (k = i - KernelSize / 2; k <= i + KernelSize / 2; ++k) {
                        for (l = j - KernelSize / 2; l <= j + KernelSize / 2; ++l) {
                            if (k >= 0 && k < h && l >= 0 && l < w) {
                                if(l==0&&k==1)
                                {
                                    if(img.getRGB(j+l,i+k)==16777216)
                                        ++counter;
                                }
                                if(l==1&&k==0)

                                    if(img.getRGB(j+l,i+k)==16777216)
                                        ++counter;
                                }
                                if(l==1&&k==2)

                                    if(img.getRGB(j+l,i+k)==16777216)
                                        ++counter;
                                }
                                if(l==2&&k==1)

                                    if(img.getRGB(j+l,i+k)==16777216)
                                        ++counter;
                                }
                                if(counter<4)
                                    img.setRGB(j,i,-16777216);

                            }


                    }

                return img2;
                }
                else
                return null;

        }

}
