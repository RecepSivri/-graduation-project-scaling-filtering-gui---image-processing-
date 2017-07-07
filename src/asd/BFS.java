package asd;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Recep Sivri on 19.04.2017.
 */
public class BFS {
    private static ArrayList<Vertex> BFSAlgorithm(Vertex Begin)
    {
        LinkedList<Vertex> queue=new LinkedList<Vertex>();
        ArrayList<Vertex> listBFS=new ArrayList<Vertex>();
        if(!Begin.visited)
            queue.add(Begin);

        while(!queue.isEmpty())
        {
            Vertex node=queue.pop();
            listBFS.add(node);
            node.visited = true;
            for(int i=0;i<node.toList.size();++i)
            {
                if(!node.toList.get(i).visited)
                     queue.add(node.toList.get(i));
            }
            for(int i=0;i<node.fromList.size();++i)
            {
                if(!node.fromList.get(i).visited)
                    queue.add(node.fromList.get(i));
            }
        }
        return listBFS;
    }
    public static ArrayList<ArrayList<Vertex>> findAllGraph(ArrayList<Vertex> files)
    {
        ArrayList<Vertex> ListOfBFs;
        ArrayList<ArrayList<Vertex>> BFSGraphs=new ArrayList<ArrayList<Vertex>>();
        ListOfBFs=BFSAlgorithm(files.get(0));
        for(int i=1;i<files.size();++i)
        {
            if(!files.get(i).visited)
            {
                BFSGraphs.add(BFS.BFSAlgorithm(files.get(i)));
            }
        }
        BFSGraphs.add(ListOfBFs);

        return BFSGraphs;
    }
}
