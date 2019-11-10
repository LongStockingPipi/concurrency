package pers.jason.core.createthreads.wrongways;

/**
 * @Author 姜治昊
 * @Description 匿名内部类创建多线程
 * @Date 2019/11/10 13:53
 */
public class AnonymousInnerClassStyle {

  public static void main(String[] args) {
    new Thread() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行");
      }
    }.start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName()+"正在执行");
      }
    }).start();
  }
}
