package me.wonwoo.delegate;

import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.reflect.ConstructorDelegate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleBeanConstructorDelegateTests {

  @Test
  void testConstructorDelegate() throws Exception {
    SampleBeanConstructorDelegate constructorDelegate = (SampleBeanConstructorDelegate) ConstructorDelegate.create(
        SampleBean.class, SampleBeanConstructorDelegate.class);
    SampleBean bean = (SampleBean) constructorDelegate.newInstance();
    assertTrue(SampleBean.class.isAssignableFrom(bean.getClass()));
  }

}