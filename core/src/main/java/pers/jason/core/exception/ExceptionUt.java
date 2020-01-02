package pers.jason.core.exception;

/**
 * 传统的异常处理无法应对子线程的异常，即主线程无法catch住子线程的异常；
 */
public class ExceptionUt implements Runnable {


  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new ExceptionUt());
    thread.start();
    Thread.sleep(50);
    for(int i=0;i<20;i++) {
      System.out.print(i);
    }
  }

  @Override
  public void run() {
    throw new RuntimeException();
  }


}
