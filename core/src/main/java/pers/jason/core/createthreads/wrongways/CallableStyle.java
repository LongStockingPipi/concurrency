package pers.jason.core.createthreads.wrongways;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author 姜治昊
 * @Description 使用Callable接口实现多线程
 * @Date 2019/11/10 13:40
 */
public class CallableStyle implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(1000);
    final String threadName = Thread.currentThread().getName();
    System.out.println(threadName + "执行");
    return threadName + "执行";
  }

  public static void main(String[] args) {
    Callable<String> callableStyle = new CallableStyle();
    FutureTask futureTask = new FutureTask(callableStyle);
    new Thread(futureTask).start();
  }
}
