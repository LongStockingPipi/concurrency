package pers.jason.core.exception;

/**
 * 全局线程异常处理器
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("线程" + t.getName() + "抛出异常：" + e.getMessage());
  }

}
