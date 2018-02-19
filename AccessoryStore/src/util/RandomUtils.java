package util;

import java.util.List;
import java.util.Random;

public class RandomUtils {
	private static Random randomObj = new Random();

	private RandomUtils() {
	}

	/**
	 * Randomizes a boolean value with the equal probabilities of returning true and
	 * false.
	 * 
	 * @return a random boolean value.
	 */
	public static boolean randomBoolean() {
		return randomObj.nextBoolean();
	}

	/**
	 * Randomizes an integer within the given range, including the minimum value and
	 * the maximum value.
	 * 
	 * @param min
	 *            the minimum value.
	 * @param max
	 *            the maximum value.
	 * @return a random integer between {@code min} (inclusive) and {@code max}
	 *         (inclusive).
	 */
	public static int randomRange(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException("The value \"min\" should not be less than the value \"max\".");
		}
		return randomObj.nextInt(max - min + 1) + min;
	}

	/**
	 * Picks an element from a given list randomly.
	 * 
	 * @param list
	 *            the list to pick an element from.
	 * @return a random element within a given list, or null if the list is empty.
	 */
	public static <T> T randomElementInList(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(randomRange(0, list.size() - 1));
	}

	public static void setSeed(long seed) {
		randomObj.setSeed(seed);
	}

	public static void unsetSeed() {
		randomObj = new Random();
	}
}
