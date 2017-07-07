package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 2.05.2017.
 */
public class Or extends Logical {
    @Override
    public BufferedImage returnlogicalResult() {
        if (getInput1().getData().getNumBands() == 3 && getInput1().getData().getNumBands() == 3)
        {
            BufferedImage MaxImage=returnMaxSizedImage();
            BufferedImage MaxImage2 = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            BufferedImage result = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int i = 0; i < MaxImage.getHeight(); ++i)
            {
                for (int j = 0; j < MaxImage.getWidth(); ++j)
                {
                    MaxImage2.setRGB(i, j, -16777216);
                    result.setRGB(i, j, -16777216);
                }
            }
            BufferedImage input1=getInput1();
            BufferedImage input2=getInput2();
            int a=0,b=0;
            int colDistance=(MaxImage.getWidth()-input1.getWidth())/2;
            int rowDistance=(MaxImage.getHeight()-input1.getHeight())/2;
            System.out.println(colDistance+"         "+rowDistance);
            for(int i=rowDistance+1;i<MaxImage.getHeight()-rowDistance-1;++i)
            {
                for(int j=colDistance+1;j<MaxImage.getWidth()-colDistance-1;++j)
                {
                    MaxImage.setRGB(j,i,input1.getRGB(b,a));
                    ++b;
                }
                b=0;
                ++a;
            }
            a=0;
            colDistance=(MaxImage2.getWidth()-input2.getWidth())/2;
            rowDistance=(MaxImage2.getHeight()-input2.getHeight())/2;

            for(int i=rowDistance+1;i<MaxImage2.getHeight()-rowDistance-1;++i)
            {
                for(int j=colDistance+1;j<MaxImage2.getWidth()-colDistance-1;++j)
                {
                    MaxImage2.setRGB(j,i,input2.getRGB(b,a));
                    ++b;
                }
                b=0;
                ++a;
            }
            for(int i=0;i<result.getHeight();++i)
            {
                for(int j=0;j<result.getWidth();++j)
                {
                    Color c1=new Color(MaxImage.getRGB(i,j));
                    Color c2=new Color(MaxImage2.getRGB(i,j));
                    int red=0,green=0,blue=0;
                    if(c1.getRed()==255||c2.getRed()==255)
                        red=255;
                    else
                    if(c1.getRed()==0)
                        red=c2.getRed();
                    else
                    if(c2.getRed()==0)
                        red=c1.getRed();

                    if(c1.getRed()==0&&c2.getRed()==0)
                        red=0;

                    if(c1.getGreen()==255||c2.getGreen()==255)
                        green=255;
                    else
                    if(c1.getGreen()==0)
                        green=c2.getGreen();
                    else
                    if(c2.getGreen()==0)
                        green=c1.getGreen();

                    if(c1.getGreen()==0&&c2.getGreen()==0)
                        green=0;

                    if(c1.getBlue()==255||c2.getBlue()==255)
                        blue=255;
                    else
                    if(c1.getBlue()==0)
                        blue=c2.getBlue();
                    else
                    if(c2.getBlue()==0)
                        blue=c1.getBlue();

                    if(c1.getBlue()==0&&c2.getBlue()==0)
                        blue=0;




                    result.setRGB(i,j,new Color(red,green,blue).getRGB());
                }
            }
            return result;

    } else
        if (getInput1().getData().getNumBands() == 1 && getInput1().getData().getNumBands() == 1) {
            BufferedImage MaxImage = returnMaxSizedImage();
            BufferedImage MaxImage2 = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            BufferedImage result = new BufferedImage(MaxImage.getWidth(), MaxImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < MaxImage.getHeight(); ++i) {
            for (int j = 0; j < MaxImage.getWidth(); ++j) {
            MaxImage2.setRGB(j,i, -16777216);
            result.setRGB(j,i, -16777216);
            }
            }
            BufferedImage input1 = getInput1();
            BufferedImage input2 = getInput2();
            int a = 0, b = 0;
            int colDistance = (MaxImage.getWidth() - input1.getWidth()) / 2;
            int rowDistance = (MaxImage.getHeight() - input1.getHeight()) / 2;
            System.out.println(colDistance + "         " + rowDistance);
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
                        int LogicResult=0;
                        if(MaxImage.getRGB(j,i)==16777216||MaxImage2.getRGB(j,i)==16777216)
                            LogicResult=16777216;
                        else
                        if(MaxImage.getRGB(j,i)==0)
                            LogicResult=MaxImage2.getRGB(j,i);
                        else
                        if(MaxImage2.getRGB(j,i)==0)
                            LogicResult=MaxImage.getRGB(j,i);

                        if(MaxImage.getRGB(j,i)==0&&MaxImage2.getRGB(j,i)==0)
                            LogicResult=0;



                    result.setRGB(j, i, LogicResult);
                }
            }
        return result;
        }
        else
            return null;
    }
}
