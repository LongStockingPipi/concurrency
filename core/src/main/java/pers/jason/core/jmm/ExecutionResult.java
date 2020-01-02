package pers.jason.core.jmm;

import java.util.Objects;

public class ExecutionResult {

  private Integer a;

  private Integer b;

  public Integer getA() {
    return a;
  }

  public void setA(Integer a) {
    this.a = a;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExecutionResult that = (ExecutionResult) o;
    return Objects.equals(a, that.a) &&
        Objects.equals(b, that.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  public ExecutionResult(Integer a, Integer b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public String toString() {
    return "ExecutionResult{" +
        "a=" + a +
        "; b=" + b +
        '}';
  }
}
