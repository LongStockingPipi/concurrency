package pers.jason.core.juc.threadpool;

import pers.jason.core.juc.threadpool.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutor只有一个线程
 */
public class SingleThreadExecutor {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    for(int i=0;i<100;i++) {
      executorService.execute(new Task());
    }
  }
}
