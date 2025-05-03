package com.adventofcode.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static List<String> linesOfOrderRules;
    static List<String> linesOfOrders;
    static Set<Integer> nrOfOrdersWhichPassed = new HashSet<>();
    static Set<Integer> nrOfOrdersWhichFailed = new HashSet<>();

    public static void main(String[] args) {

        File file = new File("src/main/resources/input5part1.txt");
//        File file = new File("src/main/resources/input5part1-demo.txt");
//        File file = new File("src/main/resources/input5part1-demo2.txt");

        int result = 0;

        Scanner sc = getScanner(file);

        fillLists(sc);

        System.out.println(linesOfOrderRules);
        System.out.println(linesOfOrders);

        goThroughRules();

        System.out.println("length of nrOfOrdersWhichPassed: " + nrOfOrdersWhichPassed.size());
        System.out.println("nrOfOrdersWhichPassed: " + nrOfOrdersWhichPassed.toString());

        System.out.println("length of nrOfOrdersWhichFailed: " + nrOfOrdersWhichFailed.size());
        System.out.println("nrOfOrdersWhichFailed: " + nrOfOrdersWhichFailed.toString());

        nrOfOrdersWhichPassed.removeAll(nrOfOrdersWhichFailed);

        System.out.println("length of nrOfOrdersWhichPassed: " + nrOfOrdersWhichPassed.size());
        System.out.println("nrOfOrdersWhichPassed: " + nrOfOrdersWhichPassed.toString());


        for (int i : nrOfOrdersWhichPassed) {
            String[] orderArray = linesOfOrders.get(i).split(",");

            result += Integer.parseInt(orderArray[orderArray.length / 2]);
        }

        System.out.println("result: " + result);
        processLineOfOrders();
    }

    private static void goThroughRules() {

        for (String rules : linesOfOrderRules) {
            System.out.println("checking rule: " + rules);

            int previousPageNr = Integer.parseInt(rules.split("\\|")[0]);
            int nextPageNr = Integer.parseInt(rules.split("\\|")[1]);

            int j = 0;

            for (String order : linesOfOrders) {
                System.out.println("checking order: " + order);
                String[] orderArray = order.split(",");

                int i = 1;
                int prevPosition = 0;
                int nextPosition = 0;
                for (String nr : orderArray) {
                    if (Integer.parseInt(nr) == previousPageNr) {
                        prevPosition = i;
                        System.out.println("first page nr. present, position: " + i);
                    }
                    if (Integer.parseInt(nr) == nextPageNr) {
                        nextPosition = i;
                        System.out.println("second page nr. present, position: " + i);
                    }
                    i++;
                }
                if (prevPosition != 0 && nextPosition != 0 && prevPosition > nextPosition) {
                    System.out.println("!!! ERROR !!!");
                    nrOfOrdersWhichFailed.add(j);
                }
                nrOfOrdersWhichPassed.add(j);
                j++;
            }
        }
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
