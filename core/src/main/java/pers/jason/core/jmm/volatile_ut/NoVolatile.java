package pers.jason.core.jmm.volatile_ut;

/**
 * 不适用于volatile场景
 */
public class NoVolatile implements Runnable {

  volatile int a = 0;

  public static void main(String[] args) throws InterruptedException {
    NoVolatile noVolatile = new NoVolatile();
    Thread thread1 = new Thread(noVolatile);
    Thread thread2 = new Thread(noVolatile);

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("a:" + noVolatile.a);
  }

  @Override
  public void run() {
    for(int i=0;i<100000;i++) {
//      synchronized (this) {
        a++;
//      }
    }
  }
}
