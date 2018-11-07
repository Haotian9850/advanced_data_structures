import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindCousins {
    /*
    * Return a list of cousins of a given node in a binary tree
    *
    * Approach: get level of the node -> print all level nodes except for the node's siblings
    * */

    public List<TreeNode> findCousinRecursive(TreeNode root, TreeNode node){
        List<TreeNode> result = new ArrayList<>();
        getCousins(result, root, node, getLevel(root, node, 1));
        return result;
    }

    private int getLevel(TreeNode root, TreeNode node, int level){
        //DFS to get node's level
        //base case (use root as a pointer)
        if(root == null){
            //not found
            return - 1;
        }
        //return condition
        if(root.val == node.val){
            return level;
        }

        //check both subtrees
        if(getLevel(root.left, node, level + 1) == -1 && getLevel(root.right, node, level + 1) == -1){
            return -1;  //the node is not present in the tree
        }else{
            return Math.max(getLevel(root.left, node, level + 1), getLevel(root.right, node, level + 1));
        }
    }

    private void getCousins(List<TreeNode> result, TreeNode root, TreeNode node, int level){
        //get all nodes that are not node's siblings in level
        //base case
        if(root == null || level < 2){
            return; //no cousin could possibly exist
        }
        if(level == 2){
            //check siblings
            if(root.left.val == node.val || root.right.val == node.val){
                //siblings!
                return;
            }
            if(root.left != null){
                result.add(root.left);
            }
            if(root.right != null){
                result.add(root.right);
            }
        }
        if(level > 2){
            //递归, check both subtree
            getCousins(result, root.left, node, level - 1);
            getCousins(result, root.right, node, level - 1);
        }
    }

    public List<TreeNode> findCousinIterative(TreeNode root, TreeNode node){
        //handle edge case
        List<TreeNode> result = new ArrayList<>();
        if(root.val == node.val){
            return result;
        }
        LinkedList<TreeNode> parentLevel = new LinkedList<>();
        LinkedList<TreeNode> childLevel = new LinkedList<>();
        parentLevel.offerLast(root);
        boolean isFound = false;
        while(!parentLevel.isEmpty()){
            TreeNode parent = parentLevel.pop();
            if(parent.left.val == node.val || parent.right.val == node.val){
                //child node found
                isFound = true;
            }else{
                if(parent.left != null){
                    childLevel.push(parent.left);
                }
                if(parent.right != null){
                    childLevel.push(parent.right);
                }
            }
            if(parentLevel.isEmpty()){
                if(isFound){
                    break;
                }
                //if not, enter next level
                parentLevel = childLevel;
                childLevel.clear();
            }
        }
        result = new ArrayList<>(childLevel);
        return result;
    }
}
