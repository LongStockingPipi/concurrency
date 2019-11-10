package pers.jason.core.stopthread.wrongway.usevolatile;

/**
 * @Author 姜治昊
 * @Description 使用布尔volatile标记为来控制线程停止，貌似可行
 * @Date 2019/11/10 17:26
 */
public class UseBooleanFlag implements Runnable{

  private volatile boolean stop = false;


  @Override
  public void run() {
    int num = 0;
    try {
      while(num <= 10000 && !stop) {
        if(num % 100 == 0) {
          System.out.println(num + "是100的倍数");
          Thread.sleep(10);
        }
        num ++;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("执行完成");
  }

  public static void main(String[] args) throws InterruptedException {
    UseBooleanFlag runnable = new UseBooleanFlag();
    Thread thread = new Thread(runnable);

    thread.start();
    Thread.sleep(1000);

    runnable.stop = true;
    System.out.println("停止！");
  }
}
