package pers.jason.core.synchronized1.classlock;

/**
 * 静态同步代码块
 */
public class Test2 implements Runnable {

  static Test2 instance1 = new Test2();
  static Test2 instance2 = new Test2();

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance1);
    Thread thread2 = new Thread(instance2);

    thread1.start();
    thread2.start();

    while(thread1.isAlive() || thread2.isAlive()) {}

    System.out.println("finished!");
  }

  @Override
  public void run() {
    try {
      saying();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void saying() throws InterruptedException {
    synchronized(Test2.class) {
      System.out.println(Thread.currentThread().getName() + "进入静态同步代码块");
      Thread.sleep(1000);
      System.out.println(Thread.currentThread().getName() + "完成静态同步代码块");
    }

  }
}
