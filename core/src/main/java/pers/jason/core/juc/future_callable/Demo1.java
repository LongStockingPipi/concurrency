package pers.jason.core.juc.future_callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Jason
 * @date 2020/2/20 18:51
 * @description
 */
public class Demo1 {

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);
    Future<Integer> future = service.submit(new MyTask());
    try {
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    service.shutdown();
  }

  static class MyTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
      Thread.sleep(2000);
      return new Random().nextInt();
    }
  }
}
