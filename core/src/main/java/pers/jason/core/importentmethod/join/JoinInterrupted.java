package pers.jason.core.importentmethod.join;

/**
 * 主线程的中断并不会影响子线程的运行
 * 通常主线程收到中断信号之后，需要中断子线程，本例中，Thread_A在catch住InterruptedException后需要再执行childThread.interrupt();
 */
public class JoinInterrupted {

  public static void main(String[] args) throws InterruptedException {
    Thread threadB = new Thread(new Thread_B());
    Thread threadA = new Thread(new Thread_A(threadB));
    threadA.start();

    Thread.sleep(2500);
    System.out.println("Thread-A状态（等待子线程的执行完成）：" + threadA.getState().name());
    System.out.println("Thread-B状态（正在运行sleep()）：" + threadB.getState().name());
    threadA.interrupt();


  }


  static class Thread_A implements Runnable {

    Thread childThread;

    public Thread_A(Thread childThread) {
      this.childThread = childThread;
    }

    @Override
    public void run() {
      System.out.println("Thread-A开始运行");
      try {
        childThread.start();
        System.out.println("Thread-A等待子线程运行");
        childThread.join();
      } catch (InterruptedException e) {
        System.out.println("Thread-A收到中断信号");
      }
      System.out.println("Thread-A结束运行");
    }
  }

  static class Thread_B implements Runnable {

    @Override
    public void run() {
      System.out.println("Thread-B开始运行");
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread-B结束运行");
    }
  }
}
