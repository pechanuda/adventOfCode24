package com.adventofcode.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
    static char[][] startupGrid;

    public static void main(String[] args) {
//        File file = new File("src/main/resources/input6part1-demo.txt");
        File file = new File("src/main/resources/input6part1.txt");

        Scanner sc = getScanner(file);

        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }


        grid = new char[lines.size()][lines.get(0).length()];
        startupGrid = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        for (char[] line : grid) {
            System.out.println(line);
        }

        // creating deep copy of grid to startupGrid

        for (int i = 0; i < grid.length; i++) {
            startupGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }


        while (isGuardPresent) {
            isGuardPresent = isGuardPresentInGrid(grid);
        }

        System.out.println("Last position:");
        for (char[] line : grid) {
            System.out.println(line);
        }

        System.out.println("result part 1: " + countTrail());

        int loopingObstaclesCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == TRAIL) {
                    // add an obstacle to the clean grid at this position,
                    // if it finishes within 1000 iterations, then it is not in a loop
                    // if it does not finish within 1000 iterations, then increase the counter
                    char[][] modifiedGrid = new char[grid.length][grid[0].length];

                    for (int k = 0; k < startupGrid.length; k++) {
                        modifiedGrid[k] = Arrays.copyOf(startupGrid[k], startupGrid[k].length);
                    }

                    modifiedGrid[i][j] = OBSTACLE;

                    System.out.println("adding obstacle to grid at: " + i + ", " + j);
                    for (int l = 0; l <= 500; l++) {
                        System.out.println("l: " + l);
                        boolean guardStillPresent = isGuardPresentInGrid(modifiedGrid);
                        if (!guardStillPresent) {
                            break;
                        } else if (l == 500) {
                            loopingObstaclesCount++;
                        }
                    }
                }
            }
        }

        System.out.println("result part 2: " + loopingObstaclesCount);
    }

    private static boolean isGuardPresentInGrid(char[][] currentGrid) {


        int gridLength = currentGrid.length;
        int guardRow = -1;
        int guardColumn = -1;
        char guardDirection = ' ';

        // break anchor/label !
        guardDirectionBreak:
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                if (currentGrid[i][j] == UP || currentGrid[i][j] == DOWN || currentGrid[i][j] == LEFT || currentGrid[i][j] == RIGHT) {
                    guardRow = i;
                    guardColumn = j;
                    guardDirection = currentGrid[i][j];
                    break guardDirectionBreak;
                }
            }
        }

        if (guardDirection == ' ') {
            return false;
        }

        if (guardDirection == UP) {
            currentGrid[guardRow][guardColumn] = TRAIL;
            for (int i = guardRow; i > -1; i--) {
                if (currentGrid[i][guardColumn] != OBSTACLE) {
                    currentGrid[i][guardColumn] = TRAIL;
                } else {
                    char newDir = rotateGuard(UP);
                    currentGrid[i + 1][guardColumn] = newDir;
                    return true;
                }
            }
        } else if (guardDirection == RIGHT) {
            currentGrid[guardRow][guardColumn] = TRAIL;
            for (int i = guardColumn; i < gridLength; i++) {
                if (currentGrid[guardRow][i] != OBSTACLE) {
                    currentGrid[guardRow][i] = TRAIL;
                } else {
                    char newDir = rotateGuard(RIGHT);
                    currentGrid[guardRow][i - 1] = newDir;
                    return true;
                }
            }
        } else if (guardDirection == DOWN) {
            currentGrid[guardRow][guardColumn] = TRAIL;
            for (int i = guardRow; i < gridLength; i++) {
                if (currentGrid[i][guardColumn] != OBSTACLE) {
                    currentGrid[i][guardColumn] = TRAIL;
                } else {
                    char newDir = rotateGuard(DOWN);
                    currentGrid[i - 1][guardColumn] = newDir;
                    return true;
                }
            }
        } else {
            currentGrid[guardRow][guardColumn] = TRAIL;
            for (int i = guardColumn; i > -1; i--) {
                if (currentGrid[guardRow][i] != OBSTACLE) {
                    currentGrid[guardRow][i] = TRAIL;
                } else {
                    char newDir = rotateGuard(LEFT);
                    currentGrid[guardRow][i + 1] = newDir;
                    return true;
                }
            }
        }
        return false;
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
