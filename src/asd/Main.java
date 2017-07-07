package asd;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Debug;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created by recep on 3/22/17.
 */
public class Main extends Application {

    boolean DeleteFlag = false;

    int counter = 0;
    //Menu objects
    MenuBar menuBar;
    Menu MenuFile;
    Menu Edit;
    Menu About;
    Menu Help;
    Menu View;
    Menu Settings;

    MenuItem New;
    MenuItem LoadImage;
    MenuItem SaveImage;
    MenuItem LoadProject;
    MenuItem SaveProject;
    MenuItem Exit;
    MenuItem Project;

    ListView<Button> FilterListView = new ListView<Button>();

    //Context for Open
    ContextMenu menuForOpen = new ContextMenu();
    MenuItem Open = new MenuItem("Open");
    MenuItem removeConnection = new MenuItem("Remove Connection");
    MenuItem removeConnection2 = new MenuItem("Remove Connection");
    MenuItem removeItem = new MenuItem("Remove Item");
    MenuItem removeItem2 = new MenuItem("Remove Item");
    ContextMenu menuForDefault = new ContextMenu();


    //images
    Image plus = new Image("img/plus.png");
    Image minus = new Image("img/minus.png");
    Image median = new Image("img/median.png");
    Image mean = new Image("img/mean.png");
    Image open = new Image("img/open.png");
    Image display = new Image("img/display.png");
    Image gaussian = new Image("img/gaussian.png");
    Image delete = new Image("img/delete.png");
    Image and = new Image("img/and.png");
    Image or = new Image("img/or.png");
    Image xor = new Image("img/xor.png");
    Image not = new Image("img/not.png");
    Image clean = new Image("img/clean.png");
    Image save = new Image("img/save.png");
    Image load = new Image("img/load.png");
    Image flag = new Image("img/flag.png");
    Image run = new Image("img/run.png");
    Image tool = new Image("img/tool.png");
    Image multiply = new Image("img/multiply.png");
    Image division = new Image("img/division.png");
    Image sobel = new Image("img/sobel.png");
    Image laplace = new Image("img/laplace.png");
    Image prewitt = new Image("img/prewitt.png");
    Image highPass = new Image("img/highPass.png");
    Image lowPass = new Image("img/lowPass.png");
    Image bandPass = new Image("img/bandPass.png");
    Image bandReject = new Image("img/bandReject.png");
    Image debugPlay = new Image("img/debugPlay.png");
    Image stop = new Image("img/stop.png");
    Image pause = new Image("img/pause.png");
    Image debugFlag = new Image("img/debugFlag.png");
    Image directory = new Image("img/directory.png");
    Image saveLog = new Image("img/savelog.png");
    Image cleanDebug = new Image("img/cleanDebug.png");
    Image close = new Image("img/close.png");
    Image logger = new Image("img/logger.png");
    Image sharp = new Image("img/sharp.png");
    Image unsharp = new Image("img/unsharp.png");
    Image grayscale = new Image("img/grayscale.png");

    ArrayList<ReferencedLine> Lines = new ArrayList<ReferencedLine>();

    boolean lineFlag;


    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    ArrayList<Vertex> contents = new ArrayList<Vertex>();

    ImageView KernelImage = new ImageView(new Image("img/Kernel.png"));
    ImageView SigmaImage = new ImageView(new Image("img/sigma.png"));

    //Layouts
    HBox layout = new HBox();
    SerializableAnchorPane Bottom = new SerializableAnchorPane();
    VBox Top = new VBox();
    BorderPane BorderPane = new BorderPane();
    BorderPane BorderPane2 = new BorderPane();
    VBox FilterBox = new VBox();
    VBox ToolBox = new VBox();
    VBox Left = new VBox();

    StackPane ElementStack = new StackPane();
    HBox TextHorizontal = new HBox();
    HBox TextHorizontal2 = new HBox();
    BorderPane StatusBox = new BorderPane();

    HBox StatusBoxHeader = new HBox();
    HBox NameBoxHeader = new HBox();

    HBox FromBoxHeader = new HBox();
    HBox ToBoxHeader = new HBox();
    HBox KernelBoxHeader = new HBox();
    HBox SigmaBoxHeader = new HBox();
    VBox BottomStatus = new VBox(5);
    BorderPane rightLayout = new BorderPane();


