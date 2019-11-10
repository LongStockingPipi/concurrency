package pers.jason.core.stopthread.wrongway.usevolatile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author 姜治昊
 * @Description 阻塞方法无法响应volatile标记位，因此无法被停止
 * @Date 2019/11/10 17:51
 */
public class ErrorExample {

  public static void main(String[] args) throws InterruptedException {
    ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

    Producer producer = new Producer(storage);
    Thread producerThread = new Thread(producer);
    producerThread.start();

    Consumer consumer = new Consumer(storage, producer);
    Thread consumerThread = new Thread(consumer);
    consumerThread.start();

    Thread.sleep(11000);
    System.out.println("标记位：" + producer.stop);
  }
}


/**
 * 生产者
 */
class Producer implements Runnable {

  protected volatile boolean stop = false;

  //阻塞队列
  BlockingQueue storage;

  public Producer(BlockingQueue storage) {
    this.storage = storage;
  }

  @Override
  public void run() {
    int num = 0;

    try {

      while(num < 50000 && !stop) {

        if(num % 100 == 0) {
          storage.put(num);
          System.out.println("将" + num + "号产品放到仓库中...");
        }

        num ++;
      }
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Consumer implements Runnable{

  BlockingQueue storage;

  Producer producer;

  public Consumer(BlockingQueue storage, final Producer producer) {
    this.storage = storage;
    this.producer = producer;
  }

  @Override
  public void run() {
    int num = 0;
    try {
      while(num < 10) {
        Thread.sleep(1000);
        Object object = storage.take();
        System.out.println("卖出了" + object + "号产品");
        num++;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println("打烊了");
      this.producer.stop = true;
    }

  }
}
