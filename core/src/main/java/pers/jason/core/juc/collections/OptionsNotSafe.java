package pers.jason.core.juc.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jason
 * @date 2020/2/19 16:44
 * @description 在组合操作下，ConcurrentHashMap并不能保证线程安全
 *  解决：1. 使用synchronize修饰代码；2. 使用ConcurrentHashMap自带的原子方法
 */
public class OptionsNotSafe implements Runnable{

  private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {
    scores.put("小明", 0);
    OptionsNotSafe notSafe = new OptionsNotSafe();
    Thread thread1 = new Thread(notSafe);
    Thread thread2 = new Thread(notSafe);

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();

    System.out.println(scores);
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      scores.put("小明", scores.get("小明") + 1);
    }
  }
}
