package pers.jason.core.juc.threadpool;

import pers.jason.core.juc.threadpool.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for(int i=0;i<100;i++) {
      executorService.execute(new Task(5000));
    }
  }
}
