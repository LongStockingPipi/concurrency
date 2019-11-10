package pers.jason.core.startthread;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/10 14:40
 */
public class RunAndStart {

  public static void main(String[] args) {
    System.out.println("主线程名：" + Thread.currentThread().getName());

    Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " running ...");

    //run run()
    runnable.run();

    //run start()
    new Thread(runnable).start();


    /*
    执行结果：

      主线程名：main

      main running ...

      Thread-0 running ...
     */
  }
}
