/*
 * @FileName: SerializeAndDeserializeBinTree.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/11/8 下午3:43
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.script;

import java.util.LinkedList;

/**
 * @program:concurrent
 * @description:层序方法实现序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2019-11-08 15:43
 **/

public class SerializeAndDeserializeBinTree {
    String serialize(TreeNode root) {
        //序列化二叉树
        LinkedList<TreeNode> list=new LinkedList<>();
        StringBuilder stringBuilder=new StringBuilder();
        if(root==null){
            return stringBuilder.toString();
        }
        list.add(root);
        while(list.size()>0){
            TreeNode treeNode=list.poll();
            if(treeNode!=null){
                list.add(treeNode.left);
                list.add(treeNode.right);
                stringBuilder.append(String.valueOf(treeNode.val)+"!");
            }else{
                stringBuilder.append("#");
            }
        }
        return stringBuilder.toString();
    }
    TreeNode deserialize(String str) {
        if(str==null||str.isEmpty()){
            return null;
        }
        if(str.charAt(0)=='#'){
            return null;
        }
        TreeNode root=null;
        int i=0;
        for(;i<str.length();i++){
            if(str.charAt(i)=='!'){
                root=new TreeNode(Integer.valueOf(str.substring(0,i)));
                break;
            }
        }
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(root);
        while(list.size()>0){
            TreeNode treeNode=list.poll();
            if(treeNode!=null) {
                i++;
                int j = i;
                for (; i < str.length(); i++) {
                    if (str.charAt(i) == '#') {
                        treeNode.left = null;
                        break;
                    }
                    if (str.charAt(i) == '!') {
                        treeNode.left = new TreeNode(Integer.valueOf(str.substring(j, i)));
                        list.add(treeNode.left);
                        break;
                    }
                }
                i++;
                j = i;
                for (; i < str.length(); i++) {
                    if (str.charAt(i) == '#') {
                        treeNode.right = null;
                        break;
                    }
                    if (str.charAt(i) == '!') {
                        treeNode.right = new TreeNode(Integer.valueOf(str.substring(j, i)));
                        list.add(treeNode.right);
                        break;
                    }
                }

            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode cursor=root;
        cursor.left=new TreeNode(2);
        cursor=cursor.left;
        cursor.right=new TreeNode(3);
        cursor.left=new TreeNode(4);
        SerializeAndDeserializeBinTree serializeAndDeserializeBinTree=new SerializeAndDeserializeBinTree();
        System.out.println(serializeAndDeserializeBinTree.serialize(root));
        System.out.println(serializeAndDeserializeBinTree.deserialize(serializeAndDeserializeBinTree.serialize(root)).toString());
    }

}
