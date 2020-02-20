package pers.jason.core.juc.future_callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author Jason
 * @date 2020/2/20 21:57
 * @description
 */
public class FutureTaskDemo {

  public static void main(String[] args) {
    Callable<Integer> callable = () -> {
      Thread.sleep(3000);
      return new Random().nextInt();
    };

    //直接启动线程
    FutureTask<Integer> futureTask = new FutureTask<>(callable);
    Thread thread = new Thread(futureTask);
    thread.start();

    try {
      int result = futureTask.get();
      System.out.println("执行结果：" + result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    //使用线程池启动线程
    ExecutorService threadPool = Executors.newFixedThreadPool(5);
    FutureTask<Integer> futureTask2 = new FutureTask<>(callable);
    threadPool.submit(futureTask2);
    try {
      System.out.println(futureTask2.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }
}
