package pers.jason.core.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Jason
 * @date 2020/2/18 15:43
 * @description 演示在高并发情况下，AdderLong的性能要高于AtomicLong
 */
public class AdderDemo {

  private static final Integer NUM = 1000000;

  public static void main(String[] args) {
    AtomicLong atomicCounter = new AtomicLong(0);
    ExecutorService executorService1 = Executors.newFixedThreadPool(20);
    Long startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM; i++) {
      executorService1.submit(new Task1(atomicCounter));
    }
    executorService1.shutdown();
    while(!executorService1.isTerminated()) {}
    System.out.println("atomic耗时：" + (System.currentTimeMillis() - startTime));
    System.out.println("atomic结果：" + atomicCounter.get());

    LongAdder longAdder = new LongAdder();
    ExecutorService executorService2 = Executors.newFixedThreadPool(20);
    startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM; i++) {
      executorService2.submit(new Task2(longAdder));
    }
    executorService2.shutdown();
    while(!executorService2.isTerminated()) {}
    System.out.println("adder耗时：" + (System.currentTimeMillis() - startTime));
    System.out.println("adder结果：" + longAdder.intValue());
  }

  private static class Task1 implements Runnable{

    private AtomicLong atomicLong;

    public Task1(AtomicLong atomicLong) {
      this.atomicLong = atomicLong;
    }

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        atomicLong.getAndIncrement();
      }

    }
  }

  private static class Task2 implements Runnable{

    private LongAdder longAdder;

    public Task2(LongAdder longAdder) {
      this.longAdder = longAdder;
    }

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        longAdder.increment();
      }
    }
  }
}
