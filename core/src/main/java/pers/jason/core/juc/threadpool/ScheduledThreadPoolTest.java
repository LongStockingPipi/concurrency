package pers.jason.core.juc.threadpool;

import pers.jason.core.juc.threadpool.task.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

  public static void main(String[] args) {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    //定时执行
    //参数2：延迟执行时间；参数3：时间单位
//    executorService.schedule(new Task(), 5, TimeUnit.SECONDS);

    //周期执行
    //参数2：首次延迟时间；参数3：间隔时间；参数4：时间单位
    executorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
  }
}
