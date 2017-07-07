package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 17.04.2017.
 */
public class Substract extends Arithmathic {
    @Override
    public BufferedImage returnResult() {
        if(getInput1().getData().getNumBands()==3&&getInput1().getData().getNumBands()==3) {
            BufferedImage MaxImage = returnMaxSizedImage();
            BufferedImage MaxImage2 = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            BufferedImage result = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int i = 0; i < MaxImage.getHeight(); ++i) {
                for (int j = 0; j < MaxImage.getWidth(); ++j) {
                    MaxImage2.setRGB(j, i, -16777216);
                    result.setRGB(j, i, -16777216);
                }
            }
            BufferedImage input1 = getInput1();
            BufferedImage input2 = getInput2();
            int a = 0, b = 0;
            int colDistance = (MaxImage.getWidth() - input1.getWidth()) / 2;
            int rowDistance = (MaxImage.getHeight() - input1.getHeight()) / 2;
            for (int i = rowDistance + 1; i < MaxImage.getHeight() - rowDistance - 1; ++i) {
                for (int j = colDistance + 1; j < MaxImage.getWidth() - colDistance - 1; ++j) {
                    MaxImage.setRGB(j, i, input1.getRGB(b, a));
                    ++b;
                }
                b = 0;
                ++a;
            }
            a = 0;
            colDistance = (MaxImage2.getWidth() - input2.getWidth()) / 2;
            rowDistance = (MaxImage2.getHeight() - input2.getHeight()) / 2;
            for (int i = rowDistance + 1; i < MaxImage2.getHeight() - rowDistance - 1; ++i) {
                for (int j = colDistance + 1; j < MaxImage2.getWidth() - colDistance - 1; ++j) {
                    MaxImage2.setRGB(j, i, input2.getRGB(b, a));
                    ++b;
                }
                b = 0;
                ++a;
            }
            for (int i = 0; i < result.getHeight(); ++i) {
                for (int j = 0; j < result.getWidth(); ++j) {
                    Color c1 = new Color(MaxImage.getRGB(j, i));
                    Color c2 = new Color(MaxImage2.getRGB(j, i));
                    int red, green, blue;
                    red = c1.getRed() - c2.getRed();
                    green = c1.getGreen() - c2.getGreen();
                    blue = c1.getBlue() - c2.getBlue();
                    if (red < 0)
                        red = 0;
                    if (green < 0)
                        green = 0;
                    if (blue < 0)
                        blue = 0;
                    result.setRGB(j, i, new Color(red, green, blue).getRGB());
                }
            }
            return result;
        }
        else
        if(getInput1().getData().getNumBands()==1&&getInput1().getData().getNumBands()==1) {
            BufferedImage MaxImage = returnMaxSizedImage();
            BufferedImage MaxImage2 = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            BufferedImage result = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < MaxImage.getHeight(); ++i) {
                for (int j = 0; j < MaxImage.getWidth(); ++j) {
                    MaxImage2.setRGB(j, i, -16777216);
                    result.setRGB(j, i, -16777216);
                }
            }
            BufferedImage input1 = getInput1();
            BufferedImage input2 = getInput2();
            int a = 0, b = 0;
            int colDistance = (MaxImage.getWidth() - input1.getWidth()) / 2;
            int rowDistance = (MaxImage.getHeight() - input1.getHeight()) / 2;
            for (int i = rowDistance + 1; i < MaxImage.getHeight() - rowDistance - 1; ++i) {
                for (int j = colDistance + 1; j < MaxImage.getWidth() - colDistance - 1; ++j) {
                    MaxImage.setRGB(j, i, input1.getRGB(b, a));
                    ++b;
                }
                b = 0;
                ++a;
            }
            a = 0;
            colDistance = (MaxImage2.getWidth() - input2.getWidth()) / 2;
            rowDistance = (MaxImage2.getHeight() - input2.getHeight()) / 2;
            for (int i = rowDistance + 1; i < MaxImage2.getHeight() - rowDistance - 1; ++i) {
                for (int j = colDistance + 1; j < MaxImage2.getWidth() - colDistance - 1; ++j) {
                    MaxImage2.setRGB(j, i, input2.getRGB(b, a));
                    ++b;
                }
                b = 0;
                ++a;
            }
            for (int i = 0; i < result.getHeight(); ++i) {
                for (int j = 0; j < result.getWidth(); ++j) {
                    if(MaxImage.getRGB(j,i)-MaxImage2.getRGB(j,i)<-16777216)
                        result.setRGB(j, i, -16777216);
                    else
                        result.setRGB(j, i, MaxImage.getRGB(j,i)-MaxImage2.getRGB(j,i));
                }
            }
            return result;
        }
        else
            return null;
    }
}
