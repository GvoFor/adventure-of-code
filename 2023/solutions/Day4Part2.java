package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day4Part2 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day4.txt"));
		String[] cards = reader.lines().toArray(String[]::new);
		int[] cardInstancesCount = new int[cards.length];

		for (int i = 0; i < cards.length; i++) {
			String numbersInfo = cards[i].split(":\\s*")[1];
			String[] spaceSeparatedNumbers = numbersInfo.split("\\s*\\|\\s*");
			List<String> winningNumbers = List.of(spaceSeparatedNumbers[0].split("\\s+"));
			Stream<String> actualNumbers = Arrays.stream(spaceSeparatedNumbers[1].split("\\s+"));
			long actualWinNumbersCount = actualNumbers.filter(winningNumbers::contains).count();

			cardInstancesCount[i] += 1; // 1 original instance
			for (int j = 0; j < actualWinNumbersCount; j++) {
				cardInstancesCount[i + j + 1] += cardInstancesCount[i];
			}
		}

		int totalCardInstances = Arrays.stream(cardInstancesCount).sum();
		System.out.println("Total card instances: " + totalCardInstances);

		reader.close();
	}

}