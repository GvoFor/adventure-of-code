package solutions;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day3Part2 {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day3.txt"));

        String[] schema = reader.lines().toArray(String[]::new);
        int[][] marks = new int[schema.length][schema[0].length()];

        int sum = 0;

        for (int y = 0; y < schema.length; y++) {
            for (int x = 0; x < schema[0].length(); x++) {
                char currentChar = schema[y].charAt(x);
                if (currentChar != '*') {
                    continue;
                }
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        markNumbersAround(marks, schema, x + j, y + i, 1);
                    }
                }
            }
        }

        for (int y = 0; y < schema.length; y++) {
            for (int x = 0; x < schema[0].length(); x++) {
                char currentChar = schema[y].charAt(x);
                if (currentChar != '*') {
                    continue;
                }

                int uniqueNumbersCount = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (marks[y + i][x + j] == 1) {
                            uniqueNumbersCount++;
                        }
                    }
                }

                if (uniqueNumbersCount == 2) {
                    sum += getGearRatio(marks, schema, x, y);
                }
            }
        }

        System.out.println("Sum of part numbers: " + sum);

        reader.close();
    }

    private static int getGearRatio(int[][] marks, String[] schema, int gearX, int gearY) {
        int partNumber1 = 0;
        int partNumber2 = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (marks[gearY + i][gearX + j] == 1) {
                    int startIndex = gearX + j;
                    while (startIndex - 1 >= 0 && marks[gearY + i][startIndex - 1] != 0) {
                        startIndex--;
                    }

                    int endIndex = gearX + j;
                    while (endIndex + 1 < marks[0].length && marks[gearY + i][endIndex + 1] != 0) {
                        endIndex++;
                    }
                    endIndex++;

                    if (partNumber1 == 0) {
                        partNumber1 = Integer.parseInt(schema[gearY + i].substring(startIndex, endIndex));
                    } else {
                        partNumber2 = Integer.parseInt(schema[gearY + i].substring(startIndex, endIndex));
                    }
                }
            }
        }
        return partNumber1 * partNumber2;
    }

    private static void markNumbersAround(int[][] marks, String[] schema, int x, int y, int mark) {
        if (x < 0 || y < 0 || x >= schema[0].length() || y >= schema.length) {
            return;
        }
        if (marks[y][x] != 0 || !Character.isDigit(schema[y].charAt(x))) {
            return;
        }

        marks[y][x] = mark;

        markNumbersAround(marks, schema, x - 1, y, mark + 1);
        markNumbersAround(marks, schema, x + 1, y, mark + 1);
    }

}
