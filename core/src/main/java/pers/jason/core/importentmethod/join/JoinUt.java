package pers.jason.core.importentmethod.join;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class JoinUt {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("主线程执行");
    Thread child = new Thread(new ChildThread());
    child.start();
    child.join(); //如果不用join，主线程子线程并行执行，主线程结束语句先打印
    System.out.println("主线程执行完毕");


  }

  static class ChildThread implements Runnable {
    @Override
    public void run() {
      try {
        Thread.sleep(5000);
        System.out.println("子线程执行完毕");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
