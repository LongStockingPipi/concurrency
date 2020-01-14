package pers.jason.core.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用ThreadLocal解决实例共享，每个线程都独享一个实例副本
 */
public class ThreadLocalNormalUsage12 {

  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal
      = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

  public String toDate(int second) {
    Date date = new Date(1000 * second);
    String result = dateFormatThreadLocal.get().format(date);
    return result;
  }


}
