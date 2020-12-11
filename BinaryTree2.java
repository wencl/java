package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree2 {

    class Node2<K,V>{
        Node2<K,V> parent;
        Node2<K,V> left;
        Node2<K,V> right;
        int val;
        boolean red = true;
        Node2(int val){
            this.val = val;
        }
    }

    //根据数据组成二叉树,只支持完全二叉树
    public Node2 initBinartTree(int[] arr){
        if(arr == null)
            return null;
        List<Node2> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            list.add(new Node2(arr[i]));
        }
        int index = 0;
        while((2*index+2)<=arr.length){
            if(arr[2*index+1]!=0)
                list.get(index).left = list.get(2*index+1);
            if(arr[2*index+2]!=0)
                list.get(index).right = list.get(2*(index+1));
            index++;
        }
        return list.get(0);
    }

    /**
     * 初始化非完全二叉树
     * @param arr
     * @return
     */
    public Node2 initNotWholeBinaryTree(int[] arr){
        if(arr == null) return null;
        List<Node2> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            list.add(new Node2(arr[i]));
        }
        Queue<Node2> queue  = new LinkedList<>();
        queue.offer(list.get(0));
        int i = 1;
        while(!queue.isEmpty()){
            Node2 node = queue.poll();
            if(node.val == 0)
                continue;
            if( i<arr.length){
                node.left = list.get(i++);
                queue.offer(node.left);
            }

            if( i<arr.length) {
                node.right = list.get(i++);
                queue.offer(node.right);
            }
        }
        return list.get(0);
    }

    public Node2 root(Node2 node){
        Node2 p;
        while((p = node.parent)!=null){
            node = p;
        }
        return node;
    }

    public void searchBefore(Node2 node){
        if(node.val!=0)
            System.out.println(node.val);
        if(node.left!=null)
            searchBefore(node.left);
        if(node.right!=null)
            searchBefore(node.right);
    }

    public void searchMiddle(Node2 node){

        if(node.left!=null)
            searchMiddle(node.left);
        if(node.val!=0)
            System.out.println(node.val);
        if(node.right!=null)
            searchMiddle(node.right);
    }

    public void searchAfter(Node2 node){

        if(node.left!=null)
            searchAfter(node.left);
        if(node.right!=null)
            searchAfter(node.right);
        if(node.val!=0)
            System.out.println(node.val);
    }

    public void searchFlat(List<Integer> list,Node2 node){
        Queue<Node2> queue = new LinkedList<>();
        queue.offer(root(node));
        while(!queue.isEmpty()){
            Node2 tmp = queue.poll();
            if(tmp.val!=0)
                list.add(tmp.val);
            if(tmp.left!=null){
                queue.offer(tmp.left);
            }
            if(tmp.right!=null)
                queue.offer(tmp.right);
        }

    }

    public int searchLevel(Node2 node){
        if(node == null || node.val == 0)
            return 0;
        int left = searchLevel(node.left);
        int right = searchLevel(node.right);
        return Math.max(left,right)+1;
    }

    //查找每层得节点，0节点除外
    public void getLevelNodes(List<Node2> list,Node2 node, int level){
        if(level == 1){
            list.add(node);
        }
        if(node.left!=null && node.left.val!=0){
            getLevelNodes(list,node.left,level-1);
        }
        if(node.right!=null&& node.right.val!=0){
            getLevelNodes(list,node.right,level-1);
        }
    }

    public static void main(String[] args) {
        int[] arr =  {1,2,3,0,4,5,6,0,7,0,8,9,0};
        BinaryTree2 bt = new BinaryTree2();
        //根据数组构造二叉树
//        System.out.println("根据数组构造完全二叉树");
//        Node2 node= bt.initBinartTree(arr);
        System.out.println("根据数组构造非完全二叉树");
        Node2 node= bt.initNotWholeBinaryTree(arr);

        //前序遍历
        System.out.println("前序遍历");
        bt.searchBefore(node);
        //中序遍历
        System.out.println("中序遍历");
        bt.searchMiddle(node);
        //后序遍历
        System.out.println("后序遍历");
        bt.searchAfter(node);
        //层序遍历
        System.out.println("层序遍历");
        List<Integer> list = new ArrayList<>();
        bt.searchFlat(list,node);
        for(Integer i:list)
            System.out.println(i);
        //查找树有多少层
        System.out.println("查找树有多少层");
        System.out.println(bt.searchLevel(node));

        //查找每层的节点数
        System.out.println("查找每层的级节点数");
        List<Node2> nodes = new ArrayList<>();
        bt.getLevelNodes(nodes,node,3);
        for(Node2 n: nodes){
            System.out.println(n.val);
        }
    }
}
