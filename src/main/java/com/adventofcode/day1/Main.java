package com.adventofcode.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        readListsFromInputFile(leftList, rightList);

        Collections.sort(leftList);
        Collections.sort(rightList);

        System.out.println("Left list:" +  leftList);
        System.out.println("Right list:" +  rightList);

        int result = 0;

        for (int i = 0; i < leftList.size(); i++) {
            int difference = Math.max(leftList.get(i), rightList.get(i)) - Math.min(leftList.get(i), rightList.get(i));
            result+=difference;
        }
        System.out.println("result: " + result);
    }

    public static void readListsFromInputFile(List<Integer> leftList, List<Integer> rightList) {

//        File file = new File("src/main/resources/input-demo.txt");
        File file = new File("src/main/resources/input.txt");

        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {

            leftList.add(sc.nextInt());
            rightList.add(sc.nextInt());
        }
    }
}
