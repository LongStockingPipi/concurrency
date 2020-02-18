package pers.jason.core.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Jason
 * @date 2020/2/18 15:30
 * @description
 */
public class AtomicUpdaterDemo implements Runnable{

  public static Candidate tom;

  public static Candidate peter;

  public static AtomicIntegerFieldUpdater<Candidate> candidateAtomicIntegerFieldUpdater
      = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      tom.score++;
      candidateAtomicIntegerFieldUpdater.getAndIncrement(peter);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    tom = new Candidate();
    peter = new Candidate();
    AtomicUpdaterDemo atomicUpdaterDemo = new AtomicUpdaterDemo();
    Thread t1 = new Thread(atomicUpdaterDemo);
    Thread t2 = new Thread(atomicUpdaterDemo);
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    System.out.println("tom:" + tom.score);
    System.out.println("peter:" + peter.score);
  }

  public static class Candidate {
    volatile int score;
  }


}
