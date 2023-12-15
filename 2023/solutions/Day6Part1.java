package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day6Part1 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day6.txt"));

		int[] racesTime = Arrays.stream(reader.readLine().split(":\\s*")[1].split("\\s+"))
				.mapToInt(Integer::parseInt).toArray();
		int[] racesDistance = Arrays.stream(reader.readLine().split(":\\s*")[1].split("\\s+"))
				.mapToInt(Integer::parseInt).toArray();

		int product = 1;
		for (int i = 0; i < racesTime.length; i++) {
			product *= countWaysToBeatRecord(racesTime[i], racesDistance[i]);
		}

		System.out.println("Product of number of ways to beat races records: " + product);

		reader.close();
	}

	private static int countWaysToBeatRecord(int raceTime, int raceRecord) {
		int waysToBeatRecordCounter = 0;
		for (int i = 1; i <= raceTime / 2; i++) {
			if (i * (raceTime - i) > raceRecord) {
				waysToBeatRecordCounter++;
			}
		}
		waysToBeatRecordCounter *= 2;
		if (raceTime % 2 == 0) {
			waysToBeatRecordCounter--;
		}
		return waysToBeatRecordCounter;
	}

}