package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 24.04.2017.
 */
public class Laplacian extends Filter {
    @Override
    public BufferedImage ImplementFilter(BufferedImage img) throws Exception {


        int i,j,total1=0,total2=0;
        BufferedImage convertedImage= convertGrayScale(img);


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
        array1[0][2]=-1;
        array1[1][1]=4;
        array1[2][0]=-1;
        array1[2][2]=-1;



        return returnFilterResult(array1,convertedImage);

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
    private BufferedImage returnFilterResult(Integer [][]array,BufferedImage convertedImage)
    {
        BufferedImage MaxImage=null;
        if(convertedImage.getData().getNumBands()==3)
            MaxImage = new BufferedImage(convertedImage.getWidth(), convertedImage.getHeight(), convertedImage.TYPE_3BYTE_BGR);
        else
        if(convertedImage.getData().getNumBands()==1)
            MaxImage = new BufferedImage(convertedImage.getWidth(), convertedImage.getHeight(), convertedImage.TYPE_BYTE_GRAY);
        else
            MaxImage=null;

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
