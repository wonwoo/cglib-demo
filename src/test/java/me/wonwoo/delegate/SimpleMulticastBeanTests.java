package me.wonwoo.delegate;

import net.sf.cglib.reflect.MulticastDelegate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMulticastBeanTests {
  @Test
  void testMulticastDelegate() throws Exception {
    MulticastDelegate multicastDelegate = MulticastDelegate.create(
        DelegationProvider.class);
    SimpleMulticastBean first = new SimpleMulticastBean();
    SimpleMulticastBean second = new SimpleMulticastBean();
    multicastDelegate = multicastDelegate.add(first);
    multicastDelegate = multicastDelegate.add(second);

    DelegationProvider provider = (DelegationProvider) multicastDelegate;
    provider.setValue("Hello world!");

    assertEquals("Hello world!", first.getValue());
    assertEquals("Hello world!", second.getValue());
  }
}