package pers.jason.core.juc.future_callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Jason
 * @date 2020/2/20 19:01
 * @description 批量获取执行结果
 */
public class MultiFutures {

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(10);
    List<Future<Integer>> futures = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      Future<Integer> future = service.submit(new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
          Thread.sleep(2000);
          return new Random().nextInt();
        }
      });
      futures.add(future);
    }

    for (int i = 0; i < 20; i++) {
      try {
        System.out.println(futures.get(i).get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
}
