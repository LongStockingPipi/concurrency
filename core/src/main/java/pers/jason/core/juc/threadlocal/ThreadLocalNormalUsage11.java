package pers.jason.core.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用synchronized避免因为共享对象的原因导致线程不安全
 */
public class ThreadLocalNormalUsage11 {

  public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public String toDate(int second) {
    Date date = new Date(1000 * second);
    String result;
    synchronized (ThreadLocalNormalUsage11.class) {
      result = simpleDateFormat.format(date);
    }
    return result;
  }


}
