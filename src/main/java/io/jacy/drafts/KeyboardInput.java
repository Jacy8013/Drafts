package io.jacy.drafts;

import sun.misc.Contended;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 键盘输入
 * @author Jacy
 */
public class KeyboardInput {
    @Contended
    volatile private int a;

    public static void main(String[] args) {
//        in1();

        in2();
    }

    static void in1() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        scanner.close();
    }

    static void in2() {
        try(InputStreamReader isr = new InputStreamReader(System.in); BufferedReader reader = new BufferedReader(isr)) {
            System.out.println(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
