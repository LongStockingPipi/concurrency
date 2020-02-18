package pers.jason.core.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自己实现自旋锁
 */
public class MySpinLock {

  private AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

  public void lock() {
    Thread thread = Thread.currentThread();
    while(!threadAtomicReference.weakCompareAndSet(null, thread)) {
      System.out.println(thread.getName() + "获取失败，再次尝试");
    }
  }

  public void unLock() {
    Thread thread = Thread.currentThread();
    threadAtomicReference.weakCompareAndSet(thread, null);
  }


  public static void main(String[] args) {
    MySpinLock spinLock = new MySpinLock();
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
        spinLock.lock();
        System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          spinLock.unLock();
          System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
        }
      }
    };
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();
  }
}
