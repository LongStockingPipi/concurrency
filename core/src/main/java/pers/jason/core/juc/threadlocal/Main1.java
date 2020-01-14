package pers.jason.core.juc.threadlocal;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 运行结果存在重复数据，因为每个线程独享一个ThreadLocalNormalUsage1实例，但是公用一个SimpleDateFormat实例，而SimpleDateFormat是线程不安全的
 * 解决方案：
 *  1. 使用同步代码块
 *  2. 使用ThreadLocal
 */
public class Main1 {

  static ExecutorService executorService = Executors.newFixedThreadPool(10);

  public static void main(String[] args) {
    Set<String> sets = new HashSet<>();
    for(int i=0;i<1000;i++) {
      int finalI = i;
      executorService.execute(() -> {
        String date = new ThreadLocalNormalUsage12().toDate(finalI);
        System.out.println(date);
        sets.add(date);
      });
    }
    executorService.shutdown();
    while(!executorService.isTerminated()) {

    }
    System.out.println(sets.size());
  }
}
