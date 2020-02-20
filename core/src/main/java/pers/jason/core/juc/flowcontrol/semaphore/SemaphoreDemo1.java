package pers.jason.core.juc.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Jason
 * @date 2020/2/20 16:06
 * @description 考虑到环境保护的因素，同时只能有三个工厂工作
 */
public class SemaphoreDemo1 {

  static Semaphore semaphore = new Semaphore(3, true);

  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 10; i++) {
      threadPool.submit(new Factory());
    }
  }

  static class Factory implements Runnable {
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + "申请许可");
      try {
        semaphore.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "得到许可");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "释放许可");
      semaphore.release();
    }
  }
}
