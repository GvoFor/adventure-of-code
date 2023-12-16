package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day7Part2 {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("2023/inputs/day7.txt"));

		List<PlayerInfo> playersInfo = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			playersInfo.add(extractPlayerInfo(line));
		}

		playersInfo.sort(PlayerInfo::compareTo);

		int totalWinnings = 0;
		for (int i = 0; i < playersInfo.size(); i++) {
			totalWinnings += (i+1) * playersInfo.get(i).getBid();
		}

		System.out.println("Total winnings: " + totalWinnings);

		reader.close();
	}

	private static PlayerInfo extractPlayerInfo(String line) {
		String[] handAndBid = line.split(" ");
		return new PlayerInfo(handAndBid[0], Integer.parseInt(handAndBid[1]));
	}

	static enum HandType {
		HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
	}

	static class PlayerInfo implements Comparable<PlayerInfo> {
		private static final Map<Character, Integer> cardToStrengthMap = new HashMap<>();

		static {
			cardToStrengthMap.put('A', 14);
			cardToStrengthMap.put('K', 13);
			cardToStrengthMap.put('Q', 12);
			cardToStrengthMap.put('T', 10);
			for (int i = 9; i > 0; i--) {
				cardToStrengthMap.put((char)('0' + i), i);
			}
			cardToStrengthMap.put('J', 0);
		}

		private final String hand;
		private final int bid;
		private final HandType handType;

		public PlayerInfo(String hand, int bid) {
			this.hand = hand;
			this.bid = bid;
			handType = determineHandType();
		}

		public String getHand() {
			return hand;
		}

		public int getBid() {
			return bid;
		}

		private HandType determineHandType() {
			int[] cardsCountByType = countCardsByType();

			int length = cardsCountByType.length - 1;
			int jokersCount = Math.max(0, cardsCountByType[0]);

			if (length == 0 || length == 1) {
				return HandType.FIVE_OF_A_KIND;
			}
			if (length == 4) {
				return HandType.ONE_PAIR;
			}
			if (length == 5) {
				return HandType.HIGH_CARD;
			}
			if (length == 2) {
				if (cardsCountByType[1] + jokersCount == 4 ||
						cardsCountByType[2] + jokersCount == 4) {
					return HandType.FOUR_OF_A_KIND;
				}
				return HandType.FULL_HOUSE;
			}
			// length == 3
			if (cardsCountByType[1] + jokersCount == 3 ||
					cardsCountByType[2] + jokersCount == 3 ||
					cardsCountByType[3] + jokersCount == 3) {
				return HandType.THREE_OF_A_KIND;
			}
			return HandType.TWO_PAIR;
		}

		private int[] countCardsByType() {
			int[] counts = new int[15];
			for (int i = 0; i < hand.length(); i++) {
				counts[cardToStrengthMap.get(hand.charAt(i))]++;
			}
			if (counts[0] == 0) {
				counts[0] = -1;
			}
			return Arrays.stream(counts).filter(value -> value != 0).toArray();
		}

		@Override
		public int compareTo(PlayerInfo another) {
			if (handType.compareTo(another.handType) > 0) {
				return 1;
			}
			if (handType.compareTo(another.handType) < 0) {
				return -1;
			}
			String anotherHand = another.getHand();
			for (int i = 0; i < hand.length(); i++) {
				if (cardToStrengthMap.get(hand.charAt(i)) > cardToStrengthMap.get(anotherHand.charAt(i))) {
					return 1;
				}
				if (cardToStrengthMap.get(hand.charAt(i)) < cardToStrengthMap.get(anotherHand.charAt(i))) {
					return -1;
				}
			}
			return 0;
		}
	}

}