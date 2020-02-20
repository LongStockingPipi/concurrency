package pers.jason.core.juc.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jason
 * @date 2020/2/20 16:44
 * @description 演示Condition的基本用法
 */
public class ConditionDemo1 {

  private static ReentrantLock lock = new ReentrantLock();

  private static Condition condition = lock.newCondition();

  public static void main(String[] args) throws InterruptedException {
    ConditionDemo1 demo1 = new ConditionDemo1();
    new Thread(() -> {
      System.out.println("线程1等待条件达成");
      demo1.waitCondition();
      System.out.println("线程1开始运行");
    }).start();
    Thread.sleep(2000);
    System.out.println("条件达成");
    demo1.comeOn();
  }

  public void waitCondition() {
    lock.lock();
    try {
      try {
        condition.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } finally {
      lock.unlock();
    }
  }

  public void comeOn() {
    lock.lock();
    try {
      condition.signal();
    } finally {
      lock.unlock();
    }
  }


}
