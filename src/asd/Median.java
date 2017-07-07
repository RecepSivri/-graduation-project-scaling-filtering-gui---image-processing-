package asd; /**
 * Created by recep on 2/25/17.
 */
/**
 * Created by recep on 2/25/17.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Arrays;


public class Median extends Filter {


    public BufferedImage  ImplementFilter(BufferedImage img ) throws Exception {

        int w = img.getWidth(null);
        int h = img.getHeight(null);
        if (img.getData().getNumBands() == 3) {
            ColorModel cm = img.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = img.copyData(null);
            BufferedImage img2 = new BufferedImage(cm, raster, isAlphaPremultiplied, null);

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
                    ;
                    totalr = 0;
                    totalg = 0;
                    totalb = 0;
                    int[] Carray = new int[KernelSize * KernelSize];
                    int a, b;
                    for (a = 0; a < KernelSize; ++a) {
                        for (b = 0; b < KernelSize; ++b) {
                            Carray[a * KernelSize + b] = array[a][b].getRGB();
                        }
                    }
                    Arrays.sort(Carray);
                    img2.setRGB(j, i, Carray[KernelSize * KernelSize / 2]);
                }
            }
            return img2;
        } else if (img.getData().getNumBands() == 1) {
            ColorModel cm = img.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

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
                    ;
                    totalr = 0;
                    totalg = 0;
                    totalb = 0;
                    int[] Carray = new int[KernelSize * KernelSize];
                    int a, b;
                    for (a = 0; a < KernelSize; ++a) {
                        for (b = 0; b < KernelSize; ++b) {
                            Carray[a * KernelSize + b] = array[a][b].getRGB();
                        }
                    }
                    Arrays.sort(Carray);
                    img2.setRGB(j, i, Carray[KernelSize * KernelSize / 2]);
                }
            }
            return img2;
        } else
            return null;
    }

}

