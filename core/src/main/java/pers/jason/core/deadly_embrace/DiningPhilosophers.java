package pers.jason.core.deadly_embrace;

/**
 * 哲学家就餐问题
 */
public class DiningPhilosophers {

  public static void main(String[] args) {
    Philosopher[] philosophers = new Philosopher[5];
    Object[] chopsticks = new Object[philosophers.length];

    for (int i = 0; i < chopsticks.length; i++) {
      chopsticks[i] = new Object();
    }

    for (int i = 0; i < philosophers.length ; i++) {
      Object leftChopstick = chopsticks[i];
      Object rightChopstick = chopsticks[(i + 1)%chopsticks.length];
      philosophers[i] = new Philosopher(leftChopstick, rightChopstick);

      new Thread(philosophers[i], "哲学家" + (i+1) + "号").start();
    }
  }

  static class Philosopher implements Runnable {

    private Object leftChopstick;
    private Object rightChopstick;

    public Philosopher(Object leftChopstick, Object rightChopstick) {
      this.leftChopstick = leftChopstick;
      this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
      while(true) {
        doSomeThing("thinking...");
        synchronized (leftChopstick) {
          doSomeThing("拿取左边筷子");
          synchronized (rightChopstick) {
            doSomeThing("拿取右边筷子");
            //...
            doSomeThing("放下右边筷子");
          }
          doSomeThing("放下左边筷子");
        }
      }
    }

    private void doSomeThing(String action) {
      System.out.println(Thread.currentThread().getName() + ":" + action);
      try {
        Thread.sleep((long) Math.random()*10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
