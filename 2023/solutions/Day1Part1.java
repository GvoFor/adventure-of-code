package solutions;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day1Part1 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day1.txt"));

		int sum = 0;
		String line;
		while ((line = reader.readLine()) != null) {

			char firstDigit = 0;
			char lastDigit = 0;

			for (int i = 0; i < line.length(); i++) {
				char currentChar = line.charAt(i);

				if (Character.isDigit(currentChar)) {
					lastDigit = currentChar;
					if (firstDigit == 0) {
						firstDigit = currentChar;
					}
				}
			}

			sum += (firstDigit - '0') * 10;
			sum += lastDigit - '0';
		}

		System.out.println("Sum of calibration values: " + sum);

		reader.close();
	}
}