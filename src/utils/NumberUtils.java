package utils;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {
    public static int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}
