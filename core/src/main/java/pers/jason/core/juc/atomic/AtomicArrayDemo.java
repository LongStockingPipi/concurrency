package pers.jason.core.juc.atomic;

import pers.jason.core.deadly_embrace.Demo1;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Jason
 * @date 2020/2/18 13:41
 * @description
 */
public class AtomicArrayDemo {

  public static void main(String[] args) throws InterruptedException {
    //内部的10个AtomicInteger都是可以原子的操作的
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(100);

    Incrementer incrementer = new Incrementer(atomicIntegerArray);
    Decrementer decrementer = new Decrementer(atomicIntegerArray);
    Thread[] inArray = new Thread[100];
    Thread[] deArray = new Thread[95];
    for (int i = 0; i < 100; i++) {
      inArray[i] = new Thread(incrementer);
      inArray[i].start();
    }
    for (int i = 0; i < 95; i++) {
      deArray[i] = new Thread(decrementer);
      deArray[i].start();
    }

    for (int i = 0; i < 100; i++) {
      inArray[i].join();
    }
    for (int i = 0; i < 95; i++) {
      deArray[i].join();
    }

    for (int i = 0; i < 100; i++) {
      if (!new Integer(5).equals(atomicIntegerArray.get(i))) {
        throw new RuntimeException("错误");
      }
    }
  }
}

class Incrementer implements Runnable {
  private AtomicIntegerArray atomicIntegerArray;

  public Incrementer(AtomicIntegerArray atomicIntegerArray) {
    this.atomicIntegerArray = atomicIntegerArray;
  }

  @Override
  public void run() {
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      atomicIntegerArray.incrementAndGet(i);
    }
  }
}

class Decrementer implements Runnable {

  private AtomicIntegerArray atomicIntegerArray;

  public Decrementer(AtomicIntegerArray atomicIntegerArray) {
    this.atomicIntegerArray = atomicIntegerArray;
  }

  @Override
  public void run() {
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      atomicIntegerArray.decrementAndGet(i);
    }
  }
}
