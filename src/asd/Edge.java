package asd;

import javafx.scene.shape.Line;

/**
 * Created by recep on 3/22/17.
 */
public class Edge {
    Vertex from;
    Vertex to;
    boolean connected;
    Line line1=new Line();
    public Edge(Vertex from, Vertex to, boolean connected) {
        this.from = from;
        this.to = to;
        this.connected = connected;
    }
    public Line getLine()
    {
        return null;
    }
}
