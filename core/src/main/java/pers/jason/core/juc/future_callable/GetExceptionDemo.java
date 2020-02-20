package pers.jason.core.juc.future_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Jason
 * @date 2020/2/20 19:06
 * @description
 */
public class GetExceptionDemo {

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);
    Future<Integer> future = service.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        Thread.sleep(1000);
        throw new Exception("失败");
      }
    });


    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("获取任务执行状态：" + future.isDone());
    try {
      System.out.println("任务执行结果" + future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("任务被中断");
    } catch (ExecutionException e) {
      e.printStackTrace();
      System.out.println("任务抛出异常");
    }
    service.shutdown();
  }
}
