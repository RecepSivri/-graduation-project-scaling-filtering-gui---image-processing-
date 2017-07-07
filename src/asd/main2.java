package asd;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vpt.ByteImage;
import vpt.DoubleImage;
import vpt.IntegerImage;
import vpt.algorithms.display.Display2D;
import vpt.util.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

/**
 * Created by Recep Sivri on 17.04.2017.
 */
public class main2 extends Application {

    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        BufferedImage img1 = null, img2 = null;

        File file = new File("C:\\Users\\Recep Sivri\\Desktop\\test\\lenaTest1.jpg");
        File file2 = new File("C:\\Users\\Recep Sivri\\Desktop\\test\\pompeii.png");

        try {
            img1 = ImageIO.read(file2);
            img2 = ImageIO.read(file2);


        } catch (IOException ex) {

        }
        BufferedImage image = returnTwoSorder(img1);
        DoubleImage img3=new DoubleImage(image.getHeight(),image.getWidth());
        for(int i=0;i<image.getHeight();++i)
        {
            for(int j=0;j<image.getWidth();++j)
            {
                img3.setXYByte(j,i,image.getRaster().getSample(j,i,0));
            }
        }

        Complex[] img = new Complex[img3.getXDim() * img3.getYDim()];
        // original data
        int a = 0;
        for (int i = 0; i < img3.getYDim(); i++) {
            for (int j = 0; j < img3.getXDim(); j++) {
                img[a] = new Complex(img3.getXYDouble(j,i), 0);
                ++a;
            }
        }

        DoubleImage real = new DoubleImage(img3.getXDim(), img3.getYDim());
        DoubleImage imaginary = new DoubleImage(img3.getXDim(), img3.getYDim());
        DoubleImage magnitute = new  DoubleImage(img3.getXDim(), img3.getYDim());
        DoubleImage result = new  DoubleImage(img3.getXDim(), img3.getYDim());
        BufferedImage displayMagnitute=new BufferedImage(img3.getXDim(),img3.getYDim(),BufferedImage.TYPE_3BYTE_BGR);
        BufferedImage displayResult=new BufferedImage(img3.getXDim(),img3.getYDim(),BufferedImage.TYPE_3BYTE_BGR);


        Complex [] transfrom=FFT.fft(img);

        for(int i=0;i<img3.getYDim();++i)
        {
            for(int j=0;j<img3.getXDim();++j)
            {
                imaginary.setXYDouble(j,i,transfrom[i*img3.getXDim()+j].im());
                real.setXYDouble(j,i,transfrom[i*img3.getXDim()+j].re());
            }
        }
        for(int i=0;i<img3.getYDim();++i)
        {
            for(int j=0;j<img3.getXDim();++j)
            {
                magnitute.setXYByte(j,i,(int)(Math.sqrt(imaginary.getXYDouble(j,i)*imaginary.getXYDouble(j,i)+real.getXYDouble(j,i)*
                        real.getXYDouble(j,i))));
            }
        }

        DoubleImage shiftedMagnitute= (DoubleImage)Tools.shiftOrigin(magnitute);
        DoubleImage shiftedreal= (DoubleImage)Tools.shiftOrigin(real);
        DoubleImage shiftedImaginary= (DoubleImage)Tools.shiftOrigin(imaginary);

        for(int i=0;i<img3.getYDim();++i)
        {
            for(int j=0;j<img3.getXDim();++j)
            {
                double distance1=Math.sqrt((img3.getYDim()/2-i)*(img3.getYDim()/2-i)+(img3.getXDim()/2-j)*(img3.getXDim()/2-j));
                if(distance1<250&&distance1>=110)
                {
                    shiftedMagnitute.setXYDouble(j,i,0.0);
                    shiftedreal.setXYDouble(j,i,0.0);
                    shiftedImaginary.setXYDouble(j,i,0.0);
                }

            }
        }
        DoubleImage imaginary2=(DoubleImage)Tools.shiftOrigin(shiftedImaginary);
        DoubleImage real2=(DoubleImage)Tools.shiftOrigin(shiftedreal);

        for(int i=0;i<imaginary2.getYDim();++i)
        {
            for(int j=0;j<imaginary2.getXDim();++j)
            {
                transfrom[i*imaginary2.getXDim()+j].setIm(imaginary2.getXYDouble(j,i));
                transfrom[i*imaginary2.getXDim()+j].setRe(real2.getXYDouble(j,i));
            }
        }

        for(int i=0;i<imaginary2.getYDim();++i)
        {
            for(int j=0;j<imaginary2.getXDim();++j)
            {
                int c=shiftedMagnitute.getXYByte(j,i);
                if(c>255)
                    c=255;
                if(c<0)
                    c=0;
                displayMagnitute.setRGB(j,i,new Color(c,c,c).getRGB());
            }
        }
        Complex[] InverseTransform=FFT.ifft(transfrom);
        for(int i=0;i<imaginary2.getYDim();++i)
        {

            for(int j=0;j<imaginary2.getXDim();++j)
            {
                result.setXYDouble(j,i,InverseTransform[i*imaginary2.getXDim()+j].re());
            }
        }
        for(int i=0;i<imaginary2.getYDim();++i)
        {
            for(int j=0;j<imaginary2.getXDim();++j)
            {
                int c=result.getXYByte(j,i);
                if(c>255)
                    c=255;
                if(c<0)
                    c=0;
                displayResult.setRGB(j,i,new Color(c,c,c).getRGB());
            }
        }
        displayImageForDebug(displayMagnitute,"magnitute");
        displayImageForDebug(displayResult,"result");
        /*Display2D.invoke(shiftedMagnitute);
        /*Display2D.invoke(real2,"real");
        Display2D.invoke(imaginary2,"imaginary2");
        Display2D.invoke(result,"result");*/

    }
    public  BufferedImage returnTwoSorder(BufferedImage image)
    {

        int max;
        if(image.getWidth()>image.getHeight())
            max=image.getWidth();
        else
            max=image.getHeight();

        int coefficient=2;

        while(coefficient<max)
        {
            coefficient=coefficient*2;
        }
        //System.out.println("coefficient:"+coefficient+"   max:"+max+"  width:"+image.getWidth()+"  height:"+image.getHeight());

        BufferedImage buffImg = new BufferedImage(coefficient, coefficient, BufferedImage.TYPE_3BYTE_BGR);

        int row=image.getWidth();
        int row2=buffImg.getWidth();
        int high=image.getHeight();
        int high2=buffImg.getHeight();


        for(int i=0;i<high2;++i)
        {
            for (int j =0; j < row2; ++j)
            {
                buffImg.setRGB(j,i,-16777216);
            }
        }

        int a=0,b=0;
        for(int i=(high2-high)/2;i<(high2-high)/2+high;++i)
        {
            for(int j=(row2-row)/2;j<row+(row2-row)/2;++j)
            {
                buffImg.setRGB(j,i,image.getRGB(b,a));
                ++b;
            }
            ++a;
            b=0;
        }
        return buffImg;

    }
    private void displayImageForDebug(BufferedImage img3, String DisplayName) {
        javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane();
        Image img2 = SwingFXUtils.toFXImage(img3, null);
        ImageView img = new ImageView(img2);
        scroll.setContent(img);
        Stage window = new Stage();
        HBox layout = new HBox();
        layout.getChildren().add(img);
        Scene scene = new Scene(scroll);
        window.setScene(scene);
        window.setTitle(DisplayName);
        window.show();
    }

}
