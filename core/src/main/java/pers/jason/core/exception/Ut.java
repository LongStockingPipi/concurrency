package pers.jason.core.exception;

public class Ut {

  public static void main(String[] args) throws InterruptedException {

    Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

    Thread thread1 = new Thread(new MyThread());
    Thread thread2 = new Thread(new MyThread());
    Thread thread3 = new Thread(new MyThread());
    Thread thread4 = new Thread(new MyThread());

    thread1.start();
    Thread.sleep(50);

    thread2.start();
    Thread.sleep(50);

    thread3.start();
    Thread.sleep(50);

    thread4.start();
    Thread.sleep(50);

    throw new RuntimeException("main线程抛出异常");
  }
}
