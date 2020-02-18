package pers.jason.core.juc.atomic;

/**
 * @author Jason
 * @date 2020/2/18 16:43
 * @description CAS的等价代码
 */
public class SimulatedCAS {
  private int value;

  public synchronized int compareAndSwap(int expectedValue, int newValue) {
    int oldValue = value;
    if(oldValue == expectedValue) {
      value = newValue;
    }
    return oldValue;
  }
}
