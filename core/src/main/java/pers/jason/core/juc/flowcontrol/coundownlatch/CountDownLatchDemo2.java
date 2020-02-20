package pers.jason.core.juc.flowcontrol.coundownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jason
 * @date 2020/2/20 15:26
 * @description 运动员准备，听到枪声一起跑
 */
public class CountDownLatchDemo2 {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    CountDownLatch countDownLatch2 = new CountDownLatch(5);
    ExecutorService threadPool = Executors.newFixedThreadPool(5);

    for (int i = 0; i < 5; i++) {
      final int no = i + 1;
      System.out.println("No." + no + "准备");
      threadPool.submit(() -> {
        try {
          countDownLatch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("No." + no + "开始跑");
        try {
          Thread.sleep((long)Math.random() * 1000000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          countDownLatch2.countDown();
          System.out.println("No." + no + "到达终点");
        }

      });
    }

    System.out.println("嘭！");
    countDownLatch.countDown();

    countDownLatch2.await();
    System.out.println("比赛结束");
  }
}
