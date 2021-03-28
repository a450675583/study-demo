package com.elgin.study.demo.leetcode;

import com.elgin.study.demo.linklist.Node;
import com.google.common.collect.Lists;

import javax.sound.midi.Soundbank;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 合并2个有序链表
 * @author zxs
 * 2021/3/28
 */
public class MergeTwoLists21 {

    public static void main(String[] args) {
        Node l1 = Node.createNode(Lists.newArrayList(6,4,2,1));
        Node l2 = Node.createNode(Lists.newArrayList(12,9,8,5,3,2));
        System.out.println(mergeTwoLists(l1,l2));
    }

    public static Node mergeTwoLists(Node l1, Node l2) {
        if (l1 == null){
            return l2;
        }

        if(l2 == null){
            return l1;
        }

        Node dumpHead = new Node(-1);
        Node preNode = dumpHead;
        while (l1!=null && l2!=null){
            if(l1.item < l2.item){
                preNode.next = l1;
                l1 = l1.next;
            }else {
                preNode.next = l2;
                l2 = l2.next;
            }
            preNode = preNode.next;
        }

        if(l1 !=null ){
            preNode.next = l1;
        }

        if(l2 !=null){
            preNode.next = l2;
        }

        return dumpHead.next;
    }
}