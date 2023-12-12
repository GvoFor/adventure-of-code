package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Part2 {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day2.txt"));

        Pattern numberBeforeRedPattern = Pattern.compile("(\\d+) red");
        Pattern numberBeforeGreenPattern = Pattern.compile("(\\d+) green");
        Pattern numberBeforeBluePattern = Pattern.compile("(\\d+) blue");

        int sum = 0;

        String line;
        while ((line = reader.readLine()) != null) {

            String gameSets = line.split(":")[1];

            Matcher numberBeforeRedMather = numberBeforeRedPattern.matcher(gameSets);
            int maxRedAmount = 0;
            while (numberBeforeRedMather.find()) {
                int redAmount = Integer.parseInt(numberBeforeRedMather.group(1));
                if (redAmount > maxRedAmount) {
                    maxRedAmount = redAmount;
                }
            }

            Matcher numberBeforeGreenMather = numberBeforeGreenPattern.matcher(gameSets);
            int maxGreenAmount = 0;
            while (numberBeforeGreenMather.find()) {
                int greenAmount = Integer.parseInt(numberBeforeGreenMather.group(1));
                if (greenAmount > maxGreenAmount) {
                    maxGreenAmount = greenAmount;
                }
            }

            Matcher numberBeforeBlueMather = numberBeforeBluePattern.matcher(gameSets);
            int maxBlueAmount = 0;
            while (numberBeforeBlueMather.find()) {
                int blueAmount = Integer.parseInt(numberBeforeBlueMather.group(1));
                if (blueAmount > maxBlueAmount) {
                    maxBlueAmount = blueAmount;
                }
            }

            sum += maxRedAmount * maxGreenAmount * maxBlueAmount;
        }

        System.out.println("Sum of powers of minimum sets: " + sum);

        reader.close();
    }

}
