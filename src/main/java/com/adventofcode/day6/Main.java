package com.adventofcode.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static char UP = '^';
    static char DOWN = 'V';
    static char LEFT = '<';
    static char RIGHT = '>';
    static char OBSTACLE = '#';
    static char TRAIL = 'X';

    static boolean isGuardPresent = true;

    static char[][] grid;

    public static void main(String[] args) {
//        File file = new File("src/main/resources/input6part1-demo.txt");
        File file = new File("src/main/resources/input6part1.txt");

        Scanner sc = getScanner(file);

        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }


        grid = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        for (char[] line : grid) {
            System.out.println(line);
        }

        while (isGuardPresent) {

//            System.out.println("Grid:");
//            for (char[] line : grid) {
//                System.out.println(line);
//            }

            int guardRow = -1;
            int guardColumn = -1;
            char guardDirection = ' ';

            // break anchor/label !
            guardDirectionBreak:
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    if (grid[i][j] == UP || grid[i][j] == DOWN || grid[i][j] == LEFT || grid[i][j] == RIGHT) {
                        guardRow = i;
                        guardColumn = j;
                        guardDirection = grid[i][j];
                        break guardDirectionBreak;
                    }
                }
            }

            if (guardDirection == UP) {
                isGuardPresent = false;
                grid[guardRow][guardColumn] = TRAIL;
                for (int i = guardRow; i > -1; i--) {
                    if (grid[i][guardColumn] != OBSTACLE) {
                        grid[i][guardColumn] = TRAIL;
                    } else {
                        isGuardPresent = true;
                        char newDir = rotateGuard(UP);
                        grid[i + 1][guardColumn] = newDir;
                        break;
                    }
                }
            } else if (guardDirection == RIGHT) {
                isGuardPresent = false;
                grid[guardRow][guardColumn] = TRAIL;
                for (int i = guardColumn; i < lines.get(0).length(); i++) {
                    if (grid[guardRow][i] != OBSTACLE) {
                        grid[guardRow][i] = TRAIL;
                    } else {
                        isGuardPresent = true;
                        char newDir = rotateGuard(RIGHT);
                        grid[guardRow][i - 1] = newDir;
                        break;
                    }
                }
            } else if (guardDirection == DOWN) {
                isGuardPresent = false;
                grid[guardRow][guardColumn] = TRAIL;
                for (int i = guardRow; i < lines.get(0).length(); i++) {
                    if (grid[i][guardColumn] != OBSTACLE) {
                        grid[i][guardColumn] = TRAIL;
                    } else {
                        isGuardPresent = true;
                        char newDir = rotateGuard(DOWN);
                        grid[i - 1][guardColumn] = newDir;
                        break;
                    }
                }
            } else {
                isGuardPresent = false;
                grid[guardRow][guardColumn] = TRAIL;
                for (int i = guardColumn; i > -1; i--) {
                    if (grid[guardRow][i] != OBSTACLE) {
                        grid[guardRow][i] = TRAIL;
                    } else {
                        isGuardPresent = true;
                        char newDir = rotateGuard(LEFT);
                        grid[guardRow][i + 1] = newDir;
                        break;
                    }
                }
            }
        }

        System.out.println("Last position:");
        for (char[] line : grid) {
            System.out.println(line);
        }

        System.out.println("result: " + countTrail());
    }

    private static int countTrail() {
        int nrOfTrails = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == TRAIL) {
                    nrOfTrails++;
                }
            }
        }
        return nrOfTrails;
    }

    private static char rotateGuard(char guardDirection) {
        if (guardDirection == UP) {
            return RIGHT;
        } else if (guardDirection == RIGHT) {
            return DOWN;
        } else if (guardDirection == DOWN) {
            return LEFT;
        } else if (guardDirection == LEFT) {
            return UP;
        } else {
            throw new IllegalArgumentException("Invalid guard direction");
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
