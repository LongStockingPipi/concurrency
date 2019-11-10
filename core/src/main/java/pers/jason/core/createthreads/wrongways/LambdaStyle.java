package pers.jason.core.createthreads.wrongways;

/**
 * @Author 姜治昊
 * @Description Lambda表达式实现多线程
 * @Date 2019/11/10 13:57
 */
public class LambdaStyle {

  public static void main(String[] args) {
    new Thread(() -> System.out.println(Thread.currentThread().getName() + "正在执行")).start();
  }
}
