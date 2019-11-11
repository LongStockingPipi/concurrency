package pers.jason.core.lifecycle;

/**
 * @Author 姜治昊
 * @Description 展示 Blocked、waiting、Timed Waiting
 * @Date 2019/11/11 22:44
 */
public class Life2 implements Runnable{

  public static void main(String[] args) throws InterruptedException {
    Thread thread3 = new Thread(new Life2());
    thread3.start();
    System.out.println(thread3.getName() + ": " + thread3.getState());
    Thread.sleep(100);
    System.out.println(thread3.getName() + ": " + thread3.getState());




    Thread thread1 = new Thread(new Runnable1());
    thread1.start();

    Thread.sleep(100);

    Thread thread2 = new Thread(new Runnable1());
    thread2.start();

    Thread.sleep(100);

    //打印TIMED_WAITING，因为正在执行 Thread.sleep(5000)
    System.out.println("thread1: " + thread1.getState());
    //打印BLOCKED，因为正在等待 synchronized 锁被释放
    System.out.println("thread2: " + thread2.getState());

    Thread.sleep(7000);

    //打印TERMINATED，因为已经执行完成
    System.out.println("thread1: " + thread1.getState());
    //打印TIMED_WAITING，因为获取了 synchronized 锁，正在执行 Thread.sleep(5000)
    System.out.println("thread2: " + thread2.getState());

    Thread.sleep(5000);

    //打印TERMINATED，因为已经执行完成
    System.out.println("thread1: " + thread1.getState());
    System.out.println("thread2: " + thread2.getState());

    Thread.sleep(1000);


  }

  @Override
  public void run() {
    syncWait();
  }


  static class Runnable1 implements Runnable {
    @Override
    public void run() {
      System.out.println("进入" + Thread.currentThread().getName() + "线程");
      sync();
    }
  }

  private static synchronized void sync() {
    try {
      System.out.println(Thread.currentThread().getName() + "抢到锁");
      System.out.println("开始睡眠...");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private synchronized void syncWait() {
    try {
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
