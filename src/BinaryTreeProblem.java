import java.util.*;

public class BinaryTreeProblem {
    /*
    * 114
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

    public void flattenIterative(TreeNode root){
        //right most node. in-place operation. Also O(n) performance
        TreeNode rightMost = null;
        TreeNode temp = null;
        while(root != null) {
            if (root.left != null) {
                rightMost = root.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                temp = root.right;
                root.right = root.left;
                rightMost.right = temp;
                //set null pointer
                root.left = null;
            }
            //move ptr
            root = root.right;
        }
    }



    /*
    * 112
    * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
    * values among the path equals a given sum
    * */
    public boolean hasPathSumRecursive(TreeNode root, int sum){
        //base cases
        if(root == null){
            return false;   //not finding a sum but reached leaf level...
        }
        if(root.left == null && root.right == null){
            return sum == 0;
        }
        sum -= root.val;
        if(root.left != null){
            return hasPathSumRecursive(root.left, sum);
        }
        if(root.right != null){
            return hasPathSumRecursive(root.right, sum);
        }
        return false;
    }

    public boolean hasPathSumIterative(TreeNode root, int sum){
        //use a stack for DFS
        //handle edge case
        if(root == null){
            return false;
        }
        Stack<TreeNode> path = new Stack();
        Stack<Integer> currSum = new Stack<>();
        //perform a DFS of the tree
        path.push(root);
        currSum.push(root.val);
        while(!path.isEmpty()){
            TreeNode currNode = path.pop();
            int temp = currSum.pop();
            //return condition (true): reached leaf node && is sum
            if(currNode.left == null && currNode.right == null){
                return temp == sum;
            }else{
                //havn't reached leaf node
                if(currNode.left != null){
                    path.push(currNode.left);
                    currSum.push(currNode.left.val + temp);
                }
                if(currNode.right != null){
                    path.push(currNode.right);
                    currSum.push(currNode.right.val + temp);
                }
            }
        }
        return false;
    }


    /*
    * 113
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
    * 437 Path Sum III
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
    * 104
    * Maximum depth of binary tree
    * */
    public int maxDepthRecursive(TreeNode root){
        //base case
        if(root == null){
            return 0;   //reached leaf
        }
        return 1 + Math.max(maxDepthRecursive(root.left), maxDepthRecursive(root.right));
    }

