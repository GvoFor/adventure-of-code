package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Part2 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day1.txt"));

		Pattern stringifiedDigitPattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine)");
		Map<String, String> stringifiedDigitToDigitMap = Map.of(
				"one", "1",
				"two", "2",
				"three", "3",
				"four", "4",
				"five", "5",
				"six", "6",
				"seven", "7",
				"eight", "8",
				"nine", "9"
		);

		int sum = 0;
		String line;
		while ((line = reader.readLine()) != null) {

			// Replace all stringified digits with their numeric versions
			// in correct order (from left to right)
			Matcher mather = stringifiedDigitPattern.matcher(line);
			StringBuilder preparedLine = new StringBuilder(line);
			while (mather.find()) {
				preparedLine.replace(
						mather.start(),
						mather.start()+1,
						stringifiedDigitToDigitMap.get(mather.group())
				);
				mather = stringifiedDigitPattern.matcher(preparedLine);
			}

			// Evaluating calibration value
			char firstDigit = 0;
			char lastDigit = 0;

			for (int i = 0; i < preparedLine.length(); i++) {
				char currentChar = preparedLine.charAt(i);

				if (Character.isDigit(currentChar)) {
					lastDigit = currentChar;
					if (firstDigit == 0) {
						firstDigit = currentChar;
					}
				}
			}

			// Add calibration value to the sum
			sum += (firstDigit - '0') * 10;
			sum += lastDigit - '0';
		}

		System.out.println("Sum of calibration values: " + sum);

		reader.close();
	}
}