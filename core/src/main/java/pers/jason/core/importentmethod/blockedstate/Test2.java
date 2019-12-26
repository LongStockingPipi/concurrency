package pers.jason.core.importentmethod.blockedstate;

/**
 * 展示notify()和notifyAll()
 */
public class Test2 {

  public static void main(String[] args) throws InterruptedException {
    Object o = new Object();
    Thread thread1 = new Thread(new Thread3(o));
    Thread thread2 = new Thread(new Thread3(o));
    Thread thread3 = new Thread(new Thread4(o));

    thread1.start();
    thread2.start();

    Thread.sleep(5000);

    thread3.start();


    while(thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {}

    System.out.println("-----------------------");

    thread1 = new Thread(new Thread3(o));
    thread2 = new Thread(new Thread3(o));
    thread3 = new Thread(new Thread5(o));

    thread1.start();
    thread2.start();

    Thread.sleep(5000);

    thread3.start();

  }
}


class Thread3 implements Runnable {
  Object object;
  public Thread3(Object o) {
    this.object = o;
  }
  @Override
  public void run() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "获得对象锁");
      System.out.println(Thread.currentThread().getName() + "开始等待");
      try {
        object.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "执行完成");
    }
  }
}

class Thread4 implements Runnable {
  Object object;
  public Thread4(Object o) {
    this.object = o;
  }
  @Override
  public void run() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "获得对象锁");
      System.out.println(Thread.currentThread().getName() + "开始唤醒所有等待线程");
      object.notifyAll();
      System.out.println(Thread.currentThread().getName() + "执行完成");
    }
  }
}


class Thread5 implements Runnable {
  Object object;
  public Thread5(Object o) {
    this.object = o;
  }
  @Override
  public void run() {
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "获得对象锁");
      System.out.println(Thread.currentThread().getName() + "开始唤醒某个等待线程");
      object.notify();
      System.out.println(Thread.currentThread().getName() + "执行完成");
    }
  }
}
