package pers.jason.core.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用ThreadLocal可以实现线程内存储数据的功能，不必使用参数传递
 */
public class ThreadLocalNormalUsage21 {

  public static void main(String[] args) {
    Service1 service1 = new Service1();
    Service2 service2 = new Service2();
    Service3 service3 = new Service3();
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    for(int i=0;i<1;i++) {
      int finalI = i;
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          service1.findUser("t_" + finalI);
          service2.pay();
          service3.log();
        }
      });
    }
  }

}

class UserContextHolder {

  static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

}

class Service1 {

  public void findUser(String name) {
    User user = new User(name);
    UserContextHolder.userThreadLocal.set(user);
  }

}

class Service2 {

  public void pay() {
    User user = UserContextHolder.userThreadLocal.get();
    System.out.println(user.toString());
    UserContextHolder.userThreadLocal.remove();
  }

}

class Service3 {

  public void log() {
    User user = UserContextHolder.userThreadLocal.get();
    System.out.println(user);
  }

}
