package com.adventofcode.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<String> linesOfOrderRules;
    static List<String> linesOfOrders;
    static List<Integer> linesOfOrdersAsInt;
    public static void main(String[] args) {

//        File file = new File("src/main/resources/input5part1.txt");
        File file = new File("src/main/resources/input5part1-demo.txt");

        int result = 0;

        Scanner sc = getScanner(file);

        fillLists(sc);

        System.out.println(linesOfOrderRules);
        System.out.println(linesOfOrders);

        goThroughRules();

        processLineOfOrders();
    }

    private static void goThroughRules() {

//        for (linesOfOrderRules)
    }

    private static void processLineOfOrders() {
        List<Integer> lineOfAnOrderAsListOfInts = new ArrayList<>();

        for (String lineOfAnOrder : linesOfOrders) {
            String[] lineOfAnOrderArray = lineOfAnOrder.split(",");
            for (String order : lineOfAnOrderArray) {
                lineOfAnOrderAsListOfInts.add(Integer.valueOf(order));
            }
        }
    }

    private static void fillLists(Scanner sc) {
        List<String> lines = new ArrayList<>();

        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        int indexOfEmpty = 0;
        int index = 0;
        for (String s : lines) {
            if (s.isEmpty()) {
                indexOfEmpty = index;
                break;
            }
            index++;
        }

        linesOfOrderRules = lines.subList(0, indexOfEmpty);
        linesOfOrders = lines.subList(indexOfEmpty + 1, lines.size());
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
}
