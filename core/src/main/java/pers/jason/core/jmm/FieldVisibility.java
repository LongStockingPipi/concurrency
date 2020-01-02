package pers.jason.core.jmm;

import java.util.HashSet;
import java.util.Set;

/**
 * JMM特点之可见性
 */
public class FieldVisibility {

  volatile int a = 1;
  volatile int b = 2;

  /**
   * 四种结果：
   * ExecutionResult{a=3, b=2} write_a -> read_a -> read_b -> write_b
   * ExecutionResult{a=3, b=3} write_a -> write_b -> read_a -> read_b
   * ExecutionResult{a=1, b=2} read_a -> read_b -> write_a -> write_b
   * ExecutionResult{a=1, b=3} read_a -> write_a -> write_b -> read_b
   *
   * 解决可见性问题可以使用volatile关键字，修饰之后运行结果：
   *
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {

    for(int i=0;i<50000;i++) {
      FieldVisibility fieldVisibility = new FieldVisibility();

      Thread thread1 = new Thread(() -> {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        fieldVisibility.change();
      });

      Thread thread2 = new Thread(() -> {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        fieldVisibility.print();
      });

      thread1.start();
      thread2.start();
    }
  }

  public void change() {
    a = 3;
    b = a;
  }

  public void print() {
    System.out.println("b=" + b + "; a=" + a);
  }
}
