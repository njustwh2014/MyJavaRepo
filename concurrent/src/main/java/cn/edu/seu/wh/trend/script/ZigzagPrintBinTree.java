/*
 * @FileName: ZigzagPrintBinTree.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/11/8 下午2:37
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.script;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @program:concurrent
 * @description:之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2019-11-08 14:37
 **/
class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
public class ZigzagPrintBinTree {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        /*思路：
        利用栈，用奇偶层实现
         */
        if(pRoot==null){
            return null;
        }
        ArrayList<ArrayList<Integer>> ret=new ArrayList<>();
        int layer=1;
        Stack<TreeNode> stack1=new Stack<>();//存奇数层节点
        Stack<TreeNode> stack2=new Stack<>();//存偶数层节点
        stack1.push(pRoot);
        while(!stack1.empty()||!stack2.empty()){
            if(layer%2!=0){
                //奇数层
                ArrayList<Integer> temp=new ArrayList<>();
                while(!stack1.empty()){
                    TreeNode treeNode=stack1.pop();
                    if(treeNode!=null){
                        stack2.push(treeNode.left);
                        stack2.push(treeNode.right);
                        temp.add(treeNode.val);
                    }
                }
                if(!temp.isEmpty()){
                    layer++;
                    ret.add(temp);
                }
            }else{
                //偶数层
                ArrayList<Integer> temp=new ArrayList<>();
                while(!stack2.empty()){
                    TreeNode treeNode=stack2.pop();
                    if(treeNode!=null){
                        stack1.push(treeNode.right);
                        stack1.push(treeNode.left);
                        temp.add(treeNode.val);
                    }
                }
                if(!temp.isEmpty()){
                    ret.add(temp);
                    layer++;
                }
            }
        }
        return ret;
    }


}
