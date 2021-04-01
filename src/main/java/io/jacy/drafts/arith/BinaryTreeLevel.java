package io.jacy.drafts.arith;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevel {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l1l = new TreeNode(3);
        TreeNode l1r = new TreeNode(5);
        TreeNode l2l1 = new TreeNode(7);
        TreeNode l2r1 = new TreeNode(8);

        TreeNode l2l2 = new TreeNode(2);
        TreeNode l2r2 = new TreeNode(4);

        root.left = l1l;
        root.right = l1r;

        l1l.left = l2l1;
        l1l.right = l2r1;

        l1r.left = l2l2;
        l1r.right = l2r2;


        List<List<Integer>> levels = loop(root);
        for (List<Integer> level : levels) {
            System.out.println(level);
        }
    }

    static List<List<Integer>> loop(TreeNode node) {
        List<List<Integer>> levelList = new ArrayList<>();
        if (node == null) {
            return levelList;
        }

//        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.remove(0);
                level.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

            levelList.add(level);
        }


        return levelList;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
