package pers.jason.core.importentmethod.wait_notify;

/**
 * 线程1同步代码块中，同步对象调用wait后，该线程便释放锁，此时线程2获取对象锁，并调用对象的notify()方法，
 * 此时线程1被唤醒，但是依然无法获得对象锁，直到线程2同步代码执行完成释放对象锁；
 */
public class Test {

  public static void main(String[] args) throws InterruptedException {
    Object object = new Object();
    Thread thread1 = new Thread(new Thread1(object));
    Thread thread2 = new Thread(new Thread2(object));

    thread1.start();
    Thread.sleep(5000);
    thread2.start();
  }
}


class Thread1 implements Runnable {

  Object object;

  public Thread1(Object o) {
    this.object = o;
  }

  @Override
  public void run() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "获得对象锁。");
      System.out.println(Thread.currentThread().getName() + "开始等待...");
      try {
        object.wait();
      } catch (InterruptedException e) {
        System.out.println(Thread.currentThread().getName() + "被中断...");
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "执行完成");
    }
  }
}

class Thread2 implements Runnable {

  Object object;

  public Thread2(Object o) {
    this.object = o;
  }

  @Override
  public void run() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "获得对象锁。");
      System.out.println(Thread.currentThread().getName() + "开始唤醒任意阻塞线程");
      object.notify();
    }
  }
}
