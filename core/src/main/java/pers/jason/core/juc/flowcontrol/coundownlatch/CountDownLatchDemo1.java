package pers.jason.core.juc.flowcontrol.coundownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jason
 * @date 2020/2/20 15:12
 * @description 五名工人检验零件，都认为通过才合格
 */
public class CountDownLatchDemo1 {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(5);
    ExecutorService threadPool = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 5; i++) {
      final int no = i+1;
      threadPool.submit(() -> {
        try {
          Thread.sleep((long)Math.random() * 10000);
          System.out.println("No." + no + "完成检验");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          countDownLatch.countDown();
        }
      });
    }

    System.out.println("等待员工检验...");
    countDownLatch.await();
    System.out.println("检验完成");
  }

}
