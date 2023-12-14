package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day4Part1 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day4.txt"));

		int sum = 0;
		String line;
		while ((line = reader.readLine()) != null) {

			String numbersInfo = line.split(":\\s*")[1];
			String[] spaceSeparatedNumbers = numbersInfo.split("\\s*\\|\\s*");
			List<String> winningNumbers = List.of(spaceSeparatedNumbers[0].split("\\s+"));
			Stream<String> actualNumbers = Arrays.stream(spaceSeparatedNumbers[1].split("\\s+"));
			int score = actualNumbers.reduce(
					 0.5,
							(Double points, String number) -> (winningNumbers.contains(number)? points * 2 : points),
							(Double r1, Double r2) -> r1 * r2
						).intValue();

			sum += score;
		}

		System.out.println("Total score: " + sum);

		reader.close();
	}
}