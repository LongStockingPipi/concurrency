package pers.jason.core.synchronized1.classlock;

/**
 * 静态同步方法锁
 */
public class Test1 implements Runnable{

  static Test1 instance1 = new Test1();
  static Test1 instance2 = new Test1();

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

  public static synchronized void saying() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "进入静态同步方法");
    Thread.sleep(1000);
    System.out.println(Thread.currentThread().getName() + "完成静态同步方法");
  }
}
