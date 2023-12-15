package solutions;

import java.io.FileInputStream;
import java.util.*;

public class Day5Part1 {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new FileInputStream("2023/inputs/day5.txt"));

		long[] seeds = extractSeeds(scanner);
		List<long[]> seedToSoilMap = extractNextRangeMap(scanner);
		List<long[]> soilToFertilizerMap = extractNextRangeMap(scanner);
		List<long[]> fertilizerToWaterMap = extractNextRangeMap(scanner);
		List<long[]> waterToLightMap = extractNextRangeMap(scanner);
		List<long[]> lightToTemperatureMap = extractNextRangeMap(scanner);
		List<long[]> temperatureToHumidityMap = extractNextRangeMap(scanner);
		List<long[]> humidityToLocationMap = extractNextRangeMap(scanner);

		long minLocation = Long.MAX_VALUE;
		for (long seed : seeds) {
			long soil = mapByRule(seed, seedToSoilMap);
			long fertilizer = mapByRule(soil, soilToFertilizerMap);
			long water = mapByRule(fertilizer, fertilizerToWaterMap);
			long light = mapByRule(water, waterToLightMap);
			long temperature = mapByRule(light, lightToTemperatureMap);
			long humidity = mapByRule(temperature, temperatureToHumidityMap);
			long location = mapByRule(humidity, humidityToLocationMap);
			if (location < minLocation) {
				minLocation = location;
			}
		}

		System.out.println("Lowest location number: " + minLocation);

		scanner.close();
	}

	private static long[] extractSeeds(Scanner scanner) {
		return Arrays.stream(
					scanner.nextLine().substring(7).split(" ")
				).mapToLong(Long::parseLong).toArray();
	}

	private static List<long[]> extractNextRangeMap(Scanner scanner) {
		scanner.skip("[^:]+:");

		List<long[]> rangeMap = new ArrayList<>();
		while(scanner.hasNextLong()) {
			long destinationRangeStart = scanner.nextLong();
			long sourceRangeStart = scanner.nextLong();
			long rangeLength = scanner.nextLong();

			rangeMap.add(new long[]{destinationRangeStart, sourceRangeStart, rangeLength});
		}

		return rangeMap;
	}

	private static long mapByRule(long source, List<long[]> rangeMap) {
		for (long[] range : rangeMap) {
			long sourceRangeStart = range[1];
			long rangeLength = range[2];
			if (source >= sourceRangeStart && source < sourceRangeStart + rangeLength) {
				long destinationRangeStart = range[0];
				return destinationRangeStart + (source - sourceRangeStart);
			}
		}
		return source;
	}
}