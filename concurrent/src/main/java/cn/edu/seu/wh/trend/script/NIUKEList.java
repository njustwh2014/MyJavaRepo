/*
 * @FileName: NIUKEList.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/11/5 下午2:23
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.script;

/**
 * @program:concurrent
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2019-11-05 14:23
 **/
class ListNode{
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class NIUKEList {

    public static ListNode deleteDuplication(ListNode pHead)
    {
        /*
        总体思路是判断当前节点与下一个节点是否重复，如重复，则循环往下遍历直到与当前节点不重复。
        链表操作技巧，设置一个头节点
        */
        if(pHead==null||pHead.next==null){
            return pHead;
        }
        ListNode newHead=new ListNode(0);
        newHead.next=pHead;
        ListNode prev=newHead;
        while(pHead!=null&&pHead.next!=null){
            if(pHead.val==pHead.next.val){
                //存在重复节点
                ListNode cursor=pHead.next;
                while(cursor!=null&&cursor.val==pHead.val){
                    cursor=cursor.next;
                }
                prev.next=cursor;
                pHead=cursor;
            }else{
                pHead=pHead.next;
                prev=prev.next;
            }
        }
        return newHead.next;
    }

    private static ListNode arrayTransfer2LinkList(int[] nums){
        if(nums==null||nums.length==0){
            return null;
        }
        ListNode head=new ListNode(nums[0]);
        ListNode cursor=head;
        for(int i=1;i<nums.length;i++){
            cursor.next=new ListNode(nums[i]);
            cursor=cursor.next;
        }
        return head;
    }

    private static void displayLinkedList(ListNode head){
        int i=0;
        while(head!=null){
            System.out.println("Node: "+i+"  ###val: "+head.val);
            i++;
            head=head.next;
        }
    }

    public static void main(String[] args) {

        //1233445
        int[] nums=new int[]{1,2,3,3,4,4,5};
        ListNode head=arrayTransfer2LinkList(nums);
        displayLinkedList(deleteDuplication(head));
    }
}
