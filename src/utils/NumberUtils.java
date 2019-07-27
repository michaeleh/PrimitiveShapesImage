package utils;

import algorithms.PSO.PSOConstants;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {
    public static int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    public static double randBetween0and1(){
        return ThreadLocalRandom.current().nextDouble(0,1);
    }

    public static int clampColor(int value){
        return Math.max(Math.min(value, PSOConstants.CHANNEL_MAX),PSOConstants.CHANNEL_MIN);
    }
    //TODO change this
    public static int clampCanvas(int value){
        return Math.max(Math.min(value, 400),0);

    }
}
