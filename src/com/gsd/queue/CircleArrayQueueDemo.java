package com.gsd.queue;

import java.util.Scanner;

/**
 * @Author dguo
 * @Date 2022/12/18 09:17
 * @Version 1.0
 */
public class CircleArrayQueueDemo {
}


/**
 * 环形数组队列
 */
class CircleArrayQueue {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(5);
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

    int maxSize;
    //下标从0开始计算，作为队列头的下标
    int front;
    //下标从0 开始 作为队列最后一个元素的下一个位置，占用一个空间作为预留使用
    int rear;
    int[] arr;

    CircleArrayQueue(int size) {
        maxSize = size;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满了
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }


    /**
     * 添加一个元素
     *
     * @param num
     */
    public void addItem(int num) {
        if (isFull()) {
            System.out.println("队列已经满啦");
            return;
        }
        arr[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 取一个元素
     */
    public void getItem() {
        if (isEmpty()) {
            System.out.println("当前队列是空，没有数据可取");
            return;
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        System.out.println(value);
    }

    /**
     * 显示队列的内容
     */
    public void showArray() {
        if (isEmpty()){
            System.out.println("当前队列是空，没有数据可取");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 获取循环数组中的有效数量
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public void showHead() {
        if (!isEmpty()) {
            System.out.println("当前获取到的元素为:" + arr[front]);
        } else {
            System.out.println("当前队列是空，没有数据可取");
        }
    }


}
