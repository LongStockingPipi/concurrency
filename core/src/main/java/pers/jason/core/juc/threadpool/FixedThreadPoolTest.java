package pers.jason.core.juc.threadpool;

import pers.jason.core.juc.threadpool.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool是大小固定的线程池，它在构建的时候，corePoolSize和maxPoolSize大小相等，且使用的是无界队列LinkedBlockingQueue
 * 注意：无界队列容易导致OOM
 * 由于演示OOM，所以将运行内存调小：VM options：-Xmx8m -Xms8m
 */
public class FixedThreadPoolTest {

  static final Integer CORE_SIZE = 2;

  private static final Integer TASK_SIZE = Integer.MAX_VALUE;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(CORE_SIZE);
    for(int i=0;i<TASK_SIZE;i++) {
      executorService.execute(new Task(9999999));
    }
  }
}


