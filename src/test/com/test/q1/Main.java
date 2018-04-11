package com.test.q1;

import java.util.Scanner;

public class Main {
    static int count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner("64");
        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            int pow = 0;
            while (true) {
                num = num / 2;
                if (num == 0) break;
                pow++;
            }
//            System.out.println(pow);
            int[] coins = new int[pow * 2];
            for (int i = 0; i < coins.length; i += 2) {
                coins[i] = (int) Math.pow(2, i / 2);
                coins[i + 1] = (int) Math.pow(2, i / 2);
//                System.out.println(coins[i] + "," + coins[i + 1] + ",");
            }
            for (int i = 0; i < coins.length; i++) {

            }
        }
    }
}


