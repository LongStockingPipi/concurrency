package pers.jason.core.juc.threadpool.task;

public class Task implements Runnable {

  static Integer SLEEP_TIME;

  public Task() {
    this(500);
  }

  public Task(Integer time) {
    this.SLEEP_TIME = time;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(SLEEP_TIME);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "任务执行");
  }
}
