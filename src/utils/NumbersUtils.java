package utils;

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

}
