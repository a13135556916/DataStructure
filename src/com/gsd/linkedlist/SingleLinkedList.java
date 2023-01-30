package com.gsd.linkedlist;

import java.util.Stack;

/**
 * @Author dguo
 * @Date 2022/12/19 15:05
 * @Version 1.0
 */
public class SingleLinkedList {


    public HeroNode getHead() {
        return head;
    }

    public static void main(String[] args) {

        SingleLinkedList list = new SingleLinkedList();

        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(5, "吴用", "智多星");
        HeroNode node4 = new HeroNode(7, "林冲", "豹子头");
        HeroNode nodeGsd = new HeroNode(9, "dguo", "大帅哥");


        HeroNode node6 = new HeroNode(2, "aa", "及时雨");
        HeroNode node8 = new HeroNode(4, "cc", "智多星");
        HeroNode node9 = new HeroNode(6, "dd", "豹子头");
        HeroNode node10 = new HeroNode(8, "ee", "大帅哥");
        HeroNode node7 = new HeroNode(10, "bb", "玉麒麟");

        SingleLinkedList listB = new SingleLinkedList();
        listB.add(node6);
        listB.add(node7);
        listB.add(node8);
        listB.add(node9);
        listB.add(node10);
        //顺序添加
//        list.add(node1);
//        list.add(node2);
//        list.add(node3);
//        list.add(node4);
        //排序添加
        list.addWithNo(node3);
        list.addWithNo(node4);
        list.addWithNo(node1);
        list.addWithNo(node2);
        list.addWithNo(nodeGsd);
        System.out.println("修改前");
        list.list();
        HeroNode node5 = new HeroNode(4, "林冲!!", "豹子头~~");
        list.updateNode(node5);
        System.out.println("修改后...");
        list.list();
//        System.out.println("删除两个节点...删除后");
//        list.delNode(3);
//        list.delNode(4);
//        list.list();
//        list.delNode(6);
        System.out.println("获取单链表的有效个数");
        System.out.println(getValidCount(list));
        int count = 1;
        System.out.println("获取倒数第" + count + "个节点信息");
        HeroNode node = getLastIndexNode(list, count);
        System.out.println(node);


//        System.out.println("获取反转后的链表");
//        SingleLinkedList reverseLink = getReverseLink(list);
//        reverseLink.list();
        System.out.println("测试逆序打印链表");
        reversePrint(list.getHead());

        System.out.println("测试合并两个有序的链表");
        list.list();
        SingleLinkedList newLinkedList = mergeLinkedList(list, listB);
        newLinkedList.list();
    }

    /**
     * 获取单链表中有效节点的个数
     *
     * @return
     */
    public static Integer getValidCount(SingleLinkedList linkedList) {
        Integer count = 0;
        HeroNode cur = linkedList.getHead().next;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 获取单链表的倒数第K个节点
     *
     * @param linkedList 链表对象
     * @param count      倒数第几个的数字
     * @return
     */
    public static HeroNode getLastIndexNode(SingleLinkedList linkedList, Integer count) {
        Integer length = getValidCount(linkedList);
        if (count > length) {
            System.out.println("输入的数字无效");
            return null;
        } else {
            Integer index = length - count;
            HeroNode node = linkedList.getHead();
            for (int i = index; i >= 0; i--) {
                node = node.next;
            }
            return node;
        }
    }

    /**
     * 逆向打印链表,使用栈的方式
     * 栈的特点是先进后出,也就是最先压入的东西最后被弹出,实现逆序的效果
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        HeroNode node = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        while (!stack.empty()) {
            HeroNode pop = stack.pop();
            System.out.println(pop);
        }
    }

    /**
     * 合并两个链表对象,并且根据节点中的no进行有序的合并
     *
     * @param linkedA
     * @param linkedB
     * @return
     */
    public static SingleLinkedList mergeLinkedList(SingleLinkedList linkedA, SingleLinkedList linkedB) {
        HeroNode headA = linkedA.getHead();
        HeroNode headB = linkedB.getHead();
        if (headA.next != null && headB.next != null) {
            HeroNode tempB = headB;
            //遍历链表B的节点
            while (tempB != null) {
                //至关重要的下一个临时下一个节点
                HeroNode next = tempB.next;
                HeroNode tempA = headA;
                while (tempA.next != null) {
                    //找到那个合适的节点:当前节点的下一个节点编号大于当前节点编号的
                    if (tempA.next.no > tempB.no) {
                        break;
                    }
                    tempA = tempA.next;
                }
                //把3个节点串起来
                HeroNode temp = tempA.next;
                tempA.next = tempB;
                tempB.next = temp;
                tempB = next;
            }
        } else {
            System.out.println("两个链表均不能为空节点");
        }
        SingleLinkedList newLinkedList = new SingleLinkedList();
        newLinkedList.head = headA;
        return newLinkedList;
    }

    /**
     * 获取反转的链表
     * 关键点是要提前获取到当前节点的下个节点,将其暂存起来,等到新链表关系添加完成后用于
     * 下一次循环使用
     *
     * @return
     */
    public static SingleLinkedList getReverseLink(SingleLinkedList linkedList) {
        SingleLinkedList newLinked = new SingleLinkedList();
        HeroNode newHead = newLinked.head;
        HeroNode cur = linkedList.getHead().next;
        while (cur != null) {
            //关键的地方 提前获取当前节点的下一个节点
            HeroNode nextNode = cur.next;
            //当前节点的下一个节点指向新链表的第一个节点
            cur.next = newHead.next;
            //新链表的第一个节点指向当前的节点
            newHead.next = cur;
            //将顺序遍历的下个节点赋值给当前节点
            cur = nextNode;
        }
        linkedList.head.next = newLinked.head.next;
        return linkedList;
    }

    /**
     * 初始化一个头节点
     */
    HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        //需要先找到最后一个节点
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 修改节点
     *
     * @param node
     */
    public void updateNode(HeroNode node) {
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到该节点,修改失败");
        } else {
            temp.name = node.name;
            temp.nickName = node.nickName;
        }
    }

    /**
     * 根据编号删除节点
     *
     * @param no
     */
    public void delNode(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到该节点,删除失败");
        } else {
            temp.next = temp.next.next;
        }
    }

    /**
     * 添加节点
     * 根据节点的编号顺序实现
     * 如果添加的编号在链表中已存在,则添加失败
     *
     * @param node
     */
    public void addWithNo(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            //说明已经到了最后的节点
            if (temp.next == null) {
                break;
            }
            //说明遍历到的节点编号已经大于目标节点编号,也就是找到了这个位置
            if (temp.next.no > node.no) {
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            System.out.printf("当前编号[%d]已存在,添加失败!\n", node.no);
            return;
        }
        //新节点的下一个节点指向 遍历到的节点的下一个节点
        node.next = temp.next;
        //遍历到的下一个节点指向新节点
        temp.next = node;

    }


    /**
     * 输出节点信息
     */
    public void list() {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("节点为空,程序结束...");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }
}


class HeroNode {
    String name;
    int no;
    String nickName;
    HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", no=" + no +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}