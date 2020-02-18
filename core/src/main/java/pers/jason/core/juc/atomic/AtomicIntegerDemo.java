package pers.jason.core.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jason
 * @date 2020/2/18 13:16
 * @description 演示AtomicInteger基本用法
 */
public class AtomicIntegerDemo implements Runnable{

  private static final AtomicInteger atomicInteger = new AtomicInteger();

  private static volatile Integer basicInteger = 0;

  public void incrementAtomic() {
    //获取并自增1
    atomicInteger.getAndIncrement();
    //获取并+5
//    atomicInteger.getAndAdd(5);

  }

  public void incrementBasic() {
    basicInteger++;
  }

  /**
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    AtomicIntegerDemo demo1 = new AtomicIntegerDemo();
    Thread thread1 = new Thread(demo1);
    Thread thread2 = new Thread(demo1);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println("atomic result:" + atomicInteger.get());
    System.out.println("basic result:" + basicInteger);
    //运行结果：
    //    atomic result:20000
    //    basic result:13610
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      incrementAtomic();
      incrementBasic();
    }
  }
}
