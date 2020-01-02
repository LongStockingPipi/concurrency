package pers.jason.core.threadsafety;

/**
 * 线程安全问题 之 死锁
 */
public class Deadlock implements Runnable {

  int num;

  public Deadlock(int num) {
    this.num = num;
  }

  static Object lock1 = new Object();
  static Object lock2 = new Object();


  @Override
  public void run() {
    if(num == 1) {
      synchronized (lock1) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (lock2) {
          System.out.println("编号1执行完成");
        }
      }
    }

     else {
       synchronized (lock2) {
         try {
           Thread.sleep(1000);
         } catch (InterruptedException e) {
           e.printStackTrace();
         }
         synchronized (lock1) {
           System.out.println("编号2执行完成");
         }
       }
    }
  }

  public static void main(String[] args) {
    Deadlock deadlock1 = new Deadlock(1);
    Deadlock deadlock2 = new Deadlock(2);
    Thread thread1 = new Thread(deadlock1);
    Thread thread2 = new Thread(deadlock2);

    thread1.start();
    thread2.start();


  }
}
