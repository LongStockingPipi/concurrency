package pers.jason.core.juc.threadpool.task;

public class ShutDownTask extends Task {

  @Override
  public void run() {
    try {
      Thread.sleep(super.SLEEP_TIME);
      System.out.println(Thread.currentThread().getName() + "任务执行");
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + "被中断了");
    }
  }
}
