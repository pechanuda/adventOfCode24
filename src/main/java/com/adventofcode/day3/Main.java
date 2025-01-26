package com.adventofcode.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // part 1
    public static void main(String[] args) {

        File file = new File("src/main/resources/inputday3.txt");
//        File file = new File("src/main/resources/inputday3-demo.txt");
        Pattern pattern = Pattern.compile("mul\\(\\d\\d?\\d?\\,\\d\\d?\\d?\\)");

        int result = 0;
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String s = sc.next();
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String mul = matcher.group(0);
                String[] operands = mul.split(",");
                String a = operands[0].substring(4);
                String b = operands[1].substring(0, operands[1].length() - 1);
                result += mul(Integer.parseInt(a),Integer.parseInt(b));
            }
        }

        System.out.println("result: " + result);
    }

    public static int mul(int a, int b) {
        return a * b;
    }
}
