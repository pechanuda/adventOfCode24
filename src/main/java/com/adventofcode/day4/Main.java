package com.adventofcode.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static char X = 'X';
    public static char M = 'M';
    public static char A = 'A';
    public static char S = 'S';
    public static void main(String[] args) {

//        File file = new File("src/main/resources/input4part1-demo2.txt");
//        File file = new File("src/main/resources/input4part1-demo.txt");
        File file = new File("src/main/resources/input4part1.txt");

        int result = 0;

        Scanner sc = getScanner(file);

        List<String> lines = new ArrayList<>();

        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        result += horizontal(getScanner(file));
        result += countVertical(lines, X, M, A, S);
        result += countVertical(lines, S, A, M, X);
        result += countDiagonalDown(lines, X, M, A, S);
        result += countDiagonalDown(lines, S, A, M, X);
        result += countDiagonalUp(lines, X, M, A, S);
        result += countDiagonalUp(lines, S, A, M, X);
        System.out.println("total result: " + result);
    }

    private static int countVertical(List<String> lines, char one, char two, char three, char four) {
        int result = 0;
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == one) {
                    if (lines.get(i + 1).charAt(j) == two) {
                        if (lines.get(i + 2).charAt(j) == three) {
                            if (lines.get(i + 3).charAt(j) == four) {
                                result++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("result vertical " + one + two + three + four + ": " + result);
        return result;
    }

    private static int countDiagonalDown(List<String> lines, char one, char two, char three, char four) {
        int result = 0;
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 0; j < lines.get(i).length() - 3; j++) {
                if (lines.get(i).charAt(j) == one) {
                    if (lines.get(i + 1).charAt(j + 1) == two) {
                        if (lines.get(i + 2).charAt(j + 2) == three) {
                            if (lines.get(i + 3).charAt(j + 3) == four) {
                                result++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("result diagonal down " + one + two + three + four + ": " + result);
        return result;
    }

    private static int countDiagonalUp(List<String> lines, char one, char two, char three, char four) {
        int result = 0;
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 3; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == one) {
                    if (lines.get(i + 1).charAt(j - 1) == two) {
                        if (lines.get(i + 2).charAt(j - 2) == three) {
                            if (lines.get(i + 3).charAt(j - 3) == four) {
                                result++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("result diagonal up " + one + two + three + four + ": " + result);
        return result;
    }

    private static int horizontal(Scanner sc) {

        int result = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            result += countHorizontal(s, X, M, A, S);
            result += countHorizontal(s, S, A, M, X);
        }

        System.out.println("result horizontal: " + result);
        return result;
    }

    private static Scanner getScanner(File file) {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sc;
    }

    private static int countHorizontal(String s, char x, char m, char a, char s2) {
        int result = 0;
        for (int i = 0; i < s.length() - 3; i++) {
            if (s.charAt(i) == x) {
                if (s.charAt(i + 1) == m) {
                    if (s.charAt(i + 2) == a) {
                        if (s.charAt(i + 3) == s2) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }
}
