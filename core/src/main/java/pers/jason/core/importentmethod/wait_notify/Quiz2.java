package pers.jason.core.importentmethod.wait_notify;

/**
 * 使用 wait notify实现线程交替工作，相比于只使用synchronized，减少多次无用的锁争夺
 */
public class Quiz2 {



  static int num = 0;

  public static void main(String[] args) throws InterruptedException {
    Object lock = new Object();
    Odd odd = new Odd(lock);
    Event event = new Event(lock);

    new Thread(event).start();
    Thread.sleep(1000);
    new Thread(odd).start();
  }

  static class Odd implements Runnable{

    Object object;

    public Odd(Object o) {
      this.object = o;
    }

    @Override
    public void run() {
      while(num < 100) {
        synchronized (object) {
          System.out.println("奇数：" + num);
          num ++;
          object.notify();
          try {
            if(num < 100) {
              //如果任务没结束，则让出对象锁，本身线程睡眠
              object.wait();
            }
          } catch (InterruptedException e) {
          }
        }

      }
    }
  }


  static class Event implements Runnable{

    Object object;

    public Event(Object o) {
      this.object = o;
    }

    @Override
    public void run() {
      while(num < 100) {
        synchronized (object) {
          System.out.println("偶数：" + num);
          num ++;
          object.notify();
          try {
            if(num < 100) {
              //如果任务没结束，则让出对象锁，本身线程睡眠
              object.wait();
            }
          } catch (InterruptedException e) {
          }
        }

      }
    }
  }
}
