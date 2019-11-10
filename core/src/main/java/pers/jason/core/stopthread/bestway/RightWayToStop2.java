package pers.jason.core.stopthread.bestway;

/**
 * @Author 姜治昊
 * @Description 恢复中断
 * @Date 2019/11/10 16:51
 */
public class RightWayToStop2 implements Runnable {

  @Override
  public void run() {
    while(true) {
      if(Thread.currentThread().isInterrupted()) {
        System.out.println("记录中断日志");
        break;
      }
      maybeThrowException();
    }
  }

  public void maybeThrowException() {
    System.out.println("run maybeThrowException() ...");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      System.out.println("maybeThrowException() throw InterruptedException");
      /**
       * 若想自己处理中断，需要在最后恢复线程中断状态
       */
      Thread.currentThread().interrupt();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new RightWayToStop2();
    Thread thread = new Thread(runnable);

    thread.start();

    Thread.sleep(8000);
    thread.interrupt();
  }
}
