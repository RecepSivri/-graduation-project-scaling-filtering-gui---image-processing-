package asd;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Recep Sivri on 2.04.2017.
 */
public class EventHandlers {

    boolean DeleteFlag=false;

    ArrayList<ReferencedLine> Lines=new ArrayList<ReferencedLine>();
    boolean lineFlag;

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    ArrayList<Vertex> contents=new ArrayList<Vertex>();

    ImageView KernelImage=new ImageView(new Image("img/Kernel.png"));
    ImageView SigmaImage=new ImageView(new Image("img/sigma.png"));
    ImageView sharpImage=new ImageView(new Image("img/pencil.png"));
    Image plus2=new Image("img/plus2.png");
    Image minus2=new Image("img/minus2.png");
    Image median2=new Image("img/median2.png");
    Image gauss2=new Image("img/gaussian2.png");
    Image mean2=new Image("img/mean2.png");
    Image multiply2=new Image("img/multiply2.png");
    Image division2=new Image("img/division2.png");
    Image sobel2=new Image("img/sobel2.png");
    Image laplace2=new Image("img/laplace2.png");
    Image prewitt2=new Image("img/prewitt2.png");
    Image shapring2=new Image("img/sharp2.png");
    Image unshapring2=new Image("img/unsharp2.png");
    Image highpass2=new Image("img/highPass2.png");
    Image lowpass2=new Image("img/lowPass2.png");
    Image bandpass2=new Image("img/bandPass2.png");
    Image bandreject2=new Image("img/bandReject2.png");
    Image and2=new Image("img/and2.png");
    Image or2=new Image("img/or2.png");
    Image xor2=new Image("img/xor2.png");
    Image not2=new Image("img/not2.png");
    Image open2=new Image("img/open2.png");
    Image display2=new Image("img/display2.png");

    Image plus=new Image("img/plus.png");
    Image minus=new Image("img/minus.png");
    Image median=new Image("img/median.png");
    Image gauss=new Image("img/gaussian.png");
    Image mean=new Image("img/mean.png");
    Image multiply=new Image("img/multiply.png");
    Image division=new Image("img/division.png");
    Image sobel=new Image("img/sobel.png");
    Image laplace=new Image("img/laplace.png");
    Image prewitt=new Image("img/prewitt.png");
    Image shapring=new Image("img/sharp.png");
    Image unshapring=new Image("img/unsharp.png");
    Image highpass=new Image("img/highPass.png");
    Image lowpass=new Image("img/lowPass.png");
    Image bandpass=new Image("img/bandPass.png");
    Image bandreject=new Image("img/bandReject.png");
    Image and=new Image("img/and.png");
    Image or=new Image("img/or.png");
    Image xor=new Image("img/xor.png");
    Image not=new Image("img/not.png");
    Image open=new Image("img/open.png");
    Image display=new Image("img/display.png");

    //Layouts
    AnchorPane Bottom=new AnchorPane();
    Button SaveImage;
    TextField FilePath;

    public void setSaveImage(Button saveImage) {
        SaveImage = saveImage;
    }

    public void setFilePath(TextField filePath) {
        FilePath = filePath;
    }

    public void setMenuForDefault(ContextMenu menuForDefault) {
        this.menuForDefault = menuForDefault;
    }

    //Context for Open
    ContextMenu menuForOpen;
    ContextMenu menuForDefault;
    MenuItem Open;
    MenuItem removeConnection;
    MenuItem removeConnection2;
    MenuItem removeItem;
    MenuItem removeItem2;


    StackPane ElementStack=new StackPane();
    BorderPane StatusBox=new BorderPane();

    HBox NameBoxHeader=new HBox();

    HBox FromBoxHeader=new HBox();
    HBox ToBoxHeader=new HBox();
    HBox KernelBoxHeader=new HBox();

    HBox SigmaBoxHeader=new HBox();
    BorderPane rightLayout=new BorderPane();

    Stack<Vertex> linesEdge=new Stack<Vertex>();
    Stack<Vertex> deleteVertex=new Stack<Vertex>();
    boolean DebugFlag=false;

    public void setDebugFlag(boolean debugFlag) {
        DebugFlag = debugFlag;
    }

    public void setRemoveItem(MenuItem removeItem) {
        this.removeItem = removeItem;
    }

    public void setRemoveItem2(MenuItem removeItem2) {
        this.removeItem2 = removeItem2;
    }

    public void setRemoveConnection2(MenuItem removeConnection2) {
        this.removeConnection2 = removeConnection2;
    }