    //active user interface elements
    Stack<Vertex> linesEdge = new Stack<Vertex>();
    Stack<Vertex> deleteVertex = new Stack<Vertex>();
    ArrayList<Vertex> files = new ArrayList<Vertex>();

    //Buttons
    Button PlusButton = new Button();
    Button MinusButton = new Button();
    Button MedianButton = new Button();
    Button MeanButton = new Button();
    Button OpenButton = new Button();
    Button DisplayButton = new Button();
    Button GaussianButton = new Button();
    Button DeleteButton = new Button();
    Button AndButton = new Button();
    Button OrButton = new Button();
    Button XorButton = new Button();
    Button NotButton = new Button();
    Button CleanButton = new Button();
    Button SaveButton = new Button();
    Button LoadButton = new Button();
    Button FlagButton = new Button();
    Button RunButton = new Button();
    Button MultiplyButton = new Button();
    Button DivisionButton = new Button();
    Button SobelButton = new Button();
    Button LaplaceButton = new Button();
    Button PrewittButton = new Button();
    Button HighPassButton = new Button();
    Button LowPassButton = new Button();
    Button BandPassButton = new Button();
    Button BandRejectButton = new Button();
    Button debugPlayButton = new Button();
    Button stopButton = new Button();
    Button pauseButton = new Button();
    Button debugFlagButton = new Button();
    Button directoryButton = new Button();
    Button saveLogButton = new Button();
    Button cleanDebugButton = new Button();
    Button sharpButton = new Button();
    Button unsharpButton = new Button();
    Button saveImage=new Button();
    Button grayScaleButton=new Button();

    TextField FilePath=new TextField();

    HBox DebugPane=new HBox();
    HBox DebugTop=new HBox();
    VBox leftDebugPane=new VBox();
    VBox rightDebugPane=new VBox();

    ImageView closeButton=new ImageView(close);
    ImageView loggerButton=new ImageView(logger);

