import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*Adjacency list implementation*/
public class GraphNode {
    public int val;
    public boolean visited;
    public Set<GraphNode> neighbors = new HashSet<>();

    public GraphNode(){
        this.visited = false;
        this.val = -1;
    }

    public GraphNode(int val){
        this.val = val;
    }

}