    public void setMenuForOpen(ContextMenu menuForOpen) {
        this.menuForOpen = menuForOpen;
    }

    public void setOpen(MenuItem open) {
        Open = open;
    }

    public void setRemoveConnection(MenuItem removeConnection) {
        this.removeConnection = removeConnection;
    }



    public void setDeleteFlag(boolean deleteFlag) {
        DeleteFlag = deleteFlag;
    }

    public void setLines(ArrayList<ReferencedLine> lines) {
        Lines = lines;
    }

    public void setLineFlag(boolean lineFlag) {
        this.lineFlag = lineFlag;
    }

    public void setOrgSceneX(double orgSceneX) {
        this.orgSceneX = orgSceneX;
    }

    public void setOrgSceneY(double orgSceneY) {
        this.orgSceneY = orgSceneY;
    }

    public void setOrgTranslateX(double orgTranslateX) {
        this.orgTranslateX = orgTranslateX;
    }

    public void setOrgTranslateY(double orgTranslateY) {
        this.orgTranslateY = orgTranslateY;
    }

    public void setContents(ArrayList<Vertex> contents) {
        this.contents = contents;
    }

    public void setKernelImage(ImageView kernelImage) {
        KernelImage = kernelImage;
    }

    public void setSigmaImage(ImageView sigmaImage) {
        SigmaImage = sigmaImage;
    }

    public void setBottom(AnchorPane bottom) {
        Bottom = bottom;
    }

    public void setElementStack(StackPane elementStack) {
        ElementStack = elementStack;
    }

    public void setStatusBox(BorderPane statusBox) {
        StatusBox = statusBox;
    }

    public void setNameBoxHeader(HBox nameBoxHeader) {
        NameBoxHeader = nameBoxHeader;
    }

    public void setFromBoxHeader(HBox fromBoxHeader) {
        FromBoxHeader = fromBoxHeader;
    }

    public void setToBoxHeader(HBox toBoxHeader) {
        ToBoxHeader = toBoxHeader;
    }

    public void setKernelBoxHeader(HBox kernelBoxHeader) {
        KernelBoxHeader = kernelBoxHeader;
    }

    public void setSigmaBoxHeader(HBox sigmaBoxHeader) {
        SigmaBoxHeader = sigmaBoxHeader;
    }

    public void setRightLayout(BorderPane rightLayout) {
        this.rightLayout = rightLayout;
    }

    public void setLinesEdge(Stack<Vertex> linesEdge) {
        this.linesEdge = linesEdge;
    }

    public void setDeleteVertex(Stack<Vertex> deleteVertex) {
        this.deleteVertex = deleteVertex;
    }

    public void setElementsOnClicked(EventHandler<MouseEvent> elementsOnClicked) {
        this.elementsOnClicked = elementsOnClicked;
    }

    public void setElementsOnDragged(EventHandler<MouseEvent> elementsOnDragged) {
        this.elementsOnDragged = elementsOnDragged;
    }

    EventHandler<MouseEvent> elementsOnClicked =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    Vertex obj2=((Vertex)(t.getSource()));

                    FromBoxHeader.getChildren().clear();
                    ToBoxHeader.getChildren().clear();
                    FromBoxHeader.getChildren().addAll(new Text("From: "), obj2.from);
                    ToBoxHeader.getChildren().addAll(new Text("To: "), obj2.to);
                    NameBoxHeader.getChildren().clear();
                    NameBoxHeader.getChildren().addAll(new Text("Name: " + ((Vertex) (t.getSource())).getName()));
                    KernelBoxHeader.getChildren().clear();
                    SigmaBoxHeader.getChildren().clear();
                    rightLayout.getChildren().clear();

                    if(obj2.getType().equals("Open"))
                    {

                        ImageView img=new ImageView(obj2.Data);
                        img.setPreserveRatio(true);
                        img.setFitHeight(64);
                        img.setFitHeight(64);
                        rightLayout.setRight(img);
                        StatusBox.setRight(rightLayout);
                    }

                    if(obj2.getFilterType().equals("Linear")&&!obj2.getType().equals("sobel")
                            &&!obj2.getType().equals("laplace")&&!obj2.getType().equals("prewitt")&&!obj2.getType().equals("highPass")
                            &&!obj2.getType().equals("lowPass")&&!obj2.getType().equals("bandPass")
                            &&!obj2.getType().equals("bandReject")|| obj2.getFilterType().equals("NonLinear")) {
                        obj2.setKernel();
                        if(obj2.getType().equals("sharp")||obj2.getType().equals("grayscale"))
                             KernelBoxHeader.getChildren().addAll(sharpImage, new Text(" "), obj2.Kernel);
                        else
                             KernelBoxHeader.getChildren().addAll(KernelImage, new Text(" "), obj2.Kernel);


                    }
                    if(obj2.getType().equals("Display"))
                    {
                        System.out.println();
                    }


