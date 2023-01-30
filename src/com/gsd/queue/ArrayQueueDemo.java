package com.gsd.queue;

import java.util.Scanner;

/**
 * @Author dguo
 * @Date 2022/12/16 21:50
 * @Version 1.0
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        char key = ' ';
        while (loop) {
            System.out.println("a (add) 添加一个元素");
            System.out.println("s (show) 显示所有元素");
            System.out.println("g (get) 获取一个元素");
            System.out.println("h (head) 显示队列的第一个元素");
            System.out.println("e (exit) 退出");
            Scanner scanner = new Scanner(System.in);
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("请输入一个数字");
                    int num = scanner.nextInt();
                    queue.addItem(num);
                    break;
                case 's':
                    queue.showArray();
                    break;
                case 'g':
                    queue.getItem();
                    break;
                case 'h':
                    queue.showHead();
                    break;
                case 'e':
                    loop = false;
                    break;

            }
        }
        System.out.println("程序结束!");
    }
}

/**
 * 自定义一个队列
 */
class ArrayQueue {

    /**
     * 队列的最大长度
     */
    int maxSize;
    /**
     * 队列的头部下标
     */
    int front;
    /**
     * 队列的尾部下标
     */
    int rear;
    /**
     * 存放数据的容器
     */
    int[] arr;

    ArrayQueue(int size) {
        maxSize = size;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    /**
     * 默认的无参构造长度为10
     */
    ArrayQueue() {
        maxSize = 10;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        if (rear == maxSize - 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 添加一个元素
     *
     * @param num
     */
    public void addItem(int num) {
        if (!isFull()) {
            rear++;
            arr[rear] = num;
        } else {
            System.out.println("队列已经满啦");
        }
    }

    /**
     * 取一个元素
     */
    public void getItem() {
        if (!isEmpty()) {
            front++;
            System.out.println("当前获取到的元素为:" + arr[front]);
        } else {
            System.out.println("当前队列是空，没有数据可取");
        }
    }

    /**
     * 显示队列的内容
     */
    public void showArray() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    public void showHead() {
        if (!isEmpty()) {
            System.out.println("当前获取到的元素为:" + arr[front]);
        } else {
            System.out.println("当前队列是空，没有数据可取");
        }
    }

}