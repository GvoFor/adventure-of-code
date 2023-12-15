package solutions;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day6Part2 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day6.txt"));

		long raceTime = Long.parseLong(reader.readLine().replaceAll("\\s+", "").split(":")[1]);
		long raceDistance = Long.parseLong(reader.readLine().replaceAll("\\s+", "").split(":")[1]);

		long waysToBeatRecordCount = countWaysToBeatRecord(raceTime, raceDistance);

		System.out.println("Number of ways to beat races records: " + waysToBeatRecordCount);

		reader.close();
	}

	private static long countWaysToBeatRecord(long raceTime, long raceRecord) {
		long waysToBeatRecordCounter = 0;
		for (long i = 1; i <= raceTime / 2; i++) {
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