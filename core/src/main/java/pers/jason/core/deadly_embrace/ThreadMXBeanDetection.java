package pers.jason.core.deadly_embrace;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 使用ThreadMXBean检测死锁
 */
public class ThreadMXBeanDetection {

  private static Object lock1 = new Object();

  private static Object lock2 = new Object();

  public static void main(String[] args) throws InterruptedException {
    LockDemo lockDemo1 = new LockDemo(true);
    LockDemo lockDemo2 = new LockDemo(false);

    Thread thread1 = new Thread(lockDemo1, "线程1");
    Thread thread2 = new Thread(lockDemo2, "线程2");

    thread1.start();
    thread2.start();


    Thread.sleep(2000);

    System.out.println(thread1.getState());
    System.out.println(thread2.getState());

    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    long[] deadLockedThreads = threadMXBean.findDeadlockedThreads();
    if(null != deadLockedThreads && deadLockedThreads.length > 0) {
      for(long id : deadLockedThreads) {
        ThreadInfo threadInfo = threadMXBean.getThreadInfo(id);
        System.out.println("死锁线程：" + id + "-" + threadInfo.getThreadName());
      }
    }

  }


  static class LockDemo implements Runnable{

    private boolean lockNum;

    public LockDemo(boolean lockNum) {
      this.lockNum = lockNum;
    }

    @Override
    public void run() {
      if(lockNum) {
        synchronized (lock1) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (lock2) {
            System.out.println("线程" + Thread.currentThread().getName() + "执行完成");
          }
        }
      } else {
        synchronized (lock2) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (lock1) {
            System.out.println("线程" + Thread.currentThread().getName() + "执行完成");
          }
        }
      }
    }
  }
}