    BinaryTree<Vertex> ProcessTree;
    boolean recursiveFlag=true;
    boolean runFlag=true;
    boolean DebugFlag=false;
    boolean DebugFlag2=false;
    TextArea Panel =new TextArea();
    String ErrorMessages="";
    SerializableScene scene;
    @Override
    public void start(Stage primaryStage) {

        PlusButton.setTooltip(new Tooltip("plus"));
        MinusButton.setTooltip(new Tooltip("minus"));
        MedianButton.setTooltip(new Tooltip("median"));
        MeanButton.setTooltip(new Tooltip("mean"));
        OpenButton.setTooltip(new Tooltip("open"));
        DisplayButton.setTooltip(new Tooltip("display"));
        GaussianButton.setTooltip(new Tooltip("gaussian"));
        DeleteButton.setTooltip(new Tooltip("delete"));
        AndButton.setTooltip(new Tooltip("and"));
        OrButton.setTooltip(new Tooltip("or"));
        XorButton.setTooltip(new Tooltip("xor"));
        NotButton.setTooltip(new Tooltip("not"));
        CleanButton.setTooltip(new Tooltip("clean"));
        SaveButton.setTooltip(new Tooltip("save"));
        LoadButton.setTooltip(new Tooltip("load"));
        FlagButton.setTooltip(new Tooltip("Debug"));
        RunButton.setTooltip(new Tooltip("run"));
        MultiplyButton.setTooltip(new Tooltip("Multiply"));
        DivisionButton.setTooltip(new Tooltip("division"));
        SobelButton.setTooltip(new Tooltip("sobel"));
        LaplaceButton.setTooltip(new Tooltip("laplace"));
        PrewittButton.setTooltip(new Tooltip("prewitt"));
        HighPassButton.setTooltip(new Tooltip("highPass"));
        LowPassButton.setTooltip(new Tooltip("lowPass"));
        BandPassButton.setTooltip(new Tooltip("bandPass"));
        BandRejectButton.setTooltip(new Tooltip("bandReject"));
        debugPlayButton.setTooltip(new Tooltip("debugPlay"));
        stopButton.setTooltip(new Tooltip("stop"));
        pauseButton.setTooltip(new Tooltip("pause"));
        debugFlagButton.setTooltip(new Tooltip("debug"));
        directoryButton.setTooltip(new Tooltip("directory"));
        saveLogButton.setTooltip(new Tooltip("saveLog"));
        cleanDebugButton.setTooltip(new Tooltip("clean"));
        sharpButton.setTooltip(new Tooltip("sharping"));
        unsharpButton.setTooltip(new Tooltip("unsharping"));
        saveImage.setTooltip(new Tooltip("saveImage"));
        grayScaleButton.setTooltip(new Tooltip("grayScale"));

        EventHandlers eventHandler = new EventHandlers();
        eventHandler.setBottom(Bottom);
        eventHandler.setContents(contents);
        eventHandler.setDeleteFlag(DeleteFlag);
        eventHandler.setDeleteVertex(deleteVertex);
        eventHandler.setElementStack(ElementStack);
        eventHandler.setFromBoxHeader(FromBoxHeader);
        eventHandler.setKernelBoxHeader(KernelBoxHeader);
        eventHandler.setKernelImage(KernelImage);
        eventHandler.setLineFlag(lineFlag);
        eventHandler.setLines(Lines);
        eventHandler.setLinesEdge(linesEdge);
        eventHandler.setNameBoxHeader(NameBoxHeader);
        eventHandler.setOrgSceneX(orgSceneX);
        eventHandler.setOrgSceneY(orgSceneY);
        eventHandler.setOrgTranslateX(orgTranslateX);
        eventHandler.setOrgTranslateY(orgTranslateY);
        eventHandler.setRightLayout(rightLayout);
        eventHandler.setSigmaBoxHeader(SigmaBoxHeader);
        eventHandler.setSigmaImage(SigmaImage);
        eventHandler.setToBoxHeader(ToBoxHeader);
        eventHandler.setStatusBox(StatusBox);
        eventHandler.setMenuForOpen(menuForOpen);
        eventHandler.setOpen(Open);
        eventHandler.setRemoveConnection(removeConnection);
        eventHandler.setMenuForDefault(menuForDefault);
        eventHandler.setRemoveConnection2(removeConnection2);
        eventHandler.setRemoveItem(removeItem);
        eventHandler.setRemoveItem2(removeItem2);
        eventHandler.setFilePath(FilePath);
        eventHandler.setSaveImage(saveImage);
        menuForOpen.getItems().addAll(Open, removeConnection, removeItem2);
        menuForDefault.getItems().addAll(removeConnection2, removeItem);


        PlusButton.setGraphic(new ImageView(plus));
        MinusButton.setGraphic(new ImageView(minus));
        MedianButton.setGraphic(new ImageView(median));
        MeanButton.setGraphic(new ImageView(mean));
        OpenButton.setGraphic(new ImageView(open));
        DisplayButton.setGraphic(new ImageView(display));
        GaussianButton.setGraphic(new ImageView(gaussian));
        DeleteButton.setGraphic(new ImageView(delete));
        AndButton.setGraphic(new ImageView(and));
        OrButton.setGraphic(new ImageView(or));
        XorButton.setGraphic(new ImageView(xor));
        NotButton.setGraphic(new ImageView(not));
        CleanButton.setGraphic(new ImageView(clean));
        SaveButton.setGraphic(new ImageView(save));
        LoadButton.setGraphic(new ImageView(load));
        FlagButton.setGraphic(new ImageView(flag));
        RunButton.setGraphic(new ImageView(run));
        MultiplyButton.setGraphic(new ImageView(multiply));
        DivisionButton.setGraphic(new ImageView(division));
        SobelButton.setGraphic(new ImageView(sobel));
        LaplaceButton.setGraphic(new ImageView(laplace));
        PrewittButton.setGraphic(new ImageView(prewitt));
        HighPassButton.setGraphic(new ImageView(highPass));
        LowPassButton.setGraphic(new ImageView(lowPass));
        BandPassButton.setGraphic(new ImageView(bandPass));
        BandRejectButton.setGraphic(new ImageView(bandReject));
        debugPlayButton.setGraphic(new ImageView(debugPlay));
        stopButton.setGraphic(new ImageView(stop));
        pauseButton.setGraphic(new ImageView(pause));
        debugFlagButton.setGraphic(new ImageView(debugFlag));
        directoryButton.setGraphic(new ImageView(directory));
        saveLogButton.setGraphic(new ImageView(saveLog));
        cleanDebugButton.setGraphic(new ImageView(cleanDebug));
        sharpButton.setGraphic(new ImageView(sharp));
        unsharpButton.setGraphic(new ImageView(unsharp));
        grayScaleButton.setGraphic(new ImageView(grayscale));


        FilterListView.getItems().addAll(GaussianButton, MeanButton, HighPassButton, LowPassButton, BandPassButton, BandRejectButton,sharpButton, MedianButton, SobelButton, LaplaceButton, PrewittButton, AndButton, OrButton
                , XorButton, NotButton, PlusButton, MinusButton, MultiplyButton, DivisionButton,grayScaleButton);
        FilterListView.setMaxSize(68, 420);

        menuBar = new MenuBar();
        MenuFile = new Menu("MenuFile");
        Edit = new Menu("Edit");
        About = new Menu("About");
        Help = new Menu("Help");
        View = new Menu("View");
        Settings = new Menu("Settings");

        New = new MenuItem("New");
        LoadImage = new MenuItem("Load Image");
        SaveImage = new MenuItem("Save Image");
        LoadProject = new MenuItem("Load Project");
        SaveProject = new MenuItem("Save Project");
        Exit = new MenuItem("Exit");

        MenuFile.getItems().addAll(New, LoadImage, SaveImage, LoadProject, SaveProject, Exit);

        menuBar.getMenus().addAll(MenuFile, View, Settings, Edit, About, Help);


        BorderPane.setTop(Top);
        BorderPane.setCenter(Bottom);
        BorderPane.setLeft(Left);
        BorderPane.setRight(FilterBox);
        BorderPane.setBottom(BorderPane2);

        StatusBox.setMaxSize(320, 225);
        StatusBox.setMinSize(320, 225);
        StatusBox.setStyle("-fx-border-color: gray;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-padding: 5;\n" +
                "    -fx-spacing: 5;");

        TextHorizontal.getChildren().addAll(new Text("Filters"), new ImageView(new Image("img/filter.png")));
        TextHorizontal2.getChildren().addAll(new Text(" Tools"), new ImageView(tool));
        FilterBox.getChildren().addAll(new Text("\n\n"), TextHorizontal, FilterListView);

        ToolBox.getChildren().addAll(OpenButton, DisplayButton, DeleteButton, CleanButton, SaveButton, LoadButton, FlagButton, RunButton);
        Top.getChildren().addAll(menuBar);
        Bottom.getChildren().addAll(ElementStack);
        ToolBox.setStyle("-fx-border-color: gray;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-padding: 5;\n" +
                "    -fx-spacing: 5;");


        Left.getChildren().addAll(new Text("\n\n"), TextHorizontal2, ToolBox, new Text(""));
        scene = new SerializableScene(BorderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TEMEL GÖRÜNTÜ İŞLEME FİLTRELERİNİN ZİNCİRLEME GERÇEKLENMESİ");
        primaryStage.show();
        primaryStage.setMaximized(true);

        //Status Box
        BorderPane2.setRight(StatusBox);
        BorderPane2.setLeft(DebugPane);
        StatusBox.setLeft(BottomStatus);
        BottomStatus.getChildren().addAll(StatusBoxHeader, NameBoxHeader, FromBoxHeader, ToBoxHeader, KernelBoxHeader, SigmaBoxHeader);
        StatusBoxHeader.getChildren().addAll(new Text("   Status Box "), new ImageView(new Image("img/status.png")));
        DebugPane.setMaxSize(1050, 225);
        DebugPane.setMinSize(1050, 225);
        DebugPane.getChildren().addAll(leftDebugPane,rightDebugPane);

        rightDebugPane.getChildren().addAll(DebugTop,Panel);

        Project=new MenuItem("Project");
        About.getItems().addAll(Project);
        DebugTop.getChildren().addAll(new Text("Logger "),loggerButton,new Text("                                                                                      " +
                " " +
                "                                                                 " +
                "                                                                                     " +
                "                                     "),closeButton);

        Panel.setMaxSize(1000,195);
        Panel.setMinSize(1000,195);
        Panel.setStyle("-fx-control-inner-background:#393939;\n"+
                " -fx-text-fill: #00cc00;");
        DebugTop.setMaxSize(1005,25);
        DebugTop.setMinSize(1005,25);
        DebugTop.setStyle("-fx-border-color: gray;\n" +
                "    -fx-border-bottom-width: 2px;\n" +
                "    -fx-padding: 5;\n" +
                "    -fx-spacing: 5;");

        leftDebugPane.getChildren().addAll(debugPlayButton,pauseButton,stopButton,debugFlagButton,directoryButton,saveLogButton,cleanDebugButton);

        leftDebugPane.setMaxSize(45,255);
        leftDebugPane.setMinSize(45,255);
        leftDebugPane.setStyle("-fx-border-color: gray;\n" +
                "    -fx-border-width: 2px;\n" +
                "    -fx-padding: 5;\n" +
                "    -fx-spacing: 5;");
        DebugPane.setStyle("-fx-border-color: gray;\n" +
                "    -fx-border-width: 2px;\n");

        PlusButton.setOnAction(e -> {
            Vertex obj = new Vertex((plus));
            obj.setName("Plus " + counter);
            obj.setType("Plus");
            obj.setFilterType("aritmathic");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));

            eventHandler.lineHandler();
            ++counter;

        });
        MinusButton.setOnAction(e -> {
            Vertex obj = new Vertex((minus));
            obj.setName("minus " + counter);
            obj.setType("minus");
            obj.setFilterType("aritmathic");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });

