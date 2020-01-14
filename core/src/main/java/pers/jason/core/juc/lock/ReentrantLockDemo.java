package pers.jason.core.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

  private static ReentrantLock lock = new ReentrantLock();

  private static void bookSeat() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + "开始预定座位");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + "完成预定座位");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

}
