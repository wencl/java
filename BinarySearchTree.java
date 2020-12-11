package org.example;

/**
 * 二分查找
 */
public class BinarySearchTree {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,6,7,8,9};
        int b = new BinarySearchTree().find(a,10);
        System.out.println(b);
    }

    public int find(int[] a , int b){
        int low = 0;
        int up = a.length-1;
        int middle = low + (up-low)>>1;
        for(;low<=middle && middle <=up;){
            if(a[middle]<b){
                low = middle+1;
            }else if(a[middle]>b){
                up = middle-1;
            }else{
                return middle;
            }
        }
        return -1;
    }
}
