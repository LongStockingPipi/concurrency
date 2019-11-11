package pers.jason.core.lifecycle;

/**
 * @Author 姜治昊
 * @Description 展示NEW、RUNNABLE、Terminated三种状态
 * @Date 2019/11/11 22:24
 */
public class Life1 {

  public static void main(String[] args) throws InterruptedException {

    Thread thread = new Thread(new Thread1());
    System.out.println(thread.getState());

    thread.start();
    System.out.println(thread.getState());

    Thread.sleep(1);
    System.out.println(thread.getState());

    Thread.sleep(1000);
    System.out.println(thread.getState());
  }

  static class Thread1 implements Runnable {
    @Override
    public void run() {
      System.out.println("开始运行");
      int sum = 0;
      for(int i=0;i<100000;i++) {
        sum = sum + i;
      }
      System.out.println("结束运行");
    }
  }
}