                    if(obj2.getType().equals("Gaussian"))
                    {
                        obj2.setSigma();
                        SigmaBoxHeader.getChildren().addAll(SigmaImage, new Text(" "), obj2.Sigma);
                        obj2.Sigma.valueProperty().addListener(e-> {
                            obj2.SigmaValue = obj2.Sigma.getValue();
                        });
                    }

                    if(((Vertex)t.getSource()).debug==true)
                    {
                        ((Vertex)t.getSource()).debug=false;
                        if(((Vertex)t.getSource()).getType().equals("Plus"))
                            ((Vertex)t.getSource()).setImage(plus);
                        else
                        if(((Vertex)t.getSource()).getType().equals("minus"))
                            ((Vertex)t.getSource()).setImage(minus);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Median"))
                            ((Vertex)t.getSource()).setImage(median);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Mean"))
                            ((Vertex)t.getSource()).setImage(mean);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Open"))
                            ((Vertex)t.getSource()).setImage(open);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Display"))
                            ((Vertex)t.getSource()).setImage(display);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Gaussian"))
                            ((Vertex)t.getSource()).setImage(gauss);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.And"))
                            ((Vertex)t.getSource()).setImage(and);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Or"))
                            ((Vertex)t.getSource()).setImage(or);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Xor"))
                            ((Vertex)t.getSource()).setImage(xor);
                        else
                        if(((Vertex)t.getSource()).getType().equals("not"))
                            ((Vertex)t.getSource()).setImage(not);
                        else
                        if(((Vertex)t.getSource()).getType().equals("multiply"))
                            ((Vertex)t.getSource()).setImage(multiply);
                        else
                        if(((Vertex)t.getSource()).getType().equals("division"))
                            ((Vertex)t.getSource()).setImage(division);
                        else
                        if(((Vertex)t.getSource()).getType().equals("sobel"))
                            ((Vertex)t.getSource()).setImage(sobel);
                        else
                        if(((Vertex)t.getSource()).getType().equals("laplace"))
                            ((Vertex)t.getSource()).setImage(laplace);
                        else
                        if(((Vertex)t.getSource()).getType().equals("prewitt"))
                            ((Vertex)t.getSource()).setImage(prewitt);
                        else
                        if(((Vertex)t.getSource()).getType().equals("highPass"))
                            ((Vertex)t.getSource()).setImage(highpass);
                        else
                        if(((Vertex)t.getSource()).getType().equals("lowPass"))
                            ((Vertex)t.getSource()).setImage(lowpass);
                        else
                        if(((Vertex)t.getSource()).getType().equals("bandPass"))
                            ((Vertex)t.getSource()).setImage(bandpass);
                        else
                        if(((Vertex)t.getSource()).getType().equals("bandReject"))
                            ((Vertex)t.getSource()).setImage(bandreject2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("sharp"))
                            ((Vertex)t.getSource()).setImage(shapring);
                        else
                        if(((Vertex)t.getSource()).getType().equals("unsharp"))
                            ((Vertex)t.getSource()).setImage(unshapring);

                    }

