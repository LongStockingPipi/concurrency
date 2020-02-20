package pers.jason.core.juc.future_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Jason
 * @date 2020/2/20 19:11
 * @description 演示get的超时设置，超时后需要手动处理
 */
public class TimeoutDemo {

  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    Future<Integer> future = threadPool.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        Thread.sleep(5000);
        return 1;
      }
    });

    try {
      future.get(1000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("线程被中断");
      future.cancel(false);
    } catch (ExecutionException e) {
      e.printStackTrace();
      System.out.println("线程执行异常");
      future.cancel(false);
    } catch (TimeoutException e) {
      e.printStackTrace();
      System.out.println("线程执行超时");
      future.cancel(true);
    } finally {
      threadPool.shutdown();
    }
  }
}
