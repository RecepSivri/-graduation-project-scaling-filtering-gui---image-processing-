package asd; /**
 * Created by Recep Sivri on 3.04.2017.
 */
/**
 * Created by recep on 2/25/17.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;


public class Mean extends Filter{


    public BufferedImage  ImplementFilter(BufferedImage img ) throws Exception {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        if (img.getData().getNumBands() == 3) {
            ColorModel cm = img.getColorModel();
            BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_3BYTE_BGR);

        /*------------------------------------------------------------------------------------------*/


            int totala = 0, totalr = 0, totalg = 0, totalb = 0, result = 0;
            Color[][] array = new Color[KernelSize][KernelSize];
            int i = 0, j = 0;
            for (i = 0; i < KernelSize; ++i) {
                array[i] = new Color[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    array[i][j] = new Color(0, 0, 0);

                }
            }


            int k, l, m = 0, n = 0;
            for (i = 0; i < h; ++i) {
                for (j = 0; j < w; ++j) {
                    m = 0;
                    n = 0;
                    for (k = i - KernelSize / 2; k <= i + KernelSize / 2; ++k) {
                        for (l = j - KernelSize / 2; l <= j + KernelSize / 2; ++l) {
                            if (k >= 0 && k < h && l >= 0 && l < w) {
                                Color c = new Color(img.getRGB(l, k));
                                array[m][n] = c;
                            } else {
                                array[m][n] = new Color(0, 0, 0);
                            }
                            ++n;
                        }
                        n = 0;
                        ++m;
                    }
                    totala = 0;
                    for (m = 0; m < KernelSize; ++m) {
                        for (n = 0; n < KernelSize; ++n) {

                            totalr = totalr + array[m][n].getRed();
                            totalg = totalg + array[m][n].getGreen();
                            totalb = totalb + array[m][n].getBlue();

                        }
                    }
                    int rate = KernelSize * KernelSize;
                    Color color = new Color(totalr / rate, totalg / rate, totalb / rate);
                    totalr = 0;
                    totalg = 0;
                    totalb = 0;

                    img2.setRGB(j, i, color.getRGB());
                }
            }
            return img2;
        }
        else
        if (img.getData().getNumBands() == 1) {
            ColorModel cm = img.getColorModel();
            BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_BYTE_GRAY);

        /*------------------------------------------------------------------------------------------*/


            int totala = 0, totalr = 0, totalg = 0, totalb = 0, result = 0;
            Color[][] array = new Color[KernelSize][KernelSize];
            int i = 0, j = 0;
            for (i = 0; i < KernelSize; ++i) {
                array[i] = new Color[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    array[i][j] = new Color(0, 0, 0);

                }
            }


            int k, l, m = 0, n = 0;
            for (i = 0; i < h; ++i) {
                for (j = 0; j < w; ++j) {
                    m = 0;
                    n = 0;
                    for (k = i - KernelSize / 2; k <= i + KernelSize / 2; ++k) {
                        for (l = j - KernelSize / 2; l <= j + KernelSize / 2; ++l) {
                            if (k >= 0 && k < h && l >= 0 && l < w) {
                                Color c = new Color(img.getRGB(l, k));
                                array[m][n] = c;
                            } else {
                                array[m][n] = new Color(0, 0, 0);
                            }
                            ++n;
                        }
                        n = 0;
                        ++m;
                    }
                    totala = 0;
                    for (m = 0; m < KernelSize; ++m) {
                        for (n = 0; n < KernelSize; ++n) {

                            totalr = totalr + array[m][n].getRed();
                            totalg = totalg + array[m][n].getGreen();
                            totalb = totalb + array[m][n].getBlue();

                        }
                    }
                    int rate = KernelSize * KernelSize;
                    Color color = new Color(totalr / rate, totalg / rate, totalb / rate);
                    totalr = 0;
                    totalg = 0;
                    totalb = 0;

                    img2.setRGB(j, i, color.getRGB());
                }
            }
            return img2;
        }
        else
            return null;

    }

}