                    if(DebugFlag)
                    {
                        ((Vertex)t.getSource()).debug=true;
                        if(((Vertex)t.getSource()).getType().equals("Plus"))
                            ((Vertex)t.getSource()).setImage(plus2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("minus"))
                            ((Vertex)t.getSource()).setImage(minus2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Median"))
                            ((Vertex)t.getSource()).setImage(median2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Mean"))
                            ((Vertex)t.getSource()).setImage(mean2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Open"))
                            ((Vertex)t.getSource()).setImage(open2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Display"))
                            ((Vertex)t.getSource()).setImage(display2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("Gaussian"))
                            ((Vertex)t.getSource()).setImage(gauss2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.And"))
                            ((Vertex)t.getSource()).setImage(and2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Or"))
                            ((Vertex)t.getSource()).setImage(or2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("asd.Xor"))
                            ((Vertex)t.getSource()).setImage(xor2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("not"))
                            ((Vertex)t.getSource()).setImage(not2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("multiply"))
                            ((Vertex)t.getSource()).setImage(multiply2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("division"))
                            ((Vertex)t.getSource()).setImage(division2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("sobel"))
                            ((Vertex)t.getSource()).setImage(sobel2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("laplace"))
                            ((Vertex)t.getSource()).setImage(laplace2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("prewitt"))
                            ((Vertex)t.getSource()).setImage(prewitt2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("highPass"))
                            ((Vertex)t.getSource()).setImage(highpass2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("lowPass"))
                            ((Vertex)t.getSource()).setImage(lowpass2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("bandPass"))
                            ((Vertex)t.getSource()).setImage(bandpass2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("bandReject"))
                            ((Vertex)t.getSource()).setImage(bandreject2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("sharp"))
                            ((Vertex)t.getSource()).setImage(shapring2);
                        else
                        if(((Vertex)t.getSource()).getType().equals("unsharp"))
                            ((Vertex)t.getSource()).setImage(unshapring2);

                        DebugFlag=false;
                    }
                    if(DeleteFlag)
                    {
                        DeleteFlag=false;
                        Delete(t);
                    }
                    else
                    {
                        orgSceneX = t.getSceneX();
                        orgSceneY = t.getSceneY();
                        orgTranslateX = ((Vertex) (t.getSource())).getTranslateX();
                        orgTranslateY = ((Vertex) (t.getSource())).getTranslateY();
                    }
                }
            };

    EventHandler<MouseEvent> elementsOnDragged =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                    ((ImageView)(t.getSource())).setTranslateY(newTranslateY);

                    for(int i=0;i<Lines.size();++i)
                    {
                        if(Lines.get(i).from.equals((Vertex)(t.getSource())))
                        {
                            double high2 = ((Vertex) t.getSource()).getTranslateY() + ((Vertex) t.getSource()).getImage().getHeight() / 2;
                            double row2 = ((Vertex) t.getSource()).getTranslateX() + ((Vertex) t.getSource()).getImage().getWidth() / 2;

                            double high = Lines.get(i).to.getTranslateY() + Lines.get(i).to.getImage().getHeight() / 2;
                            double row = Lines.get(i).to.getTranslateX() + Lines.get(i).to.getImage().getWidth() / 2;

                            ReferencedLine line1=new ReferencedLine();

                            line1.to=Lines.get(i).to;
                            line1.from=((Vertex) t.getSource());

                            line1.setStartX(row);
                            line1.setStartY(high);
                            line1.setEndX(row2);
                            line1.setEndY(high2);
                            line1.setStroke(Color.BLACK);

                            double dx,dy;
                            dx=row-row2;
                            dy=high-high2;
                            double slope=dy/dx;
                            double angle=Math.atan2(dy,dx);
                            double angle2=Math.atan2(-1*dx,dy);

                            line1.polygon.getPoints().addAll(new Double[]{
                                    row2+10*Math.cos(angle), high2+10*Math.sin(angle),
                                    row2+10*Math.cos(angle2), high2+10*Math.sin(angle2),
                                    row2-10*Math.cos(angle2),  high2-10*Math.sin(angle2)});

                            Lines.add(line1);
                            Bottom.getChildren().addAll(line1,line1.polygon);


                            Bottom.getChildren().remove(Lines.get(i).polygon);
                            Bottom.getChildren().remove(Lines.get(i));
                            Lines.remove(Lines.get(i));
                        }
                        if(Lines.get(i).to.equals((Vertex)(t.getSource())))
                        {
                            double high2 = ((Vertex) t.getSource()).getTranslateY() + ((Vertex) t.getSource()).getImage().getHeight() / 2;
                            double row2 = ((Vertex) t.getSource()).getTranslateX() + ((Vertex) t.getSource()).getImage().getWidth() / 2;

                            double high = Lines.get(i).from.getTranslateY() + Lines.get(i).from.getImage().getHeight() / 2;
                            double row = Lines.get(i).from.getTranslateX() + Lines.get(i).from.getImage().getWidth() / 2;

                            ReferencedLine line1=new ReferencedLine();

                            line1.from=Lines.get(i).from;
                            line1.to=((Vertex) t.getSource());

                            line1.setStartX(row);
                            line1.setStartY(high);
                            line1.setEndX(row2);
                            line1.setEndY(high2);
                            line1.setStroke(Color.BLACK);
                            double dx,dy;
                            dx=row2-row;
                            dy=high2-high;
                            double slope=dy/dx;
                            double angle=Math.atan2(dy,dx);
                            double angle2=Math.atan2(-1*dx,dy);

                            line1.polygon.getPoints().addAll(new Double[]{
                                    row2+10*Math.cos(angle), high2+10*Math.sin(angle),
                                    row2+10*Math.cos(angle2), high2+10*Math.sin(angle2),
                                    row2-10*Math.cos(angle2),  high2-10*Math.sin(angle2)});
                            Lines.add(line1);

                            Bottom.getChildren().addAll(line1,line1.polygon);
                            Bottom.getChildren().remove(Lines.get(i).polygon);
                            Bottom.getChildren().remove(Lines.get(i));
                            Lines.remove(Lines.get(i));
                        }
                    }
                }
            };

    public void lineHandler()
    {
        for(int a=0;a<contents.size();++a) {
            contents.get(a).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton() == MouseButton.SECONDARY)
                    {
                        if (((Vertex) mouseEvent.getSource()).getType().equals("Open"))
                        {
                            Open.setOnAction(e -> {
                                FileChooser fileChooser = new FileChooser();

                                //Set extension filter
                                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

                                //Show open file dialog
                                File file = fileChooser.showOpenDialog(null);

                                try {
                                    BufferedImage bufferedImage = ImageIO.read(file);
                                    ((Vertex) mouseEvent.getSource()).DataImage=bufferedImage;
                                    ((Vertex) mouseEvent.getSource()).Data = SwingFXUtils.toFXImage(bufferedImage, null);

                                } catch (IOException ex) {

                                }



                            });

                            removeConnection.setOnAction(e->{
                                deleteVertex.add((Vertex)mouseEvent.getSource());
                                Vertex content;
                                if(deleteVertex.size() == 2) {
                                    content = (Vertex)deleteVertex.pop();
                                    Vertex high2 = (Vertex)deleteVertex.pop();
                                    for(int i = 0; i < Lines.size(); ++i) {
                                        if(((ReferencedLine)Lines.get(i)).to.equals(content) && ((ReferencedLine)Lines.get(i)).from.equals(high2)) {
                                            Bottom.getChildren().remove(((ReferencedLine)Lines.get(i)).polygon);
                                            Bottom.getChildren().remove(Lines.get(i));
                                            Lines.remove(Lines.get(i));
                                            high2.to.getItems().remove(content.getName());
                                            content.from.getItems().remove(high2.getName());
                                            content.fromList.remove(high2);
                                            high2.toList.remove(content);
                                        }
                                        else
                                        if(((ReferencedLine)Lines.get(i)).to.equals(high2) && ((ReferencedLine)Lines.get(i)).from.equals(content)) {
                                            Bottom.getChildren().remove(((ReferencedLine)Lines.get(i)).polygon);
                                            Bottom.getChildren().remove(Lines.get(i));
                                            Lines.remove(Lines.get(i));
                                            high2.from.getItems().remove(content.getName());
                                            content.to.getItems().remove(high2.getName());
                                            content.toList.remove(high2);
                                        }
                                    }
                                }
                            });

                            removeItem2.setOnAction(e->{
                                DeleteFlag=false;
                                Delete(mouseEvent);
                                System.out.println("Deneme:    "+((Vertex)mouseEvent.getSource()).Name);
                            });
                            menuForOpen.show(((Vertex) mouseEvent.getSource()), mouseEvent.getScreenX(), mouseEvent.getScreenY());
                        }
                        else
                        {
                            removeConnection2.setOnAction(e->{
                                deleteVertex.add((Vertex)mouseEvent.getSource());
                                Vertex content;
                                if(deleteVertex.size() == 2) {
                                    content = (Vertex)deleteVertex.pop();
                                    Vertex high2 = (Vertex)deleteVertex.pop();

                                    for(int i = 0; i < Lines.size(); ++i) {
                                        if(((ReferencedLine)Lines.get(i)).to.equals(content) && ((ReferencedLine)Lines.get(i)).from.equals(high2)) {
                                            Bottom.getChildren().remove(((ReferencedLine)Lines.get(i)).polygon);
                                            Bottom.getChildren().remove(Lines.get(i));
                                            Lines.remove(Lines.get(i));
                                            high2.to.getItems().remove(content.getName());
                                            content.from.getItems().remove(high2.getName());
                                            content.fromList.remove(high2);
                                            high2.toList.remove(content);
                                        }
                                        else
                                        if(((ReferencedLine)Lines.get(i)).to.equals(high2) && ((ReferencedLine)Lines.get(i)).from.equals(content)) {
                                            Bottom.getChildren().remove(((ReferencedLine)Lines.get(i)).polygon);
                                            Bottom.getChildren().remove(Lines.get(i));
                                            Lines.remove(Lines.get(i));
                                            high2.from.getItems().remove(content.getName());
                                            content.to.getItems().remove(high2.getName());
                                            content.toList.remove(high2);
                                        }
                                    }
                                }

                            });
                            removeItem.setOnAction(e->{
                                DeleteFlag=false;
                                Delete(mouseEvent);
                                System.out.println("Deneme:    "+((Vertex)mouseEvent.getSource()).Name);
                            });

                            menuForDefault.show(((Vertex) mouseEvent.getSource()), mouseEvent.getScreenX(), mouseEvent.getScreenY());
                        }
                    }
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

                        if (mouseEvent.getClickCount() == 2) {
                            ((Vertex) mouseEvent.getSource()).value=false;


                            linesEdge.add((Vertex) mouseEvent.getSource());
                            System.out.println("eleman alındı");
                        }
                        if (mouseEvent.getClickCount() == 1) {
                            Vertex content;
                            lineFlag=true;
                            if (!linesEdge.empty())
                            {
                                content = linesEdge.peek();

                                if (content.value == false) {
                                    System.out.println("tamamdır:D");

                                    double high2 = ((Vertex) mouseEvent.getSource()).getTranslateY() + ((Vertex) mouseEvent.getSource()).getImage().getHeight() / 2;
                                    double row2 = ((Vertex) mouseEvent.getSource()).getTranslateX() + ((Vertex) mouseEvent.getSource()).getImage().getWidth() / 2;

                                    double high = content.getTranslateY() + content.getImage().getHeight() / 2;
                                    double row = content.getTranslateX() + content.getImage().getWidth() / 2;
                                    ReferencedLine line1 = new ReferencedLine();

                                    line1.from = content;
                                    line1.to = ((Vertex) mouseEvent.getSource());
                                    for(int k=0;k<Lines.size();++k)
                                    {
                                        if(Lines.get(k).from.equals(line1.from)&&Lines.get(k).to.equals(line1.to))
                                            lineFlag=false;
                                    }
                                    if(lineFlag) {
                                        line1.setStartX(row);
                                        line1.setStartY(high);
                                        line1.setEndX(row2);
                                        line1.setEndY(high2);

                                        line1.setStroke(Color.BLACK);
                                        Lines.add(line1);
                                        content.addAdjectents(((Vertex) mouseEvent.getSource()));
                                        double dx, dy;
                                        dx = row2 - row;
                                        dy = high2 - high;
                                        double slope = dy / dx;
                                        double angle = Math.atan2(dy, dx);
                                        double angle2 = Math.atan2(-1 * dx, dy);

                                        line1.polygon.getPoints().addAll(new Double[]{
                                                row2 + 10 * Math.cos(angle), high2 + 10 * Math.sin(angle),
                                                row2 + 10 * Math.cos(angle2), high2 + 10 * Math.sin(angle2),
                                                row2 - 10 * Math.cos(angle2), high2 - 10 * Math.sin(angle2)});

                                        Bottom.getChildren().addAll(line1, line1.polygon);
                                    }
                                    content.value = true;


                                }
                            }
                        }
                    }
                }
            });
        }
    }
    public void Delete(MouseEvent t)
    {
        int i,j;
        System.out.println("Delete");
        ElementStack.getChildren().remove( (t.getSource()));
        Vertex obj=(Vertex)(t.getSource());

        for(j=0;j<Lines.size();++j) {

            if (Lines.get(j).from.equals(obj)) {
                System.out.println(Lines.get(j).to.getName());
                Bottom.getChildren().remove(Lines.get(j).polygon);
                Bottom.getChildren().remove(Lines.get(j));
                if(Lines.remove(Lines.get(j)))
                    --j;
            }
        }
        for(j=0;j<Lines.size();++j) {

            if (Lines.get(j).to.equals(obj)) {
                System.out.println(Lines.get(j).to.getName());
                Bottom.getChildren().remove(Lines.get(j).polygon);
                Bottom.getChildren().remove(Lines.get(j));
                if(Lines.remove(Lines.get(j)))
                    --j;
            }
        }

        for(int a=0;a<contents.size();++a)
        {
            contents.get(a).from.getItems().remove(obj.getName());
            contents.get(a).to.getItems().remove(obj.getName());
            contents.get(a).toList.remove(obj);
            contents.get(a).fromList.remove(obj);
        }

        contents.remove(obj);
    }
}
