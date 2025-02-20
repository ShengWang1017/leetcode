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




}
