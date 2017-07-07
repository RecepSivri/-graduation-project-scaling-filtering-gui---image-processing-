package asd;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 16.05.2017.
 */
public class Sharp {
    double Sharp;
    public BufferedImage returnSharpingImage(BufferedImage input) throws Exception {

        BufferedImage result;
        BufferedImage difference;
        Gauss g=new Gauss();
        g.KernelSize=3;
        g.setSigma(2);
        result=g.ImplementFilter(input);
        Substract s=new Substract();
        s.setInput1(input);
        s.setInput2(result);
        difference= s.returnResult();

        for(int i=0;i<difference.getHeight();++i)
        {
            for(int j=0;j<difference.getWidth();++j)
            {
                int newDifference=(int)(difference.getRGB(j,i));
                if(newDifference>new Color(255,255,255).getRGB())
                    newDifference=new Color(255,255,255).getRGB();

                difference.setRGB(j,i,newDifference);
            }
        }

        Sum s1=new Sum();
        s1.setInput1(difference);
        s1.setInput2(input);
        return s1.returnResult();
    }
}
