package utils;

import algorithms.pso.PSOConstants;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Number utils class to wrap common function for readability
 */
public final class NumbersUtils {

    private NumbersUtils() {
    }

    /**
     * random int in range
     *
     * @param min value
     * @param max value
     * @return random int between min and max
     */
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * @return Random value between 0 and 1
     */
    public static double randBetween0and1() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    /**
     * Clamp Color value in valid range
     *
     * @param value of color
     * @return color in valid range
     */
    public static int clampColor(int value) {
        return clamp(value, PSOConstants.CHANNEL_MIN, PSOConstants.CHANNEL_MAX);
    }

    /**
     * clamp a number in range
     *
     * @param value to be clamped
     * @param min   value
     * @param max   value
     * @return if value is larger than max return max, if smaller than min return min else return value.
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);
    }

    /**
     * clamp array to boundaries
     * @param values of array of integers
     * @param min value to clamp
     * @param max value
     * @return array with every element is clamped
     */
    public static int[] clamp(int[] values, int min, int max) {
        return Arrays.stream(values).map(value -> clamp(value, min, max)).toArray();
    }
}
