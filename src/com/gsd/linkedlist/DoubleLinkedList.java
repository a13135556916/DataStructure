package com.gsd.linkedlist;

import java.util.Stack;

/**
 * 双向链表节点
 */
public class DoubleLinkedList {

    public HeroNode2 getHead() {
        return head;
    }

    public static void main(String[] args) {

        DoubleLinkedList list = new DoubleLinkedList();

        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(3, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(5, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(7, "林冲", "豹子头");
        HeroNode2 nodeGsd = new HeroNode2(9, "dguo", "大帅哥");


        HeroNode2 node6 = new HeroNode2(2, "aa", "及时雨");
        HeroNode2 node8 = new HeroNode2(4, "cc", "智多星");
        HeroNode2 node9 = new HeroNode2(6, "dd", "豹子头");
        HeroNode2 node10 = new HeroNode2(8, "ee", "大帅哥");
        HeroNode2 node7 = new HeroNode2(10, "bb", "玉麒麟");
        HeroNode2 newNode7 = new HeroNode2(4, "dd", "小郭");

        DoubleLinkedList listB = new DoubleLinkedList();
        listB.add(node8);
        listB.add(node9);
        listB.add(node6);
        listB.add(node7);
        listB.add(node10);
        listB.updateNode(newNode7);
        listB.list();
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
        System.out.println("正常输出");
        list.list();
        System.out.println("倒着遍历");

        HeroNode2 lastNodeTemp = list.head;
        while (lastNodeTemp.next!=null){
            lastNodeTemp = lastNodeTemp.next;        }
        while (lastNodeTemp.prev!=null){
            System.out.println(lastNodeTemp);
            lastNodeTemp = lastNodeTemp.prev;
        }


       /* HeroNode2 node5 = new HeroNode2(4, "林冲!!", "豹子头~~");
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
        HeroNode2 node = getLastIndexNode(list, count);
        System.out.println(node);


//        System.out.println("获取反转后的链表");
//        DoubleLinkedList reverseLink = getReverseLink(list);
//        reverseLink.list();
        System.out.println("测试逆序打印链表");
        reversePrint(list.getHead());

        System.out.println("测试合并两个有序的链表");
        list.list();
        DoubleLinkedList newLinkedList = mergeLinkedList(list, listB);
        newLinkedList.list();*/
    }

    /**
     * 获取单链表中有效节点的个数
     *
     * @return
     */
    public static Integer getValidCount(DoubleLinkedList linkedList) {
        Integer count = 0;
        HeroNode2 cur = linkedList.getHead().next;
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
    public static HeroNode2 getLastIndexNode(DoubleLinkedList linkedList, Integer count) {
        Integer length = getValidCount(linkedList);
        if (count > length) {
            System.out.println("输入的数字无效");
            return null;
        } else {
            Integer index = length - count;
            HeroNode2 node = linkedList.getHead();
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
    public static void reversePrint(HeroNode2 head) {
        if (head.next == null) {
            return;
        }
        HeroNode2 node = head.next;
        Stack<HeroNode2> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        while (!stack.empty()) {
            HeroNode2 pop = stack.pop();
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
    public static DoubleLinkedList mergeLinkedList(DoubleLinkedList linkedA, DoubleLinkedList linkedB) {
        HeroNode2 headA = linkedA.getHead();
        HeroNode2 headB = linkedB.getHead();
        if (headA.next != null && headB.next != null) {
            HeroNode2 tempB = headB;
            //遍历链表B的节点
            while (tempB != null) {
                //至关重要的下一个临时下一个节点
                HeroNode2 next = tempB.next;
                HeroNode2 tempA = headA;
                while (tempA.next != null) {
                    //找到那个合适的节点:当前节点的下一个节点编号大于当前节点编号的
                    if (tempA.next.no > tempB.no) {
                        break;
                    }
                    tempA = tempA.next;
                }
                //把3个节点串起来
                HeroNode2 temp = tempA.next;
                tempA.next = tempB;
                tempB.next = temp;
                tempB = next;
            }
        } else {
            System.out.println("两个链表均不能为空节点");
        }
        DoubleLinkedList newLinkedList = new DoubleLinkedList();
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
    public static DoubleLinkedList getReverseLink(DoubleLinkedList linkedList) {
        DoubleLinkedList newLinked = new DoubleLinkedList();
        HeroNode2 newHead = newLinked.head;
        HeroNode2 cur = linkedList.getHead().next;
        while (cur != null) {
            //关键的地方 提前获取当前节点的下一个节点
            HeroNode2 nextNode = cur.next;
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
    HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 最后一个节点
     */
    HeroNode2 lastNode = new HeroNode2();

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(HeroNode2 node) {
        //需要先找到最后一个节点
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.prev = temp;
        lastNode = node;
    }

    /**
     * 修改节点
     *
     * @param node
     */
    public void updateNode(HeroNode2 node) {
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到该节点,删除失败");
        } else {
            temp.prev = temp.next;
        }
    }

    /**
     * 添加节点
     * 根据节点的编号顺序实现
     * 如果添加的编号在链表中已存在,则添加失败
     *
     * @param node
     */
    public void addWithNo(HeroNode2 node) {
        HeroNode2 temp = head;
        boolean flag = false;
        boolean isLast = false;
        while (true) {
            //说明已经到了最后的节点
            if (temp.next == null) {
                isLast = true;
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
        //指向上一个节点
        node.prev = temp;
        //只有遍历到最后一个节点的时候,才更新最后一个节点的属性
        if (temp!=null && isLast){
            lastNode = temp;
        }
    }


    /**
     * 输出节点信息
     */
    public void list() {
        HeroNode2 temp = head.next;
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


/**
 * 改造成 双向链表的节点,其实也就是增加一个 preNode
 */
class HeroNode2 {
    String name;
    int no;
    String nickName;
    HeroNode2 next;
    HeroNode2 prev;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode2() {
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "name='" + name + '\'' +
                ", no=" + no +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}