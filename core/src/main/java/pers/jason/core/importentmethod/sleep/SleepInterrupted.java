package pers.jason.core.importentmethod.sleep;

import java.util.concurrent.TimeUnit;

public class SleepInterrupted implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new SleepInterrupted());
    thread.start();
    TimeUnit.MILLISECONDS.sleep(6500);
    thread.interrupt();
  }

  @Override
  public void run() {
    for(int i=0;i<10;i++) {
      System.out.println(i);
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("收到中断信号");
      }
    }
  }
}
