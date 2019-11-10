package pers.jason.core.stopthread.justforfun;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/10 18:58
 */
public class TestInterruptedState implements Runnable{

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    Runnable runnable = new TestInterruptedState();
    Thread thread = new Thread(runnable);
    thread.start();

    //中断thread线程
    thread.interrupt();

    //获取thread状态
    sb.append(getState(thread.isInterrupted()));

    //获取当前线程状态，同时重置当前线程状态
    sb.append(getState(thread.interrupted()));

    //获取当前线程状态，同时重置当前线程状态
    sb.append(getState(Thread.interrupted()));

    //获取thread状态
    sb.append(getState(thread.isInterrupted()));

    System.out.println(sb.toString());

  }

  private static String getState(boolean state) {
    return state ? "1" : "0";
  }

  @Override
  public void run() {

  }
}
