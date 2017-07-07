package asd;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vpt.DoubleImage;
import vpt.util.Tools;

import java.awt.*;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FFT {
    Complex[] transform;
    static boolean recursiveFlag=true;
    static boolean DebugFlag2=false;
    TextArea Panel;
    String ErrorMessages;
    // compute the FFT of x[], assuming its length is a power of 2
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) { throw new RuntimeException("n is not a power of 2"); }

        // fft of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd  = even;  // reuse the array
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + n/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }


    // compute the inverse FFT of x[], assuming its length is a power of 2
    public static Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] y = new Complex[n];

        // take conjugate
        for (int i = 0; i < n; i++) {
            y[i] = x[i].conjugate();
        }

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < n; i++) {
            y[i] = y[i].conjugate();
        }

        // divide by n
        for (int i = 0; i < n; i++) {
            y[i] = y[i].scale(1.0 / n);
        }
        return y;
    }

    // compute the circular convolution of x and y
    public static Complex[] cconvolve(Complex[] x, Complex[] y) {

        // should probably pad x and y with 0s so that they have same length
        // and are powers of 2
        if (x.length != y.length) { throw new RuntimeException("Dimensions don't agree"); }

        int n = x.length;

        // compute FFT of each sequence
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // point-wise multiply
        Complex[] c = new Complex[n];
        for (int i = 0; i < n; i++) {
            c[i] = a[i].times(b[i]);
        }

        // compute inverse FFT
        return ifft(c);
    }


    // compute the linear convolution of x and y
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        Complex ZERO = new Complex(0, 0);

        Complex[] a = new Complex[2*x.length];
        for (int i = 0;        i <   x.length; i++) a[i] = x[i];
        for (int i = x.length; i < 2*x.length; i++) a[i] = ZERO;

        Complex[] b = new Complex[2*y.length];
        for (int i = 0;        i <   y.length; i++) b[i] = y[i];
        for (int i = y.length; i < 2*y.length; i++) b[i] = ZERO;

        return cconvolve(a, b);
    }

    // display an array of Complex numbers to standard output
    public static void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {




    }
    public static BufferedImage setCenter(BufferedImage img) {
        BufferedImage output = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        int xdim = img.getWidth();
        int ydim = img.getHeight();

        int x;
        int y;
        int p;
        for(x = 0; x < xdim / 2; ++x) {
            for(y = 0; y < ydim / 2; ++y) {
                p = img.getRGB(y, x);
                output.setRGB(y + ydim / 2,x + xdim / 2 , p);
            }
        }

        for(x = 0; x < xdim / 2; ++x) {
            for(y = ydim / 2; y < ydim; ++y) {
                p = img.getRGB(y,x);
                output.setRGB(y - ydim / 2,x + xdim / 2,  p);
            }
        }

        for(x = xdim / 2; x < xdim; ++x) {
            for(y = 0; y < ydim / 2; ++y) {
                p = img.getRGB(y,x);
                output.setRGB( y + ydim / 2,x - xdim / 2, p);
            }
        }

        for(x = xdim / 2; x < xdim; ++x) {
            for(y = ydim / 2; y < ydim; ++y) {
                p = img.getRGB(y,x);
                output.setRGB( y - ydim / 2,x - xdim / 2, p);
            }
        }

        return output;
    }
    public  void  FourierWindow(BufferedImage image5,BinaryTree.Node<Vertex> node,BinaryTree.Node<Vertex> root,int mode,String ErrorMessages2,TextArea Panel2) throws Exception {
        Stage window=new Stage();
        window.setTitle("Fourier Window");
        window.setMinHeight(600);
        window.setMinWidth(600);
        window.setMaxWidth(600);
        window.setMaxHeight(600);
        HBox layout1=new HBox(10);
        VBox layout2=new VBox(10);
        VBox layout3=new VBox(10);
        HBox layout4=new HBox(10);
        HBox layout5=new HBox(10);
        HBox layout6=new HBox(10);
        HBox layout7=new HBox(10);

        BufferedImage image = returnTwoSorder(image5);
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
        DoubleImage resultD = new  DoubleImage(img3.getXDim(), img3.getYDim());
        BufferedImage image2=new BufferedImage(img3.getXDim(),img3.getYDim(),BufferedImage.TYPE_3BYTE_BGR);
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

        DoubleImage shiftedMagnitute= (DoubleImage) Tools.shiftOrigin(magnitute);
        final DoubleImage[] shiftedMagnitute2= {(DoubleImage) Tools.shiftOrigin(magnitute)};
        DoubleImage shiftedreal= (DoubleImage)Tools.shiftOrigin(real);
        DoubleImage shiftedImaginary= (DoubleImage)Tools.shiftOrigin(imaginary);

        for(int i=0;i<magnitute.getYDim();++i)
        {
            for(int j=0;j<magnitute.getXDim();++j)
            {
                int c=shiftedMagnitute.getXYByte(j,i);
                if(c>255)
                    c=255;
                if(c<0)
                    c=0;
                image2.setRGB(j,i,new Color(c,c,c).getRGB());
            }
        }

        final ImageView[] FFTScene = {new ImageView(SwingFXUtils.toFXImage(image2, null))};
        ImageView InverseFFTScene=new ImageView(new Image("img/empty2.png"));

        FFTScene[0].setPreserveRatio(true);
        FFTScene[0].setFitHeight(192);
        FFTScene[0].setFitHeight(192);

        InverseFFTScene.setPreserveRatio(true);
        InverseFFTScene.setFitHeight(192);
        InverseFFTScene.setFitHeight(192);

        layout6.getChildren().addAll(new Text("                                                 "),InverseFFTScene);

        layout2.getChildren().addAll(layout1);
        TextField radiusSize=new TextField();
        Text radiusName=new Text();
        Button increment= new Button();

        increment.setMaxHeight(50);
        increment.setMinHeight(50);
        increment.setMinWidth(50);
        increment.setMaxWidth(50);
        increment.setText("+");

        Button decrement= new Button();
        decrement.setMaxHeight(50);
        decrement.setMinHeight(50);
        decrement.setMinWidth(50);
        decrement.setMaxWidth(50);
        decrement.setText("-");

        Button Continue = new Button();
        Continue.setMaxHeight(30);
        Continue.setMinHeight(30);
        Continue.setMinWidth(100);
        Continue.setMaxWidth(100);
        Continue.setText("Continue");

        layout7.getChildren().addAll(new Text("                                              " +
                "                                  "),Continue);

        Button inverseFourier= new Button();
        inverseFourier.setMaxHeight(50);
        inverseFourier.setMinHeight(50);
        inverseFourier.setMinWidth(100);
        inverseFourier.setMaxWidth(100);
        inverseFourier.setText("Inverse Fourier");
        layout5.getChildren().addAll(decrement,increment);
        radiusName.setText("Radius:");
        layout4.getChildren().addAll(radiusName,radiusSize);
        layout3.getChildren().addAll(new Text(""),new Text(""),new Text(""),layout4,new Text(""),layout5,new Text(" "),inverseFourier,layout6
                ,new Text(" "),layout7);
        final int[] radius = new int[1];
        radiusSize.setOnAction(e->{

            radius[0] =Integer.parseInt(radiusSize.getText());

            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) <= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    else
                    if(mode==2)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }

                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));

        });

        increment.setOnAction(e->{
            ++radius[0];
            radiusSize.setText(radius[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) <= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    else
                    if(mode==2)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        decrement.setOnAction(e->{
            --radius[0];
            radiusSize.setText(radius[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) <= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    else
                    if(mode==2)
                    {
                        if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radius[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });

        inverseFourier.setOnAction(e->{
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for (int j = 0; j < shiftedMagnitute2[0].getXDim(); ++j)
                {
                    if(shiftedMagnitute2[0].getXYByte(j,i)==0)
                    {
                        shiftedImaginary.setXYByte(j,i,0);
                        shiftedreal.setXYByte(j,i,0);
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
            Complex[] InverseTransform=FFT.ifft(transfrom);
            for(int i=0;i<imaginary2.getYDim();++i)
            {

                for(int j=0;j<imaginary2.getXDim();++j)
                {
                    resultD.setXYDouble(j,i,InverseTransform[i*imaginary2.getXDim()+j].re());
                }
            }
            for(int i=0;i<imaginary2.getYDim();++i)
            {
                for(int j=0;j<imaginary2.getXDim();++j)
                {
                    int c=resultD.getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    displayResult.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            InverseFFTScene.setImage(SwingFXUtils.toFXImage(displayResult,null));

        });
        Continue.setOnAction(e->{
            node.data.DataImage=displayResult;


            window.close();
            try {
                if(DebugFlag2)
                    displayImageForDebug(displayResult,node.data.getName());
                else
                preOrderTraverse(root,root);
                recursiveFlag=true;
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        });
        ErrorMessages=ErrorMessages2;
        Panel=Panel2;
        layout1.getChildren().addAll(FFTScene[0],layout3);
        Scene scene=new Scene(layout2,600,600);
        window.setScene(scene);

        window.show();


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
    public  ArrayList<BufferedImage> returnFFTImage(BufferedImage image) throws Exception {
        BufferedImage img1;
        ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
        img1 = returnTwoSorder(image);
        Complex[] img = new Complex[img1.getHeight() * img1.getWidth()];
        // original data
        int a = 0;
        for (int i = 0; i < img1.getHeight(); i++) {
            for (int j = 0; j < img1.getWidth(); j++) {
                img[a] = new Complex(img1.getRGB(j, i), 0);
                //System.out.println(a);
                ++a;
            }
        }
        BufferedImage real = new BufferedImage(img1.getWidth(), img1.getHeight(), 3);
        BufferedImage imaginary = new BufferedImage(img1.getWidth(), img1.getHeight(), 3);
        BufferedImage magnitute = new BufferedImage(img1.getWidth(), img1.getHeight(), 3);

        transform = FFT.fft(img);
        for (int x = 0; x < img1.getHeight(); x++) {
            for (int y = 0; y < img1.getWidth(); y++) {

                double rgb = transform[x * img1.getWidth() + y].re() / 2.3283064370807974E-10D;
                transform[x * img1.getWidth() + y]=new Complex(rgb,transform[x * img1.getWidth() + y].im());
                real.setRGB(y, x, (int) rgb);
            }
        }
        for (int x = 0; x < img1.getHeight(); x++) {
            for (int y = 0; y < img1.getWidth(); y++) {

                double rgb = transform[x * img1.getWidth() + y].im() / 2.3283064370807974E-10D;
                transform[x * img1.getWidth() + y]=new Complex(transform[x * img1.getWidth() + y].re(),rgb);
                imaginary.setRGB(y, x, (int) rgb);
            }
        }

        for (int x = 0; x < img1.getHeight(); x++) {
            for (int y = 0; y < img1.getWidth(); y++) {
                double number = transform[x * img1.getWidth() + y].im() * transform[x * img1.getWidth() + y].im() +
                        transform[x * img1.getWidth() + y].re() * transform[x * img1.getWidth() + y].re();
                number = Math.sqrt(number);
                number=number*2.3283064370807974E-10D+0.5D;

                magnitute.setRGB(y, x, (int)number);
            }
        }
       /* Erosion erosion=new Erosion();
        magnitute=erosion.ImplementFilter(magnitute);*/
        magnitute=FFT.setCenter(magnitute);
        images.add(real);
        images.add(imaginary);
        images.add(magnitute);
        return images;
    }
    public void preOrderTraverse(BinaryTree.Node<Vertex> node,BinaryTree.Node<Vertex> root) throws Exception {


        if (node == null) {

        }
        else {


            preOrderTraverse(node.left,root);
            preOrderTraverse(node.right,root);
            if (node.data.debug) {
                BufferedImage input1 = (node.left.data).DataImage;
                BufferedImage input2=null;
                this.DebugFlag2=true;
                recursiveFlag=true;
                if(node.right!=null)
                    input2 = (node.right.data).DataImage;
                if ((node.data).getType().equals("Plus")) {
                    Sum operation = new Sum();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if ((node.data).getType().equals("minus")) {
                    Substract operation = new Substract();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if ((node.data).getType().equals("multiply")) {
                    Multiply operation = new Multiply();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if ((node.data).getType().equals("division")) {
                    Division operation = new Division();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if ((node.data).getType().equals("sobel")) {
                    Sobel operation = new Sobel();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if ((node.data).getType().equals("laplace")) {
                    Laplacian operation = new Laplacian();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if ((node.data).getType().equals("prewitt")) {
                    Prewitt operation = new Prewitt();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if ((node.data).getType().equals("asd.Mean")) {
                    Mean operation = new Mean();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }
                if ((node.data).getType().equals("asd.Median")) {
                    Median operation = new Median();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }
                if ((node.data).getType().equals("Gaussian")) {
                    Gauss operation = new Gauss();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    operation.setSigma((int) node.data.Sigma.getValue());
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if ((node.data).getType().equals("asd.And")) {

                    And operation = new And();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());

                }
                if ((node.data).getType().equals("asd.Or")) {
                    Or operation = new Or();
                    operation.setInput1((node.left.data).DataImage);
                    operation.setInput2((node.right.data).DataImage);
                    (node.data).DataImage=operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());

                }
                if ((node.data).getType().equals("asd.Xor")) {
                    Xor operation = new Xor();
                    operation.setInput1((node.left.data).DataImage);
                    operation.setInput2((node.right.data).DataImage);
                    (node.data).DataImage=operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if ((node.data).getType().equals("not")) {
                    Not operation = new Not();
                    (node.data).DataImage = operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }

                if ((node.data).getType().equals("sharp")) {
                    Sharp operation = new Sharp();
                    operation.Sharp = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.returnSharpingImage(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }

                return;


            }
            if(!this.DebugFlag2){

                System.out.println("recursive");
                if ((node.data).getType().equals("highPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindow(node.left.data.DataImage, node, root, 1, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                    Panel.setText(ErrorMessages);
                    recursiveFlag = false;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("lowPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindow(node.left.data.DataImage, node, root, 2, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                    Panel.setText(ErrorMessages);
                    recursiveFlag = false;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("bandPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindowBand(node.left.data.DataImage, node, root, 1, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                    Panel.setText(ErrorMessages);
                    recursiveFlag = false;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("bandReject") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindowBand(node.left.data.DataImage, node, root, 2, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                    Panel.setText(ErrorMessages);
                    recursiveFlag = false;
                    node.data.implemented = true;
                    return;
                }
                if (recursiveFlag) {
                    if ((node.data).getType().equals("Plus")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Sum operation = new Sum();
                            operation.setInput1((node.left.data).DataImage);
                            operation.setInput2((node.right.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            (node.data).DataImage = operation.returnResult();
                        }
                    }
                    if ((node.data).getType().equals("multiply")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Multiply operation = new Multiply();
                            operation.setInput1((node.left.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            operation.setInput2((node.right.data).DataImage);
                            (node.data).DataImage = operation.returnResult();
                        }
                    }
                    if ((node.data).getType().equals("minus")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Substract operation = new Substract();
                            operation.setInput1((node.left.data).DataImage);
                            operation.setInput2((node.right.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            (node.data).DataImage = operation.returnResult();
                        }
                    }
                    if ((node.data).getType().equals("division")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Division operation = new Division();
                            operation.setInput1((node.left.data).DataImage);
                            operation.setInput2((node.right.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            (node.data).DataImage = operation.returnResult();
                        }
                    }
                    if ((node.data).getType().equals("sobel")) {
                        Sobel operation = new Sobel();
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }
                    if ((node.data).getType().equals("laplace")) {
                        Laplacian operation = new Laplacian();
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }
                    if ((node.data).getType().equals("prewitt")) {
                        Prewitt operation = new Prewitt();
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }
                    if ((node.data).getType().equals("asd.Mean")) {
                        Mean operation = new Mean();
                        operation.KernelSize = (int) node.data.Kernel.getValue();
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }
                    if ((node.data).getType().equals("asd.Median")) {
                        Median operation = new Median();
                        operation.KernelSize = (int) node.data.Kernel.getValue();
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);

                    }
                    if ((node.data).getType().equals("Gaussian")) {
                        Gauss operation = new Gauss();
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                        operation.KernelSize = (int) node.data.Kernel.getValue();
                        operation.setSigma((int) node.data.Sigma.getValue());
                        (node.data).DataImage = operation.ImplementFilter((node.left.data).DataImage);
                    }
                    if ((node.data).getType().equals("asd.And")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            And operation = new And();
                            operation.setInput1((node.left.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            operation.setInput2((node.right.data).DataImage);
                            (node.data).DataImage = operation.returnlogicalResult();
                        }
                    }
                    if ((node.data).getType().equals("asd.Or")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Or operation = new Or();
                            operation.setInput1((node.left.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            operation.setInput2((node.right.data).DataImage);
                            (node.data).DataImage = operation.returnlogicalResult();
                        }
                    }
                    if ((node.data).getType().equals("asd.Xor")) {
                        if ((node.left.data).DataImage.getData().getNumBands() != (node.right.data).DataImage.getData().getNumBands()) {
                            ErrorMessages = ErrorMessages + "- ERROR:" + node.left.data.getName() + " and " + node.right.data.getName() + " have different channel numbers\n";
                            Panel.setText(ErrorMessages);
                        } else {
                            Xor operation = new Xor();
                            operation.setInput1((node.left.data).DataImage);
                            ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                            Panel.setText(ErrorMessages);
                            operation.setInput2((node.right.data).DataImage);
                            (node.data).DataImage = operation.returnlogicalResult();
                        }
                    }

                    if ((node.data).getType().equals("not")) {
                        Not operation = new Not();
                        operation.setInput1((node.left.data).DataImage);
                        (node.data).DataImage = operation.returnlogicalResult();
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }

                    if ((node.data).getType().equals("sharp")) {
                        Sharp operation = new Sharp();
                        operation.Sharp = (int) node.data.Kernel.getValue();
                        (node.data).DataImage = operation.returnSharpingImage((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }
                    if ((node.data).getType().equals("grayscale")) {
                        GrayScale operation = new GrayScale();
                        (node.data).DataImage = operation.convertGrayScale((node.left.data).DataImage);
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!\n";
                        Panel.setText(ErrorMessages);
                    }


                    if ((node.data).getType().equals("Display")) {
                        displayImage(node.data, node.data.getName());
                        ErrorMessages = ErrorMessages + "-" + node.data.getName() + " operator is executed sucessfully!";
                        Panel.setText(ErrorMessages);
                    }
                }

            }
        }

    }
    private void displayImage(Vertex image, String DisplayName) {
        javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane();
        Image img2 = SwingFXUtils.toFXImage(image.fromList.get(0).DataImage, null);
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
    public  void  FourierWindowBand(BufferedImage image5,BinaryTree.Node<Vertex> node,BinaryTree.Node<Vertex> root,int mode,String ErrorMessages2,TextArea Panel2) throws Exception {
        Stage window=new Stage();
        window.setTitle("Fourier Window");
        window.setMinHeight(600);
        window.setMinWidth(600);
        window.setMaxWidth(600);
        window.setMaxHeight(600);
        HBox layout1=new HBox(10);
        VBox layout2=new VBox(10);
        VBox layout3=new VBox(10);
        HBox layout4=new HBox(10);
        HBox layout5=new HBox(10);
        HBox layout6=new HBox(10);
        HBox layout7=new HBox(10);
        HBox layout8=new HBox(10);
        HBox layout9=new HBox(10);

        BufferedImage image = returnTwoSorder(image5);
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
        DoubleImage resultD = new  DoubleImage(img3.getXDim(), img3.getYDim());
        BufferedImage image2=new BufferedImage(img3.getXDim(),img3.getYDim(),BufferedImage.TYPE_3BYTE_BGR);
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

        DoubleImage shiftedMagnitute= (DoubleImage) Tools.shiftOrigin(magnitute);
        final DoubleImage[] shiftedMagnitute2= {(DoubleImage) Tools.shiftOrigin(magnitute)};
        DoubleImage shiftedreal= (DoubleImage)Tools.shiftOrigin(real);
        DoubleImage shiftedImaginary= (DoubleImage)Tools.shiftOrigin(imaginary);

        for(int i=0;i<magnitute.getYDim();++i)
        {
            for(int j=0;j<magnitute.getXDim();++j)
            {
                int c=shiftedMagnitute.getXYByte(j,i);
                if(c>255)
                    c=255;
                if(c<0)
                    c=0;
                image2.setRGB(j,i,new Color(c,c,c).getRGB());
            }
        }

        final ImageView[] FFTScene = {new ImageView(SwingFXUtils.toFXImage(image2, null))};
        ImageView InverseFFTScene=new ImageView(new Image("img/empty2.png"));

        FFTScene[0].setPreserveRatio(true);
        FFTScene[0].setFitHeight(192);
        FFTScene[0].setFitHeight(192);

        InverseFFTScene.setPreserveRatio(true);
        InverseFFTScene.setFitHeight(192);
        InverseFFTScene.setFitHeight(192);

        layout6.getChildren().addAll(new Text("                                                 "),InverseFFTScene);

        layout2.getChildren().addAll(layout1);
        TextField minRadiusSize=new TextField();
        TextField maxRadiusSize=new TextField();
        Text minRadiusName=new Text();
        Text maxRadiusName=new Text();
        Button increment= new Button();

        increment.setMaxHeight(25);
        increment.setMinHeight(25);
        increment.setMinWidth(25);
        increment.setMaxWidth(25);
        increment.setText("+");

        Button decrement= new Button();
        decrement.setMaxHeight(25);
        decrement.setMinHeight(25);
        decrement.setMinWidth(25);
        decrement.setMaxWidth(25);
        decrement.setText("-");

        Button increment2= new Button();

        increment2.setMaxHeight(25);
        increment2.setMinHeight(25);
        increment2.setMinWidth(25);
        increment2.setMaxWidth(25);
        increment2.setText("+");

        Button decrement2= new Button();
        decrement2.setMaxHeight(25);
        decrement2.setMinHeight(25);
        decrement2.setMinWidth(25);
        decrement2.setMaxWidth(25);
        decrement2.setText("-");

        layout9.getChildren().addAll(increment2,decrement2);

        Button Continue = new Button();
        Continue.setMaxHeight(30);
        Continue.setMinHeight(30);
        Continue.setMinWidth(100);
        Continue.setMaxWidth(100);
        Continue.setText("Continue");

        layout7.getChildren().addAll(new Text("                                              " +
                "                                  "),Continue);

        Button inverseFourier= new Button();
        inverseFourier.setMaxHeight(50);
        inverseFourier.setMinHeight(50);
        inverseFourier.setMinWidth(100);
        inverseFourier.setMaxWidth(100);
        inverseFourier.setText("Inverse Fourier");
        layout5.getChildren().addAll(increment,decrement);

        minRadiusName.setText("Min Radius:");
        maxRadiusName.setText("Max Radius:");
        layout8.getChildren().addAll(maxRadiusName,maxRadiusSize,new Text(" "),layout9);
        layout4.getChildren().addAll(minRadiusName,minRadiusSize,new Text("  "),layout5);
        layout3.getChildren().addAll(new Text(""),new Text(""),new Text(""),layout4,new Text(""),layout8,new Text(" "),inverseFourier,layout6
                ,new Text(" "),layout7);
        final int[] radiusMax = new int[1];
        final int[] radiusMin = new int[1];
        System.out.println("Mode:"+mode);
        maxRadiusSize.setOnAction(e->{

            radiusMax[0] =Integer.parseInt(maxRadiusSize.getText());
            System.out.println(radiusMax[0]);
            if(minRadiusSize.getText().equals(""))
            {
                for(int i=0;i<shiftedMagnitute.getYDim();++i)
                {
                    for(int j=0;j<shiftedMagnitute.getXDim();++j)
                    {
                        if(mode==1) {
                            if (Math.sqrt((shiftedMagnitute2[0].getYDim() / 2 - i) * (shiftedMagnitute2[0].getYDim() / 2 - i) +
                                    (shiftedMagnitute2[0].getXDim()/ 2 - j) * (shiftedMagnitute2[0].getXDim()/ 2 - j)) >= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                            else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                        if(mode==2)
                        {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) < radiusMax[0])
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                            else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                    }
                }
            }
            else
            {
                radiusMin[0] =Integer.parseInt(minRadiusSize.getText());
                for(int i=0;i<image2.getHeight();++i)
                {
                    for(int j=0;j<image2.getWidth();++j)
                    {
                        if(mode==1)
                        {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radiusMax[0]
                                    ||Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) < radiusMin[0]) {
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                            } else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                        if(mode==2)
                        {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) < radiusMax[0]
                                    &&Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radiusMin[0])
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                             else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));

                        }


                    }
                }
            }
            for(int i=0;i<magnitute.getYDim();++i)
            {
                for(int j=0;j<magnitute.getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));

        });
        minRadiusSize.setOnAction(e->{

            radiusMin[0] =Integer.parseInt(minRadiusSize.getText());
            System.out.println(radiusMin[0]);
            if(maxRadiusSize.getText().equals("")) {
                for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
                {
                    for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                    {
                        if (mode == 1) {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) <= radiusMin[0]) {
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                            } else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                        if (mode == 2) {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) <= radiusMin[0]) {
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                            } else
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                        }

                    }
                }
            }
            else
            {
                radiusMax[0] =Integer.parseInt(maxRadiusSize.getText());
                for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
                {
                    for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                    {
                        if(mode==1)
                        {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radiusMax[0]
                                    ||Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) < radiusMin[0]) {
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                            } else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                        if(mode==2)
                        {
                            if (Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) < radiusMax[0]
                                    &&Math.sqrt((image2.getHeight() / 2 - i) * (image2.getHeight() / 2 - i) +
                                    (image2.getWidth() / 2 - j) * (image2.getWidth() / 2 - j)) >= radiusMin[0]) {
                                shiftedMagnitute2[0].setXYByte(j, i, 0);
                            } else
                                shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                        }
                    }
                }
            }
            for(int i=0;i<magnitute.getYDim();++i)
            {
                for(int j=0;j<magnitute.getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });

        inverseFourier.setOnAction(e->{

            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for (int j = 0; j < shiftedMagnitute2[0].getXDim(); ++j)
                {
                    if(shiftedMagnitute2[0].getXYByte(j,i)==0)
                    {
                        shiftedImaginary.setXYByte(j,i,0);
                        shiftedreal.setXYByte(j,i,0);
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
            Complex[] InverseTransform=FFT.ifft(transfrom);
            for(int i=0;i<imaginary2.getYDim();++i)
            {

                for(int j=0;j<imaginary2.getXDim();++j)
                {
                    resultD.setXYDouble(j,i,InverseTransform[i*imaginary2.getXDim()+j].re());
                }
            }
            for(int i=0;i<imaginary2.getYDim();++i)
            {
                for(int j=0;j<imaginary2.getXDim();++j)
                {
                    int c=resultD.getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    displayResult.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            InverseFFTScene.setImage(SwingFXUtils.toFXImage(displayResult,null));

        });

        increment.setOnAction(e->{
            ++radiusMin[0];
            minRadiusSize.setText(radiusMin[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMin[0]
                                ||Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                     else
                        shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    if(mode==2)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMin[0]
                                &&Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                     else
                        shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }

                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        decrement.setOnAction(e->{
            --radiusMin[0];
            minRadiusSize.setText(radiusMin[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMin[0]
                                ||Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    if(mode==2)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMin[0]
                                &&Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }

                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        increment2.setOnAction(e->{
            ++radiusMax[0];
            maxRadiusSize.setText(radiusMax[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMin[0]
                                ||Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    if(mode==2)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMin[0]
                                &&Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        increment2.setOnAction(e->{
            ++radiusMax[0];
            maxRadiusSize.setText(radiusMax[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMin[0]
                                ||Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    if(mode==2)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMin[0]
                                &&Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        decrement2.setOnAction(e->{
            --radiusMax[0];
            maxRadiusSize.setText(radiusMax[0]+"");
            for(int i=0;i<image2.getHeight();++i)
            {
                for(int j=0;j<image2.getWidth();++j)
                {
                    if(mode==1)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMin[0]
                                ||Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                    if(mode==2)
                    {
                        if(Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))>= radiusMin[0]
                                &&Math.sqrt((image2.getHeight()/2-i)*(image2.getHeight()/2-i)+
                                (image2.getWidth()/2-j)*(image2.getWidth()/2-j))< radiusMax[0])
                            shiftedMagnitute2[0].setXYByte(j, i, 0);
                        else
                            shiftedMagnitute2[0].setXYByte(j, i, shiftedMagnitute.getXYByte(j,i));
                    }
                }
            }
            for(int i=0;i<shiftedMagnitute2[0].getYDim();++i)
            {
                for(int j=0;j<shiftedMagnitute2[0].getXDim();++j)
                {
                    int c=shiftedMagnitute2[0].getXYByte(j,i);
                    if(c>255)
                        c=255;
                    if(c<0)
                        c=0;
                    image2.setRGB(j,i,new Color(c,c,c).getRGB());
                }
            }
            FFTScene[0].setImage(SwingFXUtils.toFXImage(image2,null));
        });
        Continue.setOnAction(e->{
            node.data.DataImage=displayResult;


            window.close();
            try {
                if(DebugFlag2)
                    displayImageForDebug(displayResult,node.data.getName());
                else
                preOrderTraverse(root,root);
                recursiveFlag=true;
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        });
        ErrorMessages=ErrorMessages2;
        Panel=Panel2;
        layout1.getChildren().addAll(FFTScene[0],layout3);
        Scene scene=new Scene(layout2,600,600);
        window.setScene(scene);

        window.show();


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
