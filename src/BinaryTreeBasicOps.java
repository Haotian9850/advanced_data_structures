import jdk.nashorn.api.tree.Tree;

import java.util.*;

public class BinaryTreeBasicOps {
    /*
    * Traversals: all iterative approach
    * */

    /*
    * Pre-order traversal
    * */
    public List<Integer> preOrderTraversalIterative(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        //handle edge case
        if(root == null){
            return result;
        }
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode current = stack.pop();
            result.add(current.val);
            if(current.right != null){
                stack.push(current.right);
            }
            if(current.left != null){
                stack.push(current.left);
            }
        }
        return result;
    }

    /*
    * In-order traversal
    * */
    public List<Integer> inOrderTraversalIterative(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        //handle edge case
        if(root == null){
            return result;
        }
        TreeNode current = root;    //set ptr
        while(!stack.isEmpty() || current != null){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            //now current must be null, so re-use it
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    /*
    * Post-order traversal
    * */
    public List<Integer> postOrderTraversalIterative(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        //handle edge case
        if(root == null){
            return result;
        }
        stack.push(root);
        TreeNode prev = null;   //init prev pointer as null
        while(!stack.isEmpty()){
            TreeNode current = stack.peek();
            if(current.left == null && current.right == null){
                //reached leaf
                TreeNode temp = stack.pop();
                result.add(temp.val);
            }else{
                if(current.right != null){
                    stack.push(current.right);
                    current.right = null;
                }
                if(current.left != null){
                    stack.push(current.left);
                    current.left = null;
                }
            }
        }
        return result;
    }

    /*
    * Level order traversal
    * */
    public List<List<Integer>> levelOrderTraversal(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        //handle edge cases
        if(root == null){
            return result;
        }
        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        List<Integer> nodeValues = new ArrayList<>();
        currentLevel.add(root);
        while(!currentLevel.isEmpty()){
            TreeNode current = currentLevel.remove();
            if(current.left != null){
                nextLevel.add(current.left);
            }
            if(current.right != null){
                nextLevel.add(current.right);
            }
            nodeValues.add(current.val);
            if(currentLevel.isEmpty()){
                //enter next level
                result.add(new ArrayList<>(nodeValues));
                currentLevel = nextLevel;
                nextLevel.clear();
                nodeValues.clear();
            }
        }
        return result;
    }

    /*
    * Vertical order traversal
    * */
    public List<List<Integer>> verticalOrderTraversal(TreeNode root){
        //expand from root node approach!
        List<List<Integer>> result = new ArrayList<>();
        //handle edge case
        if(root == null){
            return result;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();  //key: vertical level, value: nodes in that level
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> levels = new LinkedList<>();
        queue.add(root);
        levels.add(0);  //root's vertical level: 0
        int minLevel = 0;
        int maxLevel = 0;
        while(!queue.isEmpty()){
            TreeNode currentNode = queue.remove();
            int currentLevel = levels.remove();
            //check current level in map
            if(map.containsKey(currentLevel)){
                map.get(currentLevel).add(currentNode.val);
            }else{
                //create a new vertical level
                List<Integer> newLevel = new ArrayList<>();
                newLevel.add(currentNode.val);
                map.put(currentLevel, newLevel);
            }

            minLevel = Math.min(minLevel, currentLevel);
            maxLevel = Math.max(maxLevel, currentLevel);

            //check left subtree
            if(currentNode.left != null){
                queue.add(currentNode.left);
                levels.add(currentLevel - 1);
            }
            //check right tree
            if(currentNode.right != null){
                queue.add(currentNode.right);
                levels.add(currentLevel + 1);
            }
        }

        //make result
        for(int i = minLevel; i <= maxLevel; ++ i){
            if(map.containsKey(i)){
                result.add(map.get(i));
            }
        }
        return result;
    }
}
