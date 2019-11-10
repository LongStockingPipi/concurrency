package pers.jason.core.stopthread.wrongway;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/10 17:14
 */
public class UseExpiredKeyword {


  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> {
      final String threadName = Thread.currentThread().getName();
      for(int i=0;i<10;i++) {
        System.out.println(threadName + ": 事务" + i + "开始运行...");
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(threadName + ": 事务" + i + "提交完成！");
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();

    Thread.sleep(2000);

    //stop()本质不安全，停止线程会立即解锁已锁定的所有监视器
    thread.stop();

    //suspend()和resume()可能造成死锁
  }

}



