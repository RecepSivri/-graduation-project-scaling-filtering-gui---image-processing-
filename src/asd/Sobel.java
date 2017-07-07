package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 24.04.2017.
 */
public class Sobel extends Filter {
    @Override
    public BufferedImage ImplementFilter(BufferedImage img) throws Exception {


        int i,j,total1=0,total2=0;
        BufferedImage convertedImage= null;
        BufferedImage Gx;
        BufferedImage Gy;
        BufferedImage MaxImage;
        if(img.getData().getNumBands()==3) {
            MaxImage = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_3BYTE_BGR);
            convertedImage= convertGrayScale(img);
        }
        else
        if(img.getData().getNumBands()==1) {
            convertedImage= img;
            MaxImage = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_BYTE_GRAY);
        }
        else {
            MaxImage = null;
        }
        KernelSize=3;

        Color c;
        Color c1;
        Integer array1[][];
        array1=new Integer[KernelSize][];
        for(i=0;i<KernelSize;++i)
            array1[i]=new Integer[KernelSize];
        for(i=0;i<KernelSize;++i)
        {
            for (j = 0; j < KernelSize; ++j)
                array1[i][j]=0;

        }
        array1[0][0]=-1;
        array1[1][0]=-2;
        array1[2][0]=-1;
        array1[0][2]=1;
        array1[1][2]=2;
        array1[2][2]=1;

        Integer array2[][];
        array2=new Integer[KernelSize][];
        for(i=0;i<KernelSize;++i)
            array2[i]=new Integer[KernelSize];
        for(i=0;i<KernelSize;++i)
        {
            for (j = 0; j < KernelSize; ++j)
                array2[i][j]=0;

        }
        array2[0][0]=-1;
        array2[0][1]=-2;
        array2[0][2]=-1;
        array2[2][0]=1;
        array2[2][1]=2;
        array2[2][2]=1;
        Gx=returnGradX(array1,convertedImage);
        Gy=returnGradX(array2,convertedImage);
        for(i=0;i<MaxImage.getHeight();++i)
        {
            for(j=0;j<MaxImage.getWidth();++j)
            {
                c=new Color(Gx.getRGB(j,i));
                c1=new Color(Gy.getRGB(j,i));
                total1=(c.getRed()+c.getGreen()+c.getBlue())/3;
                total2=(c1.getRed()+c1.getGreen()+c1.getBlue())/3;

                total1=total1+total2;
                if(total1>255)
                    total1=255;
                Color c2=new Color(total1,total1,total1);
                MaxImage.setRGB(j,i,c2.getRGB());
            }
        }
        return MaxImage;

    }
    public BufferedImage convertGrayScale(BufferedImage img ) throws Exception {
        Color c;
        BufferedImage MaxImage = new BufferedImage(img.getWidth(), img.getHeight(), img.TYPE_3BYTE_BGR);
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
        Gauss gaussian=new Gauss();
        gaussian.KernelSize=3;
        gaussian.setSigma(0.5);
        MaxImage=gaussian.ImplementFilter(MaxImage);

        return MaxImage;
    }
    private BufferedImage returnGradX(Integer [][]array,BufferedImage convertedImage)
    {
        BufferedImage MaxImage = new BufferedImage(convertedImage.getWidth(), convertedImage.getHeight(), convertedImage.TYPE_3BYTE_BGR);
        int i,j,m=0,n=0,k,l;
        int totalRed=0,totalGreen=0,totalBlue=0;
        int h=convertedImage.getHeight();
        int w=convertedImage.getWidth();
        Color c;
        for(i=0;i<h;++i)
        {
            for(j=0;j<w;++j)
            {
                m=0;
                n=0;

                for (k = i - KernelSize/2; k <= i + KernelSize/2; ++k)
                {
                    for (l = j - KernelSize/2; l <= j + KernelSize/2; ++l)
                    {
                        if (k >= 0 && k < h && l >= 0 && l < w)
                        {
                            c=new Color(convertedImage.getRGB(l,k));
                            totalRed=totalRed+array[m][n]*c.getRed();
                            totalGreen=totalGreen+array[m][n]*c.getGreen();
                            totalBlue=totalBlue+array[m][n]*c.getBlue();
                        }
                        ++n;
                    }
                    n = 0;
                    ++m;
                }
                if(totalRed>255)
                    totalRed=255;
                if(totalGreen>255)
                    totalGreen=255;
                if(totalBlue>255)
                    totalBlue=255;
                if(totalRed<0)
                    totalRed=0;
                if(totalGreen<255)
                    totalGreen=0;
                if(totalBlue<255)
                    totalBlue=0;
                totalRed=totalBlue+totalRed+totalGreen;
                totalRed=totalRed/3;
                MaxImage.setRGB(j,i,new Color(totalRed,totalRed,totalRed).getRGB());
                totalRed=0;
                totalGreen=0;
                totalBlue=0;
            }
        }
        return MaxImage;
    }
}
