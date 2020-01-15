package pers.jason.core.juc.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁
 * ReentrantLock构造方法参数可以设置是否公平，true为公平锁，false为非公平所，默认为true
 * 公平情况下，即使每个线程打印两轮，但是由于期间释放掉了锁，因此需要重新排队到队尾
 * 非公平情况下，首次打印完成的线程放弃锁后，由于其他线程此可还处于阻塞状态，因此该线程有可能直接继续获取到锁执行第二轮打印
 */
public class FairAndUnFairLock implements Runnable{

  static Printer printer = new Printer();

  public static void main(String[] args) throws InterruptedException {

    Thread[] threads = new Thread[10];
    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(new FairAndUnFairLock());
    }

    for (int i = 0; i < 10; i++) {
      threads[i].start();
      Thread.sleep(10);
    }
  }


  @Override
  public void run() {
    printer.doPrint("xxx", 2);
  }
}

class Printer {
  private Lock lock = new ReentrantLock(false);

  public void doPrint(String document, int time) {
    for (int i = 0; i < time; i++) {
      lock.lock();
      try {
        String threadName = Thread.currentThread().getName();
        int duration = new Random().nextInt(10) + 1;
        System.out.println(threadName + "正在打印...");
        Thread.sleep(duration * 100);
        System.out.println(threadName + "打印完成");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }
}
