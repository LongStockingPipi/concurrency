package pers.jason.core.importentmethod.wait_notify;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait和notify实现生产者消费者模式
 */
public class ProducerConsumerModel {

  public static void main(String[] args) {
    EventStorage storage = new EventStorage();
    Producer producer = new Producer(storage);
    Consumer consumer = new Consumer(storage);
    new Thread(producer).start();
    new Thread(consumer).start();
  }

  /**
   * 仓库类
   */
  static class EventStorage {
    private int maxSize;

    private List<Integer> storage;

    public EventStorage() {
      this.maxSize = 5;
      this.storage = new ArrayList<>();
    }

    public synchronized void put(int i) {
      while(storage.size() == maxSize) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      storage.add(i);
      System.out.print("添加了" + i+ ",");
      printState();
      notify();
    }

    public synchronized void get() {
      while(storage.size() == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      int d = storage.get(0);
      storage.remove(0);
      System.out.print("拿到了" + d + ",");
      printState();
      notify();
    }

    private void printState() {
      System.out.println("仓库当前储量：" + storage.size());
    }
  }

  static class Producer implements Runnable {
    EventStorage storage;

    public Producer (EventStorage storage) {
      this.storage = storage;
    }
    @Override
    public void run() {
      for(int i=0;i<15;i++) {
        storage.put(i);
      }
    }
  }


  static class Consumer implements Runnable {
    EventStorage storage;

    public Consumer (EventStorage storage) {
      this.storage = storage;
    }
    @Override
    public void run() {
      for(int i=0;i<15;i++) {
        storage.get();
      }
    }
  }
}
