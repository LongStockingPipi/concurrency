package pers.jason.core.juc.flowcontrol.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jason
 * @date 2020/2/20 17:28
 * @description 拼车，4人出发
 */
public class CyclicBarrierDemo {

  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(8);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
      System.out.println("人齐了");
    });
    for (int i = 0; i < 8; i++) {
      threadPool.submit(() -> {
        System.out.println(Thread.currentThread().getName() + "等待");
        try {
          cyclicBarrier.await();
          System.out.println(Thread.currentThread().getName() + "上车");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
