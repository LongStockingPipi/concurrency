package pers.jason.core.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock不会像Synchronized一样自动释放锁，所以使用finally中释放锁
 */
public class MustUnLock {

  private static Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    lock.lock();
    try {
      //获取本锁保护的资源
      System.out.println(Thread.currentThread().getName() + "运行");
    } finally {
      lock.unlock();
    }
  }
}
