package com.test;

import java.util.Scanner;

public class QB {
    static class Node {
        private int coef;
        private int exp;
        public Node next = null;

        public Node(int coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner("1\n" +
                "1\n" +
                "-2 2\n" +
                "1\n" +
                "2 2");
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int[] arr_coef_n = new int[N];
            int[] arr_exp_n = new int[N];
            for (int n = 0; n < N; n++) {
                int tmp_coef_n = scanner.nextInt();
                arr_coef_n[n] = tmp_coef_n;
                int tmp_exp_n = scanner.nextInt();
                arr_exp_n[n] = tmp_exp_n;
            }
            // 储存第一个多项式数据
            int M = scanner.nextInt();
            int[] arr_coef_m = new int[M];
            int[] arr_exp_m = new int[M];
            for (int m = 0; m < M; m++) {
                int tmp_coef_m = scanner.nextInt();
                arr_coef_m[m] = tmp_coef_m;
                int tmp_exp_m = scanner.nextInt();
                arr_exp_m[m] = tmp_exp_m;
            }
            // 储存第二个多项式数据
            Node poly_n = create(arr_coef_n, arr_exp_n);
            Node poly_m = create(arr_coef_m, arr_exp_m);
            Node re = new Node(-1, -1);
            add(poly_n, poly_m, re);
            print(re.next);
        }
    }

    public static Node create(int[] coef, int[] exp) {
        Node head = new Node(coef[0], exp[0]);
        Node tail;
        head.next = null;
        tail = head;
        for (int p = 1; p < coef.length; p++) {
            Node node = new Node(coef[p], exp[p]);
            node.next = null;
            tail.next = node;
            tail = node;
        }
        return head;
    }

    public static void add(Node n, Node m, Node re) {
//        Node re = new Node(-1, -1);
        while (true) {
            if (m == null && n == null) {
                return;
            }
            if (m != null && n != null) {
                if (n.exp > m.exp) {
                    Node temp_m = m.next;
                    Node temp_re = re.next;
                    re.next = m;
                    m.next = temp_re;
                    // 链接
                    re = re.next;
                    m = temp_m;
                } else if (n.exp < m.exp) {
                    Node temp_n = n.next;
                    Node temp_re = re.next;
                    re.next = n;
                    n.next = temp_re;
                    // 链接
                    re = re.next;
                    n = temp_n;
                } else if (n.exp == m.exp) {
                    int sum = n.coef + m.coef;
                    if (sum != 0) {
                        Node temp_n = n.next;
                        Node temp_m = m.next;
                        Node temp_re = re.next;
                        m.coef = sum;
                        re.next = m;
                        m.next = temp_re;
                        n = temp_n;
                        m = temp_m;
                        re = re.next;
                    } else {
                        n = n.next;
                        m = m.next;
                    }
                }
            } else if (m == null) {
                Node temp_re = re.next;
                Node temp_n = n.next;
                re.next = n;
                n.next = temp_re;
                n = temp_n;
                re = re.next;
            } else {
                Node temp_re = re.next;
                Node temp_m = m.next;
                re.next = m;
                m.next = temp_re;
                m = temp_m;
                re = re.next;
            }
        }
    }

    static void print(Node node) {
        StringBuffer bf = new StringBuffer();
        while (null != node) {
            boolean coefVisible = node.coef != 1 && node.coef != -1;
            boolean expVisible = node.exp != 1;
            if (node.exp == 0) {
                bf.append(node.coef);
            } else if (node.coef != 0) {
                if (coefVisible)
                    bf.append(node.coef);
                else if (node.coef == -1)
                    bf.append("-");
                if (expVisible)
                    bf.append("x^").append(node.exp);
                else
                    bf.append("x");
            }
            if (node.coef != 0 && node.next != null && node.next.coef >= 0)
                bf.append("+");
            node = node.next;
        }
        if (bf.toString().endsWith("+"))
            bf.delete(bf.length() - 1, bf.length());
        if (bf.length() == 0)
            bf.append("0");
        System.out.println(bf.toString());
    }
}

