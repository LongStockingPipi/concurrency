package pers.jason.core.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 姜治昊
 * @Description 线程池实现多线程
 * @Date 2019/11/10 13:23
 */
public class ThreadPoolDemo {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for(int i=0;i<100;i++) {
      executorService.submit(new Task());
    }
  }

}

class Task implements Runnable {

  @Override
  public void run() {
    final String name = Thread.currentThread().getName();
    System.out.println(name + ": task running ...");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + ": task stop");
  }
}
