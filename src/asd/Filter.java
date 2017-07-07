package asd;

import java.awt.image.BufferedImage;

/**
 * Created by recep on 3/6/17.
 */
public abstract class Filter {
    protected int KernelSize;

    public int getKernelSize() {
        return KernelSize;
    }

    public void setKernelSize(int kernelSize) {
        KernelSize = kernelSize;
    }
    public abstract BufferedImage ImplementFilter(BufferedImage img )throws Exception ;
}
