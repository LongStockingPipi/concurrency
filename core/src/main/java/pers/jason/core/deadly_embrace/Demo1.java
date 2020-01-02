package pers.jason.core.deadly_embrace;

public class Demo1 implements Runnable{

  private boolean lockNum;

  private static Object lock1 = new Object();

  private static Object lock2 = new Object();

  public Demo1(boolean lockNum) {
    this.lockNum = lockNum;
  }

  public static void main(String[] args) throws InterruptedException {
    Demo1 demo1_1 = new Demo1(true);
    Demo1 demo1_2 = new Demo1(false);

    Thread thread1 = new Thread(demo1_1, "线程1");
    Thread thread2 = new Thread(demo1_2, "线程2");

    thread1.start();
    thread2.start();

    Thread.sleep(2000);

    System.out.println(thread1.getState());
    System.out.println(thread2.getState());
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
