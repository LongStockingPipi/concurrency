package pers.jason.core.createthreads;

/**
 * @Author 姜治昊
 * @Description 继承Thread类实现多线程
 * @Date 2019/11/10 12:56
 */
public class ThreadStyle extends Thread {

  @Override
  public void run() {
    System.out.println("继承Thread类实现多线程");
  }

  public static void main(String[] args) {
    new ThreadStyle().run();
  }
}
