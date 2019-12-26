package pers.jason.core.importentmethod.blockedstate;

/**
 * 测试题：两个线程交替打印0~100的奇偶数
 */
public class Quiz {

  static Integer num = 0;

  public static void main(String[] args) {

    Object lock = new Object();
    Odd odd = new Odd(lock);
    Even even = new Even(lock);
    new Thread(odd).start();
    new Thread(even).start();
  }


  static class Odd implements Runnable {

    Object object;

    public Odd( Object o) {
      this.object = o;
    }

    @Override
    public void run() {
      while(num < 100) {
        synchronized (object) {
          if((num & 1) == 1) {
            System.out.println("奇数线程-" + num);
            num ++;
          }
        }
      }
    }
  }

  static class Even implements Runnable {

    Object object;

    public Even(Object o) {
      this.object = o;
    }

    @Override
    public void run() {
      while(num < 100) {
        synchronized (object) {
          if((num & 1) == 0) {
            System.out.println("偶数线程-" + num);
            num++;
          }
        }
      }
    }
  }

}
