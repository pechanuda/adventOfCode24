package com.adventofcode.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("src/main/resources/input6part1-demo.txt");

        int result = 0;

        Scanner sc = getScanner(file);

        List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        for (String line : lines) {
            System.out.println(line);
        }
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
