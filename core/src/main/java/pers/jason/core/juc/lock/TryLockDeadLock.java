package pers.jason.core.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock()避免死锁
 * 设置超时时间并主动释放锁可以有效地避免死锁
 */
public class TryLockDeadLock {

  private static Lock lock1 = new ReentrantLock();

  private static Lock lock2 = new ReentrantLock();

  public static void main(String[] args) {
    Thread thread1 = new Thread(new Thread1());
    Thread thread2 = new Thread(new Thread2());

    thread1.start();
    thread2.start();
  }


  static class Thread1 implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        try {
          if(lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
            try {
              System.out.println("线程1获取到锁1");
              Thread.sleep(2000);
              if(lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                try {
                  System.out.println("线程1获取锁2");
                  Thread.sleep(2000);
                  break;
                } finally {
                  lock2.unlock();
                }
              } else {
                System.out.println("线程1获取锁2失败，重试");
              }
            } finally {
              lock1.unlock();
            }
          } else {
            System.out.println("线程1获取锁1失败，重试");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class Thread2 implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        try {
          if(lock2.tryLock(80, TimeUnit.MILLISECONDS)) {
            try {
              System.out.println("线程2获取到锁2");
              Thread.sleep(2000);
              if(lock1.tryLock(80, TimeUnit.MILLISECONDS)) {
                try {
                  System.out.println("线程2获取锁1");
                  Thread.sleep(2000);
                  break;
                } finally {
                  lock1.unlock();
                }
              } else {
                System.out.println("线程2获取锁1失败，重试");
              }
            } finally {
              lock2.unlock();
            }
          } else {
            System.out.println("线程2获取锁2失败，重试");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
