package util;

import java.util.ArrayList;
import java.util.Random;

public class RandomUtils {
	private static Random randomObj = new Random();

	private RandomUtils() {
	}

	/**
	 * Randoms an integer within the given range, including the minimum value and
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
	 * Gets any random element in the specified list that is not the specified
	 * element {@code meElem}.
	 * 
	 * @param list
	 *            the list to pick a random element from.
	 * @param meElem
	 *            the element within the list that will be excluded from the
	 *            possible random elements.
	 * @return a random element within the list that is not meElem, or null if the
	 *         list has the size of 1 or less.
	 */
	public static <T> T getOtherRandomElemInList(ArrayList<T> list, T meElem) {
		int meElemIndex = list.indexOf(meElem);
		if (meElemIndex == -1) {
			throw new IllegalArgumentException("Specified meElem is not in the given list.");
		}

		if (list.size() <= 1) {
			return null;
		}

		int otherElemIndex = randomRange(0, list.size() - 2);

		if (otherElemIndex >= meElemIndex) {
			otherElemIndex++;
		}

		return list.get(otherElemIndex);
	}

	public static void setSeed(long seed) {
		randomObj.setSeed(seed);
	}

	public static void unsetSeed() {
		randomObj = new Random();
	}
}
