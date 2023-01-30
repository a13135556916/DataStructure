package com.gsd.linkedlist;

/**
 * 约瑟夫问题
 *
 * @Author dongdongguo
 * @Date 2023/1/30 14:12
 * @Version 1.0
 */
public class Josepfu {
    public static void main(String[] args) {

        /**
         * 解决约瑟夫问题可以使用两种方式,1.环形链表 2.数组取模,使用环形链表更加直观一些
         * 步骤如下:
         * 1. 创建一个环形链表
         *  1.1 创建节点
         *  1.2 创建环形链表 ,并且提供基础的方法
         *
         * 2. 使用环形链表解决约瑟夫问题
         */

        //初始化环形链表
        CircleLinkedList linkedList = new CircleLinkedList();
        linkedList.addBoys(50);
        linkedList.showBoys();
        //开始解决约瑟夫问题
        linkedList.outBoy(10, 50, 10);
    }


}

class CircleLinkedList {

    //头节点
    private Boy first;

    /**
     * 添加小孩
     *
     * @param num
     */
    public void addBoys(Integer num) {
        if (num < 1) {
            System.out.println("小孩的数量必须大于0");
            return;
        }
        //临时变量
        Boy curBoy = null;
        for (Integer i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(boy);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 显示小孩
     */
    public void showBoys() {
        Boy curBoy = first;
        if (curBoy == null) {
            System.out.println("环形链表中没有小孩");
            return;
        }
        while (true) {
            System.out.println("当前是第" + curBoy.getNo() + "个小孩");
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 开始进行出队操作
     *
     * @param startNo 从第几个小孩开始
     * @param sum     共计多少个小孩
     * @param count   数几次
     */
    public void outBoy(Integer startNo, Integer sum, Integer count) {
        if (startNo < 1 || startNo > sum || count > sum) {
            System.out.println("参数错误!");
            return;
        }
        Boy helper = first;
        //先将Helper指向first节点的前一个节点,这里的helper节点是为了用于后面的出队使用:单项环形链表出队需要获取到目标节点的前一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //初始化两个节点的位置
        for (Integer i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //开始玩约瑟夫游戏
        while (true) {
            if (helper == first) {
                break;
            }
            //由于报号需本身也要报,所以次数是总数-1
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("当前出队的小孩是" + first.getNo());
            //小孩出队 头结点指向下一个节点
            first = first.getNext();
            //helper节点指向头结点
            helper.setNext(first);
        }
        System.out.println("最后一个小孩是:" + first.getNo());
    }

    public Boy getFirst() {
        return first;
    }

    public void setFirst(Boy first) {
        this.first = first;
    }
}

/**
 * 创建一个节点,用于存放小孩对象
 */
class Boy {
    private Integer no;

    private Boy next;

    public Boy(Integer boy) {
        this.no = boy;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

