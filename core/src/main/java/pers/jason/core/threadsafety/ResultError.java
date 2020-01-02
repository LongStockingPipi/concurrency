package pers.jason.core.threadsafety;

/**
 * 线程安全问题 之 运算结果错误
 * 演示计数不准确，并找出错误位置
 */
public class ResultError implements Runnable{

  int index = 0;

  @Override
  public void run() {
    for(int i=0;i<10000;i++) {
      index ++;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ResultError myResultError = new ResultError();
    Thread thread1 = new Thread(myResultError);
    Thread thread2 = new Thread(myResultError);

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("最终结果：" + myResultError.index);


  }

}
