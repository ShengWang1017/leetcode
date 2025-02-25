package com.leetcode.binaryTree;

import java.util.*;

public class Solution {

    /**
     * 定义树的节点
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //先进先出的队列
        List<List<Integer>> res = new ArrayList<>();
        //!!! 考虑root为null的情况
        if (root==null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        int layer = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            res.add(new ArrayList<>());
            List<Integer> layerList = res.get(layer++);
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                if (current.left!=null) queue.add(current.left);
                if (current.right!= null) queue.add(current.right);
                layerList.add(current.val);
            }
        }
        return res;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //1. 递归关系：DFS(root) = DFS(root.left) +   F(root) + DFS(root.right)

        //2. 终止条件
        if (root==null) return res;

        //3. F是什么，以及递归
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));

        return res;
    }


    public boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }

    public boolean checkSymmetric(TreeNode p, TreeNode q) {
        if (p==null&&q==null) return true;
        if (p==null||q==null) return false;

        //check内容
        if (checkSymmetric(p.left, q.right) && checkSymmetric(p.right, q.left)) {
            return p.val == q.val;
        }else return false;

    }


    /**
     * 543. 二叉树的直径
     * @param root
     * @return
     */
    int maxDia = 0;
    public int diameterOfBinaryTree(TreeNode root){
        depth(root);
        return maxDia;
    }
    public int depth(TreeNode root) {
        //停止条件
        if (root==null) return 0;
        //1.递归关系,计算左右树的深度
        int depLeft = depth(root.left);
        int depRight = depth(root.right);

        //额外带计算一下直径
        int currentDia = depLeft+depRight;  //没有+1
        maxDia = Math.max(currentDia,maxDia);
        return Math.max(depLeft, depRight) +1 ;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {


        return helper(nums, 0, nums.length-1);
    }
    public TreeNode helper(int[] nums, int left, int right) {
        if (left>right) return null;

        int mid = (left+right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    long min = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root){
        return inOrder(root);
    }

    //中序遍历
    public boolean inOrder(TreeNode root) {
        if (root==null) return true;

        boolean bLeft = inOrder(root.left);
        if (root.val>min) min = root.val;
        else return false;
        boolean bRight = inOrder(root.right);
        return bLeft&&bRight;
    }

    public boolean lc98Helper(TreeNode root,long low,long top) {    // 递归，用上下界来限制
        if (root==null) return true;
       //check(root) = check(root.left)+check(root.right) + F(root)
        boolean checkLeft = lc98Helper(root.left, low, root.val);
        boolean checkRight = lc98Helper(root.right, root.val, top);
        //F: root.val>max && root.val<min
        if (checkLeft&&checkRight) return root.val > low && root.val < top;
        else return false;
    }

    /**
     * 230. 二叉搜索树中第 K 小的元素
     */
    int count = 0;
    int kth = -1;
    public int kthSmallest(TreeNode root,int k) {
        if (root==null) return kth;
        kthSmallest(root.left,k);
        if (++count == k) {
            kth= root.val;
        }
        kthSmallest(root.right,k);
        return kth;

    }

    /**
     * 199. 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     */
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();

        if (root==null) return res;
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left!=null) {
                    queue.add(node.left);
                }
                if (node.right!=null) {
                    queue.add(node.right);
                }
                if (i==n-1) res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 114. 二叉树展开为链表
     */
    List<TreeNode> nodeList;
    public void flatten(TreeNode root) {
        nodeList = new ArrayList<>();
        preOrder(root);
        nodeList.add(null);
        TreeNode pre = new TreeNode(-1);
        for (TreeNode treeNode : nodeList) {
            pre.right = treeNode;
            pre.left = null;
            pre = treeNode;
        }
    }
    public void preOrder(TreeNode root) {
        if (root == null) return;

        nodeList.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    HashMap<Integer,TreeNode> treeRes;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        for (int i : preorder) {
            preList.add(i);
        }
        for (int i : inorder) {
            inList.add(i);
        }
        treeRes = new HashMap<>();
        return lc105Helper(preList, inList, 0, preList.size() - 1);
    }

    public TreeNode lc105Helper(List<Integer> preorder, List<Integer> inorder , int l ,int r) {//l and r 是为了确定每次递归，当前子树所在的范围
        //终止条件
        if (l>r) return null;
//        int rootVal = preorder.get(l);
        treeRes.put(preorder.get(l),new TreeNode(preorder.get(l)));  //pre的0索引处，就是新的根节点
        int rootIndex = inorder.indexOf(preorder.get(l));
        //递归出，再分为左子树，右子树的区间
        TreeNode left = lc105Helper(preorder, inorder, l, rootIndex - 1);
        TreeNode right = lc105Helper(preorder, inorder, rootIndex + 1, r);
        treeRes.get(preorder.get(l)).left = left;
        treeRes.get(preorder.get(l)).right = right;
        return treeRes.get(preorder.get(l));

    }

    /**
     * 437. 路径总和 III
     * @param root
     * @param targetSum
     * @return
     */
/*    public int pathSum(TreeNode root, long targetSum) {
        if (root==null) return 0;
        int paths =rootSum(root,targetSum);
        paths += pathSum(root.left, targetSum);
        paths += pathSum(root.right, targetSum);
        return paths;
    }
    //返回总共有多少个线路ways,表示根root为起点的路径
    public int rootSum(TreeNode root, long target) {
        if (root==null) return 0;
        int way = 0;

        long val = root.val;
        if (val==target) way++;

        way+= rootSum(root.left,target - val);
        way += rootSum(root.right, target - val);
        return way;
    }*/

    /**
     * 437. 路径总和 III
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, long targetSum) {
        Map<Long, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0L, 1);   //!!!初始时要有前缀和为0，防止后续从root出发的前缀和等于target时，不能正确计数；
        return dfs(root, preSumMap, targetSum, 0);  //一开始前缀就是0？
    }

    //返回有多少条路径
    public int dfs(TreeNode root, Map<Long, Integer> preSum, long targetSum, long currentSum) {
        if (root==null) return 0;

        currentSum+=root.val;
        int ways = 0;
        if (preSum.containsKey(currentSum-targetSum)){
            // 这里不是ways++，因为可能已经存在很多条
            ways += preSum.get(currentSum - targetSum);
        }

        preSum.put(currentSum, preSum.getOrDefault(currentSum,0) + 1);
        ways += dfs(root.left, preSum, targetSum, currentSum);
        ways += dfs(root.right, preSum, targetSum, currentSum);

        //!!!回溯，将map中的当前减去
        preSum.put(currentSum, preSum.get(currentSum) - 1);
        if (preSum.get(currentSum)==0) preSum.remove(currentSum);

        return ways;

    }

    TreeNode res ;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findPQ(root, p, q);
        return res;
    }
    public int findPQ(TreeNode root,TreeNode p , TreeNode q) {
        if (root==null) return 0;
        int visitedNode = 0;
        if (root==p||root==q) visitedNode++;
        visitedNode+= findPQ(root.left, p, q);
        visitedNode += findPQ(root.right, p, q);
        if (visitedNode == 2) {
            res = root;
            visitedNode++;//避免最近祖父节点更远的节点，成为结果返回，找到该节点之后，所有其他的节点都不可能是了。
        }
        return visitedNode;
    }

    int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    public int maxGain(TreeNode root) {
        if (root==null) return 0;
        int currentPathSum =root.val;

        int leftCore = maxGain(root.left);
        int rightCore = maxGain(root.right);
        //两边贡献值都小于0,当前节点值就是最大贡献值
        if (leftCore <= 0 && rightCore <= 0) {
            maxPathSum = Math.max(maxPathSum, currentPathSum);
            return root.val;
        }
        if (leftCore>0) currentPathSum+=leftCore;
        if (rightCore>0) currentPathSum += rightCore;
        //更新 maxPathSum
        maxPathSum = Math.max(maxPathSum, currentPathSum);

        return Math.max(leftCore,rightCore)+root.val;
    }


}
