package pers.jason.core.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author 姜治昊
 * @Description 定时器实现多线程
 * @Date 2019/11/10 13:45
 */
public class TimerTaskStyle {

  public static void main(String[] args) {

    System.out.println(Thread.currentThread().getName() + "正在执行");
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行");
      }
    }, 1000, 1000);
  }
}
