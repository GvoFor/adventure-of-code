package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2Part1 {

    private static final int RED_MAX = 12;
    private static final int GREEN_MAX = 13;
    private static final int BLUE_MAX = 14;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day2.txt"));

        Pattern numberBeforeRedPattern = Pattern.compile("(\\d+) red");
        Pattern numberBeforeGreenPattern = Pattern.compile("(\\d+) green");
        Pattern numberBeforeBluePattern = Pattern.compile("(\\d+) blue");

        int sum = 0;

        String line;
        while ((line = reader.readLine()) != null) {

            String[] gameInfo = line.split(":");
            String[] gameSets = gameInfo[1].split(";");

            boolean isGamePossible = true;
            for (String set : gameSets) {
                Matcher numberBeforeRedMather = numberBeforeRedPattern.matcher(set);
                if (numberBeforeRedMather.find()) {
                    if (Integer.parseInt(numberBeforeRedMather.group(1)) > RED_MAX) {
                        isGamePossible = false;
                        break;
                    }
                }

                Matcher numberBeforeGreenMather = numberBeforeGreenPattern.matcher(set);
                if (numberBeforeGreenMather.find()) {
                    if (Integer.parseInt(numberBeforeGreenMather.group(1)) > GREEN_MAX) {
                        isGamePossible = false;
                        break;
                    }
                }

                Matcher numberBeforeBlueMather = numberBeforeBluePattern.matcher(set);
                if (numberBeforeBlueMather.find()) {
                    if (Integer.parseInt(numberBeforeBlueMather.group(1)) > BLUE_MAX) {
                        isGamePossible = false;
                        break;
                    }
                }
            }

            if (!isGamePossible) {
                continue;
            }

            int gameID = Integer.parseInt(gameInfo[0].substring(5));
            sum += gameID;
        }

        System.out.println("Sum of IDs of possible games: " + sum);

        reader.close();
    }

}