    public int maxDepthIterative(TreeNode root){
        //DFS the tree iteratively
        //handle edge case
        int result = 0;
        if(root == null){
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            ++ result;
            int n = queue.size();
            for(int i = 0; i < n; ++ i){
                TreeNode temp = queue.poll();
                if(temp.left != null){
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
        }
        return result;
    }

    /*
    * 199
    * Binary tree right side view
    * */
    public List<Integer> rightSideView(TreeNode root){
        //BFS the tree, return every last node at every level
        List<Integer> result = new ArrayList<>();
        //handle edge case
        if(root == null){
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; ++ i){
                TreeNode temp = queue.poll();
                if(i == 0){
                    //add to result
                    result.add(temp.val);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
                if(temp.left != null){
                    queue.add(temp.left);
                }
            }
        }
        return result;
    }

    /*
    * 236
    * LCA (classic of classics!). This is LOWEST COMMON ANCESTOR OF BINARY TREE NOT BST!
    * */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q){
        //base case
        if(root == null || root == p || root == q){
            return root;
        }
        //search in each subtree
        TreeNode left = lowestCommonAncestorRecursive(root.left, p, q);
        TreeNode right = lowestCommonAncestorRecursive(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }else if(left == null){
            return right;
        }else{
            return left;
        }
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q){
        //handle edge case
        if(root == null || p == null || q == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        //maintain a HashMap to store child-parent relation
        HashMap<TreeNode, TreeNode> map = new HashMap<>();  //key: child (left and right), value: parent
        //init
        stack.push(root);
        map.put(root, null);
        while(!map.containsKey(p) || map.containsKey(q)){
            TreeNode node = stack.pop();
            if(node.left != null){
                map.put(node.left, node);
                stack.push(node.left);
            }
            if(node.right != null){
                map.put(node.right, node);
                stack.push(node.right);
            }
        }
        HashSet<TreeNode> ancestors = new HashSet<>();
        //find common ancestor
        while(p != null){
            ancestors.add(p);
            p = map.get(p);
        }
        while(!ancestors.contains(q)){
            q = map.get(p);
        }
        return q;
    }

    /*
    * 98
    * Validate binary search tree
    * */
    public boolean isValidBST(TreeNode root){
        //handle edge case
        if(root == null){
            return true;
        }
        return validate(root, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    private boolean validate(TreeNode root, double min, double max){
        //base case
        if(root == null){
            return true;    //check all
        }
        if(root.val <= min || root.val >= max){
            return false;   //not satisfying BST property
        }
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    public boolean isValidBSTIteravtie(TreeNode root){
        //explicit in-order traversal (Stack) and validate on-the-go
        Stack<TreeNode> traversal = new Stack<>();
        LinkedList<Integer> vals = new LinkedList<>();
        TreeNode current = root;    //set pointer
        while(current != null || !traversal.isEmpty()){
            while(current != null){
                traversal.push(current);
                current = current.left;
            }
            //current must be null here...so reuse it
            current = traversal.pop();
            vals.add(current.val);
            //do the check
            if(vals.size() > 1 && vals.get(vals.size() - 2) >= vals.get(vals.size() - 1)){
                return false;
            }
            current = current.right;
        }
        return true;
    }

    /*
    * 314
    * Binary tree vertical order traversal
    * */
    public List<List<Integer>> verticalOrder(TreeNode root){
        //BFS tree, also use a hashmap to keep track of vertical layer (spread from root)
        List<List<Integer>> result = new ArrayList<>();
        //handle edge case
        if(root == null){
            return result;
        }
        HashMap<Integer, List<Integer>> dict = new HashMap<>();
        LinkedList<TreeNode> queue = new LinkedList<>();    //BFS of tree
        LinkedList<Integer> levels = new LinkedList<>();   //current vertical layer
        queue.add(root);
        levels.add(0);
        int minLevel = 0;
        int maxLevel = 0;
        while(!queue.isEmpty()){
            TreeNode temp = queue.poll();
            int currLevel = levels.poll();
            //check current level
            if(dict.containsKey(currLevel)){
                //add to existing level
                dict.get(currLevel).add(temp.val);
            }else{
                //create new level
                List<Integer> newLevel = new ArrayList<>();
                newLevel.add(temp.val);
                dict.put(currLevel, newLevel);
            }
            //update min / max level tracker
            minLevel = Math.min(minLevel, currLevel);
            maxLevel = Math.max(maxLevel, currLevel);
            //check left / right subtree
            if(temp.left != null){
                queue.add(temp.left);
                levels.add(currLevel - 1);  //Note: level number might be negative!
            }
            if(temp.right != null){
                queue.add(temp.right);
                levels.add(currLevel + 1);
            }
        }
        //make result
        for(int i = minLevel; i < maxLevel + 1; ++ i){
            if(dict.containsKey(i)){
                result.add(dict.get(i));
            }
        }
        return result;
    }

    /*
    * 101
    * Symmetric tree
    * */
    public boolean isSymmetric(TreeNode root){
        //DFS the tree
        //handle edge case
        if(root == null || (root.left == null && root.right == null)){
            return true;
        }
        return symmetricDFS(root, root);
    }

    private boolean symmetricDFS(TreeNode leftNode, TreeNode rightNode){
        //base case: reached leaf, nothing went wrong
        if(leftNode == null && rightNode == null){
            return true;
        }
        //check tree shape first, then check val
        if(leftNode == null || rightNode == null){
            return false;
        }
        if(leftNode.val == rightNode.val){
            //check val via mirror pattern
            return symmetricDFS(leftNode.right, rightNode.left) && symmetricDFS(leftNode.left, rightNode.right);
        }
        return false;   //every thing should be already handled...
    }

    /*
    * 543
    * Diameter of binary tree
    * */
    public int diameterOfBinaryTreeRecursive(TreeNode root){
        //DFS tree, spread from root
        int result = 0;
        //handle edge cases
        if(root == null){
            return result;
        }
        diameterDFS(result, root);
        return result;
    }

    private int diameterDFS(int result, TreeNode root){
        //base case: reached leaf
        if(root == null){
            return 0;
        }
        int leftMax = diameterDFS(result, root.left);
        int rightMax = diameterDFS(result, root.right);
        //update intermediate result
        result = Math.max(result, leftMax + rightMax);
        return Math.max(leftMax, rightMax) + 1; //return to previous layer (only to update result)
    }

    public int diameterOfBinaryTreeIterative(TreeNode root){
        //calculate max left & right path length for every node in the tree
        //handle edge case
        if(root == null){
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, Integer> lenMap = new HashMap<>();
        //init
        int result = Integer.MIN_VALUE;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp != null){
                //use wrapper type to avoid nullPointerException
                Integer left = lenMap.get(temp.left);
                Integer right = lenMap.get(temp.right);
                if(left != null && right != null){
                    lenMap.put(temp, Math.max(left, right) + 1);
                    //update result
                    result = Math.max(result, left + right);
                }else{
                    stack.push(temp);
                    stack.push(temp.left);
                    stack.push(temp.right);
                }
            }else{
                lenMap.put(null, 0);
            }
        }
        return result;
    }

    /*
    * 94
    * Binary tree inorder traversal: iterative approach
    * */
    public List<Integer> inOrderIterative(TreeNode root){
        //return all nodes in-order as a list of nodes
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while(!stack.isEmpty() || current != null){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            //current must be null now, so reuse it
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    /*
    * 257
    * Binary tree paths: given a binary tree, return all root-to-leaf path
    * */
    public List<String> binaryTreePaths(TreeNode root){
        //handle edge case
        List<String> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        binaryTreePathUtil(root, result, "");
        return result;
    }

    private void binaryTreePathUtil(TreeNode node, List<String> result, String temp){
        //DFS the tree, append temp to result at base case layer
        if(node.left == null && node.right == null){
            result.add(temp + String.valueOf(node.val));
        }
        if(node.left != null){
            binaryTreePathUtil(node.left, result, temp + String.valueOf(node.val) + "->");
        }
        if(node.right != null){
            binaryTreePathUtil(node.right, result, temp + String.valueOf(node.val) + "->");
        }
    }

    public List<String> binaryTreePathsIterative(TreeNode root){
        List<String> resultList = new ArrayList();
        if (root == null) {
            return resultList;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(root.val + "");
        while (!nodeQueue.isEmpty()) {
            TreeNode currNode = nodeQueue.poll();
            String item = pathQueue.poll();
            if (currNode.left == null && currNode.right == null) {
                resultList.add(item);
            }
            if (currNode.left != null) {
                nodeQueue.offer(currNode.left);
                pathQueue.offer(item + "->" + currNode.left.val);
            }
            if (currNode.right != null) {
                nodeQueue.offer(currNode.right);
                pathQueue.offer(item + "->" + currNode.right.val);
            }
        }
        return resultList;
    }

    /*
    * 100
    * Same tree: given two binary trees, write a function to check if they are the same or not
    * */
    public boolean isSameTree(TreeNode p, TreeNode q){
        //DFS to check shape -> check val
        //base cacse
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /*
    * Binary tree maximum path sum: Given a non-empty binary tree, find the maximum path sum.
    * The path must contain at least one node and does not need to go through the root.
    * */
    public int maxPathSumRecursive(TreeNode root){
        int result = Integer.MIN_VALUE;
        if(root == null){
            return 0;
        }
        maxPathSumUtil(root, result);
        return result;
    }

    private int maxPathSumUtil(TreeNode root, int temp){
        //bottom-up approach
        if(root == null){
            return 0;
        }
        int leftSum = Math.max(maxPathSumUtil(root.left, temp), 0); //involves greedy?
        int rightSum = Math.max(maxPathSumUtil(root.right, temp), 0);
        int sum = leftSum + rightSum + root.val;
        //update intermediate result
        temp = Math.max(temp, sum);
        return Math.max(leftSum, rightSum) + root.val;
    }

    public int maxPathSumIterative(TreeNode root){
        //path DOES NOT need to go through root

    }
}
