package pers.jason.core.createthreads;

/**
 * @Author 姜治昊
 * @Description 使用Runnable接口实现多线程
 * @Date 2019/11/10 12:55
 */
public class RunnableStyle implements Runnable {

  public void run() {
    System.out.println("使用Runnable接口实现多线程");
  }

  public static void main(String[] args) {
    new Thread(new RunnableStyle()).start();
  }
}
