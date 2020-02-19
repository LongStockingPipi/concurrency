package pers.jason.core.juc.immutable;

/**
 * @author Jason
 * @date 2020/2/19 13:21
 * @description
 */
public class TestFinal1 {

  private final String name = "Jason";

  private final int age;

  private final String sex;

  {
    sex = "male";
  }

  public TestFinal1() {
    age = 18;
  }

  public TestFinal1(int age) {
    this.age = age;
  }
}
