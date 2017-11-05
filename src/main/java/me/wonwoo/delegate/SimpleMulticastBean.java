package me.wonwoo.delegate;

public class SimpleMulticastBean implements DelegationProvider {
  private String value;

  public String getValue() {
    return value;
  }

  @Override
  public void setValue(String value) {
    this.value = value;
  }
}