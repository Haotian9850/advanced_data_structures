import java.util.ArrayList;
import java.util.List;
/*Adjacency list representation*/
public class Graph {
    public List<GraphNode> nods = new ArrayList<>();

    public Graph(){
    }

    public Graph(List<GraphNode> nodes){
        for(GraphNode n : nodes){
            this.nods.add(n);
        }
    }

    public void addEdge(int i, int j){
        //set i-j undirected edge
        //very inefficient approach
        GraphNode nodeI = new GraphNode();
        GraphNode nodeJ = new GraphNode();
        for(GraphNode n : this.nods){
            if(n.val == i){
                nodeI = n;
            }
            if(n.val == j){
                nodeJ = n;
            }
        }
        //set edge
        nodeI.neighbors.add(nodeJ);
        nodeJ.neighbors.add(nodeI);
    }

    public void visualize(){
        for(GraphNode n : this.nods){
            System.out.print("Node " + n.val + ": ");
            for(GraphNode node : n.neighbors){
                System.out.print(node.val + " ");
            }
            System.out.println();   //new line separator
        }
    }
}
