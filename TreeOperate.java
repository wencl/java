package org.example;

public class TreeOperate<K,V> {

    //二叉搜索树
    public TreeNode<K,V> insert(TreeNode<K,V> root,K k,V v){
        TreeNode<K,V> node = new TreeNode(k,v);//新进节点是红色的
        if(root == null) {
            root = node;
            root.red = false;//根节点是黑色的
        }else{
            if(compare(root.v,v)<0){
                if(root.right == null){
                    node.parent = root;
                    root.right = node;
                }else{
                    insert(root.right,k,v);
                }
            }else if(compare(root.v,v)>0){
                if(root.left == null){
                    node.parent = root;
                    root.left = node;
                }else{
                    insert(root.left,k,v);
                }
            }
        }
        return root;
    }

    public int compare(V v1,V v2){
        if(v1 instanceof Integer){
            Integer a1 = (Integer) v1;
            Integer a2 = (Integer) v2;
            return a1-a2;
        }else{
            return 1;
        }
    }

    public void balance(TreeNode<K,V> node){
        //1.如果父亲节点为红色，叔叔节点为黑色，直接把父亲节点换成红色，自己换成黑色
        if(compare(node.parent.v,node.parent.parent.v)<0){//父亲节点小于爷爷节点的值，说明在左节点，叔叔节点在右节点
            if(node.parent.red && !node.parent.parent.right.red){//父亲节点是红色，叔叔节点是黑色

            }
        }

    }

    public static void main(String[] args) {
        TreeOperate<String,Integer> to = new TreeOperate();
        TreeNode<String,Integer> root = null;
        for(int i =1,j=1;i<50;j++,i+=j){
            root = to.insert(root,"a"+i,i);
        }
        //结果为链表形式，需要自平衡
        System.out.println(1);
    }
}
