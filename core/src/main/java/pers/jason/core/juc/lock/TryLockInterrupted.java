package pers.jason.core.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取锁的时候接受中断信号
 */
public class TryLockInterrupted implements Runnable{

  static Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    Thread thread1 = new Thread(new TryLockInterrupted());
    Thread thread2 = new Thread(new TryLockInterrupted());

    thread1.start();
    thread2.start();
    thread2.interrupt();

  }

  @Override
  public void run() {
    try {
      if(lock.tryLock(800, TimeUnit.MILLISECONDS)) {
        try {
          System.out.println(Thread.currentThread().getName() + "获取到了锁");
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          System.out.println(Thread.currentThread().getName() + "执行期间被中断");
        } finally {
          lock.unlock();
        }
      }
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + "获取锁期间被中断");
    }
  }
}
