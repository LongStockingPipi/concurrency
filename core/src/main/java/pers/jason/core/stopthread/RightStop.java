package pers.jason.core.stopthread;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/10 15:34
 */
public class RightStop implements Runnable {
  @Override
  public void run() {
    Long time = System.currentTimeMillis();
    final String threadName = Thread.currentThread().getName();
    System.out.println(threadName + "开始执行");
    int num = 0;
    while(num < Integer.MAX_VALUE / 2 && !Thread.currentThread().isInterrupted()) {
      if(num % 10000 == 0) {
        System.out.println(num + "是10000的倍数");
      }
      num ++;
    }
    System.out.println(threadName+"执行完成，耗时：" + (System.currentTimeMillis() - time) + "ms");
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new RightStop());

    thread.start();

    Thread.sleep(1000);

    thread.interrupt();
  }
}
