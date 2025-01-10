package com.adventofcode.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/main/resources/inputday2.txt");
//        File file = new File("src/main/resources/inputday2-demo.txt");
//        File file = new File("src/main/resources/inputday2-demo2.txt");

        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int counter = 0;
        Scanner stringScanner;

        List<Integer> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            stringScanner = new Scanner(sc.nextLine());
            while (stringScanner.hasNextInt()) {
                list.add(stringScanner.nextInt());
            }
            System.out.println(list);

            if (isSafe(list, false)) {
                counter++;
                System.out.println("safe");
            }

            list.clear();
        }

        System.out.println("counter: " + counter);
    }

    private static boolean isSafe(List<Integer> list, boolean secondChance) {
        return (isDecreasing(list, secondChance) || isIncreasing(list, secondChance))  && isDiffering(list, secondChance);
    }

    private static boolean isDiffering(List<Integer> list, boolean secondChance) {
        List<Integer> copiedList = new ArrayList<>(list);
        List<Integer> copiedList2 = new ArrayList<>(list);

        for (int i = 0; i < list.size() - 1; i++) {
            int difference = Math.max(list.get(i), list.get(i + 1)) - Math.min(list.get(i), list.get(i + 1));
            if (!(difference > 0 && difference < 4)) {
                if (!secondChance) {
                    copiedList.remove(i);
                    copiedList2.remove(i+1);
                    return (isSafe(copiedList, true) || isSafe(copiedList2, true));
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasing(List<Integer> list, boolean secondChance) {
        List<Integer> copiedList = new ArrayList<>(list);
        List<Integer> copiedList2 = new ArrayList<>(list);

        for (int i = 0; i < list.size() - 1; i++) {
            if ((list.get(i) > list.get(i + 1))) {
                if (!secondChance) {
                    copiedList.remove(i);
                    copiedList2.remove(i+1);
                    return (isSafe(copiedList, true) || isSafe(copiedList2, true));
                }
                return false;
            }
        }
        return true;
    }

    private static boolean isIncreasing(List<Integer> list, boolean secondChance) {
        List<Integer> copiedList = new ArrayList<>(list);
        List<Integer> copiedList2 = new ArrayList<>(list);

        for (int i = 0; i < list.size() - 1; i++) {
            if ((list.get(i) < list.get(i + 1))) {
                if (!secondChance) {
                    copiedList.remove(i);
                    copiedList2.remove(i+1);
                    return (isSafe(copiedList, true) || isSafe(copiedList2, true));
                }
                return false;
            }
        }
        return true;
    }
}
