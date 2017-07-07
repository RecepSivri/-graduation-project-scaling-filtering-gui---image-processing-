package asd;

import java.awt.image.BufferedImage;

/**
 * Created by Recep Sivri on 2.05.2017.
 */
public class Xor extends Logical {
    @Override
    public BufferedImage returnlogicalResult() {
        BufferedImage img1=getInput1();
        BufferedImage img2=getInput2();
        Not not=new Not();
        not.setInput1(img1);

        BufferedImage result1;
        BufferedImage result2;

        BufferedImage image=not.returnlogicalResult();
        not.setInput1(img2);
        BufferedImage image2=not.returnlogicalResult();

        Multiply m1=new Multiply();
        m1.setInput1(img1);
        m1.setInput2(image2);

        result1=m1.returnResult();

        m1.setInput1(img2);
        m1.setInput2(image);

        result2=m1.returnResult();

        Sum s1=new Sum();

        s1.setInput1(result1);
        s1.setInput2(result2);
        return s1.returnResult();
    }
}
