package pers.jason.core.stopthread.bestway;

/**
 * @Author 姜治昊
 * @Description 传递中断
 * @Date 2019/11/10 16:22
 */
public class RightWayToStop1 implements Runnable {


  @Override
  public void run() {
    while(true) {
      try {
        maybeThrowException();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("保存中断日志...");
      }
    }
  }

  public void maybeThrowException() throws InterruptedException {
    System.out.println("run maybeThrowException() ...");
    Thread.sleep(2000);
  }

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new RightWayToStop1();
    Thread thread = new Thread(runnable);

    thread.start();

    Thread.sleep(2000);
    thread.interrupt();
  }
}
