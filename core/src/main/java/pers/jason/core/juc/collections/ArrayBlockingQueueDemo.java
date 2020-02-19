package pers.jason.core.juc.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Jason
 * @date 2020/2/19 17:45
 * @description 面试10个人，等待座位有三个
 */
public class ArrayBlockingQueueDemo {

  private static final String STOP = "stop";


  public static void main(String[] args) {
    BlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(2);
    Thread t1 = new Thread(new Receptionist(blockingDeque));
    Thread t2 = new Thread(new Interviewer(blockingDeque));

    t2.start();
    t1.start();
  }

  static class Receptionist implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Receptionist(BlockingQueue<String> blockingQueue) {
      this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i < 16; i++) {
          blockingQueue.put("person" + i);
          System.out.println("person" + i + "等待");
        }
        blockingQueue.put(STOP);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static class Interviewer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Interviewer(BlockingQueue<String> blockingQueue) {
      this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
      while(true) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          String person;
          if (!!(person = blockingQueue.take()).equals(STOP)) break;
          System.out.println(person + "面试完成");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("面试结束");
    }
  }



}
