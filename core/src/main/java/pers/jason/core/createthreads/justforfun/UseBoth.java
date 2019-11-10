package pers.jason.core.createthreads.justforfun;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/10 13:15
 */
public class UseBoth {

  public static void main(String[] args) {
    new Thread(new Runnable() {
      public void run() {
        System.out.println("from runnable");
      }
    }) {
      @Override
      public void run() {
        System.out.println("from thread");
      }
    }.start();
  }
}
