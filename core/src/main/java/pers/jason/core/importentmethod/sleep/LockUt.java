package pers.jason.core.importentmethod.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUt implements Runnable {

  private Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    LockUt lockUt = new LockUt();
    Thread thread1 = new Thread(lockUt);
    Thread thread2 = new Thread(lockUt);
    thread1.start();
    thread2.start();
  }

  @Override
  public void run() {
    lock.lock();
    System.out.println(Thread.currentThread().getName() + "获得锁");
    try {
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + "执行完成");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
