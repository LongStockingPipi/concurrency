package pers.jason.core.jmm;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class OutOfOrderExecution {

  private static int x = 0;
  private static int y = 0;
  private static int a = 0;
  private static int b = 0;

  static Set<ExecutionResult> results = new HashSet<>();

  /**
   * 四种情况：
   * ExecutionResult{a=1; b=0}
   * ExecutionResult{a=1; b=1}
   * ExecutionResult{a=0; b=0} 发生了重排序也可能发生了可见性问题
   * ExecutionResult{a=0; b=1}
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    for(int i=0;i<1000000;i++) {
      x=0;y=0;a=0;b=0;
      CountDownLatch latch = new CountDownLatch(3);
      Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            latch.countDown();
            latch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          a = 1;
          x = b;
        }
      });

      Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            latch.countDown();
            latch.await();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          b = 1;
          y = a;
        }
      });

      thread1.start();
      thread2.start();
      latch.countDown();
      thread1.join();
      thread2.join();
      results.add(new ExecutionResult(x, y));
    }

    for(ExecutionResult result : results) {
      System.out.println(result.toString());
    }
  }

}
