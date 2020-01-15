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
      System.out.println("获取失败，再次尝试");
    }
  }

  public void unLock() {
    Thread thread = Thread.currentThread();
    threadAtomicReference.weakCompareAndSet(thread, null);
  }


  public static void main(String[] args) {
    MySpinLock lock = new MySpinLock();
  }
}
