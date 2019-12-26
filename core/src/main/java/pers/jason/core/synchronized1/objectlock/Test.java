package pers.jason.core.synchronized1.objectlock;

public class Test implements Runnable{

  static Test instance = new Test();
//  static Test instance1 = new Test();

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance);
    Thread thread2 = new Thread(instance);
//    Thread thread2 = new Thread(instance1);
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

  public synchronized void saying() throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + "进入同步方法");
    Thread.sleep(1000);
    System.out.println(Thread.currentThread().getName() + "完成同步方法");
  }
}
