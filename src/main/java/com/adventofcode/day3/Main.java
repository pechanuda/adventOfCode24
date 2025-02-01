package com.adventofcode.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // part 2
    public static void main(String[] args) {

        // extenuating circumstances
        // I've removed all spaces and newlines from the input file. The regex didn't like it. Inconsistent with behavior in regex101 (maybe different flavor?)

        File file = new File("src/main/resources/inputday3.txt");
//        File file = new File("src/main/resources/inputday3-demo.txt");
//        File file = new File("src/main/resources/inputday3-extra.txt");
//        File file = new File("src/main/resources/inputday3-demo-part2.txt");

        Pattern newPattern = Pattern.compile("do\\(\\)((?:(?!don't\\().)+)");
        Pattern mulPattern = Pattern.compile("mul\\(\\d\\d?\\d?\\,\\d\\d?\\d?\\)");

        int result = 0;
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String s = sc.next();

            Matcher matcher = newPattern.matcher(s);

            while (matcher.find()) {
                String mulUntilDont = matcher.group(0);
                System.out.println("mulUntilDont: " + mulUntilDont);
                Matcher mulMatcher = mulPattern.matcher(mulUntilDont);

                while (mulMatcher.find()) {
                    String mul = mulMatcher.group(0);
                    System.out.println("mul: " + mul);
                    String[] operands = mul.split(",");
                    String a = operands[0].substring(4);
                    String b = operands[1].substring(0, operands[1].length() - 1);
                    result += mul(Integer.parseInt(a),Integer.parseInt(b));
                }
            }

        }

        System.out.println("result: " + result);
    }

    public static int mul(int a, int b) {
        return a * b;
    }
}
