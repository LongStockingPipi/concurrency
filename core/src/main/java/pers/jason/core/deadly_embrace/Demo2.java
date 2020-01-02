package pers.jason.core.deadly_embrace;

/**
 * 模拟转账
 */
public class Demo2 {

  public static void main(String[] args) throws InterruptedException {
    Account account1 = new Account(1000);
    Account account2 = new Account(600);


    TransferService service1 = new TransferService(account1, account2);
    TransferService service2 = new TransferService(account2, account1);

    Thread thread1 = new Thread(service1);
    Thread thread2 = new Thread(service2);

    thread1.start();
    thread2.start();

    Thread.sleep(2000);

    System.out.println(thread1.getState());
    System.out.println(thread2.getState());
  }

  static class TransferService implements Runnable{

    private Account accountFrom;

    private Account accountTarget;

    public TransferService(Account accountFrom, Account accountTarget) {
      this.accountFrom = accountFrom;
      this.accountTarget = accountTarget;
    }

    @Override
    public void run() {
      try {
        transferMoney(accountFrom, accountTarget, 200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    private void transferMoney(Account accountFrom, Account accountTarget, float money) throws InterruptedException {
      synchronized (accountFrom) {
        Thread.sleep(100);
        synchronized (accountTarget) {
          if(accountFrom.balance < money) {
            System.out.println("转账失败，余额不足");
          } else {
            accountFrom.balance = accountFrom.balance - money;
            accountTarget.balance = accountTarget.balance + money;
            System.out.println("转账成功");
          }
        }
      }
    }
  }

  static class Account {
    private float balance;

    public Account(float balance) {
      this.balance = balance;
    }
  }
}