        MedianButton.setOnAction(e -> {
            Vertex obj = new Vertex((median));
            obj.setName("asd.Median " + counter);
            obj.setType("asd.Median");
            obj.setFilterType("NonLinear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        MeanButton.setOnAction(e -> {
            Vertex obj = new Vertex((mean));
            obj.setName("asd.Mean " + counter);
            obj.setType("asd.Mean");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        OpenButton.setOnAction(e -> {
            Vertex obj = new Vertex((open));
            obj.setType("Open");
            obj.setName("Open " + counter);
            obj.setFilterType("NotFilter");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;

        });
        DisplayButton.setOnAction(e -> {
            Vertex obj = new Vertex((display));
            obj.setType("Display");
            obj.setName("Display " + counter);
            obj.setFilterType("NotFilter");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        GaussianButton.setOnAction(e -> {
            Vertex obj = new Vertex((gaussian));
            obj.setName("Gaussian " + counter);
            obj.setType("Gaussian");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        AndButton.setOnAction(e -> {
            Vertex obj = new Vertex((and));
            obj.setName("asd.And " + counter);
            obj.setType("asd.And");
            obj.setFilterType("asd.Logical");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        OrButton.setOnAction(e -> {
            Vertex obj = new Vertex((or));
            obj.setName("asd.Or " + counter);
            obj.setType("asd.Or");
            obj.setFilterType("asd.Logical");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        XorButton.setOnAction(e -> {
            Vertex obj = new Vertex((xor));
            obj.setName("asd.Xor " + counter);
            obj.setType("asd.Xor");
            obj.setFilterType("asd.Logical");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        NotButton.setOnAction(e -> {
            Vertex obj = new Vertex((not));
            obj.setName("not " + counter);
            obj.setType("not");
            obj.setFilterType("asd.Logical");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        MultiplyButton.setOnAction(e -> {
            Vertex obj = new Vertex((multiply));
            obj.setName("multiply " + counter);
            obj.setType("multiply");
            obj.setFilterType("aritmathic");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        DivisionButton.setOnAction(e -> {
            Vertex obj = new Vertex((division));
            obj.setName("division " + counter);
            obj.setType("division");
            obj.setFilterType("aritmathic");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        SobelButton.setOnAction(e -> {
            Vertex obj = new Vertex((sobel));
            obj.setName("sobel " + counter);
            obj.setType("sobel");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        LaplaceButton.setOnAction(e -> {
            Vertex obj = new Vertex((laplace));
            obj.setName("laplace " + counter);
            obj.setType("laplace");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        PrewittButton.setOnAction(e -> {
            Vertex obj = new Vertex((prewitt));
            obj.setName("prewitt " + counter);
            obj.setType("prewitt");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        HighPassButton.setOnAction(e -> {
            Vertex obj = new Vertex((highPass));
            obj.setName("highPass " + counter);
            obj.setType("highPass");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        LowPassButton.setOnAction(e -> {
            Vertex obj = new Vertex((lowPass));
            obj.setName("lowPass " + counter);
            obj.setType("lowPass");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        BandPassButton.setOnAction(e -> {
            Vertex obj = new Vertex((bandPass));
            obj.setName("bandPass " + counter);
            obj.setType("bandPass");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        BandRejectButton.setOnAction(e -> {
            Vertex obj = new Vertex((bandReject));
            obj.setName("bandReject " + counter);
            obj.setType("bandReject");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        sharpButton.setOnAction(e -> {
            Vertex obj = new Vertex((sharp));
            obj.setName("sharp " + counter);
            obj.setType("sharp");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        grayScaleButton.setOnAction(e -> {
            Vertex obj = new Vertex((grayscale));
            obj.setName("grayscale " + counter);
            obj.setType("grayscale");
            obj.setFilterType("Linear");
            contents.add(obj);
            contents.get(contents.size() - 1).setCursor(Cursor.HAND);
            contents.get(contents.size() - 1).setOnMousePressed(eventHandler.elementsOnClicked);
            contents.get(contents.size() - 1).setOnMouseDragged(eventHandler.elementsOnDragged);
            ElementStack.getChildren().addAll(contents.get(contents.size() - 1));
            eventHandler.lineHandler();
            ++counter;
        });
        DeleteButton.setOnAction(e -> {
            DeleteFlag = true;
            eventHandler.setDeleteFlag(DeleteFlag);
        });
        RunButton.setOnAction(e -> {

            DebugFlag2=false;
            FFT.DebugFlag2=false;
            recursiveFlag=true;
            runFlag=true;
            ErrorMessages="";
            FFT.recursiveFlag=true;
            ArrayList<ArrayList<Vertex>> BFSGraphs;
            ArrayList<ArrayList<Vertex>> BFSGraphs2;
            //taking files.
            for (int i = 0; i < contents.size(); ++i) {
                if (contents.get(i).getType().equals("Open"))
                    files.add(contents.get(i));
            }


            for(int i=0;i<files.size();++i)
            {
                if(files.get(i).DataImage==null)
                {
                    runFlag=false;
                    ErrorMessages=ErrorMessages+"ERROR:"+files.get(i).getName()+" doesn't contains an image!\n";
                    Panel.setText(ErrorMessages);
                }
            }
            for(int i=0;i<contents.size();++i)
            {
                if(contents.get(i).toList.contains(contents.get(i))&&contents.get(i).fromList.contains(contents.get(i)))
                {
                    runFlag=false;
                    ErrorMessages=ErrorMessages+"ERROR:"+contents.get(i).getName()+" is in its from list and to list!\n";
                    Panel.setText(ErrorMessages);
                }
            }
            if(DebugFlag)
            {
                ErrorMessages="Debug Mode Active!\n";
                Panel.setText(ErrorMessages);
                BFSGraphs = BFS.findAllGraph(files);


                System.out.println(BFSGraphs.size());

                BinaryTree<Vertex> operationTree = new BinaryTree<Vertex>();
                for (int i = 0; i < BFSGraphs.size(); ++i) {
                    for (int j = 0; j < BFSGraphs.get(i).size(); ++j) {
                        BFSGraphs.get(i).get(j).implemented = false;
                        if (!BFSGraphs.get(i).get(j).getType().equals("Open"))
                            BFSGraphs.get(i).get(j).DataImage = null;
                    }
                }
                ProcessTree = operationTree.getPriorityOperationTree(BFSGraphs.get(0));

                try {
                    preOrderTraverse(ProcessTree.root);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                BFSGraphs.clear();
                DebugFlag=false;

            }
            else
            if(runFlag) {
                ErrorMessages="Graph is Valid!\n";
                Panel.setText(ErrorMessages);
                BFSGraphs = BFS.findAllGraph(files);


                System.out.println(BFSGraphs.size());

                BinaryTree<Vertex> operationTree = new BinaryTree<Vertex>();
                for (int i = 0; i < BFSGraphs.size(); ++i) {
                    for (int j = 0; j < BFSGraphs.get(i).size(); ++j) {
                        BFSGraphs.get(i).get(j).implemented = false;
                        if (!BFSGraphs.get(i).get(j).getType().equals("Open"))
                            BFSGraphs.get(i).get(j).DataImage = null;
                    }
                }
                ProcessTree = operationTree.getPriorityOperationTree(BFSGraphs.get(0));

                try {
                    preOrderTraverse(ProcessTree.root);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                BFSGraphs.clear();

            }

            files.clear();
            for (int i = 0; i < contents.size(); ++i)
                contents.get(i).visited = false;

        });

        CleanButton.setOnAction(e -> {
            ElementStack.getChildren().clear();
            int i;
            for (i = 0; i < Lines.size(); ++i) {
                Bottom.getChildren().remove(Lines.get(i));
                Bottom.getChildren().remove(Lines.get(i).polygon);
            }
            Lines.clear();
            contents.clear();
            NameBoxHeader.getChildren().clear();
            FromBoxHeader.getChildren().clear();
            ToBoxHeader.getChildren().clear();
            KernelBoxHeader.getChildren().clear();
            SigmaBoxHeader.getChildren().clear();
            rightLayout.getChildren().clear();
        });

        SaveButton.setOnAction(e -> {



            try {
                FileOutputStream f = new FileOutputStream(new File("myObjects.bin"));
                ObjectOutputStream o = new ObjectOutputStream(f);

                // Write objects to file
                o.writeObject(Bottom);

                o.close();
                f.close();


            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            } catch (IOException ex) {
                System.out.println("Error initializing stream");
            }

        });
        LoadButton.setOnAction(e->{


            try {

                FileInputStream fi = new FileInputStream(new File("myObjects.bin"));
                ObjectInputStream oi = new ObjectInputStream(fi);


                Bottom = (SerializableAnchorPane) oi.readObject();



                oi.close();
                fi.close();

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            } catch (IOException ex) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }


        });
        FlagButton.setOnAction(e->{
            DebugFlag=true;
            eventHandler.setDebugFlag(DebugFlag);

        });
        Project.setOnAction(e->{

                    Stage aboutWindow=new Stage();

                    ImageView img=new ImageView(new Image("img/GTU.png"));
                    VBox layout1=new VBox();
                    VBox layout2=new VBox();
                    Hyperlink link = new Hyperlink();
                    link.setText("https://www.youtube.com/watch?v\n=vSRN3OUru1s&t");
                    layout2.getChildren().addAll(new Text(" \n\n  This project is a term Project That is implemented \nby Recep Sivri and " +
                            "provided by Doç.Dr. Erchan\nAptoula.For details:\n    "),link);
                    Scene s=new Scene(layout1,300,300);

                    final WebView browser = new WebView();
                    final WebEngine webEngine = browser.getEngine();
                    link.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            webEngine.load("https://www.youtube.com/watch?v=vSRN3OUru1s&t");
                        }
                    });
                    aboutWindow.setMinHeight(300);
                    aboutWindow.setMinWidth(300);


                    img.setPreserveRatio(true);
                    img.setFitHeight(48);
                    img.setFitHeight(48);




                    layout1.getChildren().addAll(img,layout2,browser);
                    layout1.setVgrow(browser, Priority.ALWAYS);
                    aboutWindow.setTitle("About Project");
                    aboutWindow.setScene(s);
                    aboutWindow.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void displayImage(Vertex image, String DisplayName) {
        ScrollPane scroll = new ScrollPane();
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
    private void displayImageForDebug(BufferedImage img3, String DisplayName) {
        ScrollPane scroll = new ScrollPane();
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


    public void preOrderTraverse(BinaryTree.Node<Vertex> node) throws Exception {


        if (node == null) {

        }
        else {


            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
            if (node.data.debug) {
                BufferedImage input1 = (node.left.data).DataImage;
                BufferedImage input2=null;
                DebugFlag2=true;
                if(node.right!=null)
                     input2 = (node.right.data).DataImage;
                if (recursiveFlag&&(node.data).getType().equals("Plus")) {
                    Sum operation = new Sum();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("minus")) {
                    Substract operation = new Substract();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("multiply")) {
                    Multiply operation = new Multiply();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("division")) {
                    Division operation = new Division();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("sobel")) {
                    Sobel operation = new Sobel();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("laplace")) {
                    Laplacian operation = new Laplacian();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("prewitt")) {
                    Prewitt operation = new Prewitt();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("asd.Mean")) {
                    Mean operation = new Mean();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }
                if (recursiveFlag&&(node.data).getType().equals("asd.Median")) {
                    Median operation = new Median();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }
                if (recursiveFlag&&(node.data).getType().equals("Gaussian")) {
                    Gauss operation = new Gauss();
                    operation.KernelSize = (int) node.data.Kernel.getValue();
                    operation.setSigma((int) node.data.Sigma.getValue());
                    (node.data).DataImage = operation.ImplementFilter(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("asd.And")) {

                    And operation = new And();
                    operation.setInput1(input1);
                    operation.setInput2(input2);
                    (node.data).DataImage=operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());

                }
                if (recursiveFlag&&(node.data).getType().equals("asd.Or")) {
                        Or operation = new Or();
                        operation.setInput1((node.left.data).DataImage);
                        operation.setInput2((node.right.data).DataImage);
                        (node.data).DataImage=operation.returnlogicalResult();
                        displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());

                }
                if (recursiveFlag&&(node.data).getType().equals("asd.Xor")) {
                    Xor operation = new Xor();
                    operation.setInput1((node.left.data).DataImage);
                    operation.setInput2((node.right.data).DataImage);
                    (node.data).DataImage=operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result from "+node.data.getName());
                }
                if (recursiveFlag&&(node.data).getType().equals("not")) {
                    Not operation = new Not();
                    (node.data).DataImage = operation.returnlogicalResult();
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }

                if (recursiveFlag&&(node.data).getType().equals("sharp")) {
                    Sharp operation = new Sharp();
                    operation.Sharp = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.returnSharpingImage(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());

                }
                if (recursiveFlag&&(node.data).getType().equals("unsharp")) {
                    Sharp operation = new Sharp();
                    operation.Sharp = (int) node.data.Kernel.getValue();
                    (node.data).DataImage = operation.returnSharpingImage(input1);
                    displayImageForDebug((node.data).DataImage, "Debug Result"+node.data.getName());
                }
                if ((node.data).getType().equals("highPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindow(node.left.data.DataImage, node, ProcessTree.root, 1, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    recursiveFlag = false;
                    FFT.DebugFlag2=true;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("lowPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindow(node.left.data.DataImage, node, ProcessTree.root, 2, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    recursiveFlag = false;
                    FFT.DebugFlag2=true;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("bandPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindowBand(node.left.data.DataImage, node, ProcessTree.root, 1, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    recursiveFlag = false;
                    FFT.DebugFlag2=true;
                    node.data.implemented = true;
                    return;
                }
                if ((node.data).getType().equals("bandReject") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindowBand(node.left.data.DataImage, node, ProcessTree.root, 2, ErrorMessages, Panel);
                    node.left = null;
                    node.right = null;
                    recursiveFlag = false;
                    FFT.DebugFlag2=true;
                    node.data.implemented = true;
                    return;
                }
                return;


            }
            if(!DebugFlag2){
                if ((node.data).getType().equals("highPass") && !node.data.implemented && node.left.data.DataImage != null) {
                    FFT fft = new FFT();
                    fft.FourierWindow(node.left.data.DataImage, node, ProcessTree.root, 1, ErrorMessages, Panel);
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
                    fft.FourierWindow(node.left.data.DataImage, node, ProcessTree.root, 2, ErrorMessages, Panel);
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
                    fft.FourierWindowBand(node.left.data.DataImage, node, ProcessTree.root, 1, ErrorMessages, Panel);
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
                    fft.FourierWindowBand(node.left.data.DataImage, node, ProcessTree.root, 2, ErrorMessages, Panel);
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
}