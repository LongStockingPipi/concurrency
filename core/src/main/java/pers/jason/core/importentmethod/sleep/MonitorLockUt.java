package pers.jason.core.importentmethod.sleep;

/**
 * 线程sleep的时候不会释放monitor锁
 */
public class MonitorLockUt implements Runnable{

  public static void main(String[] args) {
    MonitorLockUt monitorLockUt = new MonitorLockUt();
    Thread thread1 = new Thread(monitorLockUt);
    Thread thread2 = new Thread(monitorLockUt);

    thread1.start();
    thread2.start();
  }

  @Override
  public void run() {
    say();
  }

  public synchronized void say() {
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + "进入同步方法");
    System.out.println(threadName + "开始睡眠");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(threadName + "睡眠完成");
    System.out.println(threadName + "退出同步方法");
  }
}
