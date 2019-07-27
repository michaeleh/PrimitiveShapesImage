package utils;

import java.util.concurrent.CountDownLatch;

public class ObserverLatch {
    private CountDownLatch latch;
    public static int index;
    public void init(int num){
        latch = new CountDownLatch(num);
        index = num;
    }

    public void countdown(){
        latch.countDown();
    }

    public void await(){
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
