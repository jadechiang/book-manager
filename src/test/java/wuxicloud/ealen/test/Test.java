package wuxicloud.ealen.test;


import java.util.concurrent.*;

/**
 * #Author : EalenXie && #CreateTime : 2017-04-07.
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            Thread.sleep(3000);
            return "future";
        });
        try {
            System.out.println("Waiting.......");
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
