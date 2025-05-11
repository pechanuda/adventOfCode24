package com.adventofcode.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static List<String> linesOfOrderRules;
    static List<String> linesOfOrders;
    static List<String[]> listOfOrdersWithIncorrectOrder = new ArrayList<>();
    static Set<Integer> nrOfOrdersWhichPassed = new HashSet<>();
    static Set<Integer> nrOfOrdersWhichFailed = new HashSet<>();
    static Map<Integer, String[]> mapOfOrdersWithIncorrectOrder = new HashMap<>();
    static boolean areAllFixed = false;

    // Takeways:
    /* *
    * Collections.shuffle(linesOfOrderRules); -> get random order of a collection
    * some shenanigans with map and set - can use set to get rid of duplicates
    */

    public static void main(String[] args) {

        File file = new File("src/main/resources/input5part1.txt");
//        File file = new File("src/main/resources/input5part1-demo.txt");
//        File file = new File("src/main/resources/input5part1-demo2.txt");

        int result = 0;
        int resultPartTwo = 0;

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


        // PART 2
        // reorder wrongly ordered orders and get sum of their middle nrs

        goThroughIncorrectRules();
        goThroughIncorrectRules();
        goThroughIncorrectRules();
        goThroughIncorrectRules();
        goThroughIncorrectRules();

        while (!areAllFixed) {
            goThroughIncorrectRules();
        }

        System.out.println("done");
//        TODO: count result when all orders in mapOfOrdersWithIncorrectOrder are reordered
        for (String[] orderArr: mapOfOrdersWithIncorrectOrder.values()) {
            resultPartTwo += Integer.parseInt(orderArr[orderArr.length / 2]);
        }
        System.out.println("Map:");
        mapOfOrdersWithIncorrectOrder.forEach((key, value) -> System.out.println(key + " " + Arrays.toString(value)));


        System.out.println("result part 2: " + resultPartTwo);
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

                    String[] orderArrayCopy = orderArray.clone();

                    int tmp = Integer.parseInt(orderArrayCopy[prevPosition - 1]);
                    orderArrayCopy[prevPosition - 1] = orderArrayCopy[nextPosition - 1];
                    orderArrayCopy[nextPosition - 1] = String.valueOf(tmp);

                    System.out.println("This should be corrected: " + Arrays.toString(orderArrayCopy));

                    listOfOrdersWithIncorrectOrder.add(orderArrayCopy);
                    mapOfOrdersWithIncorrectOrder.put(j, orderArrayCopy);
                }
                nrOfOrdersWhichPassed.add(j);
                j++;
            }
        }
    }

    private static void goThroughIncorrectRules() {
        Collections.shuffle(linesOfOrderRules);

        for (String rules : linesOfOrderRules) {
            System.out.println("checking rule: " + rules);

            int previousPageNr = Integer.parseInt(rules.split("\\|")[0]);
            int nextPageNr = Integer.parseInt(rules.split("\\|")[1]);

            int j = 0;
            int nrOfErrors = 0;

            Map<Integer, String[]> newMapOfOrdersWithIncorrectOrder = new HashMap<>();

            for (String[] order : mapOfOrdersWithIncorrectOrder.values()) {
                System.out.println("checking order: " + Arrays.toString(order));

                int i = 1;
                int prevPosition = 0;
                int nextPosition = 0;
                for (String nr : order) {
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
                    nrOfErrors++;
                    nrOfOrdersWhichFailed.add(j);

                    String[] orderArrayCopy = order.clone();

                    int tmp = Integer.parseInt(orderArrayCopy[prevPosition - 1]);
                    orderArrayCopy[prevPosition - 1] = orderArrayCopy[nextPosition - 1];
                    orderArrayCopy[nextPosition - 1] = String.valueOf(tmp);

                    System.out.println("This should be corrected: " + Arrays.toString(orderArrayCopy));

                    listOfOrdersWithIncorrectOrder.add(orderArrayCopy);
                    newMapOfOrdersWithIncorrectOrder.put(j, orderArrayCopy);
                } else {
                    newMapOfOrdersWithIncorrectOrder.put(j, order);
                }
                nrOfOrdersWhichPassed.add(j);
                j++;
            }
            mapOfOrdersWithIncorrectOrder = newMapOfOrdersWithIncorrectOrder;
            if (nrOfErrors == 0) {
                areAllFixed = true;
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
