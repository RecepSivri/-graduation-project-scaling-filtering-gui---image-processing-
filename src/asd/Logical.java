package asd;

import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 2.05.2017.
 */
public abstract  class Logical {

    private BufferedImage input1;
    private BufferedImage input2;

    public BufferedImage getInput1() {
        return input1;
    }

    public BufferedImage getInput2() {
        return input2;
    }

    public void setInput1(BufferedImage input1) {
        this.input1 = input1;

    }

    public void setInput2(BufferedImage input2) {
        this.input2 = input2;
    }

    public abstract BufferedImage returnlogicalResult();

    public BufferedImage returnMaxSizedImage() {



        int xdim1,ydim1,xdim2,ydim2,maxX,maxY;
        xdim1=getInput1().getHeight();
        ydim1=getInput1().getWidth();
        xdim2=getInput2().getHeight();
        ydim2=getInput2().getWidth();

        if (xdim1 > xdim2)
            maxX = xdim1;
        else
            maxX = xdim2;

        if (ydim1 > ydim2)
            maxY = ydim1;
        else
            maxY = ydim2;
        BufferedImage buffImg = new BufferedImage(maxY, maxX, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < maxY; ++i)
        {
            for (int j = 0; j < maxX; ++j)
            {
                buffImg.setRGB(i, j, -16777216);
            }
        }
        return buffImg;
    }
}
