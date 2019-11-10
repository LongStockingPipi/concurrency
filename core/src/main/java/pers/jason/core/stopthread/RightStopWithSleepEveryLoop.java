package pers.jason.core.stopthread;

/**
 * @Author 姜治昊
 * @Description 带有sleep线程的中断
 * @Date 2019/11/10 15:44
 */
public class RightStopWithSleepEveryLoop {


  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> {
      final Long now = System.currentTimeMillis();
      final String threadName = Thread.currentThread().getName();
      System.out.println(threadName + "开始执行...");

      /**
       * 此时迭代无需每次都检测是否收到中断信号，因为sleep()、wait()等方法会自动检测，若有中断，则抛异常
       */
      for(int i=0;i<10;i++) {
        try {
          System.out.println(threadName+" ... " + i);
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println("第" + i + "次的时候收到中断信号");
          break;
        }
      }
      System.out.println(threadName + "执行完成，耗时：" + ( System.currentTimeMillis() - now) + "ms");
    };

    Thread thread = new Thread(runnable);
    thread.start();

    Thread.sleep(2000);
    thread.interrupt();
  }
}
