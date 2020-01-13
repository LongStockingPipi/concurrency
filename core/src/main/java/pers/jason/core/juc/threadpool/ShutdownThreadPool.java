package pers.jason.core.juc.threadpool;

import pers.jason.core.juc.threadpool.task.ShutDownTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 演示关闭线程池
 */
public class ShutdownThreadPool {

  public static void main(String[] args) throws InterruptedException {
//    test1();
    test2();
  }

  public static void test2() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for(int i=0;i<100;i++) {
      executorService.execute(new ShutDownTask());
    }
    Thread.sleep(800);
    List<Runnable> taskList = executorService.shutdownNow();
    System.out.println("取消的任务有：" + taskList.size() + "个");
  }

  public static void test1() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for(int i=0;i<100;i++) {
      executorService.execute(new ShutDownTask());
    }
    System.out.println("isShutdown:" + executorService.isShutdown());
    Thread.sleep(150);
    executorService.shutdown();
    System.out.println("isShutdown:" + executorService.isShutdown());
    try{
      executorService.execute(new ShutDownTask());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("awaitTermination:" + executorService.awaitTermination(2, TimeUnit.SECONDS));
      Thread.sleep(2000);
      System.out.println("isTerminated:" + executorService.isTerminated());
      System.out.println("awaitTermination:" + executorService.awaitTermination(5, TimeUnit.SECONDS));
      Thread.sleep(5000);
      System.out.println("isTerminated:" + executorService.isTerminated());
    }
  }
}
