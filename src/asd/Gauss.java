package asd;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Created by recep on 2/26/17.
 */
public class Gauss extends Filter {

    private double sigma=2;

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    @Override
    public BufferedImage  ImplementFilter(BufferedImage img ) throws Exception {

        if (img.getData().getNumBands() == 3) {
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            ColorModel cm = img.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = img.copyData(null);
            BufferedImage img2 = new BufferedImage(cm, raster, isAlphaPremultiplied, null);

            BufferedImage img3 = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

            /*------------------------------------------------------------------------------------------*/


            int totala = 0, totalr = 0, totalg = 0, totalb = 0, result = 0;
            double PI = Math.PI, result2;
            Double[][] array = new Double[KernelSize][KernelSize];
            int i = 0, j = 0;
            for (i = 0; i < h; ++i) {
                for (j = 0; j < w; ++j) {
                    img3.setRGB(j, i, img.getRGB(j, i));

                }
            }

            double totalSum = 0, pixelValue = 0;
            for (i = 0; i < KernelSize; ++i) {//creating kernel to calculating gaussian kernel.
                array[i] = new Double[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    array[i][j] = new Double(0.0);
                }
            }
            for (i = 0; i < KernelSize; ++i) {//calculating gaussian kernel with sigma and kernel size.
                array[i] = new Double[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    result2 = ((KernelSize / 2 - i) * (KernelSize / 2 - i) + (KernelSize / 2 - j) * (KernelSize / 2 - j)) * (-1);
                    result2 = result2 / (2 * sigma * sigma);
                    result2 = Math.exp(result2);
                    result2 = result2 / (2 * PI * sigma * sigma);
                    array[i][j] = result2;
                    totalSum = totalSum + result2;

                }
            }
            int k, l, m = 0, n = 0;

            for (m = 0; m < KernelSize; ++m)//dividing kernel elements with total of elements of kernel.
            {
                for (n = 0; n < KernelSize; ++n) {
                    array[m][n] = array[m][n] / totalSum;
                }

            }
            int totalRed = 0;
            int totalGreen = 0;
            int totalBlue = 0;
            int totalAlpha = 0;

            for (i = 0; i < h; ++i) {

                for (j = 0; j < w; ++j) {
                    m = 0;
                    n = 0;
                    totalRed = 0;
                    totalGreen = 0;
                    totalBlue = 0;
                    totalAlpha = 0;
                    for (k = i - KernelSize / 2; k <= i + KernelSize / 2; ++k) {
                        for (l = j - KernelSize / 2; l <= j + KernelSize / 2; ++l) {
                            if (k >= 0 && k < h && l >= 0 && l < w) {
                                totalRed = totalRed + (int) (new Color(img3.getRGB(l, k)).getRed() * array[m][n]);
                                totalGreen = totalGreen + (int) (new Color(img3.getRGB(l, k)).getGreen() * array[m][n]);
                                totalBlue = totalBlue + (int) (new Color(img3.getRGB(l, k)).getBlue() * array[m][n]);
                                totalAlpha = totalAlpha + (int) (new Color(img3.getRGB(l, k)).getAlpha() * array[m][n]);
                            }
                            ++n;
                        }
                        n = 0;
                        ++m;
                    }

                    img2.setRGB(j, i, new Color(totalRed, totalGreen, totalBlue, totalAlpha).getRGB());

                }
            }
            return img2;
        }
        else
        if (img.getData().getNumBands() == 1) {

            int w = img.getWidth(null);
            int h = img.getHeight(null);

            BufferedImage img2 = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);

            for (int a = 0; a < h; ++a)
                for (int b = 0; b < w; ++b)
                    img2.setRGB(b,a,img.getRGB(b,a));

            BufferedImage img3 = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

            /*------------------------------------------------------------------------------------------*/

            int totala = 0, totalr = 0, totalg = 0, totalb = 0, result = 0;
            double PI = Math.PI, result2;
            Double[][] array = new Double[KernelSize][KernelSize];
            int i = 0, j = 0;
            for (i = 0; i < h; ++i) {
                for (j = 0; j < w; ++j) {
                    img3.setRGB(j, i, img.getRGB(j, i));

                }
            }

            double totalSum = 0, pixelValue = 0;
            for (i = 0; i < KernelSize; ++i) {//creating kernel to calculating gaussian kernel.
                array[i] = new Double[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    array[i][j] = new Double(0.0);
                }
            }
            for (i = 0; i < KernelSize; ++i) {//calculating gaussian kernel with sigma and kernel size.
                array[i] = new Double[KernelSize];
                for (j = 0; j < KernelSize; ++j) {
                    result2 = ((KernelSize / 2 - i) * (KernelSize / 2 - i) + (KernelSize / 2 - j) * (KernelSize / 2 - j)) * (-1);
                    result2 = result2 / (2 * sigma * sigma);
                    result2 = Math.exp(result2);
                    result2 = result2 / (2 * PI * sigma * sigma);
                    array[i][j] = result2;
                    totalSum = totalSum + result2;

                }
            }
            int k, l, m = 0, n = 0;

            for (m = 0; m < KernelSize; ++m)//dividing kernel elements with total of elements of kernel.
            {
                for (n = 0; n < KernelSize; ++n) {
                    array[m][n] = array[m][n] / totalSum;
                }

            }
            int totalGauss = 0;
            for (i = 0; i < h; ++i) {

                for (j = 0; j < w; ++j) {
                    m = 0;
                    n = 0;
                    totalGauss = 0;
                    for (k = i - KernelSize / 2; k <= i + KernelSize / 2; ++k) {
                        for (l = j - KernelSize / 2; l <= j + KernelSize / 2; ++l) {
                            if (k >= 0 && k < h && l >= 0 && l < w) {
                                totalGauss = totalGauss + (int) (new Color(img3.getRGB(l, k)).getRed() * array[m][n]);
                            }
                            ++n;
                        }
                        n = 0;
                        ++m;
                    }

                    img2.setRGB(j, i, totalGauss);

                }
            }
            return img2;
        }
        else
            return null;
    }

}