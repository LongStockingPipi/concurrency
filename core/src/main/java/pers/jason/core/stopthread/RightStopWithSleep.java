package pers.jason.core.stopthread;

/**
 * @Author 姜治昊
 * @Description 带有sleep线程的中断
 * @Date 2019/11/10 15:44
 */
public class RightStopWithSleep {


  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> {
      final Long now = System.currentTimeMillis();
      final String threadName = Thread.currentThread().getName();
      System.out.println(threadName + "开始执行...");
      try {
        Thread.sleep(8000);
      } catch (InterruptedException e) {
        System.out.println("线程" + threadName + "停止运行：" + e.getMessage());
        e.printStackTrace();
      }
      System.out.println(threadName + "执行完成，耗时：" + ( System.currentTimeMillis() - now) + "ms");
    };

    Thread thread = new Thread(runnable);
    thread.start();

    Thread.sleep(2000);
    thread.interrupt();
  }
}
