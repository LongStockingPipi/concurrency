package pers.jason.core.juc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 每个任务执行的前后都可以添加钩子函数
 */
public class PauseableThreadPool extends ThreadPoolExecutor {

  public static void main(String[] args) throws InterruptedException {

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    PauseableThreadPool service =
        new PauseableThreadPool(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    for (int i = 0; i < 10000; i++) {
      service.execute(runnable);
    }

    Thread.sleep(1000);
    service.pause();
    System.out.println("线程池被暂停了");

    Thread.sleep(2000);
    System.out.println("线程池开启");
    service.resume();
  }


  private boolean isPaused;

  private final ReentrantLock lock = new ReentrantLock();

  private Condition unPaused = lock.newCondition();

  public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
  }

  public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
  }

  public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
  }

  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    lock.lock();
    try {
      while(isPaused) {
        unPaused.await();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void pause() {
    lock.lock();
    try {
      isPaused = true;
    } catch (Exception e) {

    } finally {
      lock.unlock();
    }
  }

  public void resume() {
    lock.lock();
    try{
      isPaused = false;
      unPaused.signalAll();
    } catch (Exception e) {

    } finally {
      lock.unlock();
    }
  }
}
