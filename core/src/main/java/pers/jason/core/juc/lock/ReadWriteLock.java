package pers.jason.core.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

  private static ReentrantReadWriteLock bookLock = new ReentrantReadWriteLock(true);

  private static ReentrantReadWriteLock.ReadLock readBook = bookLock.readLock();

  private static ReentrantReadWriteLock.WriteLock writeBook = bookLock.writeLock();

  public static void main(String[] args) {
    ReadWriteLock readWriteLock = new ReadWriteLock();
    //全部读锁，则所有线程都可以在第一时间获取锁
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        try {
          readWriteLock.read();
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    //全部写锁，则线程一次等待上一个线程释放锁才能获取锁
//    for (int i = 0; i < 10; i++) {
//      new Thread(() -> new ReadWriteLock().write()).start();
//    }

    //先写后读，只有写锁线程释放死锁，读锁线程才可以获得读锁
//    new Thread(() -> new ReadWriteLock().write()).start();
//    for (int i = 0; i < 10; i++) {
//      new Thread(() -> new ReadWriteLock().read()).start();
//    }
  }

  public void write() {
    writeBook.lock();
    String threadName = Thread.currentThread().getName();
    try {
      System.out.println(threadName + "正在记笔记");
      Thread.sleep(2000);
      System.out.println(threadName + "写完了");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      writeBook.unlock();
    }
  }

  public void read() throws InterruptedException {
    readBook.lock();
    String threadName = Thread.currentThread().getName();
//    Thread.sleep(20);
    try {
      System.out.println(threadName + "正在读书");
      Thread.sleep(1000);
      System.out.println(threadName + "读完了");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      readBook.unlock();
    }
  }
}
