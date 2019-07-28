package utils;

import algorithms.PSO.PSOConstants;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double randBetween0and1() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    public static int clampColor(int value) {
        return clamp(value, PSOConstants.CHANNEL_MIN, PSOConstants.CHANNEL_MAX);
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);

    }
}
