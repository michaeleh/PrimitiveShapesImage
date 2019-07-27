package utils;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {
    public static int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }

    public static double randBetween0and1(){
        return ThreadLocalRandom.current().nextDouble(0,1);
    }
}
