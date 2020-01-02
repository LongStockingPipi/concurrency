package pers.jason.core.exception;

public class MyThread implements Runnable {
  @Override
  public void run() {
    throw new RuntimeException();
  }
}
