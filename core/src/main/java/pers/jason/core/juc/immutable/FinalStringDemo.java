package pers.jason.core.juc.immutable;

/**
 * @author Jason
 * @date 2020/2/19 14:12
 * @description
 */
public class FinalStringDemo {

  public static void main(String[] args) {
    String a = "AAA2";
    final String b = "AAA";
    String c = "AAA";
    final String d = getString();

    String e = b + 2;
    String f = c + 2;
    String g = d + 2;

    //由于b为常量，因此e直接从常量池中获取a
    System.out.println(a==e);

    //下面两种都是运行时生成的数据，指向堆中的对象
    System.out.println(a==f);
    System.out.println(a==g);
  }

  private static String getString() {
    return "AAA";
  }
}
