package solutions;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day3Part1 {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day3.txt"));

        String[] schema = reader.lines().toArray(String[]::new);
        boolean[][] marks = new boolean[schema.length][schema[0].length()];

        int sum = 0;

        for (int y = 0; y < schema.length; y++) {
            for (int x = 0; x < schema[0].length(); x++) {
                char currentChar = schema[y].charAt(x);

                if (currentChar == '.' || Character.isDigit(currentChar)) {
                    continue;
                }

                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        markNumbersAround(marks, schema, x + j, y + i);
                    }
                }
            }
        }

        for (int y = 0; y < marks.length; y++) {
            boolean isNumberStarted = false;
            int startIndex = 0;
            for (int x = 0; x < marks[0].length; x++) {
                if (!isNumberStarted && marks[y][x]) {
                    startIndex = x;
                    isNumberStarted = true;
                } else if (isNumberStarted && (!marks[y][x] || x == marks[0].length - 1)) {
                    int endIndex = marks[y][x]? x + 1 : x;
                    int partNumber = Integer.parseInt(schema[y].substring(startIndex, endIndex));
                    sum += partNumber;
                    isNumberStarted = false;
                }
            }
        }

        System.out.println("Sum of part numbers: " + sum);

        reader.close();
    }

    private static void markNumbersAround(boolean[][] marks, String[] schema, int x, int y) {
        if (x < 0 || y < 0 || x >= schema[0].length() || y >= schema.length) {
            return;
        }
        if (marks[y][x] || !Character.isDigit(schema[y].charAt(x))) {
            return;
        }

        marks[y][x] = true;

        markNumbersAround(marks, schema, x - 1, y);
        markNumbersAround(marks, schema, x + 1, y);
    }

}
