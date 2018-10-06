import java.util.ArrayList;
import java.util.List;

public class BinaryTreeProblem {
    /*
    * Given a binary tree, flatten it to a linked list in-place
    * Note: the resultant linked list should still be a valid binary tree (not BST!)
    * */
    public void flatten(TreeNode root){
        flattenUtil(root);
    }

    private TreeNode flattenUtil(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode leftTail = flattenUtil(root.left);
        TreeNode rightTail = flattenUtil(root.right);
        if(root.left != null){
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;   //construct linkedlist-like binary tree
            leftTail.right = temp;
        }
        if(rightTail != null){
            return rightTail;
        }
        if(leftTail != null){
            return leftTail;
        }
        return root;
    }

    /*
    * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
    * values among the path equals a given sum
    * */
    public boolean hasPathSum(TreeNode root, int sum){
        //base cases
        if(root == null){
            return false;   //not finding a sum but reached leaf level...
        }
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        sum -= root.val;
        if(root.left != null){
            return hasPathSum(root.left, sum);
        }
        if(root.right != null){
            return hasPathSum(root.right, sum);
        }
        return false;
    }

    /*
    * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum
    * */
    public List<List<Integer>> pathSumRootToLeaf(TreeNode root, int sum){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        DFS(root, sum, result, temp);
        return result;
    }

    private void DFS(TreeNode root, int sum, List<List<Integer>> result, List<Integer> temp){
        //base cases
        if(root == null){
            return; //not finding a sum but reached a leaf...
        }
        temp.add(root.val);
        if(root.left == null && root.right == null){
            if(root.val == sum){
                result.add(new ArrayList(temp));
            }
        }
        DFS(root.left, sum - root.val, result, temp);
        DFS(root.right, sum - root.val, result, temp);
        temp.remove(temp.size() - 1);   //clear temp storage
    }

    /*
    * Given a binary tree, find the number of paths that downward paths that sum to a given value
    * */
    public int pathSumDownwards(TreeNode root, int sum){
        //handle edge case
        if(root == null){
            return 0;
        }
        return util(root, sum);
    }
    private int util(TreeNode root, int temp){
        //base case
        if(root == null){
            return 0;
        }
        temp -= root.val;
        if(temp == 0){
            //a path is found
            return 1 + util(root.left, temp) + util(root.right, temp);
        }else{
            //no path found on this layer. keep searching
            return util(root.left, temp) + util(root.right, temp);
        }
    }

    /*
    *
    * */
}
