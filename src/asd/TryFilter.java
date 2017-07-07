package asd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Recep Sivri on 24.04.2017.
 */
public class TryFilter {
    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Users\\Recep Sivri\\Desktop\\data\\data\\negative");
        File[] listOfFiles = folder.listFiles();

       /* for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("MenuFile " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }*/

        Median filter = new Median();
        Laplacian filter2 = new Laplacian();
        for(int i=0;i<listOfFiles.length;++i) {
            System.out.println(listOfFiles[i]);
            File input = new File(listOfFiles[i].toString());
            BufferedImage img = ImageIO.read(input);
            BufferedImage output;
            BufferedImage output2;
            filter.KernelSize = 5;
            output = filter.ImplementFilter(img);
            filter2.KernelSize = 5;
            output2 = filter2.ImplementFilter(output);
            ImageIO.write(output2, "png", new File("C:\\Users\\Recep Sivri\\Desktop\\filtrelenmişNegatif//" + i + ".png"));
        }

    }
}
