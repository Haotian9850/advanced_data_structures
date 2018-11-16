import java.util.ArrayList;
import java.util.List;

public class main {
    /*main class for data structure testing*/
    public static void main(String[] args){
        List<GraphNode> lst = new ArrayList();
        lst.add(new GraphNode(1));
        lst.add(new GraphNode(2));
        lst.add(new GraphNode(3));
        lst.add(new GraphNode(4));
        lst.add(new GraphNode(5));
        lst.add(new GraphNode(6));



        Graph gph = new Graph(lst);


        gph.addEdge(1, 2);
        gph.addEdge(1, 3);
        gph.addEdge(2, 4);
        gph.addEdge(4, 6);
        gph.addEdge(6, 5);
        gph.addEdge(4, 5);
        gph.addEdge(5, 2);
        gph.addEdge(5, 3);

        gph.visualize();
    }
}
