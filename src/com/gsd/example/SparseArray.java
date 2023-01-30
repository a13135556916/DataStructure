package com.gsd.example;

import java.io.*;

/**
 * 稀疏数组
 *
 * @Author dguo
 * @Date 2022/12/8 18:16
 * @Version 1.0
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //第一步 创建一个普通的二维数组
        int[][] array = new int[11][11];
        //我们的规则是1是黑子 2是白子(围棋)
        array[2][3] = 1;
        array[3][4] = 2;
        array[6][6] = 1;
        //输出一下这个二维数组
        System.out.println("原始数组...");
        for (int[] row : array) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }

        //第二步 将普通数组转换为稀疏数组
        //首先获取这个数组的有效元素个数
        int sum = 0;
        for (int[] row : array) {
            for (int item : row) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        System.out.println("稀疏数组...");
        //创建一个稀疏数组 第一行用来存储二维数组的行数与列数还有有效元素数量
        int[][] sparseArray = new int[sum + 1][3];
        int count = 0;
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        System.out.println("输出稀疏数组...");
        for (int[] row : sparseArray) {
            for (int i = 0; i < 3; i++) {
                System.out.printf("%d\t", row[i]);
            }
            System.out.println();
        }

        writeToFile(sparseArray);

        sparseArray = readFromFileToSparseArray("map.data");

        System.out.println("将稀疏数组转换为原始数组...");
        int rowCount = sparseArray[0][0];
        int cellCount = sparseArray[0][1];
        int spareSum = sparseArray[0][2];
        int[][] newArray = new int[rowCount][cellCount];
        for (int i = 1; i <= spareSum; i++) {
            int rowIndex = sparseArray[i][0];
            int cellIndex = sparseArray[i][1];
            int value = sparseArray[i][2];
            newArray[rowIndex][cellIndex] = value;
        }
        for (int[] row : newArray) {
            for (int item : row) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
    }


    /**
     * 将稀疏数组的数据写入至本地文件中
     *
     * @param array
     */
    public static void writeToFile(int[][] array) {
        System.out.println("将稀疏数组的数据写入至本地文件中...");
        try (Writer output = new FileWriter("map.data")) {
            for (int[] row : array) {
                StringBuffer sb = new StringBuffer();
                for (int item : row) {
                    sb.append(item + " ");
                }
                output.write(sb.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件中读取数据 将其转换为数组
     *
     * @param path
     * @return
     */
    public static int[][] readFromFileToSparseArray(String path) {
        StringBuffer sb = new StringBuffer();
        try (Reader reader = new BufferedReader(new FileReader(path))) {
            int content;
            while ((content = reader.read()) != -1) {
                sb.append((char) content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] row = sb.toString().split("\n");
        String[] headRow = row[0].split(" ");
        int totalSum = Integer.parseInt(headRow[2]);
        //初始化一个稀疏数组,注意 稀疏数字始终只有3列
        int[][] sparseArray = new int[row.length][3];
        //计算这个二维数组有多少个有效数字
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            for (String item : row[i].split(" ")) {
                count++;
            }
        }
        //得到了有效值
        int index = 0;
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < row[i].split(" ").length; j++) {
                int value = Integer.parseInt(row[i].split(" ")[j]);
                sparseArray[i][j] = value;
            }
        }
        System.out.println(1);
        return sparseArray;
    }
}
