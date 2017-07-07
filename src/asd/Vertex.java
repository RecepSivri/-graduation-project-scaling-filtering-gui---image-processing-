package asd;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by recep on 3/22/17.
 */
public class Vertex extends ImageView {
    ArrayList<Edge> adjectents=new ArrayList<Edge>();
    ArrayList<Vertex> fromList=new ArrayList<Vertex>();
    ArrayList<Vertex> toList=new ArrayList<Vertex>();
    boolean value;
    boolean debug=false;
    String Name;
    String Type;
    String FilterType;
    boolean visited=false;
    public String getFilterType() {
        return FilterType;
    }

    public void setFilterType(String filterType) {
        FilterType = filterType;
    }

    ComboBox<String> from=new ComboBox<String>();
    ComboBox<String> to=new ComboBox<String>();

    Slider Kernel=new Slider();
    double KernelValue =3.0;
    double SigmaValue =1.0;

    Image Data=new Image("img/empty.png");
    BufferedImage DataImage;
    boolean implemented=false;
    BufferedImage param1=null;
    BufferedImage param2=null;
    Slider Sigma=new Slider();
    public void setSigma()
    {
        Sigma.setMax(10);
        Sigma.setMin(1);
        Sigma.setValue(SigmaValue);
        Sigma.setShowTickLabels(true);
        Sigma.setShowTickMarks(true);
        Sigma.setMajorTickUnit(1);
        Sigma.setMinorTickCount(0);
        Sigma.setBlockIncrement(1);
        Sigma.setMaxWidth(250);
        Sigma.setMinWidth(250);
    }
    public void setKernel()
    {
        Kernel.setMax(15.0);
        Kernel.setMin(3.0);
        Kernel.setValue(KernelValue);
        Kernel.setShowTickLabels(true);
        Kernel.setShowTickMarks(true);
        Kernel.setMajorTickUnit(2);
        Kernel.setMinorTickCount(0);
        Kernel.setBlockIncrement(2);
        Kernel.setMaxWidth(250);
        Kernel.setMinWidth(250);
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void addAdjectents(Vertex vertex)
    {
        adjectents.add(new Edge(this,vertex,false));
        this.toList.add(vertex);
        this.to.getItems().add(vertex.getName());
        vertex.fromList.add(this);
        vertex.from.getItems().addAll(this.getName());
    }
    public Edge findEdge(Vertex from,Vertex to)
    {
        for(int i=0;i<adjectents.size();++i)
        {
            if(adjectents.get(i).from.equals(from)&&adjectents.get(i).to.equals(to))
                return adjectents.get(i);
        }
        return null;
    }
    public Vertex(Image image) {
        super(image);
    }
}
