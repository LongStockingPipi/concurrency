package pers.jason.core.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Jason
 * @date 2020/2/18 14:18
 * @description 使用原子引用类型实现自旋锁
 */
public class AtomicReferenceDemo {

  private AtomicReference<Thread> lock = new AtomicReference<>();

  public void lock() {
    Thread thread = Thread.currentThread();
    while(lock.compareAndSet(null, thread)) {
      System.out.println("获取锁失败，尝试重新获取");
    }
  }

  public void unLock() {
    Thread thread = Thread.currentThread();
    lock.compareAndSet(thread, null);
    System.out.println("释放锁");
  }


}
